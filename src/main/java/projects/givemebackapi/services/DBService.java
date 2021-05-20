package projects.givemebackapi.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.repositories.AmigoEmprestimoRepository;
import projects.givemebackapi.repositories.DonoItemRepository;
import projects.givemebackapi.repositories.ItemEmprestadoRepository;

@Service
public class DBService {

    @Autowired
    private DonoItemRepository donoItemRepositorie;

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepositorie;

    @Autowired
    private AmigoEmprestimoRepository amigoEmprestimoRepository;


    public void instanciaBaseDeDados(){

        DonoItem donoItem = new DonoItem(null, "Samuel Almeida", "555-777-522");
        DonoItem donoItem1 = new DonoItem(null, "Ellon Musk", "666-999-323");
        DonoItem donoItem2 = new DonoItem(null, "Steve Jobs", "112-346-978");
    
        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "José", "987", "rua do Jose");
        AmigoEmprestimo amigoEmprestimo1 = new AmigoEmprestimo(null, "Pedro", "987", "rua do Pedro");
        AmigoEmprestimo amigoEmprestimo2 = new AmigoEmprestimo(null, "João", "987", "avenida do João");

        ItemEmprestado itemEmprestado = new ItemEmprestado(null, "video game", "um video game", donoItem ,LocalDate.of(2022, 2, 15), amigoEmprestimo);
        ItemEmprestado itemEmprestado1 = new ItemEmprestado(null, "relogio", "um relogio", donoItem2 ,LocalDate.of(2022, 2, 15), amigoEmprestimo1);
        ItemEmprestado itemEmprestado2 = new ItemEmprestado(null, "tenis", "um tenis", donoItem1 ,LocalDate.of(2022, 2, 15), amigoEmprestimo2);

        ItemEmprestado itemEmprestado3 = new ItemEmprestado(null, "bone", "um bone", donoItem1 ,LocalDate.of(2022, 2, 15), amigoEmprestimo1);
        ItemEmprestado itemEmprestado4 = new ItemEmprestado(null, "pipa", "uma pipa", donoItem1 ,LocalDate.of(2022, 2, 15), amigoEmprestimo2);
        ItemEmprestado itemEmprestado5 = new ItemEmprestado(null, "caneta", "uma caneta", donoItem1 ,LocalDate.of(2022, 2, 15), amigoEmprestimo2);


        donoItem.getItensEmprestados().addAll(Arrays.asList(itemEmprestado, itemEmprestado1, itemEmprestado2));
       
        amigoEmprestimoRepository.saveAll(Arrays.asList(amigoEmprestimo, amigoEmprestimo1, amigoEmprestimo2));

        donoItemRepositorie.saveAll(Arrays.asList(donoItem,donoItem1,donoItem2));
        
        itemEmprestadoRepositorie.saveAll(Arrays.asList(itemEmprestado, itemEmprestado1, itemEmprestado2, itemEmprestado3, itemEmprestado4, itemEmprestado5));
       
        
    }
    
}
