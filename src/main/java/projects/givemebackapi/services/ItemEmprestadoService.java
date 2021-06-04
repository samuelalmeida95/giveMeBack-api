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
        if (!itemEmprestadoRepository.findByNomeItem(nomeItem).isPresent())
            throw new ObjectNotFoundException(
                    "Item não existe ou não está emprestado! " + nomeItem + " Tipo: " + ItemEmprestado.class.getName());

        return itemEmprestadoRepository.findByNomeItem(nomeItem).get();
    }
    

    public List<ItemEmprestado> findByEmprestadoPara(Integer idAmigo) {
        if (itemEmprestadoRepository.findByAmigoEmprestimoId(idAmigo).isEmpty())
            throw new ObjectNotFoundException(
                    "Amigo não tem itens emprestados no momento,  Tipo: " + ItemEmprestado.class.getName());

        return itemEmprestadoRepository.findByAmigoEmprestimoId(idAmigo);
    }


    public List<ItemEmprestado> findByDono(Integer idDono) {
        if (itemEmprestadoRepository.findByDonoItemId(idDono).isEmpty())
            throw new ObjectNotFoundException(
                    "Dono não tem itens emprestados no momento,  Tipo: " + ItemEmprestado.class.getName());

        return itemEmprestadoRepository.findByDonoItemId(idDono);
    }


    public List<ItemEmprestado> findByStatus(TipoStatus status) {
        if (itemEmprestadoRepository.findByStatus(status).isEmpty())
            throw new ObjectNotFoundException(
                    "Não existem itens com o status: " + status + " , Tipo: " + ItemEmprestado.class.getName());

        return itemEmprestadoRepository.findByStatus(status);
    }


    public List<ItemEmprestado> findAll() {
        return itemEmprestadoRepository.findAll();
    }


    public ItemEmprestado update(Integer id, ItemEmprestado novoItem) {
        ItemEmprestado item = this.findById(id);
        verificaSeItemEstaEmprestado(item.getIdItem());
        verificaSeItemJaExiste(item);

        item.update(novoItem);
        return this.itemEmprestadoRepository.save(item);
    }

    public ItemEmprestado verificaSeItemJaExiste(ItemEmprestado item) {
        if (itemEmprestadoRepository.findByNomeItem(item.getNomeItem()).isPresent())
            throw new ObjectAlreadyExistsException(
                    "Este item já existe!  Nome: " + item.getNomeItem() + ", Tipo: " + ItemEmprestado.class.getName());

        return item;
    }


    public ItemEmprestado emprestarItem(ItemEmprestado item, Integer idDono, Integer idAmigo) {
        Optional<ItemEmprestado> itemOptional = itemEmprestadoRepository.findByNomeItem(item.getNomeItem());
        verificaSeItemJaExiste(itemOptional.get());

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
        ItemEmprestado itemDevolvido = findById(idItem);
        AmigoEmprestimo amigoComItemEmprestado = amigoEmprestimoService.findByNome(nomeAmigo);

        verificaSeItemEstaDevolvido(idItem);
        buscarItemEmprestadoParaUmAmigo(amigoComItemEmprestado.getId(), idItem);
        devolver(itemDevolvido);
        avaliarAmigo(nomeAmigo, avaliacao);

        return this.itemEmprestadoRepository.save(itemDevolvido);
    }


    public void verificaSeItemEstaDevolvido(Integer idItem) {
        if (findById(idItem).getStatus() == TipoStatus.DEVOLVIDO)
            throw new ObjectAlreadyExistsException(
                    "Este item não está emprestado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());
    }


    public void verificaSeItemEstaEmprestado(Integer idItem) {
        if (findById(idItem).getStatus() == TipoStatus.EMPRESTADO)
            throw new ObjectAlreadyExistsException(
                    "Este item já está emprestado! Id: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());
    }


    public void devolver(ItemEmprestado itemDevolvido) {
        itemDevolvido.setStatus(TipoStatus.DEVOLVIDO);
        itemDevolvido.setAmigoEmprestimo(null);
        itemDevolvido.setDataDevolucaoItem(LocalDate.now());
    }


    public ItemEmprestado buscarItemEmprestadoParaUmAmigo(Integer idAmigo, Integer idItem) {
        if (!itemEmprestadoRepository.findByIdItemAndAmigoId(idAmigo, idItem).isPresent())
            throw new ObjectNotFoundException("Este item não está emprestado para o amigo: " + idAmigo
                    + ", idItem: " + idItem + ", Tipo: " + ItemEmprestado.class.getName());

        return itemEmprestadoRepository.findByIdItemAndAmigoId(idAmigo, idItem).get();
    }


    public AmigoEmprestimo avaliarAmigo(String nomeAmigo, AvaliacaoStatus avaliacao) {
        AmigoEmprestimo amigoParaAvaliar = amigoEmprestimoService.findByNome(nomeAmigo);
        amigoParaAvaliar.setAvaliacao(avaliacao);

        return this.amigoEmprestimoRepository.save(amigoParaAvaliar);
    }


    public ItemEmprestado emprestarItemNovamente(Integer idItem, Integer idAmigo) {
        ItemEmprestado itemParaEmprestar = findById(idItem);

        verificaSeTemAvaliacao(idAmigo);
        verificaSeDonoEAmigoSeConhecem(itemParaEmprestar.getDonoItem().getId(), idAmigo);
        verificaSeItemEstaEmprestado(itemParaEmprestar.getDonoItem().getId());
        emprestar(itemParaEmprestar, itemParaEmprestar.getIdItem(), idAmigo);

        return this.itemEmprestadoRepository.save(itemParaEmprestar);
    }


    public AmigoEmprestimo verificaSeDonoEAmigoSeConhecem(Integer idDono, Integer idAmigo) {
        return amigoEmprestimoService.findyByIdDonoAndIdAmigoEmprestimo(idDono, idAmigo);
    }


    public AmigoEmprestimo verificaSeTemAvaliacao(Integer idAmigo) {
        if (findById(idAmigo).getAmigoEmprestimo().getAvaliacao() == AvaliacaoStatus.NAO_AVALIADO)
            throw new ObjectAlreadyExistsException(
                    "Você deve emprestar um item somente para amigos que já tenham recebido alguma avaliação, Tipo: "
                            + ItemEmprestado.class.getName());

        return findById(idAmigo).getAmigoEmprestimo();
    }


    public void delete(Integer idItem) {
         findById(idItem);
         verificaSeItemEstaEmprestado(idItem);

        try {
            itemEmprestadoRepository.deleteById(idItem);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Item não pode ser deletado, possui amigos e itens associados.");
        }
    }
}
