package projects.givemebackapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
@Entity
public class AmigoEmprestimo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "Campo NOME  é obrigatório!")
  @Length(min = 3, max = 100, message = "Campo NOME  deve ter entre 3 e 100 caracteres")
  private String nome;

  @NotEmpty(message = "Campo WHATSAPP é obrigatório!")
  @Length(min = 3, max = 20, message = "Campo WHATSAPP deve ter entre 3 e 100 caracteres")
  private String whatsapp;

  @NotEmpty(message = "Campo ENREDECO é obrigatório!")
  @Length(min = 3, max = 100, message = "Campo ENDEREÇO deve ter entre 3 e 100 caracteres")
  private String endereco;

  @Enumerated(value = EnumType.STRING)
  private AvaliacaoStatus avaliacao;

  
  @ManyToOne(cascade=CascadeType.PERSIST)
  @JoinColumn
  private DonoItem donoItem;

  public AmigoEmprestimo(Integer id, String nome, String whatsapp, String endereco, DonoItem donoItem) {
    this.id = id;
    this.nome = nome;
    this.whatsapp = whatsapp;
    this.endereco = endereco;
    this.donoItem = donoItem;
    this.avaliacao = AvaliacaoStatus.NAO_AVALIADO; //padrao novo amigo nao é avaliado.
  }

  public AmigoEmprestimo() {
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

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public AvaliacaoStatus getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(AvaliacaoStatus avaliacao) {
    this.avaliacao = avaliacao;
  }

  public DonoItem getDonoItem() {
    return donoItem;
  }

  public void setDonoItem(DonoItem donoItem) {
    this.donoItem = donoItem;
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
    AmigoEmprestimo other = (AmigoEmprestimo) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  
  
}
