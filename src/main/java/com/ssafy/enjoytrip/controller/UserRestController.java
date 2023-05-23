package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dto.valid.LoginForm;
import com.ssafy.enjoytrip.model.service.UserService;
import com.ssafy.enjoytrip.security.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Controller
@RequestMapping("/api/user")
@Slf4j
@Api(tags = "USER REST API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {

    private final UserService service;
    private final SecurityService securityService;

    @Autowired
    public UserRestController(UserService service, SecurityService securityService) {
        this.service = service;
        this.securityService = securityService;
    }

    @ApiOperation(value = "로그인 요청", notes = "로그인에 성공하면 jwt토큰을 발급, 실패하면 실패메시지 반환",response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value = "유저 로그인 정보", required = true) LoginForm loginForm){
        User user = service.login(loginForm);
        log.info("login : "+user);
        Map<String, Object> map = new HashMap<>();
        if(user != null){
            map.put("user", user);
            String accessToken = securityService.createJwtToken(String.valueOf(user.getUserNo()));
            String refreshToken = securityService.createRefreshToken(user.getUserNo());
            map.put("access-token", accessToken);
            map.put("refresh-token", refreshToken);
            map.put("msg","success");
        }else{
            map.put("msg","fail");
        }
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "access토큰 재발급 요청", notes = "유효한 refreshToken이라면 accessToken을 재발급한다.",response = Map.class)
    @GetMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestHeader(name = "refresh-token") @ApiParam(value = "refresh-token", required = true) String refreshToken){
        log.info("refresh 요청 들어옴");
        Map<String, Object> map = new HashMap<>();
       try{
           String accessToken = securityService.reCreateJwtToken(refreshToken);
           map.put("access-token", accessToken);
           map.put("msg","success");
       }catch (Exception e){
           e.printStackTrace();
           map.put("msg","fail");
       }
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }

    @ApiOperation(value = "로그아웃",response = Map.class)
    @GetMapping("/logout/{userNo}")
    public ResponseEntity<Map<String, Object>> logout(@RequestHeader(name = "access-token") String accessToken, @PathVariable int userNo){
        service.logout(accessToken, userNo);
        Map<String, Object> map = new HashMap<>();
        map.put("msg","success");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 성공시 환영인사, 실패시 오류메시지",response = Map.class)
    @PostMapping
    public ResponseEntity<Map<String, Object>> regist(@RequestBody @ApiParam(value = "회원가입 유저 정보", required = true) User user) {
        int regist = service.regist(user);
        String msg="success";
        String result="";
        switch (regist){
            case -1:
                result = "해당 아이디로 등록된 회원이 존재합니다. 다른 아이디로 다시 시도해주세요";
                msg = "fail";
                break;
            case 1:
                msg = "success";
                break;
            case 0:
            default:
                msg = "fail";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 정보 수정", notes = "변경에 성공하면 성공메시지, 실패하면 실패메시지 반환",response = Map.class)
    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody @ApiParam(value = "유저 아이디와 비밀번호", required = true) LoginForm loginForm,
                                                      @RequestBody @ApiParam(value = "새로운 비밀번호", required = true) String newPwd) {
        User user = service.login(loginForm);
        Map<String, Object> map = new HashMap<>();
        String msg = "비밀번호 변경 성공";
        if(user==null){
            msg="비밀번호가 일치하지 않습니다";
        }
        else{
            user.setUserPwd(newPwd);
            int result = service.update(user);
            if(result!=1){
                msg="비밀번호 변경 실패";
            }
        }
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "탈퇴 성공 메시지 반환", response = Map.class)
    @DeleteMapping("/{userNo}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable @ApiParam(value = "user no", required = true) int userNo){
        int result = service.delete(userNo);
        Map<String, Object> map = new HashMap<>();
        String msg = "정상적으로 탈퇴되었습니다.";;
        if(result == 1){
            map.put("msg", msg);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        msg = "탈퇴 실패.";
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "유저 정보 가져오기", notes = "유저가 존재하면 유저정보 반환, 실패시 오류메시지 반환",response = Map.class)
    @GetMapping("/select/{userNo}")
    public ResponseEntity<Map<String, Object>> select(@PathVariable int userNo){
        User user = service.selectById(userNo);
        Map<String, Object> map = new HashMap<>();
        if(user == null){
            map.put("msg","fail");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        map.put("user", user);
        map.put("msg", "success");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
