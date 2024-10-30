package Capstone.fpsgame.global.auth;

import io.jsonwebtoken.Claims;
public interface AuthorizationProvider {
    String create(LucidUserInfo userInfo);

    Claims parseClaims(String token);

    boolean validateToken(String token);

    public Long getUserId(String token);
}
