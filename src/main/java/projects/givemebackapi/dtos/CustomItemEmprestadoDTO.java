package projects.givemebackapi.dtos;

import java.time.LocalDate;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public CustomAmigoEmprestimoDTO getEmprestado_para() {
        return emprestado_para;
    }

    public void setEmprestado_para(CustomAmigoEmprestimoDTO emprestado_para) {
        this.emprestado_para = emprestado_para;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomItemEmprestadoDTO other = (CustomItemEmprestadoDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }  

    
    
}
