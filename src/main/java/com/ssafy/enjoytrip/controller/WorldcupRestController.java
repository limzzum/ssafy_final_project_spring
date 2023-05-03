package com.ssafy.enjoytrip.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.Worldcup;
import com.ssafy.enjoytrip.model.service.WorldcupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worldcup")
public class WorldcupRestController {
    private WorldcupService service;

    @Autowired
    public WorldcupRestController(WorldcupService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> selectAll(){
        Map<String,Object> map = new HashMap<>();
        List<Worldcup> list = service.getWorldcupList();
        map.put("result", list);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();
        service.updateWorldcupWins(id);
        List<Worldcup> list = service.getWorldcupList();
        map.put("result", list);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
