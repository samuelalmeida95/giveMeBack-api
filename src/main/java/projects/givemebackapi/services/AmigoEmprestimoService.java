package projects.givemebackapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.repositories.AmigoEmprestimoRepository;
import projects.givemebackapi.services.exceptions.ObjectAlreadyExistsException;
import projects.givemebackapi.services.exceptions.ObjectNotFoundException;

@Service
public class AmigoEmprestimoService {

    @Autowired
    private AmigoEmprestimoRepository amigoEmprestimoRepository;

    public AmigoEmprestimo findById(Integer idAmigo) {
        Optional<AmigoEmprestimo> amigo = amigoEmprestimoRepository.findById(idAmigo);

        return amigo.orElseThrow(() -> new ObjectNotFoundException(
                "Amigo não encontrado! " + idAmigo + " Tipo: " + AmigoEmprestimo.class.getName()));
    }

    public AmigoEmprestimo findByNome(String nome) {
        Optional<AmigoEmprestimo> amigoEmprestimoOptional = amigoEmprestimoRepository.findByNome(nome);

        if (!amigoEmprestimoOptional.isPresent())
            throw new ObjectNotFoundException(
                    "Amigo não encontrado! " + nome + " Tipo: " + AmigoEmprestimo.class.getName());

        AmigoEmprestimo amigo = amigoEmprestimoOptional.get();
        return amigo;
    }

    public List<AmigoEmprestimo> findAll() {
        return amigoEmprestimoRepository.findAll();
    }

    public AmigoEmprestimo create(AmigoEmprestimo amigo) {
        amigo.setId(null);

        if (this.amigoEmprestimoRepository.findByNome(amigo.getNome()).isPresent())
            throw new ObjectNotFoundException("Já existe um amigo com esse nome, por favor entre com outro, Nome: "
                    + amigo + " Tipo: " + AmigoEmprestimo.class.getName());

        return amigoEmprestimoRepository.save(amigo);
    }

    public AmigoEmprestimo update(Integer idAmigo, AmigoEmprestimo novoAmigo) {
        Optional<AmigoEmprestimo> amigoEmprestimoOptional = amigoEmprestimoRepository.findById(idAmigo);

        if (!amigoEmprestimoOptional.isPresent())
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + idAmigo + ", Tipo: " + AmigoEmprestimo.class.getName());

        if (this.amigoEmprestimoRepository.findByNome(novoAmigo.getNome()).isPresent()) 
            throw new ObjectAlreadyExistsException(
                    "Você não pode alterar seu Nome de usuário porque é igual ao existente, por favor entre com dados diferentes, Nome: "
                            + novoAmigo.getNome() + ", Tipo: " + AmigoEmprestimo.class.getName());
        

        AmigoEmprestimo amigoEmprestimoAtualizado = updateData(novoAmigo, amigoEmprestimoOptional.get());
        return this.amigoEmprestimoRepository.save(amigoEmprestimoAtualizado);
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
        List<AmigoEmprestimo> amigos = this.amigoEmprestimoRepository.findByAvaliacao();

        if(amigos.isEmpty()) {
            throw new ObjectNotFoundException(
                    "Não existem amigos bem avaliados. " + AmigoEmprestimo.class.getName());
        }
        return amigos;
    }
}
