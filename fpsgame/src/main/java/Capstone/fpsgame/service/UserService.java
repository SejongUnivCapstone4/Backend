package Capstone.fpsgame.service;

import Capstone.fpsgame.domain.User;
import Capstone.fpsgame.domain.UserInfo;
import Capstone.fpsgame.domain.Weapon;
import Capstone.fpsgame.dto.SiginInRequestDto;
import Capstone.fpsgame.dto.SignInResponseDto;
import Capstone.fpsgame.dto.SignUpRequestDto;
import Capstone.fpsgame.repository.UserInfoRepository;
import Capstone.fpsgame.repository.UserRepository;
import Capstone.fpsgame.repository.WeaponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final WeaponRepository weaponRepository;

    public void signUp(SignUpRequestDto dto){
        UserInfo userInfo=userInfoRepository.save(UserInfo.builder().build());
        userRepository.save(dto.toUser(userInfo));
    }

    public SignInResponseDto signIn(SiginInRequestDto dto) {
        //TODO 커스텀 예외를 만들고 ControllerAdvice를 이용해서 예외 controll하기
      User user= userRepository.findByEmailAndPassword(dto.email(), dto.password()).orElseThrow(
               ()->new RuntimeException("해당 이메일과 비밀번호는 존재하지 않습니다.")
       );
      UserInfo userInfo=userInfoRepository.findByUser(user).orElseThrow(
              ()->new RuntimeException("해당 회원에 대한 정보가 존재하지 않습니다.")
      );
      Weapon weapon= weaponRepository.findByUserInfo(userInfo).orElseThrow(
              ()->new RuntimeException("해당 회원에 대한 무기 정보가 존재하지 않습니다.")
      );

    return SignInResponseDto.from(user,userInfo,weapon);
    }

}
