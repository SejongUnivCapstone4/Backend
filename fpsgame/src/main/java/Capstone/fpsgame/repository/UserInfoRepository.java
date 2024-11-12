package Capstone.fpsgame.repository;

import Capstone.fpsgame.domain.User;
import Capstone.fpsgame.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByUser(User user);
}
