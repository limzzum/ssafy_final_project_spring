package com.ssafy.enjoytrip.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.Comment;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dto.form.PostBoard;
import com.ssafy.enjoytrip.model.service.BoardMapPlaceService;
import com.ssafy.enjoytrip.model.service.BoardService;
import com.ssafy.enjoytrip.model.service.CommentService;
import com.ssafy.enjoytrip.model.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@Slf4j
@Api(tags = "BOARD REST API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardRestController {
    private final BoardService service;
    private final BoardMapPlaceService boardMapPlaceService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public BoardRestController(BoardService service, BoardMapPlaceService boardMapPlaceService, CommentService commentService, UserService userService){
        this.service = service;
        this.boardMapPlaceService = boardMapPlaceService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/test")
    public ResponseEntity<Map<String, Object>> test(@RequestBody Map<String,Object> body){
        List list= (List) body.get("list");

        log.info(list.toString());
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }
    @ApiOperation(value = "board 상세 정보 요청", notes = "postId에 해당하는 board info 반환",response = ResponseEntity.class)
    @GetMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> detail(@PathVariable @ApiParam(value = "board primarykey", required = true) int postId){
        Map<String,Object> map = new HashMap<>();
        String msg = "success";
        try {
            Board select = service.select(postId);
            List<Place> search = boardMapPlaceService.search(postId);

            map.put("result", select);
            map.put("places", search);

            Map<String,Object> option = new HashMap<>();
            option.put("postId",postId);
            option.put("parentId",-1);
            option.put("postId",postId);
            List<Comment> comments = commentService.search(option);

            for (Comment comment:comments) {
                option.put("parentId", comment.getCommentId());
                List<Comment> sub = commentService.search(option);
                comment.setSubs(sub);
                comment.setUserName(userService.selectById(comment.getUserNo()).getUserName());
            }
            map.put("comments", comments);
            service.updateHits(postId);
        }catch (Exception e){
            msg = "fail";
        }
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "board 수정 요청", notes = "title or content 수정 요청",response = ResponseEntity.class)
    @PutMapping
    public ResponseEntity<Map<String, Object>> modify(@RequestBody @ApiParam(value = "수정할 board", required = true) Board board){
        Map<String,Object> map = new HashMap<>();
        int update = service.update(board);
        String msg = "success";
        if(update==1){
            map.put("msg",msg);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        msg = "fail";
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "board 삭제 요청", notes = "postId에 해당하는 board 삭제",response = ResponseEntity.class)
    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable @ApiParam(value = "삭제할 board id", required = true) int postId){
        Map<String,Object> map = new HashMap<>();
        int delete = service.delete(postId);
        String msg = "success";
        if(delete==1){
            map.put("msg",msg);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        msg = "fail";
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "board 리스트 요청", notes = "option값을 json으로 전달: title, content, boardType, userNo, userName 전달 가능 ",response = ResponseEntity.class)
    @PostMapping("/search/{page}")
    public ResponseEntity<Map<String, Object>> list(@RequestBody @ApiParam(value = "boardList option값", required = true) Map<String,Object> body,
                                                    @ApiParam(value = "page") @PathVariable int page){
        PageHelper.startPage(page,10);
        Map<String,Object> map = new HashMap<>();

        try {
            Page<Board> list = service.search(body);
            map.put("result", list);
            map.put("pages",list.getPages());
            map.put("totalNum",list.getTotal());
            map.put("msg", "success");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg", "fail");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }

    }

    @ApiOperation(value = "board 등록", notes = "등록할 board를 requestBody로 요청", response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<Map<String, Object>> regist(@RequestBody @ApiParam(value = "등록할 board", required = true) PostBoard board){
        String  msg = "success";
        try{
            service.insert(board);
            Map<String , Object> map = new HashMap<>();
            if(board.getPlaces()!=null && board.getPlaces().size()!=0){
                map.put("postId", board.getPostId());
                map.put("places", board.getPlaces());
                boardMapPlaceService.insert(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            msg = "fail";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }



}
