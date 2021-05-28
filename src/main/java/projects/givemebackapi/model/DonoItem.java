package projects.givemebackapi.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
public class DonoItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "Campo NOME é obrigatório!")
  @Length(
    min = 3,
    max = 100,
    message = "Campo NOME  deve ter entre 3 e 100 caracteres"
  )
  private String nome;

  @NotEmpty(message = "Campo WHATSAPP é obrigatório!")
  @Length(
    min = 3,
    max = 50,
    message = "Campo WHATSAPP deve ter entre 3 e 100 caracteres"
  )
  private String whatsapp;

  @OneToMany(mappedBy = "donoItem") // um dono para muitos itens
  private List<ItemEmprestado> itensEmprestados = new LinkedList<>();

  public DonoItem(Integer id, String nome, String whatsapp) {
    this.id = id;
    this.nome = nome;
    this.whatsapp = whatsapp;
  }

  public DonoItem() {}
}
