package projects.givemebackapi.dtos;

import lombok.Data;
import projects.givemebackapi.model.AmigoEmprestimo;

@Data
public class CustomAmigoEmprestimoDTO {

  private String nome;
  private String whatsApp;
  private String endereco;
  private String amigoDono;

  public CustomAmigoEmprestimoDTO(AmigoEmprestimo amigo) {
    this.nome = amigo.getNome();
    this.whatsApp = amigo.getWhatsapp();
    this.endereco = amigo.getEndereco();
    this.amigoDono = amigo.getDonoItem().getNome();
  }

  public CustomAmigoEmprestimoDTO() {}
}
