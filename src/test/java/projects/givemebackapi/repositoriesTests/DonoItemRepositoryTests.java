package projects.givemebackapi.repositoriesTests;

import javax.validation.ConstraintViolationException;

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

        Assertions.assertThat(donoItemSalvo.getIdDono()).isNotNull();

        Assertions.assertThat(donoItemSalvo.getNomeDono()).isNotNull();

        Assertions.assertThat(donoItemSalvo.getWhatsappDono()).isNotNull();

        Assertions.assertThat(donoItemSalvo.getItensEmprestados()).isNotNull();

        Assertions.assertThat(donoItemSalvo.getItensEmprestados().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Salvar throw ConstraintViolationException quando DonoItem for vazio")
    void salvar_ConstraintViolationExceptionQuandoDonoItemVazio() {

        DonoItem donoVazio = new DonoItem();

        Assertions.assertThatThrownBy(() -> this.donoItemRepository.save(donoVazio))
                .isInstanceOf(ConstraintViolationException.class);

    }

}
