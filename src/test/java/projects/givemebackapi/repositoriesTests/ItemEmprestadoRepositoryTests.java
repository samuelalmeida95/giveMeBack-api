package projects.givemebackapi.repositoriesTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.repositories.ItemEmprestadoRepository;
import projects.givemebackapi.util.ItemEmprestadoCreator;



@DataJpaTest
@DisplayName("Testes para Repositorio de ItemEmprestado")
public class ItemEmprestadoRepositoryTests {

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepository;


    @Test
    @DisplayName("Salvar e persistir ItemEmprestado quando bem sucedido")
    void salvar_PersistirItemEmprestadoQuandoBemSucedido() {
        ItemEmprestado itemEmprestadoParaSalvar = ItemEmprestadoCreator.criarItemEmprestado();
        ItemEmprestado itemEmprestadoSalvo = this.itemEmprestadoRepository.save(itemEmprestadoParaSalvar);

        Assertions.assertThat(itemEmprestadoSalvo).isNotNull();
        Assertions.assertThat(itemEmprestadoSalvo.getIdItem()).isNotNull();
        Assertions.assertThat(itemEmprestadoSalvo.getNomeItem()).isNotNull();
        Assertions.assertThat(itemEmprestadoSalvo.getDescricaoItem()).isNotNull();
        Assertions.assertThat(itemEmprestadoSalvo.getNomeItem()).isEqualTo(itemEmprestadoParaSalvar.getNomeItem());
    }
    
}
