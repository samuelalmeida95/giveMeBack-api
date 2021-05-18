package projects.givemebackapi.repositoriesTests;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import projects.givemebackapi.repositories.ItemEmprestadoRepository;

@DataJpaTest
@DisplayName("Testes para Repositorio de ItemEmprestado")
public class ItemEmprestadoRepositoryTests {

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepository;
    
}
