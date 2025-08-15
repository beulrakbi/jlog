package project.jlog.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.jlog.user.entity.User;
import project.jlog.user.repository.UserRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 임시 비밀번호 발급
    public boolean sendTempPassword(String email) {
        try {
            // 기존 로직 그대로
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("해당 이메일 사용자를 찾을 수 없습니다."));

            String tempPassword = generateTempPassword();
            String encodedPassword = passwordEncoder.encode(tempPassword);

            User updatedUser = User.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .password(encodedPassword)
                    .userRole(user.getUserRole())
                    .build();

            userRepository.save(updatedUser);

            sendEmail(email, user.getUserId(), tempPassword);

            return true; // 성공
        } catch (Exception e) {
            return false; // 실패
        }
    }


    // 임시 비밀번호 생성 (영문 + 숫자 8자리)
    private String generateTempPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 8; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // HTML 메일 발송
    private void sendEmail(String to, String userId, String tempPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("임시 비밀번호 안내");
            helper.setText(
                    "<h3>아이디: " + userId + "</h3>" +
                    "<h3>임시 비밀번호: " + tempPassword + "</h3>" +
                            "<p>로그인 후 반드시 비밀번호를 변경해주세요.</p>",
                    true
            );
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }
}
