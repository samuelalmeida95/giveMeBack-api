package projects.givemebackapi.repositoriesTests;

import java.util.Optional;

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

        Assertions.assertThat(donoItemSalvo.getId()).isNotNull();

        Assertions.assertThat(donoItemSalvo.getNome()).isNotNull();

        Assertions.assertThat(donoItemSalvo.getWhatsapp()).isNotNull();

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

    @Test
    @DisplayName("Salvar e atualizar Dono de um item quando bem sucedido")
    void salvar_AtualizarDonoItemQuandoBemSucedido() {

        DonoItem donoItemParaSerSalvo = DonoItemCreator.criarDonoItem();

        DonoItem donoItemSalvo = this.donoItemRepository.save(donoItemParaSerSalvo);

        donoItemSalvo.setNome("Alladin");

        DonoItem donoItemAtualizado = this.donoItemRepository.save(donoItemSalvo);

        Assertions.assertThat(donoItemAtualizado).isNotNull();

        Assertions.assertThat(donoItemAtualizado.getId()).isNotNull();

        Assertions.assertThat(donoItemAtualizado.getNome()).isEqualTo(donoItemSalvo.getNome());
    }

    @Test
    @DisplayName("Deletar e remover Dono quando bem sucedido")
    void deletar_RemoverDonoItemQuandoBemSucedido() {
        DonoItem donoParaSerSalvo = DonoItemCreator.criarDonoItem();

        DonoItem donoItemSalvo = this.donoItemRepository.save(donoParaSerSalvo);

        this.donoItemRepository.delete(donoItemSalvo);

        Optional<DonoItem> donoItemOptional = this.donoItemRepository.findById(donoItemSalvo.getId());

        Assertions.assertThat(donoItemOptional).isEmpty();
    }

    @Test
    @DisplayName("Encontrar por id retorna empty quando não for encontrado")
    void encontrar_PorIdRetornarEmptyQuandoNaoEncontrado() {

        Optional<DonoItem> categoriaOptional = this.donoItemRepository.findById(1);

        Assertions.assertThat(categoriaOptional).isEmpty();
    }
}
