package project.jlog.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import project.jlog.user.dto.UserRequestDTO;
import project.jlog.user.entity.User;
import project.jlog.user.enumPackage.UserRole;
import project.jlog.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void validateDuplicate(UserRequestDTO dto, BindingResult bindingResult) {
        if (userRepository.existsByUserId(dto.getUserId())) {
            bindingResult.rejectValue("userId", "duplicate", "이미 존재하는 아이디입니다.");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "이미 존재하는 이메일입니다.");
        }
    }

    public User userJoin(String userId, String password, String email) {
        User user = User.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .userRole(UserRole.USER)
                .build();
        return userRepository.save(user);
    }

}
