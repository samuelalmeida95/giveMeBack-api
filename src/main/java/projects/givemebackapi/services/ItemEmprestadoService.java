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

    public ItemEmprestado findById(Integer idItemEmprestado) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItemEmprestado);

        return itemOptional.orElseThrow(() -> new RuntimeException(
                "Item não encontrado! " + idItemEmprestado + " Tipo: " + ItemEmprestado.class.getName()));
    }

    public ItemEmprestado findByNome(String nomeItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findByNomeItem(nomeItem);

        if (!itemOptional.isPresent())
            throw new RuntimeException("Item não encontrado! " + nomeItem + " Tipo: " + ItemEmprestado.class.getName());
        

        ItemEmprestado itemEmprestadoEncontrado = itemOptional.get();
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

    public ItemEmprestado update(Integer id, ItemEmprestado novoItemEmprestado) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(id);

        if (!itemOptional.isPresent()) 
            throw new RuntimeException(
                    "ItemEmprestado não encontrado! Id: " + id + ", Tipo: " + ItemEmprestado.class.getName());
        
        ItemEmprestado itemAtualizado = itemOptional.get();
        itemAtualizado.update(novoItemEmprestado);
        return this.itemEmprestadoRepository.save(itemAtualizado);
    }

    public ItemEmprestado emprestarItem(ItemEmprestado itemEmprestado, Integer id, Integer idAmigoEmprestimo) {
        DonoItem dono = donoItemService.findById(id);
        AmigoEmprestimo amigo = amigoEmprestimoService.findById(idAmigoEmprestimo);

        itemEmprestado.setAmigoEmprestimo(amigo);
        itemEmprestado.setDonoItem(dono);
        itemEmprestado.setStatus(TipoStatus.EMPRESTADO);

        return itemEmprestadoRepository.save(itemEmprestado);
    }

    public ItemEmprestado devolver(Integer idItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        if (!itemOptional.isPresent())
            throw new RuntimeException(
                    "ItemEmprestado não encontrado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado itemEmprestado = itemOptional.get();
        itemEmprestado.setStatus(TipoStatus.DEVOLVIDO);
        itemEmprestado.setAmigoEmprestimo(null);
        return this.itemEmprestadoRepository.save(itemEmprestado);
    }

    public ItemEmprestado giveInAgain(Integer id, Integer idAmigoEmprestimo) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(id);
        AmigoEmprestimo amigoEncontrado = amigoEmprestimoService.findById(idAmigoEmprestimo);

        if (!itemOptional.isPresent())
            throw new RuntimeException(
                    "ItemEmprestado não encontrado! Id: " + id + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado itemAtualizado = itemOptional.get();
        itemAtualizado.setAmigoEmprestimo(amigoEncontrado);
        itemAtualizado.setStatus(TipoStatus.EMPRESTADO);
        return this.itemEmprestadoRepository.save(itemAtualizado);
    }
}
