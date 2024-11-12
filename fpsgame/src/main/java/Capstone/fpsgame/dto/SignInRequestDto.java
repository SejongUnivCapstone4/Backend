package Capstone.fpsgame.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class SignInRequestDto {
    private String email;
    private String password;
}
