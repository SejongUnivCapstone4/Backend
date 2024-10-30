package Capstone.fpsgame.controller;

import Capstone.fpsgame.dto.SiginInRequestDto;
import Capstone.fpsgame.dto.SignInResponseDto;
import Capstone.fpsgame.dto.SignUpRequestDto;
import Capstone.fpsgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public void signUp(SignUpRequestDto dto){
        //TODO email 중복 처리 예외 컨트롤
        userService.signUp(dto);
    }
    public ResponseEntity<SignInResponseDto> signIn(SiginInRequestDto dto){
        HttpHeaders headers = new HttpHeaders();
       return new ResponseEntity<SignInResponseDto>(userService.signIn(dto),headers,HttpStatus.OK);
    }

}
