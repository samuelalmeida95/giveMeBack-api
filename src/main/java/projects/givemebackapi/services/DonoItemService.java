package projects.givemebackapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.repositories.DonoItemRepository;
import projects.givemebackapi.services.exceptions.ObjectAlreadyExistsException;
import projects.givemebackapi.services.exceptions.ObjectNotFoundException;

@Service
public class DonoItemService {

    @Autowired
    private DonoItemRepository donoItemRepository;

    public DonoItem findById(Integer id) {
        return donoItemRepository
        .findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(
                "Dono de item não encontrado! " + id + " Tipo:  " + DonoItem.class.getName()));
    }

    //implementação nova... sem optional.
    public DonoItem findByNome(String nome) {
        return donoItemRepository
        .findByNome(nome)
        .orElseThrow(() ->  new ObjectNotFoundException(
            "Dono de item não encontrado! " + nome + " Tipo: " + DonoItem.class.getName()));
    }

    public List<DonoItem> findAll() {
        return donoItemRepository.findAll();
    }

    public DonoItem create(DonoItem dono) {
        dono.setId(null);
        return donoItemRepository.save(dono);
    }

    public DonoItem update(Integer id, DonoItem novoDono) {
       DonoItem donoBuscado = findById(id);

        if (donoItemRepository.findByNome(novoDono.getNome()).isPresent())
            throw new ObjectAlreadyExistsException(
                    "Você não pode alterar seu Nome de usuário porque é igual ao existente, por favor entre com dados diferentes, Nome: "
                            + novoDono.getNome() + ", Tipo: " + DonoItem.class.getName());

        DonoItem donoItemAtualizado = updateData(donoBuscado, novoDono);
        return donoItemRepository.save(donoItemAtualizado);
    }

    public DonoItem updateData(DonoItem novoDono, DonoItem dono) {
        novoDono.setNome(dono.getNome());
        novoDono.setWhatsapp(dono.getWhatsapp());
        return novoDono;
    }

    public void delete(Integer id) {
        findById(id);
        try {
            donoItemRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Dono não pode ser deletado, possui amigos e itens associados.");
        }
    }
}
