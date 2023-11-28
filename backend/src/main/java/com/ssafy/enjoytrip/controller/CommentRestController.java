package com.ssafy.enjoytrip.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.Comment;
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

import java.util.*;

@RestController
@RequestMapping("/api/comment")
@Slf4j
@Api(tags = "COMMENT REST API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentRestController {
    private final CommentService service;
    private final UserService userService;

    @Autowired
    public CommentRestController(CommentService service,UserService userService){
        this.service = service;
        this.userService = userService;
    }

    @ApiOperation(value = "댓글 등록", notes = "required : content, postId, userNo", response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<Map<String, Object>> regist(@RequestBody @ApiParam(value = "등록할 comment", required = true) Comment comment){
        String  msg = "success";
        try{
            service.insert(comment);
        }catch (Exception e){
            e.printStackTrace();
            msg = "fail";
        }
        Map<String, Object> map = new HashMap<>();
        comment = service.select(comment.getCommentId());
        log.info(comment.toString());
        comment.setUserName(userService.selectById(comment.getUserNo()).getUserName());
        log.info(comment.toString());
        map.put("msg", msg);
        map.put("result",comment);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "댓글 수정 요청", notes = "",response = ResponseEntity.class)
    @PutMapping
    public ResponseEntity<Map<String, Object>> modify(@RequestBody @ApiParam(value = "수정할 댓글", required = true) Comment comment){
        String  msg = "success";
        try{
            service.update(comment);
        }catch (Exception e){
            e.printStackTrace();
            msg = "fail";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    @ApiOperation(value = "댓글 삭제 요청", notes = "",response = ResponseEntity.class)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable @ApiParam(value = "삭제할 board id", required = true) int commentId){
        Map<String,Object> map = new HashMap<>();
        Comment select = service.select(commentId);
        String msg = "fail";
        if(select.getSubs().size() != 0){
            map.put("result","대댓글이 있어 삭제할 수 없습니다.");
        }else {
        try {
            service.delete(commentId);
            msg = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        }
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }

//    @ApiOperation(value = "board 에 달린 댓글 요청", notes = "postId에 해당하는 댓글 반환",response = ResponseEntity.class)
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> lista(@RequestBody @ApiParam(value = "comment option(postId, userNo, content 입력 가능)", required = true) Map<String, Object> body
//                                                    ){
//        Map<String,Object> map = new HashMap<>();
//        body.put("parentId",0);
//        List<Comment> search = service.search(body);
//
//        Comment[][] list = new Comment[search.size()][];
//        System.out.println(list);
//        List<Comment> comments = search;
//        System.out.println(comments);
//
//        for (int i = 0; i < search.size(); i++) {
//            body.put("parentId", search.get(i).getCommentId());
//            List<Comment> sub = service.search(body);
//            comments.get(i).setSubs(sub);
//        }
//        map.put("result", comments);
//
//        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
//    }
//

//
//
//
//    @ApiOperation(value = "board 리스트 요청", notes = "option값을 json으로 전달: title, content, boardType, userNo, userName 전달 가능 ",response = ResponseEntity.class)
//    @PostMapping("/search/{page}")
//    public ResponseEntity<Map<String, Object>> list(@RequestBody @ApiParam(value = "boardList option값", required = true) Map<String,Object> body,
//                                                    @ApiParam(value = "page") @PathVariable int page){
//        PageHelper.startPage(page,10);
//        Map<String,Object> map = new HashMap<>();
//
//        try {
//            Page<Board> list = service.search(body);
//            map.put("result", list);
//            map.put("pages",list.getPages());
//            map.put("totalNum",list.getTotal());
//            map.put("msg", "success");
//            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
//        }catch (Exception e){
//            e.printStackTrace();
//            map.put("msg", "fail");
//            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
//        }
//
//    }
//
//



}
