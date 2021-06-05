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

    public ItemEmprestado findById(Integer idItem)
    {
        Optional<ItemEmprestado> itemBuscado = itemEmprestadoRepository.findById(idItem);
        return itemBuscado.orElseThrow(() -> new ObjectNotFoundException(
                "Item não existe ou não está emprestado! " + idItem 
                + " Tipo: " + ItemEmprestado.class.getName()));
    }

    public ItemEmprestado findByNome(String nomeItem)
    {
        Optional<ItemEmprestado> itemEmprestadoEncontrado = itemEmprestadoRepository.findByNomeItem(nomeItem);
        if (!itemEmprestadoEncontrado.isPresent())
            throw new ObjectNotFoundException(
                    "Item não existe ou não está emprestado! " + nomeItem 
                    + " Tipo: " + ItemEmprestado.class.getName());
        return itemEmprestadoEncontrado.get();
    }

    public List<ItemEmprestado> findByEmprestadoPara(Integer idAmigo)
    {
        amigoEmprestimoService.findById(idAmigo);
        List<ItemEmprestado> itemEmprestado = itemEmprestadoRepository.findByAmigoEmprestimoId(idAmigo);
        if (itemEmprestado.isEmpty())
            throw new ObjectNotFoundException(
                    "Amigo não tem itens emprestados no momento,  Tipo: "
                     + ItemEmprestado.class.getName());
        return itemEmprestado;
    }

    public List<ItemEmprestado> findByDono(Integer idDono)
    {
        List<ItemEmprestado> itensDeUmDono = itemEmprestadoRepository.findByDonoItemId(idDono);
        donoItemService.findById(idDono);
        if (itensDeUmDono.isEmpty())
            throw new ObjectNotFoundException(
                    "Dono não tem itens emprestados no momento,  Tipo: "
                    + ItemEmprestado.class.getName());
        return itensDeUmDono;
    }

    public List<ItemEmprestado> findByStatus(TipoStatus status)
    {
        List<ItemEmprestado> itensDevolvidos = itemEmprestadoRepository.findByStatus(status);
        if (itensDevolvidos.isEmpty())
            throw new ObjectNotFoundException(
                    "Não existem itens com o status: " + status 
                    + " , Tipo: " + ItemEmprestado.class.getName());
        return itensDevolvidos;
    }

    public List<ItemEmprestado> findAll()
    {
        return itemEmprestadoRepository.findAll();
    }

    public ItemEmprestado update(Integer id, ItemEmprestado novoItem)
    {
        ItemEmprestado itemParaSerAtualizado = this.findById(id);
        verificaSeItemEstaEmprestado(id);
        verificaSeNomeDoItemJaExiste(novoItem);
        itemParaSerAtualizado.update(novoItem);
        return this.itemEmprestadoRepository.save(itemParaSerAtualizado);
    }

    public void verificaSeNomeDoItemJaExiste(ItemEmprestado novoItem)
    {
        String nomeItem = novoItem.getNomeItem();
        if (itemEmprestadoRepository.findByNomeItem(nomeItem).isPresent())
            throw new ObjectAlreadyExistsException(
                    "Você não pode adicionar um Item com esse nome porque é igual ao"
                    + " existente, por favor entre com dados diferentes, Nome item: " + novoItem.getNomeItem()
                    + ", Tipo: " + ItemEmprestado.class.getName());
    }

    public ItemEmprestado criarEmprestarNovoItem(ItemEmprestado item, Integer idDono, Integer idAmigo)
    {
        verificaSeNomeDoItemJaExiste(item);
        emprestarItem(item, idDono, idAmigo);
        return itemEmprestadoRepository.save(item);
    }
    
    public ItemEmprestado emprestarItem(ItemEmprestado item, Integer idDono, Integer idAmigo)
    {
        AmigoEmprestimo amigoParaEmprestar = amigoEmprestimoService.findById(idAmigo);
        DonoItem donoItem = donoItemService.findById(idDono);
        amigoParaEmprestar.setAvaliacao(AvaliacaoStatus.NAO_AVALIADO);
        verificaSeDonoConheceAmigo(idDono, idAmigo);
        item.setAmigoEmprestimo(amigoParaEmprestar);
        item.setDonoItem(donoItem);
        item.setStatus(TipoStatus.EMPRESTADO);
        item.setDataEmprestimoItem(LocalDate.now());
        item.setDataDevolucaoItem(LocalDate.now().plusDays(20));
        return item;
    }

    public ItemEmprestado devolverAvaliar(Integer idItem, String nomeAmigo, AvaliacaoStatus avaliacao)
    {
        ItemEmprestado itemDevolvido = findById(idItem);
        AmigoEmprestimo amigoParaDevolverItem = amigoEmprestimoService.findByNome(nomeAmigo);
        Integer idAmigo = amigoParaDevolverItem.getId();
        verificaSeItemEstaComEsteAmigo(idAmigo, idItem);
        verificaSeItemEstaDevolvido(idItem);
        itemDevolvido.setStatus(TipoStatus.DEVOLVIDO);
        itemDevolvido.setAmigoEmprestimo(null);
        itemDevolvido.setDataDevolucaoItem(LocalDate.now());
        avaliarAmigo(nomeAmigo, avaliacao);
        return itemEmprestadoRepository.save(itemDevolvido);
    }

    public ItemEmprestado verificaSeItemEstaComEsteAmigo(Integer idAmigo, Integer idItem){
        Optional<ItemEmprestado> itemComAmigo = itemEmprestadoRepository.findByIdItemAndAmigoId(idAmigo, idItem);
        if (!itemComAmigo.isPresent())
            throw new ObjectNotFoundException(
                    "Este item não está emprestado para o amigo: " + idAmigo 
                    + ", idItem: " + idItem 
                    + ", Tipo: " + ItemEmprestado.class.getName());
        return itemComAmigo.get();
    }

    public ItemEmprestado verificaSeItemEstaDevolvido(Integer idItem){
        ItemEmprestado item = findById(idItem);
        if (item.getStatus() == TipoStatus.DEVOLVIDO)
            throw new ObjectAlreadyExistsException(
                    "Este item não está emprestado! Id: " + idItem 
                    + ", Tipo: " + ItemEmprestado.class.getName());
        return item;
    }

    public AmigoEmprestimo avaliarAmigo(String nomeAmigo, AvaliacaoStatus avaliacao)
    {
        AmigoEmprestimo amigoAvaliado = amigoEmprestimoService.findByNome(nomeAmigo);
        amigoAvaliado.setAvaliacao(avaliacao);
        return amigoEmprestimoRepository.save(amigoAvaliado);
    }

    public ItemEmprestado emprestarItemNovamente(Integer idItem, Integer idAmigo)
    {
        ItemEmprestado itemParaEmprestarNovamente = findById(idItem);
        Integer idDono = itemParaEmprestarNovamente.getDonoItem().getId();
        verificaSeDonoConheceAmigo(idDono, idAmigo);
        verificaSeAmigoTemAvaliacao(idAmigo);
        verificaSeItemEstaEmprestado(idItem);
        emprestarItem(itemParaEmprestarNovamente, idDono, idAmigo);
        return itemEmprestadoRepository.save(itemParaEmprestarNovamente);
    }

    public AmigoEmprestimo verificaSeDonoConheceAmigo(Integer idDono, Integer idAmigo)
    {
        AmigoEmprestimo amigoConhecido = amigoEmprestimoService.findyByIdDonoAndIdAmigoEmprestimo(idDono,idAmigo);
        return amigoConhecido;
    }

    public ItemEmprestado verificaSeItemEstaEmprestado(Integer idItem)
    {
        ItemEmprestado itemParaEmprestarNovamente = findById(idItem);
        if (itemParaEmprestarNovamente.getStatus() == TipoStatus.EMPRESTADO)
            throw new ObjectAlreadyExistsException(
                    "Este item já está emprestado! Id: " + itemParaEmprestarNovamente.getIdItem() 
                    + ", Tipo: " + ItemEmprestado.class.getName());
        return itemParaEmprestarNovamente;
    }

    public AmigoEmprestimo verificaSeAmigoTemAvaliacao(Integer idAmigo)
    {
        AmigoEmprestimo amigoEncontrado = amigoEmprestimoService.findById(idAmigo);
        if (amigoEncontrado.getAvaliacao() == AvaliacaoStatus.NAO_AVALIADO)
            throw new ObjectAlreadyExistsException(
                    "Emprestimo inválido! esse amigo nunca foi avaliado, você deve emprestar um item somente a"
                    + "amigos que já tenham recebido alguma avaliação, Amigo: " + amigoEncontrado.getNome()
                    + ", Tipo: " + ItemEmprestado.class.getName());
        return amigoEncontrado;
    }

    public void delete(Integer idItem)
    {
        findById(idItem);
        verificaSeItemEstaEmprestado(idItem);
        try {
            itemEmprestadoRepository.deleteById(idItem);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Item não pode ser deletado, possui amigos e itens associados.");
        }
    }
}
