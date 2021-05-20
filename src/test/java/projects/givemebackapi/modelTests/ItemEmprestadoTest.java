package projects.givemebackapi.modelTests;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;

public class ItemEmprestadoTest {

    @Test
    public void criar_ItemEmprestadoVazioRetornaNullQuandoBemSucedido() {

        ItemEmprestado itemEmprestado = new ItemEmprestado();

        Assertions.assertThat(itemEmprestado.getIdItem()).isNull();

        Assertions.assertThat(itemEmprestado.getNomeItem()).isNull();

        Assertions.assertThat(itemEmprestado.getDataDevolucaoItem()).isNull();

        Assertions.assertThat(itemEmprestado.getDonoItem()).isNull();
    }

    @Test
    public void criar_ItemEmprestadoNaoRetornaNullQuandoBemSucedido() {

        DonoItem donoItem = new DonoItem(1, "Samuel", "996172418");

        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "Fulano do teste", "855789548", "rua da areia");

        ItemEmprestado item = new ItemEmprestado(1, "Bola de baskete", "Uma bola", donoItem,
                LocalDate.of(2022, 2, 15), amigoEmprestimo);

        Assertions.assertThat(item.getIdItem()).isNotNull();

        Assertions.assertThat(item.getNomeItem()).isNotNull();

        Assertions.assertThat(item.getDescricaoItem()).isNotNull();

        Assertions.assertThat(item.getDonoItem()).isNotNull();

        Assertions.assertThat(item.getStatus()).isNotNull();

        Assertions.assertThat(item.getAmigoEmprestimo()).isNotNull();

        Assertions.assertThat(item.getStatus()).isEqualTo(TipoStatus.EMPRESTADO);

    }

}
