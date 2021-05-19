package projects.givemebackapi.model;

import java.util.Date;

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

import lombok.Data;

@Data
@Entity
public class ItemEmprestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;
    
    @NotEmpty(message = "Campo NOME ITEM é obrigatório!") 
    @Length(min = 3, max = 100, message = "Campo NOME ITEM deve ter entre 3 e 100 caracteres")
    private String nomeItem;

    @NotEmpty(message = "Campo DESCRIÇÃO é obrigatório!") 
    @Length(min = 3, max = 100, message = "Campo DESCRIÇÃO deve ter entre 3 e 100 caracteres")
    private String descricaoItem;
    
    private Date dataDevolucaoItem;

    @Enumerated(value = EnumType.STRING)
    private TipoStatus status;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "donoItem_id")
    private DonoItem donoItem;
    
    
    public ItemEmprestado(Integer idItem, String nomeItem, String descricaoItem, TipoStatus status, DonoItem donoItem) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.status = status;
        this.donoItem = donoItem;
    }

    public ItemEmprestado(){}
}
