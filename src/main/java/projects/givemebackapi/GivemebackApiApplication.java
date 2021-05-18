package projects.givemebackapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import projects.givemebackapi.services.DBService;

@SpringBootApplication
public class GivemebackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GivemebackApiApplication.class, args);
	}

	@Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instanciaBaseDeDados() {
        if (strategy.equals("create")) {
            this.dbService.instanciaBaseDeDados();
        }
        return false;
    }

}
