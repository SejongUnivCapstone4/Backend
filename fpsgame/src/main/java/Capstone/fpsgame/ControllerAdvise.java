package Capstone.fpsgame;


import Capstone.fpsgame.dto.LogMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(Exception.class)
    public String  exception(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage()); // 예외 메시지 추가
        return "/error";
        // error.html 페이지로 이동
    }
}
