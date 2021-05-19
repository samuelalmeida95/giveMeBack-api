package projects.givemebackapi.modelTests;

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
            
            AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "Fulano do teste", "887513","887513","887513","887513");

            ItemEmprestado item = new ItemEmprestado(1, "Bola de baskete", "Uma bola", TipoStatus.EMPRESTADO, donoItem, amigoEmprestimo);
    
            Assertions.assertThat(item.getIdItem()).isNotNull();
    
            Assertions.assertThat(item.getNomeItem()).isNotNull();
            
            Assertions.assertThat(item.getDescricaoItem()).isNotNull();

            Assertions.assertThat(item.getDonoItem()).isNotNull();
            
            Assertions.assertThat(item.getStatus()).isNotNull();

            Assertions.assertThat(item.getAmigoEmprestimo()).isNotNull();

            Assertions.assertThat(item.getStatus()).isEqualTo(TipoStatus.EMPRESTADO);
    
        }

    }

