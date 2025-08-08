package project.jlog.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jlog.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    //아이디 중복 체크
    boolean existsByUserId(String userId);

    //이메일 중복 체크
    boolean existsByEmail(String email);

    Optional<User> findByUserId(String userId);

}
