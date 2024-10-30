package Capstone.fpsgame.global.userDetail;

//import com.example.curtaincall.global.auth.CurtaincallUserInfo;
import Capstone.fpsgame.global.auth.LucidUserInfo;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {
    private final LucidUserInfo userInfo;

    public CustomUserDetails(LucidUserInfo lucidUserInfo) {
        this.userInfo = lucidUserInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + userInfo.getRole());

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public String getNickName(){
        return userInfo.getNickName();
    }
    public String getEmail(){
        return userInfo.getEmail();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
