package com.ssafy.enjoytrip.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.ContentType;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.Sido;
import com.ssafy.enjoytrip.model.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/place")
@Slf4j
public class PlaceRestController {
    @Autowired
    PlaceService service;

    @PostMapping("/search/{page}")
    public ResponseEntity<Map<String, Object>> list(@RequestBody Place place, @PathVariable int page){
        PageHelper.startPage(page,10);
        log.info("list condition : "+place);
        Page<Place> list = service.search(place);
        log.info("list result : "+list);
        Map<String, Object> map = new HashMap<>();
        map.put("result", list);
        map.put("pages",list.getPages());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Map<String, Object>> detail(@PathVariable int id){
        Place place = service.select(id);
        Map<String, Object> map = new HashMap<>();
        map.put("result", place);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/content")
    public ResponseEntity<Map<String, Object>> content(){
        List<ContentType> contents = service.getContents();
        log.info("content : "+contents);
        Map<String, Object> map = new HashMap<>();
        map.put("result", contents);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/region")
    public ResponseEntity<Map<String, Object>> region(){
        List<Sido> regions = service.getRegions();
        log.info("region : "+regions);
        Map<String, Object> map = new HashMap<>();
        map.put("result", regions);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

}
