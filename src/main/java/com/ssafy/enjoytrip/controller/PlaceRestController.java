package com.ssafy.enjoytrip.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.enjoytrip.model.dto.ContentType;
import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.Sido;
import com.ssafy.enjoytrip.model.service.PlaceService;
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
@RequestMapping("/api/place")
@Slf4j
@Api(tags = "PLACE REST API")
@CrossOrigin(origins = "*")
public class PlaceRestController {
    @Autowired
    PlaceService service;

    @ApiOperation(value = "관광지 리스트 요청", notes = "option값을 json으로 전달: sidoCode, contentTypeId, title 전달 가능 ",response = ResponseEntity.class)
    @PostMapping("/search/{page}")
    public ResponseEntity<Map<String, Object>> list(@RequestBody  @ApiParam(value = "관광지 검색 옵션(sidoCode, contentTypeId, title)", required = true) Place place,
                                                    @PathVariable int page){
        PageHelper.startPage(page,10);
        log.info("list condition : "+place);
        Page<Place> list = service.search(place);
        log.info("list result : "+list);
        Map<String, Object> map = new HashMap<>();
        map.put("result", list);
        map.put("pages",list.getPages());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "관광지 상세 정보 요청", notes = "id에 해당하는 관광지 정보 반환",response = ResponseEntity.class)
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> detail(@PathVariable int id){
        Place place = service.select(id);
        Map<String, Object> map = new HashMap<>();
        log.info("place detail : "+place);
        map.put("result", place);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "관광지 타입 요청", notes = "관광지 타입 리스트 반환",response = ResponseEntity.class)
    @GetMapping("/content")
    public ResponseEntity<Map<String, Object>> content(){
        List<ContentType> contents = service.getContents();
        log.info("content : "+contents);
        Map<String, Object> map = new HashMap<>();
        map.put("result", contents);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "지역 리스트 요청", notes = "지역 리스트 반환",response = ResponseEntity.class)
    @GetMapping("/region")
    public ResponseEntity<Map<String, Object>> region(){
        List<Sido> regions = service.getRegions();
        log.info("region : "+regions);
        Map<String, Object> map = new HashMap<>();
        map.put("result", regions);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

}
