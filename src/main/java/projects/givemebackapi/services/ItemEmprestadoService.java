package projects.givemebackapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.repositories.ItemEmprestadoRepository;

@Service
public class ItemEmprestadoService {

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepository;

    @Autowired
    private DonoItemService donoItemService;

    @Autowired
    private AmigoEmprestimoService amigoEmprestimoService;

    public ItemEmprestado findById(Integer idItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        return itemOptional.orElseThrow(() -> new RuntimeException(
                "Item não encontrado! " + idItem + " Tipo: " + ItemEmprestado.class.getName()));
    }

    public ItemEmprestado findByNome(String nomeItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findByNomeItem(nomeItem);

        if (!itemOptional.isPresent())
            throw new RuntimeException(
                  "Item não encontrado! " + nomeItem + " Tipo: " + ItemEmprestado.class.getName());
        
        ItemEmprestado itemEmprestadoEncontrado = itemOptional.get();
        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByEmprestadoPara(Integer idAmigo) {
        Optional<List<ItemEmprestado>> itemOptional = itemEmprestadoRepository.findByAmigoEmprestimoId(idAmigo);

        if (!itemOptional.isPresent())
            throw new RuntimeException(
                  "Amigo: " + idAmigo + ", não encontrado,  Tipo: " + ItemEmprestado.class.getName());
        
        List<ItemEmprestado> itemEmprestadoEncontrado = itemOptional.get();
        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByDono(Integer idDono) {
        Optional<List<ItemEmprestado>> itemOptional = itemEmprestadoRepository.findByDonoItemId(idDono);

        if (!itemOptional.isPresent())
            throw new RuntimeException(
                  "Dono: " + idDono + ", não encontrado,  Tipo: " + ItemEmprestado.class.getName());
        
        List<ItemEmprestado> itemEmprestadoEncontrado = itemOptional.get();
        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByStatus(TipoStatus status) {
        List<ItemEmprestado> itensDevolvidos = itemEmprestadoRepository.findByStatus(status);

        if (itensDevolvidos.isEmpty()) {
            throw new RuntimeException(
                  "Não existem itens com o status: " + status + " , Tipo: " + ItemEmprestado.class.getName());
        }

        return itensDevolvidos;
    }

    public List<ItemEmprestado> findAll() {
        return itemEmprestadoRepository.findAll();
    }

    public ItemEmprestado update(Integer id, ItemEmprestado novoItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(id);

        if (!itemOptional.isPresent()) 
            throw new RuntimeException(
                   "Item Emprestado não encontrado! Id: " + id + ", Tipo: " + ItemEmprestado.class.getName());
        
        ItemEmprestado itemAtualizado = itemOptional.get();
        itemAtualizado.update(novoItem);
        return this.itemEmprestadoRepository.save(itemAtualizado);
    }

    public ItemEmprestado emprestarItem(ItemEmprestado item, Integer id, Integer idAmigo) {
        DonoItem dono = donoItemService.findById(id);
        AmigoEmprestimo amigo = amigoEmprestimoService.findById(idAmigo);

        item.setAmigoEmprestimo(amigo);
        item.setDonoItem(dono);
        item.setStatus(TipoStatus.EMPRESTADO);

        return itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado devolver(Integer idItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        if (!itemOptional.isPresent())
            throw new RuntimeException(
                   "ItemEmprestado não encontrado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado item = itemOptional.get();
        item.setStatus(TipoStatus.DEVOLVIDO);
        item.setAmigoEmprestimo(null);
        return this.itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado giveInAgain(Integer idItem, Integer idAmigo) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);
        AmigoEmprestimo amigoEncontrado = amigoEmprestimoService.findById(idAmigo);

        if (!itemOptional.isPresent())
            throw new RuntimeException(
                   "ItemEmprestado não encontrado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado itemAtualizado = itemOptional.get();
        itemAtualizado.setAmigoEmprestimo(amigoEncontrado);
        itemAtualizado.setStatus(TipoStatus.EMPRESTADO);
        return this.itemEmprestadoRepository.save(itemAtualizado);
    }
}
