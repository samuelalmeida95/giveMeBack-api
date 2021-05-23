package projects.givemebackapi.dtosTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.dtos.CustomAmigoEmprestimoDTO;
import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.util.AmigoEmprestimoCreator;

public class CustomAmigoEmprestimoDTOTest {

    @Test
    @DisplayName("Criar CustomAmigoEmprestimoDTO nao retorna null quando bem sucedido")
    public void criar_CustomAmigoEmprestimoDTOVazioRetornaNullQuandoBemSucedido() {

        CustomAmigoEmprestimoDTO amigoDTO = new CustomAmigoEmprestimoDTO();

        Assertions.assertThat(amigoDTO.getNome()).isNull();      

        Assertions.assertThat(amigoDTO.getEndereco()).isNull();

        Assertions.assertThat(amigoDTO.getWhatsApp()).isNull();
    }

    @Test
    @DisplayName("Criar CustomAmigoEmprestimoDTO retorna null quando bem sucedido")
    public void criar_CustomAmigoEmprestimoDTONaoRetornaNullQuandoBemSucedido() {

        AmigoEmprestimo amigoFull = AmigoEmprestimoCreator.criarAmigoEmprestimo();

        CustomAmigoEmprestimoDTO amigoDTO = new CustomAmigoEmprestimoDTO(amigoFull);

        Assertions.assertThat(amigoDTO.getNome()).isNotNull();

        Assertions.assertThat(amigoDTO.getEndereco()).isNotNull();

        Assertions.assertThat(amigoDTO.getWhatsApp()).isNotNull();
    }
    
}
