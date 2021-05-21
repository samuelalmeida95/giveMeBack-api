package projects.givemebackapi.dtos;

import lombok.Data;
import projects.givemebackapi.model.DonoItem;

@Data
public class DonoItemDTO {
    
    private Integer id;
    private String nome;
    private String whatsapp;

    public DonoItemDTO(DonoItem dono) {
        this.id = dono.getId();
        this.nome = dono.getNome();
        this.whatsapp = dono.getWhatsapp();
    }

    public DonoItemDTO() {}   
}
