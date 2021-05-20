package projects.givemebackapi.repositoriesTests;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.repositories.AmigoEmprestimoRepository;
import projects.givemebackapi.util.AmigoEmprestimoCreator;

@DataJpaTest
@DisplayName("Testes para Repositorio de AmigoEmprestimo")
public class AmigoEmprestimoRepositoryTests {

    @Autowired
    private AmigoEmprestimoRepository amigoEmprestimoRepository;

    @Test
    @DisplayName("Salvar e persistir AmigoEmprestimo quando bem sucedido")
    void cria_AmigoEmprestimoQuandoBemSucedido() {

        AmigoEmprestimo amigoEmprestimoParaSalvar = AmigoEmprestimoCreator.criarAmigoEmprestimo();

        AmigoEmprestimo amigoEmprestimoSalvo = this.amigoEmprestimoRepository.save(amigoEmprestimoParaSalvar);

        Assertions.assertThat(amigoEmprestimoSalvo).isNotNull();

        Assertions.assertThat(amigoEmprestimoSalvo.getId()).isNotNull();

        Assertions.assertThat(amigoEmprestimoSalvo.getNome()).isNotNull();

        Assertions.assertThat(amigoEmprestimoSalvo.getWhatsapp()).isNotNull();
    }

    @Test
    @DisplayName("Salvar throw ConstraintViolationException quando AmigoEmprestimo for vazio")
    void salvar_ConstraintViolationExceptionQuandoAmigoEmprestimoVazio() {

        AmigoEmprestimo amigoEmprestimoVazio = new AmigoEmprestimo();

        Assertions.assertThatThrownBy(() -> this.amigoEmprestimoRepository.save(amigoEmprestimoVazio))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Salvar e atualizar AmigoEmprestimo quando bem sucedido")
    void salvar_AtualizarAmigoEmprestimoQuandoBemSucedido() {

        AmigoEmprestimo AmigoEmprestimoParaSerSalvo = AmigoEmprestimoCreator.criarAmigoEmprestimo();

        AmigoEmprestimo AmigoEmprestimoSalvo = this.amigoEmprestimoRepository.save(AmigoEmprestimoParaSerSalvo);

        AmigoEmprestimoSalvo.setNome("Alladin");

        AmigoEmprestimo AmigoEmprestimoAtualizado = this.amigoEmprestimoRepository.save(AmigoEmprestimoSalvo);

        Assertions.assertThat(AmigoEmprestimoAtualizado).isNotNull();

        Assertions.assertThat(AmigoEmprestimoAtualizado.getId()).isNotNull();

        Assertions.assertThat(AmigoEmprestimoAtualizado.getNome()).isEqualTo(AmigoEmprestimoSalvo.getNome());
    }

    @Test
    @DisplayName("Deletar e remover AmigoEmprestimo quando bem sucedido")
    void deletar_RemoverAmigoEmprestimoQuandoBemSucedido() {
        AmigoEmprestimo amigoParaSerSalvo = AmigoEmprestimoCreator.criarAmigoEmprestimo();

        AmigoEmprestimo AmigoEmprestimoSalvo = this.amigoEmprestimoRepository.save(amigoParaSerSalvo);

        this.amigoEmprestimoRepository.delete(AmigoEmprestimoSalvo);

        Optional<AmigoEmprestimo> AmigoEmprestimoOptional = this.amigoEmprestimoRepository
                .findById(AmigoEmprestimoSalvo.getId());

        Assertions.assertThat(AmigoEmprestimoOptional).isEmpty();
    }

    @Test
    @DisplayName("Encontrar AmigoEmprestimo por id retorna empty quando não for encontrado")
    void encontrar_PorIdRetornarEmptyQuandoNaoEncontrado() {

        Optional<AmigoEmprestimo> categoriaOptional = this.amigoEmprestimoRepository.findById(1);

        Assertions.assertThat(categoriaOptional).isEmpty();
    }
}
