package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    private BoardService service;
    public BoardController(BoardService service){
        this.service = service;
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
