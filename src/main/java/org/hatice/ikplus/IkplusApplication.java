package org.hatice.ikplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.hatice.ikplus.repository")
public class IkplusApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(IkplusApplication.class, args);
	}
	// DENEME 1-2 BERKAY BAŞOL
	
}