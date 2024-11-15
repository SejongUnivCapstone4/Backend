package Capstone.fpsgame.repository;

import Capstone.fpsgame.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndPassword(String email,String password);

    boolean existsByEmail(String email);
}
