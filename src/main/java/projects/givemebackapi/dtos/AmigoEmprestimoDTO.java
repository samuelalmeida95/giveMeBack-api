package projects.givemebackapi.dtos;

import lombok.Data;
import projects.givemebackapi.model.AmigoEmprestimo;

@Data
public class AmigoEmprestimoDTO {
    
    private Integer id;
    private String nome;
    private String whatsApp;
    private String endereco;

    public AmigoEmprestimoDTO(AmigoEmprestimo amigo) {
      this.id = amigo.getId();
      this.nome = amigo.getNome();
      this.whatsApp = amigo.getWhatsapp();  
      this.endereco = amigo.getEndereco();
    }

    public AmigoEmprestimoDTO(){}    
}