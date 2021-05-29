package projects.givemebackapi.dtos;

import lombok.Data;
import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.AvaliacaoStatus;

@Data
public class AmigoEmprestimoDTO {

  private Integer id;
  private String nome;
  private String whatsApp;
  private String endereco;
  private String amigoDono;
  private AvaliacaoStatus avaliacao;

  public AmigoEmprestimoDTO(AmigoEmprestimo amigo) {
    this.id = amigo.getId();
    this.nome = amigo.getNome();
    this.whatsApp = amigo.getWhatsapp();
    this.endereco = amigo.getEndereco();
    this.amigoDono = amigo.getDonoItem().getNome();
    this.avaliacao = amigo.getAvaliacao();
  }

  public AmigoEmprestimoDTO() {}
}
