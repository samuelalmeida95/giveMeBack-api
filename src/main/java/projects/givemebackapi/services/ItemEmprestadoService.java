package projects.givemebackapi.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.AvaliacaoStatus;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.repositories.AmigoEmprestimoRepository;
import projects.givemebackapi.repositories.ItemEmprestadoRepository;
import projects.givemebackapi.services.exceptions.ObjectAlreadyExistsException;
import projects.givemebackapi.services.exceptions.ObjectNotFoundException;

@Service
public class ItemEmprestadoService {

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepository;

    @Autowired
    private AmigoEmprestimoRepository amigoEmprestimoRepository;

    @Autowired
    private DonoItemService donoItemService;

    @Autowired
    private AmigoEmprestimoService amigoEmprestimoService;

    public ItemEmprestado findById(Integer idItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        return itemOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Item não existe ou não está emprestado! " + idItem + " Tipo: " + ItemEmprestado.class.getName()));
    }

    public ItemEmprestado findByNome(String nomeItem) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findByNomeItem(nomeItem);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "Item não existe ou não está emprestado! " + nomeItem + " Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado itemEmprestadoEncontrado = itemOptional.get();
        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByEmprestadoPara(Integer idAmigo) {
        Optional<List<ItemEmprestado>> itemEmprestado = itemEmprestadoRepository.findByAmigoEmprestimoId(idAmigo);

        if (!itemEmprestado.isPresent())
            throw new ObjectNotFoundException(
                    "Amigo: " + idAmigo + ", não encontrado,  Tipo: " + ItemEmprestado.class.getName());

        amigoEmprestimoService.findById(idAmigo);

        List<ItemEmprestado> itemEmprestadoEncontrado = itemEmprestado.get();

        if (itemEmprestadoEncontrado.isEmpty())
            throw new ObjectNotFoundException(
                    "Amigo não tem itens emprestados no momento,  Tipo: " + ItemEmprestado.class.getName());

        return itemEmprestadoEncontrado;
    }

    public List<ItemEmprestado> findByDono(Integer idDono) {
        Optional<List<ItemEmprestado>> itemDeUmDono = itemEmprestadoRepository.findByDonoItemId(idDono);

        donoItemService.findById(idDono);

        List<ItemEmprestado> itensDeUmDono = itemDeUmDono.get();

        if (itensDeUmDono.isEmpty())
            throw new ObjectNotFoundException(
                    "Dono não tem itens emprestados no momento,  Tipo: " + ItemEmprestado.class.getName());

        return itensDeUmDono;
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
        ItemEmprestado item = this.findById(id);

        if (item.getStatus() == TipoStatus.EMPRESTADO)
            throw new ObjectAlreadyExistsException("Este item está emprestado, não pode ser alterado! Nome item: "
                    + item.getNomeItem() + ", Emprestado para: " + item.getAmigoEmprestimo().getNome() + " , Tipo: "
                    + ItemEmprestado.class.getName());

        if (itemEmprestadoRepository.findByNomeItem(novoItem.getNomeItem()).isPresent())
            throw new ObjectAlreadyExistsException("Você não pode alterar o Nome deste Item porque é igual ao"
                    + "existente, por favor entre com dados diferentes, Nome item: " + novoItem.getNomeItem()
                    + ItemEmprestado.class.getName());

        item.update(novoItem);
        return this.itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado emprestarItem(ItemEmprestado item, Integer idDono, Integer idAmigo) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findByNomeItem(item.getNomeItem());

        if (itemOptional.isPresent())
            throw new ObjectAlreadyExistsException(
                    "Este item já existe!  Nome: " + item.getNomeItem() + ", Tipo: " + ItemEmprestado.class.getName());

        emprestar(item, idDono, idAmigo);
        return itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado emprestar(ItemEmprestado item, Integer idDono, Integer idAmigo) {
        this.amigoEmprestimoService.findyByIdDonoAndIdAmigoEmprestimo(idDono, idAmigo);

        AmigoEmprestimo amigoParaEmprestar = amigoEmprestimoService.findById(idAmigo);
        DonoItem donoItem = donoItemService.findById(idDono);

        amigoParaEmprestar.setAvaliacao(AvaliacaoStatus.NAO_AVALIADO);
        item.setAmigoEmprestimo(amigoParaEmprestar);
        item.setDonoItem(donoItem);
        item.setStatus(TipoStatus.EMPRESTADO);
        item.setDataEmprestimoItem(LocalDate.now());
        item.setDataDevolucaoItem(LocalDate.now().plusDays(20));
        return item;
    }

    public ItemEmprestado devolverAvaliar(Integer idItem, String nomeAmigo, AvaliacaoStatus avaliacao) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        AmigoEmprestimo amigo = amigoEmprestimoService.findByNome(nomeAmigo);
        Integer idAmigo = amigo.getId();

        Optional<ItemEmprestado> itemOptionalComAmigo = itemEmprestadoRepository.findByIdItemAndAmigoId(idAmigo, idItem);

        if (!itemOptionalComAmigo.isPresent())
            throw new ObjectNotFoundException("Este item não está emprestado para o amigo: " + nomeAmigo + ", idItem: "
                    + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "ItemEmprestado não encontrado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado item = itemOptional.get();

        if (item.getStatus() == TipoStatus.DEVOLVIDO)
            throw new ObjectAlreadyExistsException(
                    "Este item não está emprestado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        item.setStatus(TipoStatus.DEVOLVIDO);
        item.setAmigoEmprestimo(null);
        item.setDataDevolucaoItem(LocalDate.now());

        avaliarAmigo(nomeAmigo, avaliacao);

        return this.itemEmprestadoRepository.save(item);
    }

    public AmigoEmprestimo avaliarAmigo(String nomeAmigo, AvaliacaoStatus avaliacao) {
        AmigoEmprestimo amigoAvaliado = amigoEmprestimoService.findByNome(nomeAmigo);
        amigoAvaliado.setAvaliacao(avaliacao);
        return this.amigoEmprestimoRepository.save(amigoAvaliado);
    }

    public ItemEmprestado emprestarItemNovamente(Integer idItem, Integer idAmigo) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findById(idItem);

        Integer idDono = itemOptional.get().getDonoItem().getId();

        this.amigoEmprestimoService.findyByIdDonoAndIdAmigoEmprestimo(idDono, idAmigo);

        AmigoEmprestimo amigoEncontrado = amigoEmprestimoService.findById(idAmigo);

        if (!itemOptional.isPresent())
            throw new ObjectNotFoundException(
                    "ItemEmprestado não encontrado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        if (amigoEncontrado.getAvaliacao() == AvaliacaoStatus.NAO_AVALIADO)
            throw new ObjectAlreadyExistsException(
                    "Emprestimo inválido! esse amigo nunca foi avaliado," + "você deve emprestar um item somente a"
                            + "amigos que já tenham recebido alguma avaliação . Amigo: " + amigoEncontrado.getNome()
                            + ", Tipo: " + ItemEmprestado.class.getName());

        ItemEmprestado item = itemOptional.get();

        if (item.getStatus() == TipoStatus.EMPRESTADO)
            throw new ObjectAlreadyExistsException("Este item já está emprestado! Id: " + item.getIdItem() + ", Tipo: "
                    + ItemEmprestado.class.getName());

        emprestar(item, idDono, idAmigo);
        
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
