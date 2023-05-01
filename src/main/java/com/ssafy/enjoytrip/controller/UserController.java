package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    String login(@ModelAttribute User user, HttpSession session){
        user = service.login(user);
        if(user != null) session.setAttribute("user",user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/regist")
    String regist(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
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
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/";
    }

}
