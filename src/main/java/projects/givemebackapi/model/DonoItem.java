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

@Entity
public class DonoItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "Campo NOME é obrigatório!")
  @Length(min = 3, max = 100, message = "Campo NOME  deve ter entre 3 e 100 caracteres")
  private String nome;

  @NotEmpty(message = "Campo WHATSAPP é obrigatório!")
  @Length(min = 3, max = 50, message = "Campo WHATSAPP deve ter entre 3 e 100 caracteres")
  private String whatsapp;

  @OneToMany(mappedBy = "donoItem") // um dono para muitos itens
  private List<ItemEmprestado> itensEmprestados = new LinkedList<>();

  @OneToMany // um dono para muitos amigos
  private List<AmigoEmprestimo> amigosEmprestimo = new LinkedList<>();

  public DonoItem(Integer id, String nome, String whatsapp) {
    this.id = id;
    this.nome = nome;
    this.whatsapp = whatsapp;
  }

  public DonoItem() {
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

  public String getWhatsapp() {
    return whatsapp;
  }

  public void setWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
  }

  public List<ItemEmprestado> getItensEmprestados() {
    return itensEmprestados;
  }

  public void setItensEmprestados(List<ItemEmprestado> itensEmprestados) {
    this.itensEmprestados = itensEmprestados;
  }

  public List<AmigoEmprestimo> getAmigosEmprestimo() {
    return amigosEmprestimo;
  }

  public void setAmigosEmprestimo(List<AmigoEmprestimo> amigosEmprestimo) {
    this.amigosEmprestimo = amigosEmprestimo;
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
    DonoItem other = (DonoItem) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  
}
