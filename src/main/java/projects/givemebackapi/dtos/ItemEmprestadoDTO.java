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
    private TipoStatus status;
    private Calendar dataEmprestimo;
    private Calendar dataDevolucao;

    public ItemEmprestadoDTO(ItemEmprestado item) {
       this.id = item.getIdItem();
       this.nome = item.getNomeItem();
       this.desc = item.getDescricaoItem();
       this.status = item.getStatus();
       this.dataEmprestimo = item.getDataEmprestimoItem();
       this.dataDevolucao = item.getDataDevolucaoItem();
    }

    public ItemEmprestadoDTO() {}
    
}
