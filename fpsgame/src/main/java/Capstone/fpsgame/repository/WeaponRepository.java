package Capstone.fpsgame.repository;

import Capstone.fpsgame.domain.UserInfo;
import Capstone.fpsgame.domain.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeaponRepository extends JpaRepository<Weapon,Long> {
    Optional<Weapon> findByUserInfo(UserInfo userInfo);
}
