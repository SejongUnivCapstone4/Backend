package Capstone.fpsgame.global.userDetail;


import Capstone.fpsgame.domain.User;
import Capstone.fpsgame.global.auth.LucidUserInfo;
import Capstone.fpsgame.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public CustomUserDetails loadUserById(Long id){
        User user=userRepository.findById(id).orElseThrow(()->
                new RuntimeException("id값에 해당하는 user가 없습니다."));
        LucidUserInfo userInfo=LucidUserInfo.builder().role(user.getUserRole())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickName(user.getNickName()).build();
        return new CustomUserDetails(userInfo);
    }
}
