package Capstone.fpsgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FpsgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(FpsgameApplication.class, args);
	}

}
