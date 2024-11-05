package Capstone.fpsgame;


import Capstone.fpsgame.dto.LogMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<LogMessage> exception(Exception e){
        return ResponseEntity.ok(LogMessage.builder().message(String.valueOf(e)).build());
    }
}
