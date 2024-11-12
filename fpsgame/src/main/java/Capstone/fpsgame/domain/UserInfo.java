package Capstone.fpsgame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


@Entity
@Data
//@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="movement_speed")
    private float movementSpeed;
    @Column(name="jump_power")
    private float jumpPower;
    private Long health;
    @OneToOne(mappedBy = "userInfo")
    private User user;
    //foreign key를 Userinfo에서 관리함
    @OneToOne
    @JoinColumn(name = "weapon_id")
    private Weapon weapon;

    @Builder
    public UserInfo(){
        movementSpeed= 0.2F;
        jumpPower=0.2F;
        health= 200L;
    }
    public UserInfo toUserInfo(Weapon weapon){
        this.weapon=weapon;
        return this;
    }



}
