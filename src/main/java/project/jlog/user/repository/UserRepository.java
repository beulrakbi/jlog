package project.jlog.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jlog.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
