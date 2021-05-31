package projects.givemebackapi.dtos;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.AvaliacaoStatus;

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

  public AmigoEmprestimoDTO() {
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

  public AvaliacaoStatus getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(AvaliacaoStatus avaliacao) {
    this.avaliacao = avaliacao;
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
    AmigoEmprestimoDTO other = (AmigoEmprestimoDTO) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
