package com.project.bootboard.controller;

import com.project.bootboard.dto.MemberDto;
import com.project.bootboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String loginForm(@ModelAttribute("check") String check,
                            @ModelAttribute("error") String error,
                            Authentication authentication) {
        if (authentication != null) {
            return "redirect:/board/list";
        }
        return "/member/login-form";
    }

    @PostMapping("/login/suc")
    public String login(MemberDto memberDto) {
        return "/member/login-form";
    }

    @GetMapping("/sign/sign-form")
    public String signForm() {
        return "/member/sign-form";
    }

    @ResponseBody
    @GetMapping("/sign/id-check")
    public String idCheck(@RequestParam("id") String id) {
        String check = memberService.idCheck(id);
        if (check == null) {
            return "ok";
        }
        return "fail";
    }

    @PostMapping("/sign/sign-member")
    public String signMember(MemberDto memberDto, Model model) {
        memberService.signMember(memberDto);
        model.addAttribute("msg","가입성공");
        model.addAttribute("url","/");
        return "/etc/alert";
    }

}
