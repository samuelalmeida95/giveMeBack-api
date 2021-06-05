package projects.givemebackapi.dtosTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.dtos.CustomItemEmprestadoDTO;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.util.ItemEmprestadoCreator;

public class CustomItemEmprestadoDTOtest {

    @Test
    @DisplayName("Criar CustomItemEmprestadoDTO vazio retorna null quando bem sucedido")
    public void criar_CustomItemEmprestadoVazioRetornaNullQuandoBemSucedido() {

        CustomItemEmprestadoDTO itemEmprestadoDTO = new CustomItemEmprestadoDTO();

        Assertions.assertThat(itemEmprestadoDTO.getId()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getNome()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getDataDevolucao()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getStatus()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getEmprestadoPara()).isNull();
    }

    @Test
    @DisplayName("Criar CustomItemEmprestadoDTO nao retorna null quando bem sucedido")
    public void criar_CustomItemEmprestadoDTONaoRetornaNullQuandoBemSucedido() {

       ItemEmprestado item = ItemEmprestadoCreator.criarItemEmprestado();

       CustomItemEmprestadoDTO itemEmprestadoDTO = new CustomItemEmprestadoDTO(item);

        Assertions.assertThat(itemEmprestadoDTO.getId()).isNull();

        Assertions.assertThat(itemEmprestadoDTO.getNome()).isNotNull();

        Assertions.assertThat(itemEmprestadoDTO.getDataDevolucao()).isNotNull();

        Assertions.assertThat(itemEmprestadoDTO.getStatus()).isNotNull();

        Assertions.assertThat(itemEmprestadoDTO.getStatus()).isEqualTo(TipoStatus.EMPRESTADO);

        Assertions.assertThat(itemEmprestadoDTO.getEmprestadoPara()).isNotNull();
    }

    @Test
    @DisplayName("Criar CustomItemEmprestadoDTO contem um amigo quando bem sucedido")
    public void criar_CustomItemEmprestadoContemUmAmigoQuandoBemSucedido(){

        ItemEmprestado item = ItemEmprestadoCreator.criarItemEmprestado();

        CustomItemEmprestadoDTO itemEmprestadoDTO = new CustomItemEmprestadoDTO(item);

        Assertions.assertThat(itemEmprestadoDTO.getEmprestadoPara().getNome()).isEqualTo("Fulano do teste");
        
        Assertions.assertThat(itemEmprestadoDTO.getEmprestadoPara().getWhatsApp()).isEqualTo("887513474");

        Assertions.assertThat(itemEmprestadoDTO.getEmprestadoPara().getEndereco()).isEqualTo("rua das palmeiras");

        Assertions.assertThat(itemEmprestadoDTO.getEmprestadoPara().getWhatsApp()).isNotEqualTo("00000000");

        Assertions.assertThat(itemEmprestadoDTO.getEmprestadoPara().getEndereco()).isNotEqualTo("rua que não é a do amigo emprestado.");
    }
    
}
