package projects.givemebackapi.dtosTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.dtos.DonoItemDTO;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.util.DonoItemCreator;

public class DonoItemDTOTest {
    
    @Test
    public void criar_DonoItemDTOVazioRetornaNullQuandoBemSucedido() {

        DonoItemDTO donoItem = new DonoItemDTO();

        Assertions.assertThat(donoItem.getId()).isNull();

        Assertions.assertThat(donoItem.getNome()).isNull();

        Assertions.assertThat(donoItem.getWhatsapp()).isNull();
    }

    @Test
    public void criar_DonoItemDTONaoRetornaNullQuandoBemSucedido() {

        DonoItem donoItem = DonoItemCreator.criarDonoItem();

        DonoItemDTO donoItemDTO = new DonoItemDTO(donoItem);

        Assertions.assertThat(donoItemDTO.getId()).isNotNull();

        Assertions.assertThat(donoItemDTO.getNome()).isNotNull();
        
        Assertions.assertThat(donoItemDTO.getWhatsapp()).isNotNull();
    }
}
