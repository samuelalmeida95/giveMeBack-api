package projects.givemebackapi.services;

import java.util.List;

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
        return amigoEmprestimoRepository
        .findById(idAmigo)
        .orElseThrow(() -> new ObjectNotFoundException(
                "Amigo não encontrado! " + idAmigo + " Tipo:  " + AmigoEmprestimo.class.getName()));
    }

    public AmigoEmprestimo findByNome(String nome) {
       return amigoEmprestimoRepository
        .findByNome(nome)
        .orElseThrow(() -> new ObjectNotFoundException(
            "Amigo não encontrado! " + nome + " Tipo: " + AmigoEmprestimo.class.getName()));
   
    }

    public AmigoEmprestimo findyByIdDonoAndIdAmigoEmprestimo(Integer idDono, Integer idAmigo) {
        return amigoEmprestimoRepository
        .findyByIdDonoAndIdAmigoEmprestimo(idAmigo, idDono)
        .orElseThrow(() -> new NoSuchElementException("AmigoEmprestimo não está associado a este DonoItem"));
    }
        
    public List<AmigoEmprestimo> findAll() {
        return amigoEmprestimoRepository.findAll();
    }

    public void findByWhatsapp(String whatsAmigo) {
        if (amigoEmprestimoRepository.findByWhatsapp(whatsAmigo).isPresent())
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
        AmigoEmprestimo amigoEmprestimo = findById(idAmigo);

        if(amigoEmprestimoRepository.findByNome(novoAmigo.getNome()).isPresent())
                throw new ObjectAlreadyExistsException(
                        "Nome de usuário igual ao existente, entre com dados diferentes");

        return amigoEmprestimoRepository.save(updateData(novoAmigo, amigoEmprestimo));
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
