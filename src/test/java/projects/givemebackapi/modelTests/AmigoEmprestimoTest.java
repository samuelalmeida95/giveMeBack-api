package projects.givemebackapi.modelTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.model.AmigoEmprestimo;

public class AmigoEmprestimoTest {
    
    @Test
    void cria_AmigoEmprestimoRetornaNullQuandoBemSucedido() {

        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo();

        Assertions.assertThat(amigoEmprestimo.getId()).isNull();

        Assertions.assertThat(amigoEmprestimo.getNome()).isNull();

        Assertions.assertThat(amigoEmprestimo.getWhatsapp()).isNull();

        Assertions.assertThat(amigoEmprestimo.getEmail()).isNull();

        Assertions.assertThat(amigoEmprestimo.getInstagram()).isNull();

        Assertions.assertThat(amigoEmprestimo.getEndereco()).isNull();

        Assertions.assertThat(amigoEmprestimo.getItemEmprestado()).isNull();
    }

    @Test
    void cria_AmigoEmprestimoQuandoBemSucedido() {

        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "José", "987", "rua x" , "jose@br", "jose_bonito");

        Assertions.assertThat(amigoEmprestimo.getNome()).isNotNull();

        Assertions.assertThat(amigoEmprestimo.getNome()).isEqualTo("José");

        Assertions.assertThat(amigoEmprestimo.getItemEmprestado().getNomeItem()).isEqualTo("Meia");

    }

}
