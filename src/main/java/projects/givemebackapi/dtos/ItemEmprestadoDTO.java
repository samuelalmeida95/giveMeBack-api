package projects.givemebackapi.dtos;

import java.time.LocalDate;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
public class ItemEmprestadoDTO {

    private Integer id;
    private String nome;
    private String nomeDono;
    private String descricao;
    private TipoStatus status;
    private String emprestado_para;
    private String prazoPadrao = "Cada emprestimo tem 20 dias de prazo.";
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public ItemEmprestadoDTO(ItemEmprestado item) {
        this.id = item.getIdItem();
        this.nome = item.getNomeItem();
        this.descricao = item.getDescricaoItem();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }

    public String getEmprestado_para() {
        return emprestado_para;
    }

    public void setEmprestado_para(String emprestado_para) {
        this.emprestado_para = emprestado_para;
    }

    public String getPrazoPadrao() {
        return prazoPadrao;
    }

    public void setPrazoPadrao(String prazoPadrao) {
        this.prazoPadrao = prazoPadrao;
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
        ItemEmprestadoDTO other = (ItemEmprestadoDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
