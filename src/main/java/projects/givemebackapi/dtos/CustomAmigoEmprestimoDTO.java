package projects.givemebackapi.dtos;

import projects.givemebackapi.model.AmigoEmprestimo;

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

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getWhatsApp() {
    return whatsApp;
  }

  public void setWhatsApp(String whatsApp) {
    this.whatsApp = whatsApp;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getAmigoDono() {
    return amigoDono;
  }

  public void setAmigoDono(String amigoDono) {
    this.amigoDono = amigoDono;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
    CustomAmigoEmprestimoDTO other = (CustomAmigoEmprestimoDTO) obj;
    if (nome == null) {
      if (other.nome != null)
        return false;
    } else if (!nome.equals(other.nome))
      return false;
    return true;
  }

  
}
