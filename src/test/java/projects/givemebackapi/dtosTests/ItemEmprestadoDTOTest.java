package projects.givemebackapi.dtosTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.dtos.ItemEmprestadoDTO;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.util.ItemEmprestadoCreator;

public class ItemEmprestadoDTOTest {
    
    @Test
    @DisplayName("Criar ItemEmprestado vazio retorna null quando bem sucedido")
    public void criar_ItemEmprestadoVazioRetornaNullQuandoBemSucedido() {

        ItemEmprestadoDTO itemEmprestadoDTO = new ItemEmprestadoDTO();

        Assertions.assertThat(itemEmprestadoDTO.getId()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getNome()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getDataDevolucao()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getStatus()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getDesc()).isNull();
    }

    @Test
    @DisplayName("Criar ItemEmprestado não retorna null quando bem sucedido")
    public void criar_ItemEmprestadoNaoRetornaNullQuandoBemSucedido() {

       ItemEmprestado item = ItemEmprestadoCreator.criarItemEmprestado();

       ItemEmprestadoDTO itemDTO = new ItemEmprestadoDTO(item);

        Assertions.assertThat(itemDTO.getId()).isNull();

        Assertions.assertThat(itemDTO.getNome()).isNotNull();

        Assertions.assertThat(itemDTO.getDesc()).isNotNull();

        Assertions.assertThat(itemDTO.getDataDevolucao()).isNotNull();

        Assertions.assertThat(itemDTO.getStatus()).isNotNull();

        Assertions.assertThat(itemDTO.getStatus()).isEqualTo(TipoStatus.EMPRESTADO);

    }
}
