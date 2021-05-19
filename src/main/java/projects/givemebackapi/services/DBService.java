package projects.givemebackapi.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
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
        DonoItem donoItem1 = new DonoItem(null, "Ragnar", "666-999-323");
        DonoItem donoItem2 = new DonoItem(null, "Ronaldinha", "112-346-978");
    
        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "José", "987", "rua x" , "jose@br", "jose_bonito");
        AmigoEmprestimo amigoEmprestimo1 = new AmigoEmprestimo(null, "pedro", "987", "rua x" , "pedro@br", "pedro_bonito");
        AmigoEmprestimo amigoEmprestimo2 = new AmigoEmprestimo(null, "joao", "987", "rua x" , "joao@br", "joao_bonito");

        ItemEmprestado itemEmprestado = new ItemEmprestado(null, "Video Game", "um video game",TipoStatus.EMPRESTADO, donoItem , amigoEmprestimo);
        ItemEmprestado itemEmprestado1 = new ItemEmprestado(null, "Relogio", "um relogio",TipoStatus.EMPRESTADO, donoItem2 , amigoEmprestimo1);
        ItemEmprestado itemEmprestado2 = new ItemEmprestado(null, "tenis", "um tenis",TipoStatus.EMPRESTADO, donoItem1 , amigoEmprestimo2);

        ItemEmprestado itemEmprestado3 = new ItemEmprestado(null, "bone", "um bone",TipoStatus.EMPRESTADO, donoItem1 , amigoEmprestimo2);
        ItemEmprestado itemEmprestado4 = new ItemEmprestado(null, "pipa", "uma pipa",TipoStatus.EMPRESTADO, donoItem1 , amigoEmprestimo2);
        ItemEmprestado itemEmprestado5 = new ItemEmprestado(null, "caneta", "uma caneta",TipoStatus.EMPRESTADO, donoItem1 , amigoEmprestimo2);


        donoItem.getItensEmprestados().addAll(Arrays.asList(itemEmprestado, itemEmprestado1, itemEmprestado2));
       
        amigoEmprestimoRepository.saveAll(Arrays.asList(amigoEmprestimo, amigoEmprestimo1, amigoEmprestimo2));

        donoItemRepositorie.saveAll(Arrays.asList(donoItem,donoItem1,donoItem2));
        
        itemEmprestadoRepositorie.saveAll(Arrays.asList(itemEmprestado, itemEmprestado1, itemEmprestado2, itemEmprestado3, itemEmprestado4, itemEmprestado5));
       
        
    }
    
}
