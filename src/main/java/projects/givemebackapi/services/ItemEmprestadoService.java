package projects.givemebackapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.repositories.ItemEmprestadoRepository;
import projects.givemebackapi.services.exceptions.ObjectAlreadyExistsException;
import projects.givemebackapi.services.exceptions.ObjectNotFoundException;

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

        return itemOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Item não encontrado! " + idItem + " Tipo: " + ItemEmprestado.class.getName()));
    }

    public ItemEmprestado findByNome(String nomeItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findByNomeItem(nomeItem);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "Item não encontrado! " + nomeItem + " Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado itemEmprestadoEncontrado = itemOptional.get();
        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByEmprestadoPara(Integer idAmigo) {
        Optional<List<ItemEmprestado>> itemOptional = itemEmprestadoRepository.findByAmigoEmprestimoId(idAmigo);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "Amigo: " + idAmigo + ", não encontrado,  Tipo: " + ItemEmprestado.class.getName());
        
        amigoEmprestimoService.findById(idAmigo);

        List<ItemEmprestado> itemEmprestadoEncontrado = itemOptional.get();
        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByDono(Integer idDono) {
        Optional<List<ItemEmprestado>> itemOptional = itemEmprestadoRepository.findByDonoItemId(idDono);

        donoItemService.findById(idDono);

        List<ItemEmprestado> itemEmprestadoEncontrado = itemOptional.get();
        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByStatus(TipoStatus status) {
        List<ItemEmprestado> itensDevolvidos = itemEmprestadoRepository.findByStatus(status);

        if (itensDevolvidos.isEmpty())
            throw new ObjectNotFoundException(
                    "Não existem itens com o status: " + status + " , Tipo: " + ItemEmprestado.class.getName());

        return itensDevolvidos;
    }

    public List<ItemEmprestado> findAll() {
        return itemEmprestadoRepository.findAll();
    }

    public ItemEmprestado update(Integer id, ItemEmprestado novoItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(id);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "Item Emprestado não encontrado! Id: " + id + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado item = itemOptional.get();

        if (item.getStatus() == TipoStatus.EMPRESTADO)
            throw new ObjectAlreadyExistsException("Este item está emprestado, não pode ser alterado! Nome item: "
                    + item.getNomeItem() + ", Emprestado para: " + item.getAmigoEmprestimo().getNome() + " , Tipo: "
                    + ItemEmprestado.class.getName());

        if (itemEmprestadoRepository.findByNomeItem(novoItem.getNomeItem()).isPresent())
            throw new ObjectAlreadyExistsException(
                    "Você não pode alterar o Nome deste Item porque é igual ao existente, por favor entre com dados diferentes, Nome item: "
                            + novoItem.getNomeItem() + ItemEmprestado.class.getName());

        item.update(novoItem);
        return this.itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado emprestarItem(ItemEmprestado item, Integer id, Integer idAmigo) {
        DonoItem dono = donoItemService.findById(id);
        AmigoEmprestimo amigo = amigoEmprestimoService.findById(idAmigo);
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findByNomeItem(item.getNomeItem());

        if (itemOptional.isPresent())
            throw new ObjectAlreadyExistsException(
                    "Este item já existe!  Nome: " + item.getNomeItem() + ", Tipo: " + ItemEmprestado.class.getName());

        item.setAmigoEmprestimo(amigo);
        item.setDonoItem(dono);
        item.setStatus(TipoStatus.EMPRESTADO);

        return itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado devolver(Integer idItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "ItemEmprestado não encontrado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado item = itemOptional.get();

        if (item.getStatus() == TipoStatus.DEVOLVIDO)
            throw new ObjectAlreadyExistsException(
                    "Este item não está emprestado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        item.setStatus(TipoStatus.DEVOLVIDO);
        item.setAmigoEmprestimo(null);
        return this.itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado giveInAgain(Integer idItem, Integer idAmigo) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        AmigoEmprestimo amigoEncontrado = amigoEmprestimoService.findById(idAmigo);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "ItemEmprestado não encontrado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado item = itemOptional.get();

        if (item.getStatus() == TipoStatus.EMPRESTADO)
            throw new ObjectAlreadyExistsException("Este item já está emprestado! Id: " + item.getIdItem() + ", Tipo: "
                    + ItemEmprestado.class.getName());

        item.setAmigoEmprestimo(amigoEncontrado);
        item.setStatus(TipoStatus.EMPRESTADO);
        return this.itemEmprestadoRepository.save(item);
    }

    public void delete(Integer id) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(id);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "ItemEmprestado não encontrado! Id: " + id + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado item = itemOptional.get();

        if (item.getStatus() == TipoStatus.EMPRESTADO)
            throw new ObjectAlreadyExistsException("Este item está emprestado, não pode ser deletado! Nome item: "
                    + item.getNomeItem() + ", Emprestado para: " + item.getAmigoEmprestimo().getNome() + " , Tipo: "
                    + ItemEmprestado.class.getName());

        try {
            itemEmprestadoRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Item não pode ser deletado, possui amigos e itens associados.");
        }
    }
}
