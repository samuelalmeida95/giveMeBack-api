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

import lombok.Data;

@Data
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
    this.avaliacao = AvaliacaoStatus.NAO_AVALIADO;
  }

  public AmigoEmprestimo() {
  }
}
