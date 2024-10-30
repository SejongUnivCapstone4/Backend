package Capstone.fpsgame.global.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LucidUserInfo {
    private Long id;
    private String nickName;
    private String email;
    private String password;
    private String role;
}
