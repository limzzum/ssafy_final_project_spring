package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@Controller
@RequestMapping("/api/user")
@Slf4j
public class UserRestController {

    private UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpSession session){
        user = service.login(user);
        log.info("login : "+user);
        Map<String, Object> map = new HashMap<>();
        if(user != null){
            session.setAttribute("user",user);
            map.put("user", user);
            map.put("msg",user.getUserName()+"님 환영합니다");
        }else{
            map.put("msg","로그인 실패");
        }
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session){
        session.invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put("msg","로그아웃 되었습니다");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> regist(@RequestBody User user) {
        int result = service.regist(user);
        String msg = "";
        switch (result){
            case -1:
                msg = "해당 아이디로 등록된 회원이 존재합니다. 다른 아이디로 다시 시도해주세요";
                break;
            case 1:
                msg = user.getUserName()+"님, 회원가입을 환영합니다!";
                break;
            case 0:
            default:
                msg = "오류 발생! 잠시 후 다시 시도해주세요.";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody User user, @RequestBody String newPwd) {
        User check = service.login(user);
        String msg = "비밀번호 변경 성공";
        if(check==null) msg="비밀번호가 일치하지 않습니다";
        else{
            check.setUserPwd(newPwd);
            int result = service.update(check);
            if(result!=1) msg="비밀번호 변경 실패";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg",msg);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/delete/{userId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String userId, HttpSession session){
        service.delete(userId);
        session.invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put("msg","정상적으로 탈퇴되었습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @GetMapping("/select/{userId}")
    public ResponseEntity<Map<String, Object>> select(@PathVariable String userId){
        User user = service.select(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("user",user);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
