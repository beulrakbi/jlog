package project.jlog.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import project.jlog.exception.DataNotFoundException;
import project.jlog.user.dto.UserRequestDTO;
import project.jlog.user.entity.User;
import project.jlog.user.enumPackage.UserRole;
import project.jlog.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

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

    public User getUser(String userId){
        Optional<User> user = this.userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new DataNotFoundException("user not found");
        }
    }

    //비밀번호 재설정
    public void updatePwd(String password){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //지금 로그인 한 사람의 정보
        String currentUserId = auth.getName(); //사용자의 아이디를 저장

        User user1 = userRepository.findByUserId(currentUserId) //사용자의 아이디를 검증
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        User updatedUser = User.builder()
                .userId(user1.getUserId())
                .email(user1.getEmail())
                .password(passwordEncoder.encode(password))
                .userRole(user1.getUserRole())
                .build();
        this.userRepository.save(updatedUser);
    }

}
