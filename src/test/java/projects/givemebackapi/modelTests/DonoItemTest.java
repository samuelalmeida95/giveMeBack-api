package projects.givemebackapi.modelTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.model.DonoItem;

public class DonoItemTest {
    
    @Test
    public void criar_DonoItemVazioRetornaNullQuandoBemSucedido() {

        DonoItem item = new DonoItem();

        Assertions.assertThat(item.getId()).isNull();

        Assertions.assertThat(item.getNome()).isNull();

        Assertions.assertThat(item.getWhatsapp()).isNull();

        Assertions.assertThat(item.getItensEmprestados()).isNull();
    }

    @Test
    public void criar_DonoItemNaoRetornaNullQuandoBemSucedido() {

        DonoItem item = new DonoItem(1, "Samuel", "996172418");

        Assertions.assertThat(item.getId()).isNotNull();

        Assertions.assertThat(item.getNome()).isNotNull();
        
        Assertions.assertThat(item.getWhatsapp()).isNotNull();

        Assertions.assertThat(item.getItensEmprestados()).isNotNull();
    }
}
