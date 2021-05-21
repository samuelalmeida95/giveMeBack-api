package projects.givemebackapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.repositories.AmigoEmprestimoRepository;

@Service
public class AmigoEmprestimoService {

    @Autowired
    private AmigoEmprestimoRepository amigoEmprestimoRepository;

    public AmigoEmprestimo findById(Integer idAmigoEmprestimo) {
        Optional<AmigoEmprestimo> amigoEmprestimo = amigoEmprestimoRepository.findById(idAmigoEmprestimo);

        return amigoEmprestimo.orElseThrow(() -> new RuntimeException(
                "Dono de item não encontrado! " + idAmigoEmprestimo + " Tipo: " + AmigoEmprestimo.class.getName()));
    }

    public AmigoEmprestimo findByNome(String nome) {
        Optional<AmigoEmprestimo> amigoEmprestimoOptional = amigoEmprestimoRepository.findByNome(nome);

        if (amigoEmprestimoOptional.isPresent()) {
            AmigoEmprestimo amigoEmprestimo = amigoEmprestimoOptional.get();
            return amigoEmprestimo;
        }

        throw new RuntimeException(
                "Dono de item não encontrado! " + nome + " Tipo: " + AmigoEmprestimo.class.getName());
    }

    public List<AmigoEmprestimo> findAll() {
        return amigoEmprestimoRepository.findAll();
    }

    public AmigoEmprestimo create(AmigoEmprestimo dono) {
        dono.setId(null);
        return amigoEmprestimoRepository.save(dono);
    }

    public AmigoEmprestimo update(Integer idAmigoEmprestimo, AmigoEmprestimo novoAmigoEmprestimo) {
        Optional<AmigoEmprestimo> amigoEmprestimoOptional = amigoEmprestimoRepository.findById(idAmigoEmprestimo);

        if (amigoEmprestimoOptional.isPresent()) {
            AmigoEmprestimo amigoEmprestimoAtualizado = updateData(amigoEmprestimoOptional.get(), novoAmigoEmprestimo);
            return this.amigoEmprestimoRepository.save(amigoEmprestimoAtualizado);
        }

        throw new RuntimeException(
                "Objeto não encontrado! Id: " + idAmigoEmprestimo + ", Tipo: " + AmigoEmprestimo.class.getName());
    }

    public AmigoEmprestimo updateData(AmigoEmprestimo novoAmigoEmprestimo, AmigoEmprestimo amigoEmprestimo) {
        novoAmigoEmprestimo.setNome(amigoEmprestimo.getNome());
        novoAmigoEmprestimo.setWhatsapp(amigoEmprestimo.getWhatsapp());
        novoAmigoEmprestimo.setWhatsapp(amigoEmprestimo.getEndereco());
        return novoAmigoEmprestimo;
    }
}
