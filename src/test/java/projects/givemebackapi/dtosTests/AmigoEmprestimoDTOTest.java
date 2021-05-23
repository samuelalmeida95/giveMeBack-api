package projects.givemebackapi.dtosTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.dtos.AmigoEmprestimoDTO;
import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.util.AmigoEmprestimoCreator;

public class AmigoEmprestimoDTOTest {
    
    @Test
    public void criar_AmigoDTOVazioRetornaNullQuandoBemSucedido() {

        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO();

        Assertions.assertThat(amigoDTO.getId()).isNull();

        Assertions.assertThat(amigoDTO.getNome()).isNull();

        Assertions.assertThat(amigoDTO.getEndereco()).isNull();

        Assertions.assertThat(amigoDTO.getWhatsApp()).isNull();
    }

    @Test
    public void criar_AmigoEmprestimoNaoRetornaNullQuandoBemSucedido() {

        AmigoEmprestimo amigoFull = AmigoEmprestimoCreator.criarAmigoEmprestimo();

        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(amigoFull);

        Assertions.assertThat(amigoDTO.getId()).isNotNull();

        Assertions.assertThat(amigoDTO.getNome()).isNotNull();

        Assertions.assertThat(amigoDTO.getEndereco()).isNotNull();

        Assertions.assertThat(amigoDTO.getWhatsApp()).isNotNull();
    }
}
