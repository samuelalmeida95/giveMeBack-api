package projects.givemebackapi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Length;
@Entity
public class ItemEmprestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @NotEmpty(message = "Campo NOME é obrigatório!")
    @Length(min = 3, max = 100, message = "Campo NOME ITEM deve ter entre 3 e 100 caracteres")
    private String nomeItem;

    @NotEmpty(message = "Campo DESCRIÇÃO é obrigatório!")
    @Length(min = 3, max = 100, message = "Campo DESCRIÇÃO deve ter entre 3 e 100 caracteres")
    private String descricaoItem;

    private LocalDate dataDevolucaoItem;

    private LocalDate dataEmprestimoItem;

    @Enumerated(value = EnumType.STRING)
    private TipoStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "donoItem_id")
    private DonoItem donoItem;

    @ManyToOne
    @JoinColumn(name = "amigoEmprestimo_id")
    private AmigoEmprestimo amigoEmprestimo;

    public ItemEmprestado(Integer id, String nome, String descricao, DonoItem dono,
            AmigoEmprestimo amigoEmp) {

        this.idItem = id;
        this.nomeItem = nome;
        this.descricaoItem = descricao;
        this.dataEmprestimoItem = LocalDate.now();
        this.dataDevolucaoItem = LocalDate.now().plusDays(20);
        this.status = TipoStatus.EMPRESTADO;
        this.donoItem = dono;
        this.amigoEmprestimo = amigoEmp;
    }

    public ItemEmprestado() {
    }

    public void update(ItemEmprestado novoItemEmprestado) {
        this.nomeItem = novoItemEmprestado.nomeItem;
        this.descricaoItem = novoItemEmprestado.descricaoItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public LocalDate getDataDevolucaoItem() {
        return dataDevolucaoItem;
    }

    public void setDataDevolucaoItem(LocalDate dataDevolucaoItem) {
        this.dataDevolucaoItem = dataDevolucaoItem;
    }

    public LocalDate getDataEmprestimoItem() {
        return dataEmprestimoItem;
    }

    public void setDataEmprestimoItem(LocalDate dataEmprestimoItem) {
        this.dataEmprestimoItem = dataEmprestimoItem;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }

    public DonoItem getDonoItem() {
        return donoItem;
    }

    public void setDonoItem(DonoItem donoItem) {
        this.donoItem = donoItem;
    }

    public AmigoEmprestimo getAmigoEmprestimo() {
        return amigoEmprestimo;
    }

    public void setAmigoEmprestimo(AmigoEmprestimo amigoEmprestimo) {
        this.amigoEmprestimo = amigoEmprestimo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((donoItem == null) ? 0 : donoItem.hashCode());
        result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
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
        ItemEmprestado other = (ItemEmprestado) obj;
        if (donoItem == null) {
            if (other.donoItem != null)
                return false;
        } else if (!donoItem.equals(other.donoItem))
            return false;
        if (idItem == null) {
            if (other.idItem != null)
                return false;
        } else if (!idItem.equals(other.idItem))
            return false;
        return true;
    }

    
}
