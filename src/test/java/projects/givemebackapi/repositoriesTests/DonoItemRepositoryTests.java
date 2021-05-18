package projects.givemebackapi.repositoriesTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.repositories.DonoItemRepository;
import projects.givemebackapi.util.DonoItemCreator;

@DataJpaTest
@DisplayName("Testes para Repositorio de DonoItem")
public class DonoItemRepositoryTests {

    @Autowired
    private DonoItemRepository donoItemRepository;


    @Test
    @DisplayName("Salvar e persistir Dono de um item quando bem sucedido")
    void salvar_PersistirDonoItemQuandoBemSucedido() {

        DonoItem donoParaSerSalvo = DonoItemCreator.criarDonoItem();
        DonoItem donoItemSalvo = this.donoItemRepository.save(donoParaSerSalvo);

        Assertions.assertThat(donoItemSalvo).isNotNull();
        
    }
    
}

    

