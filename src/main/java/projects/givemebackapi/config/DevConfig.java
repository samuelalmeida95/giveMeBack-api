package projects.givemebackapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import projects.givemebackapi.services.DBService;

@Configuration
public class DevConfig {

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