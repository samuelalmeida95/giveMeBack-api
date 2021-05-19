package projects.givemebackapi.modelTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.model.DonoItem;

public class DonoItemTest {
    
    @Test
    public void criar_DonoItemVazioRetornaNullQuandoBemSucedido() {

        DonoItem item = new DonoItem();

        Assertions.assertThat(item.getIdDono()).isNull();

        Assertions.assertThat(item.getNomeDono()).isNull();

        Assertions.assertThat(item.getWhatsappDono()).isNull();

        Assertions.assertThat(item.getItensEmprestados()).isNotNull();

        Assertions.assertThat(item.getItensEmprestados()).size().isEqualTo(0);

    }

    @Test
    public void criar_DonoItemNaoRetornaNullQuandoBemSucedido() {

        DonoItem item = new DonoItem(1, "Samuel", "996172418");

        Assertions.assertThat(item.getIdDono()).isNotNull();

        Assertions.assertThat(item.getNomeDono()).isNotNull();
        
        Assertions.assertThat(item.getWhatsappDono()).isNotNull();

        Assertions.assertThat(item.getItensEmprestados()).isNotNull();

        Assertions.assertThat(item.getItensEmprestados()).size().isEqualTo(0);
    }
}
