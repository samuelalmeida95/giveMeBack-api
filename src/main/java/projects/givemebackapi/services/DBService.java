package projects.givemebackapi.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.repositories.DonoItemRepository;
import projects.givemebackapi.repositories.ItemEmprestadoRepository;

@Service
public class DBService {

    @Autowired
    private DonoItemRepository donoItemRepositorie;

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepositorie;


    public void instanciaBaseDeDados(){

        DonoItem donoItem = new DonoItem(null, "Samuel Almeida", "555-777-522");
        DonoItem donoItem2 = new DonoItem(null, "Will", "225-347-179");

        ItemEmprestado item1 = new ItemEmprestado(null, "Video Game", "um video game",TipoStatus.EMPRESTADO, donoItem);
        ItemEmprestado item2 = new ItemEmprestado(null, "Bicicleta", "uma bike", TipoStatus.EMPRESTADO, donoItem2);

        donoItem.getItensEmprestados().addAll(Arrays.asList(item1));
        donoItem2.getItensEmprestados().addAll(Arrays.asList(item2));

        donoItemRepositorie.saveAll(Arrays.asList(donoItem, donoItem2));

        itemEmprestadoRepositorie.saveAll(Arrays.asList(item1, item2));
    }
    
}
