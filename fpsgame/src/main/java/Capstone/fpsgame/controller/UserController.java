package Capstone.fpsgame.controller;

import Capstone.fpsgame.dto.SignInRequestDto;
import Capstone.fpsgame.dto.SignInResponseDto;
import Capstone.fpsgame.dto.SignUpRequestDto;
import Capstone.fpsgame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

//TODO error.html보충하기
// 로그인 후 과정처리하기
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입 폼 페이지
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("signUpRequest", new SignUpRequestDto());
        return "signup"; // signup.html로 이동
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signUpRequest") SignUpRequestDto dto, Model model) {
        userService.signUp(dto);
        model.addAttribute("message", "회원가입이 완료되었습니다.");
        return "redirect:/api/signin"; // 회원가입 완료 후 로그인 페이지로 이동
    }

    // 로그인 폼 페이지
    @GetMapping("/signin")
    public String showSignInForm(Model model) {
        model.addAttribute("signInRequest", new SignInRequestDto());
        return "signin"; // signin.html로 이동
    }

    // 로그인 처리
    @PostMapping("/signin")
    public String signIn(@ModelAttribute("signInRequest") SignInRequestDto dto,Model model) {

        SignInResponseDto signInResponseDto=userService.signIn(dto);
        sendSignalToUnreal(signInResponseDto); // 예를 들어, 사용자 이름을 함께 보낼 수 있음
        return "signinsuccess"; // 로그인 성공 시 홈 페이지로 리다이렉트
    }
    private void sendSignalToUnreal(SignInResponseDto dto) {
        String unrealServerUrl = "http://unreal-server-url/endpoint"; // Unreal 서버의 엔드포인트 URL
        RestTemplate restTemplate = new RestTemplate();
        Map<String, SignInResponseDto> params = new HashMap<>();
        params.put("userInfo", dto);
        try {
            restTemplate.postForObject(unrealServerUrl, params, String.class);
        } catch (Exception e) {
            System.out.println("Unreal 서버와의 통신에 실패했습니다: " + e.getMessage());
        }
    }

    // 홈 페이지

}
