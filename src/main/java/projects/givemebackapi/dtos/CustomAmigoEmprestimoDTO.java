package projects.givemebackapi.dtos;

import lombok.Data;
import projects.givemebackapi.model.AmigoEmprestimo;

@Data
public class CustomAmigoEmprestimoDTO {

  private String nome;
  private String whatsApp;
  private String endereco;

  public CustomAmigoEmprestimoDTO(AmigoEmprestimo amigo) {
    this.nome = amigo.getNome();
    this.whatsApp = amigo.getWhatsapp();
    this.endereco = amigo.getEndereco();
  }

  public CustomAmigoEmprestimoDTO() {}
}
