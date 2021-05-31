package projects.givemebackapi.dtos;

import java.time.LocalDate;

import lombok.Data;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;

@Data
public class CustomItemEmprestadoDTO {
    
    private Integer id;
    private String nome;
    private TipoStatus status;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private CustomAmigoEmprestimoDTO emprestado_para;

    public CustomItemEmprestadoDTO(ItemEmprestado item) {
       this.id = item.getIdItem();
       this.nome = item.getNomeItem();
       this.dataEmprestimo = item.getDataEmprestimoItem();
       this.dataDevolucao = item.getDataDevolucaoItem();
       this.status = item.getStatus();
       this.emprestado_para = new CustomAmigoEmprestimoDTO(item.getAmigoEmprestimo()); 
    }

    public CustomItemEmprestadoDTO() {}  
}
