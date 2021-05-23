package projects.givemebackapi.dtosTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.dtos.AmigoEmprestimoDTO;
import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.util.AmigoEmprestimoCreator;

public class AmigoEmprestimoDTOTest {
    
    @Test
    @DisplayName("Criar AmigoEmprestimoDTO retorna null quando bem sucedido")
    public void criar_AmigoEmprestimoDTORetornaNullQuandoBemSucedido() {

        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO();

        Assertions.assertThat(amigoDTO.getId()).isNull();

        Assertions.assertThat(amigoDTO.getNome()).isNull();

        Assertions.assertThat(amigoDTO.getEndereco()).isNull();

        Assertions.assertThat(amigoDTO.getWhatsApp()).isNull();
    }

    @Test
    @DisplayName("Criar AmigoEmprestimoDTO nao retorna null quando bem sucedido")
    public void criar_AmigoEmprestimoDTONaoRetornaNullQuandoBemSucedido() {

        AmigoEmprestimo amigoFull = AmigoEmprestimoCreator.criarAmigoEmprestimo();

        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(amigoFull);

        Assertions.assertThat(amigoDTO.getId()).isNotNull();

        Assertions.assertThat(amigoDTO.getNome()).isNotNull();

        Assertions.assertThat(amigoDTO.getEndereco()).isNotNull();

        Assertions.assertThat(amigoDTO.getWhatsApp()).isNotNull();
    }
}
