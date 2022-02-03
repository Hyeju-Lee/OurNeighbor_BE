package solux.wansuki.OurNeighbor_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OurNeighborBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurNeighborBeApplication.class, args);
	}

}
