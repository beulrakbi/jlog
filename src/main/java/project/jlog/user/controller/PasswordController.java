package project.jlog.user.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jlog.user.dto.EmailDTO;
import project.jlog.user.service.EmailService;
import project.jlog.user.service.UserService;

@RestController
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
}
