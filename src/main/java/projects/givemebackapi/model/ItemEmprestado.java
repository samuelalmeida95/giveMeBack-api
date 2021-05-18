package projects.givemebackapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class ItemEmprestado {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;
    
    private String nomeItem;
    private String descricaoItem;
    private Date dataDevolucaoItem;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="donoItem_id")
    private DonoItem donoItem;
    
    
    public ItemEmprestado(Integer idItem, String nomeItem, String descricaoItem, DonoItem donoItem) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.donoItem = donoItem;
    }

    public ItemEmprestado(){}
}
