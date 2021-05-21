package projects.givemebackapi.dtos;

import java.time.LocalDate;

import lombok.Data;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;

@Data
public class ItemEmprestadoDTO {
    
    private Integer id;
    private String nome;
    private String desc;
    private LocalDate dataDevolucao;
    private TipoStatus status;

    public ItemEmprestadoDTO(ItemEmprestado item) {
       this.id = item.getIdItem();
       this.nome = item.getNomeItem();
       this.desc = item.getDescricaoItem();
       this.dataDevolucao = item.getDataDevolucaoItem();
       this.status = item.getStatus();
    }

    public ItemEmprestadoDTO() {}
    
}
