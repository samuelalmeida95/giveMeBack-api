package projects.givemebackapi.modelTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.model.TipoStatus;

public class TipoStatusTest {
    
    @Test
    void chamar_TipoStatusQuandoBemSucedido(){
      TipoStatus  testeDevolvido =  TipoStatus.DEVOLVIDO;

      TipoStatus  testeEmprestado =  TipoStatus.EMPRESTADO;

      Assertions.assertThat(testeDevolvido).isNotNull();

      Assertions.assertThat(testeEmprestado).isNotNull();

      Assertions.assertThat(testeDevolvido).isEqualTo(TipoStatus.DEVOLVIDO);

      Assertions.assertThat(testeEmprestado).isEqualTo(TipoStatus.EMPRESTADO);

      Assertions.assertThat(testeDevolvido).isNotEqualTo(TipoStatus.EMPRESTADO);
    }
}
