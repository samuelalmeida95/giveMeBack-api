package projects.givemebackapi.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class DonoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDono;
    private String nomeDono;
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
