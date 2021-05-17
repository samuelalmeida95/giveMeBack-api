package projects.givemebackapi.model;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class DonoItem {

    private Integer id;
    private String nome;
    private String whatsapp;

    private List<ItemEmprestado> itensEmprestados;

    public DonoItem(Integer id, String nome, String whatsapp) {
        this.id = id;
        this.nome = nome;
        this.whatsapp = whatsapp;
        this.itensEmprestados = new LinkedList<>();
    }

    public DonoItem() {}
      

}
