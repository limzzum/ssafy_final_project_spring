package com.ssafy.enjoytrip.controller;

import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.BoardService;
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
public class BoardRestController {
    private final BoardService service;

    @Autowired
    public BoardRestController(BoardService service){
        this.service = service;
    }

    @GetMapping("/{type}/{page}")
    public ResponseEntity<Map<String, Object>> list(@PathVariable String type,@PathVariable int page){
        PageHelper.startPage(page,10);
        List<Board> list = service.getAll(type);
        Map<String, Object> map = new HashMap<>();
        map.put("result", list);

        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{type}/my")
    public ResponseEntity<Map<String, Object>> list(@PathVariable String type, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        List<Board> list = service.getAll(type, loginUser.getUserNo());
        Map<String, Object> map = new HashMap<>();
        map.put("result", list);

        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody Board board, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        board.setUserNo(loginUser.getUserNo());
        int result = service.add(board);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);

        if(result == 1){
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }



}
