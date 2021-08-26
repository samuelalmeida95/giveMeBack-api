package projects.givemebackapi.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.model.AvaliacaoStatus;
import projects.givemebackapi.repositories.AmigoEmprestimoRepository;
import projects.givemebackapi.repositories.DonoItemRepository;
import projects.givemebackapi.services.exceptions.NoSuchElementException;
import projects.givemebackapi.services.exceptions.ObjectAlreadyExistsException;
import projects.givemebackapi.services.exceptions.ObjectNotFoundException;

@Service
public class AmigoEmprestimoService {

    @Autowired
    private AmigoEmprestimoRepository amigoEmprestimoRepository;

    @Autowired
    private DonoItemRepository donoItemRepository;

    public AmigoEmprestimo findById(Integer idAmigo) {
        Optional<AmigoEmprestimo> amigo = amigoEmprestimoRepository.findById(idAmigo);

        return amigo.orElseThrow(() -> new ObjectNotFoundException(
                "Amigo não encontrado! " + idAmigo + " Tipo:  " + AmigoEmprestimo.class.getName()));
    }

    public AmigoEmprestimo findByNome(String nome) {
        Optional<AmigoEmprestimo> amigoEmprestimo = amigoEmprestimoRepository.findByNome(nome);

        if (!amigoEmprestimo.isPresent())
            throw new ObjectNotFoundException(
                    "Amigo não encontrado! " + nome + " Tipo: " + AmigoEmprestimo.class.getName());

         return amigoEmprestimo.get();   
    }

    public AmigoEmprestimo findyByIdDonoAndIdAmigoEmprestimo(Integer idDono, Integer idAmigo) {
        Optional<AmigoEmprestimo> amigoDono = amigoEmprestimoRepository
                .findyByIdDonoAndIdAmigoEmprestimo(idAmigo, idDono);

        if (!amigoDono.isPresent())
            throw new NoSuchElementException(
                    "AmigoEmprestimo não está associado a este DonoItem");

         return amigoDono.get();
    }
        
    public List<AmigoEmprestimo> findAll() {
        return amigoEmprestimoRepository.findAll();
    }

    public void findByWhatsapp(String whatsAmigo){
        Optional<AmigoEmprestimo> whatsappAmigo = amigoEmprestimoRepository.findByWhatsapp(whatsAmigo);
        
        if(whatsappAmigo.isPresent())
            throw new ObjectNotFoundException(
                "Amigo já cadastrado, Whatsapp: " + whatsAmigo + " Tipo: " + AmigoEmprestimo.class.getName());   
    }

    @Transactional
    public AmigoEmprestimo create(AmigoEmprestimo amigo, String nomeDono) {
        amigo.setId(null);   
        findByWhatsapp(amigo.getWhatsapp());
        amigo.setAvaliacao(AvaliacaoStatus.NAO_AVALIADO);
        amigo.setDonoItem(donoItemRepository.findByNome(nomeDono).get());

        return amigoEmprestimoRepository.save(amigo);
    }

    public AmigoEmprestimo update(Integer idAmigo, AmigoEmprestimo novoAmigo) {
        Optional<AmigoEmprestimo> amigoEmprestimo = amigoEmprestimoRepository.findById(idAmigo);

        if (!amigoEmprestimo.isPresent())
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + idAmigo + ", Tipo: " + AmigoEmprestimo.class.getName());

        if (amigoEmprestimoRepository.findByNome(novoAmigo.getNome()).isPresent())
            throw new ObjectAlreadyExistsException(
                    "Você não pode alterar seu Nome de usuário porque é igual ao existente, por favor entre com dados diferentes, Nome: "
                            + novoAmigo.getNome() + ", Tipo: " + AmigoEmprestimo.class.getName());

        AmigoEmprestimo amigoEmprestimoAtualizado = updateData(novoAmigo, amigoEmprestimo.get());
        return amigoEmprestimoRepository.save(amigoEmprestimoAtualizado);
    }

    public AmigoEmprestimo updateData(AmigoEmprestimo novoAmigo, AmigoEmprestimo amigo) {
        amigo.setNome(novoAmigo.getNome());
        amigo.setWhatsapp(novoAmigo.getWhatsapp());
        amigo.setWhatsapp(novoAmigo.getEndereco());
        return amigo;
    }

    public void delete(Integer id) {
        findById(id);
        try {
            amigoEmprestimoRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    "Amigo não pode ser deletado, possui itens emprestados associados.");
        }
    }

    public List<AmigoEmprestimo> findByAvaliacao() {
        List<AmigoEmprestimo> amigosPorAvaliacao = amigoEmprestimoRepository.findByAvaliacaoBoaOrOtima();

        if (amigosPorAvaliacao.isEmpty())
            throw new ObjectNotFoundException("Não existem amigos bem avaliados. " + AmigoEmprestimo.class.getName());

        return amigosPorAvaliacao;
    }

    public List<AmigoEmprestimo> findByPioresAvaliados() {
        List<AmigoEmprestimo> pioresAvaliados = amigoEmprestimoRepository.findByAvaliacaoPessimaOrRuim();

        if (pioresAvaliados.isEmpty())
            throw new ObjectNotFoundException("Não existem amigos piores avaliados. " + AmigoEmprestimo.class.getName());

        return pioresAvaliados;
    }
}
