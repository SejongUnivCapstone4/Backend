package Capstone.fpsgame.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="attack_power")
    private float attackPower;
    @Column(name="ammo_capacity")
    private int ammoCapacity;
    private float weight;
    @OneToOne(mappedBy = "weapon")
    private UserInfo userInfo;

}
