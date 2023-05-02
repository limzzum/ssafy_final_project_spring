package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.SearchPlace;
import com.ssafy.enjoytrip.model.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/place")
public class PlaceRestController {
    @Autowired
    PlaceService service;

    @PostMapping("/list")
    public ResponseEntity<Map<String, Object>> list(@RequestBody SearchPlace searchPlace){
        System.out.println(searchPlace);
        List<Place> places = service.searchPlace(searchPlace);
        Map<String, Object> map = new HashMap<>();
        map.put("result", places);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Map<String, Object>> detail(@PathVariable int id){
        Place place = service.getPlace(id);
        Map<String, Object> map = new HashMap<>();
        map.put("result", place);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

}
