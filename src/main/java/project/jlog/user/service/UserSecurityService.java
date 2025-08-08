package project.jlog.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.jlog.user.repository.UserRepository;

import java.util.Optional;

/* 스프링 시큐리티가 로그인 시 사용할 서비스인 UserSecurityService */
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
        project.jlog.user.entity.User user = userRepository.findByUserId(userId)
                .orElseThrow(()->new UsernameNotFoundException("유저 없음: " + userId));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(user.getUserRole().name())
                .build();
    }
}
