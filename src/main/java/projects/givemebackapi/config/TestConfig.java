package projects.givemebackapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import projects.givemebackapi.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dBservice;

    @Bean
    public void instanciaBaseDeDados(){
        this.dBservice.instanciaBaseDeDados();
    }
}
