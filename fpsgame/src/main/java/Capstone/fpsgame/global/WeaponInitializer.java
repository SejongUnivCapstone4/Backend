package Capstone.fpsgame.global;

import Capstone.fpsgame.domain.Weapon;
import Capstone.fpsgame.repository.WeaponRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class WeaponInitializer implements CommandLineRunner {
    private final WeaponRepository weaponRepository;
    private Weapon rifle;
    private Weapon pistol;
    private Weapon submachine;
    private Weapon sniper;
    private Weapon rocketLauncher;

    public WeaponInitializer(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        rifle=weaponRepository.save(Weapon.builder().name("rifle").attackPower(20)                .weight(20)
                .build());
        pistol=weaponRepository.save(Weapon.builder().name("pistol").attackPower(20)
                .build());
        submachine=weaponRepository.save(Weapon.builder().name("submachine").attackPower(20)                .weight(20)
                .build());
        sniper=weaponRepository.save(Weapon.builder().name("sniper").attackPower(20)
                .build());
        rocketLauncher=weaponRepository.save(Weapon.builder().name("rocketLauncher").attackPower(20)
                .build());

    }
}
