package project.jlog.user.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.jlog.user.dto.EmailDTO;
import project.jlog.user.dto.UpdatePwdDTO;
import project.jlog.user.entity.User;
import project.jlog.user.service.EmailService;
import project.jlog.user.service.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class PasswordController {

    private final UserService userService;
    private final EmailService emailService;

    // 1. 임시 비밀번호 발급 요청
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody EmailDTO request) {
        try {
            emailService.sendTempPassword(request.getEmail());
            return ResponseEntity.ok("임시 비밀번호가 발송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("메일 발송 실패 또는 이메일이 존재하지 않습니다.");
        }
    }

    // 2. 비밀번호 설정 페이지
    @GetMapping("/new_pwd")
    public String newPwd(){
        return "/update_pwd";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new_pwd")
    public String newPwd(@Valid UpdatePwdDTO updatePwdDTO, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "update_pwd";
        }
        if(!updatePwdDTO.getPwd1().equals(updatePwdDTO.getPwd2())){
            bindingResult.rejectValue("pwd2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
            return "update_pwd";
        }
        userService.updatePwd(updatePwdDTO.getPwd1());
        return "redirect:/";
    }
}
