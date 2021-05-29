package projects.givemebackapi.dtos;

import java.util.Calendar;

import lombok.Data;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;

@Data
public class ItemEmprestadoDTO {

    private Integer id;
    private String nome;
    private String desc;
    private String nomeDono;
    private TipoStatus status;
    private String emprestado_para;
    private Calendar dataEmprestimo;
    private Calendar dataDevolucao;

    public ItemEmprestadoDTO(ItemEmprestado item) {
        this.id = item.getIdItem();
        this.nome = item.getNomeItem();
        this.desc = item.getDescricaoItem();
        this.status = item.getStatus();
        this.nomeDono = item.getDonoItem().getNome();
        this.dataEmprestimo = item.getDataEmprestimoItem();
        this.dataDevolucao = item.getDataDevolucaoItem();

        if (item.getAmigoEmprestimo() == null)
            this.emprestado_para = "ninguém";
        else
            this.emprestado_para = item.getAmigoEmprestimo().getNome();

    }

    public ItemEmprestadoDTO() {
    }

}
