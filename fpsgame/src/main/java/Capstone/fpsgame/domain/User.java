package Capstone.fpsgame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull @Email
    private String email;
    @Column(name="nick_name")
    private String nickName;
    @NonNull
    private String password;
    @LastModifiedDate
    @Column(name="password_recent_date")
    private LocalDate passwordRecentDate;
    @Column(name="phone_number")
    private String phoneNumber;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_info_id")
    private UserInfo userInfo;
    @ColumnDefault("'USER'")
    private String userRole;
    @Builder
    public User(String email,String nickName,String password,String phoneNumber,UserInfo userInfo){
        this.email=email;
        this.nickName=nickName;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.userInfo=userInfo;
    }

}
