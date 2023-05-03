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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@Slf4j
public class BoardRestController {
    private final BoardService service;

    @Autowired
    public BoardRestController(BoardService service){
        this.service = service;
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

    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> regist(@RequestBody Board board, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        board.setUserNo(loginUser.getUserNo());
        int result = service.insert(board);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);

        if(result == 1){
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }



}
