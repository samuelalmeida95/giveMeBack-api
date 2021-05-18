package projects.givemebackapi.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.repositories.DonoItemRepositorie;
import projects.givemebackapi.repositories.ItemEmprestadoRepositorie;

@Service
public class DBService {

    @Autowired
    private DonoItemRepositorie donoItemRepositorie;

    @Autowired
    private ItemEmprestadoRepositorie itemEmprestadoRepositorie;


    public void instanciaBaseDeDados(){

        DonoItem donoItem = new DonoItem(null, "Samuel Almeida", "555-777-522");
        DonoItem donoItem2 = new DonoItem(null, "Will", "225-347-179");

        ItemEmprestado item1 = new ItemEmprestado(null, "Video Game", "um video game", donoItem);
        ItemEmprestado item2 = new ItemEmprestado(null, "Bicicleta", "uma bike", donoItem2);

        donoItem.getItensEmprestados().addAll(Arrays.asList(item1));
        donoItem2.getItensEmprestados().addAll(Arrays.asList(item2));

        donoItemRepositorie.saveAll(Arrays.asList(donoItem, donoItem2));

        itemEmprestadoRepositorie.saveAll(Arrays.asList(item1, item2));
    }
    
}