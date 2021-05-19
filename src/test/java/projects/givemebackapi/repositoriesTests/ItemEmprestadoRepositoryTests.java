package projects.givemebackapi.repositoriesTests;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
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
        Assertions.assertThat(itemEmprestadoSalvo.getStatus()).isNotNull();
        Assertions.assertThat(itemEmprestadoSalvo.getStatus()).isEqualTo(TipoStatus.EMPRESTADO);
        Assertions.assertThat(itemEmprestadoSalvo.getNomeItem()).isEqualTo(itemEmprestadoParaSalvar.getNomeItem());
    }
    
    @Test
    @DisplayName("Salvar throw ConstraintViolationException quando nome item for vazio")
    void salvar_ConstraintViolationExceptionQuandoNomeItemEVazio(){
        ItemEmprestado itemEmprestadoVazio =  new ItemEmprestado();
        
        Assertions.assertThatThrownBy(() -> this.itemEmprestadoRepository.save(itemEmprestadoVazio))
                  .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Salvar e atualizar ItemEmprestado quando bem sucedido")
    void salvar_AtualizarItemEmprestadoQuandoBemSucedido(){
        ItemEmprestado itemEmprestadoParaSerSalvo = ItemEmprestadoCreator.criarItemEmprestado();

        ItemEmprestado itemEmprestadoSalvo = this.itemEmprestadoRepository.save(itemEmprestadoParaSerSalvo);

        itemEmprestadoSalvo.setNomeItem("Relogio Mormai");

        ItemEmprestado itemEmprestadoAtulizado = this.itemEmprestadoRepository.save(itemEmprestadoSalvo);

        Assertions.assertThat(itemEmprestadoAtulizado).isNotNull();
        Assertions.assertThat(itemEmprestadoAtulizado.getIdItem()).isNotNull();
        Assertions.assertThat(itemEmprestadoAtulizado.getStatus()).isNotNull();
        Assertions.assertThat(itemEmprestadoSalvo.getStatus()).isEqualTo(TipoStatus.EMPRESTADO);
        Assertions.assertThat(itemEmprestadoAtulizado.getNomeItem()).isEqualTo(itemEmprestadoSalvo.getNomeItem());
  
    }

    @Test
    @DisplayName("Deletar e remover ItemEmprestado quando bem sucedido")
    void deletar_RemoverItemEmprestadoQuandoBemSucedido(){
        ItemEmprestado itemEmprestadoParaSerSalva = ItemEmprestadoCreator.criarItemEmprestado();

        ItemEmprestado itemEmprestadoSalva = this.itemEmprestadoRepository.save(itemEmprestadoParaSerSalva);

        this.itemEmprestadoRepository.delete(itemEmprestadoSalva);

        Optional<ItemEmprestado> itemEmprestadoOptional = this.itemEmprestadoRepository.findById(itemEmprestadoSalva.getIdItem());

        Assertions.assertThat(itemEmprestadoOptional).isEmpty();
  
    }

    @Test
    @DisplayName("Encontrar ItemEmprestado por id quando bem sucedido")
    void encontrar_PorIdItemEmprestadoQuandoBemSucedido(){
        ItemEmprestado ItemEmprestadoParaSerSalva = ItemEmprestadoCreator.criarItemEmprestado();

        ItemEmprestado ItemEmprestadoSalva = this.itemEmprestadoRepository.save(ItemEmprestadoParaSerSalva);

        Optional<ItemEmprestado> ItemEmprestadoOptional = this.itemEmprestadoRepository.findById(ItemEmprestadoSalva.getIdItem());

        Assertions.assertThat(ItemEmprestadoOptional)
                  .isNotEmpty()
                  .contains(ItemEmprestadoSalva);
       
    }

    @Test
    @DisplayName("Encontrar por id retorna empty quando nao for encontrado")
    void encontrar_PorIdRetornarEmptyQuandoNaoEncontrado(){

        Optional<ItemEmprestado> ItemEmprestadoOptional = this.itemEmprestadoRepository.findById(1);

        Assertions.assertThat(ItemEmprestadoOptional).isEmpty();
    }
}
