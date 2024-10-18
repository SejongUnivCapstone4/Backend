package Capstone.fpsgame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="movement_speed")
    private Long movementSpeed;
    @Column(name="jump_power")
    private Long jumpPower;
    private Long health;
    @OneToOne(mappedBy = "user_info")
    //foreign key를 Userinfo에서 관리함
    @JoinColumn(name="weapon_id")
    private Weapon weapon;


}
