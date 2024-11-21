package Capstone.fpsgame.controller;

import Capstone.fpsgame.dto.SignInRequestDto;
import Capstone.fpsgame.dto.SignInResponseDto;
import Capstone.fpsgame.dto.SignUpRequestDto;
import Capstone.fpsgame.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.HashMap;
import java.util.Map;

// TODO error.html보충하기
// 로그인 후 과정처리하기
// TODO 테스트코드 작성하기
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
        String exeFilePath = "C:\\Windows\\Windows\\MPP.exe"; // 실행할 exe 파일 경로
        // JSON 데이터 생성
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // DTO를 JSON 문자열로 변환
            String jsonInput = objectMapper.writeValueAsString(dto);
            // ProcessBuilder로 exe 파일 실행
            ProcessBuilder processBuilder = new ProcessBuilder(exeFilePath);
            Process process = processBuilder.start();

            // exe 파일로 JSON 데이터를 전달
            try (OutputStream os = process.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
                writer.write(jsonInput); // JSON 데이터를 stdin으로 전달
                writer.flush(); // 데이터 강제 전송
            }

            // exe 파일 출력 읽기
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Unreal 출력: " + line);
                }
            }
            // 프로세스 종료 대기
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Unreal exe 파일 실행 성공");
            } else {
                System.err.println("Unreal exe 파일 실행 실패, 종료 코드: " + exitCode);
            }
        } catch (Exception e) {
            System.err.println("Unreal exe 파일 실행 중 오류 발생: " + e.getMessage());
        }
    }
    private void executeUnrealExe() {
        String exeFilePath = "C:\\Windows\\Windows\\MPP.exe"; // 실행할 exe 파일 경로
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(exeFilePath);
            System.out.println(processBuilder);
            processBuilder.start(); // exe 파일 실행
        } catch (IOException e) {
            System.out.println("exe 파일 실행에 실패했습니다: " + e.getMessage());
        }
    }

    // 홈 페이지
}

