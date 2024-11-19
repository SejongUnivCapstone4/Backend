package Capstone.fpsgame.service;

import Capstone.fpsgame.domain.User;
import Capstone.fpsgame.domain.UserInfo;
import Capstone.fpsgame.domain.Weapon;
import Capstone.fpsgame.dto.SignInRequestDto;
import Capstone.fpsgame.dto.SignInResponseDto;
import Capstone.fpsgame.dto.SignUpRequestDto;
import Capstone.fpsgame.global.exception.UserDuplicatedException;
import Capstone.fpsgame.global.exception.UserInfoNotFountException;
import Capstone.fpsgame.global.exception.UserNotFoundException;
import Capstone.fpsgame.global.exception.WeaponNotFounException;
import Capstone.fpsgame.repository.UserInfoRepository;
import Capstone.fpsgame.repository.UserRepository;
import Capstone.fpsgame.repository.WeaponRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Watchable;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final WeaponRepository weaponRepository;


    public void signUp(SignUpRequestDto dto){

        //TODO email 중복 처리 예외 컨트롤
        if(checkDuplicatedEmail(dto)) {
            throw new UserDuplicatedException();
        }
        UserInfo userInfo = userInfoRepository.save(UserInfo.builder().build());
        userInfo=userInfo.toUserInfo(weaponRepository.findByName("rifle").get());

        userRepository.save(dto.toUser(userInfo));
    }

    private boolean checkDuplicatedEmail(SignUpRequestDto dto) {
        return userRepository.existsByEmail(dto.getEmail());
    }

    public SignInResponseDto signIn(SignInRequestDto dto) {
        System.out.println(dto.getEmail());
        System.out.println(dto.getPassword());
        //TODO 커스텀 예외를 만들고 ControllerAdvice를 이용해서 예외 controll하기
      User user= userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).orElseThrow(
              UserNotFoundException::new
       );
      UserInfo userInfo=userInfoRepository.findByUser(user).orElseThrow(
          UserInfoNotFountException::new
      );
      Weapon weapon= userInfo.getWeapon();
    return SignInResponseDto.from(user,userInfo,weapon);
    }

}
