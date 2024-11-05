package Capstone.fpsgame.controller;

import Capstone.fpsgame.dto.SiginInRequestDto;
import Capstone.fpsgame.dto.SignInResponseDto;
import Capstone.fpsgame.dto.SignUpRequestDto;
import Capstone.fpsgame.repository.UserRepository;
import Capstone.fpsgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @RequestMapping("/main/signUp")
    public void signUp(SignUpRequestDto dto){

        userService.signUp(dto);
    }
    @RequestMapping("/main/signIn")
    public ResponseEntity<SignInResponseDto> signIn(SiginInRequestDto dto){
        HttpHeaders headers = new HttpHeaders();
       return new ResponseEntity<>(userService.signIn(dto), headers, HttpStatus.OK);
    }

}
