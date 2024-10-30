package Capstone.fpsgame.dto;

import Capstone.fpsgame.domain.User;
import Capstone.fpsgame.domain.UserInfo;
import lombok.Builder;

public record SignUpRequestDto(String email, String password, String phoneNumber) {
    @Builder
    public SignUpRequestDto(String email, String password, String phoneNumber){
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
    }
    public User toUser(UserInfo userInfo){
        return User.builder()
                .email(this.email())
                .password(this.password())
                .phoneNumber(this.phoneNumber()).build();
    }
}
