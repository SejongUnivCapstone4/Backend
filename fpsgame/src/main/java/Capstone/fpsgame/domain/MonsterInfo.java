package Capstone.fpsgame.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonsterInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="movement_speed")
    private Long movementSpeed;
    @Column(name="jump_power")
    private Long jumpPower;
    private Long health;



}
