package projects.givemebackapi.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.AvaliacaoStatus;
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

        DonoItem donoItem = new DonoItem(null, "Samuel", "555-777-522");
        DonoItem donoItem1 = new DonoItem(null, "Ellon Musk", "666-999-323");
        DonoItem donoItem2 = new DonoItem(null, "Steve Jobs", "112-346-978");
    
        AmigoEmprestimo amigoEmprestimo = new AmigoEmprestimo(null, "José", "987", "rua do Jose", donoItem);
        AmigoEmprestimo amigoEmprestimo1 = new AmigoEmprestimo(null, "Pedro", "123", "rua do Pedro", donoItem2);
        AmigoEmprestimo amigoEmprestimo2 = new AmigoEmprestimo(null, "Marcos", "634", "avenida do Marcos", donoItem1);
        AmigoEmprestimo amigoEmprestimo3 = new AmigoEmprestimo(null, "Flavio", "023", "avenida do Flavio", donoItem);
        AmigoEmprestimo amigoEmprestimo4 = new AmigoEmprestimo(null, "Alexa", "819", "avenida da Alexa", donoItem2);
        AmigoEmprestimo amigoEmprestimo5 = new AmigoEmprestimo(null, "Ronaldo", "379", "avenida do Ronaldo", donoItem1);
        AmigoEmprestimo amigoEmprestimo6 = new AmigoEmprestimo(null, "Robbin", "951", "rua do Robbin", donoItem2);

        ItemEmprestado itemEmprestado = new ItemEmprestado(null, "video game", "um video game", donoItem , amigoEmprestimo);
        ItemEmprestado itemEmprestado1 = new ItemEmprestado(null, "relogio", "um relogio", donoItem2 , amigoEmprestimo1);
        ItemEmprestado itemEmprestado2 = new ItemEmprestado(null, "tenis", "um tenis", donoItem1 , amigoEmprestimo2);

        ItemEmprestado itemEmprestado3 = new ItemEmprestado(null, "bone", "um bone", donoItem1 , amigoEmprestimo2);
        ItemEmprestado itemEmprestado4 = new ItemEmprestado(null, "pipa", "uma pipa", donoItem1 , amigoEmprestimo2);
        ItemEmprestado itemEmprestado5 = new ItemEmprestado(null, "caneta", "uma caneta", donoItem1 , amigoEmprestimo2);

        amigoEmprestimo.setAvaliacao(AvaliacaoStatus.OTIMA);
        amigoEmprestimo1.setAvaliacao(AvaliacaoStatus.OTIMA);
        amigoEmprestimo2.setAvaliacao(AvaliacaoStatus.BOA);
        amigoEmprestimo3.setAvaliacao(AvaliacaoStatus.PESSIMA);
        amigoEmprestimo4.setAvaliacao(AvaliacaoStatus.RUIM);
        amigoEmprestimo5.setAvaliacao(AvaliacaoStatus.PESSIMA);
        amigoEmprestimo6.setAvaliacao(AvaliacaoStatus.BOA);

        donoItem
        .getItensEmprestados()
        .addAll(Arrays
        .asList(itemEmprestado, 
                itemEmprestado1, 
                itemEmprestado2));

        amigoEmprestimoRepository
        .saveAll(Arrays
        .asList(amigoEmprestimo, 
               amigoEmprestimo1, 
               amigoEmprestimo2,
               amigoEmprestimo3, 
               amigoEmprestimo4, 
               amigoEmprestimo5, 
               amigoEmprestimo6));

        donoItemRepositorie
        .saveAll(Arrays
        .asList(donoItem, 
               donoItem1, 
               donoItem2));

        itemEmprestadoRepositorie
        .saveAll(Arrays
        .asList(itemEmprestado, 
                itemEmprestado1, 
                itemEmprestado2,
                itemEmprestado3, 
                itemEmprestado4, 
                itemEmprestado5));
       
        
    }
    
}
