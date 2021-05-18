package projects.givemebackapi.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class DonoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDono;

    
    @NotEmpty(message = "Campo NOME DONO é obrigatório!") 
    @Length(min = 3, max = 100, message = "Campo NOME DONO deve ter entre 3 e 100 caracteres")
    private String nomeDono;

    
    @NotEmpty(message = "Campo WHATSAPP é obrigatório!") 
    @Length(min = 3, max = 50, message = "Campo WHATSAPP deve ter entre 3 e 100 caracteres")
    private String whatsappDono;

    @OneToMany(mappedBy = "donoItem")  //um dono para muitos itens
    private List<ItemEmprestado> itensEmprestados;

    public DonoItem(Integer id, String nome, String whatsapp) {
        this.idDono = id;
        this.nomeDono = nome;
        this.whatsappDono = whatsapp;
        this.itensEmprestados = new LinkedList<>();
    }
 
    public DonoItem() {}
      
}
