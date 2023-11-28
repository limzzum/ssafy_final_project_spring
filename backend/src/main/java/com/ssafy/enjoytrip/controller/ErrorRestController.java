package com.ssafy.enjoytrip.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/error")
@ApiIgnore
@CrossOrigin(origins = "*")
public class ErrorRestController {

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> login(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result","로그인이 필요합니다");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/access-token")
    public ResponseEntity<Map<String, Object>> accessToken(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result","access 토큰이 만료되었습니다");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
