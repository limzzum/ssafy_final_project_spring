package com.ssafy.enjoytrip.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardRestController {
    private final BoardService service;

    @Autowired
    public BoardRestController(BoardService service){
        this.service = service;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> list(@PathVariable int postId){
        Map<String,Object> map = new HashMap<>();
        Board select = service.select(postId);
        map.put("result", select);

        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> modify(@RequestBody Board board){
        log.info("modify");
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
    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int postId){
        log.info("delete");
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
    @GetMapping("/{type}/{page}")
    public ResponseEntity<Map<String, Object>> list(@PathVariable String type,@PathVariable int page){
        PageHelper.startPage(page,10);
        Map<String,Object> keys = new HashMap<>(), map = new HashMap<>();
        keys.put("boardType",type);
        log.info("search : "+keys);
        Page<Board> list = service.search(keys);
        map.put("result", list);
        map.put("pages",list.getPages());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> regist(@RequestBody Board board){
        log.info(String.valueOf(board.getUserNo()));
        log.info(board.getBoardType());
        int result = service.insert(board);
        Map<String, Object> map = new HashMap<>();
        String  msg = "success";

        if(result == 1){
            map.put("msg", msg);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        msg="fail";
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }



}
