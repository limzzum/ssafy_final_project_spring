package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.Worldcup;
import com.ssafy.enjoytrip.model.service.WorldcupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "WORLDCUP REST API")
@RequestMapping("/api/worldcup")
@CrossOrigin(origins = "*")
public class WorldcupRestController {
    private WorldcupService service;

    @Autowired
    public WorldcupRestController(WorldcupService service) {
        this.service = service;
    }

    @ApiOperation(value = "월드컵 리스트 요청", notes = "월드컵 후보 리스트를 반환한다.",response = ResponseEntity.class)
    @GetMapping
    public ResponseEntity<Map<String, Object>> selectAll(){
        Map<String,Object> map = new HashMap<>();
        List<Worldcup> list = service.getWorldcupList();
        Collections.shuffle(list);
        map.put("result", list);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "우승지역 우승횟수 업데이트 요청", notes = "업데이트 후 리스트 결과 반환",response = ResponseEntity.class)
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable int id){
        Map<String,Object> map = new HashMap<>();
        service.updateWorldcupWins(id);
        List<Worldcup> list = service.getWorldcupList();
        Collections.sort(list,(o1,o2)->Integer.compare(o1.getWins(),o2.getWins())*-1);
        map.put("result", list);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
