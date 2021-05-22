package projects.givemebackapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.repositories.DonoItemRepository;
import projects.givemebackapi.services.exceptions.ObjectNotFoundException;

@Service
public class DonoItemService {

    @Autowired
    private DonoItemRepository donoItemRepository;

    public DonoItem findById(Integer id) {
        Optional<DonoItem> donoItem = donoItemRepository.findById(id);

        return donoItem.orElseThrow(() -> new ObjectNotFoundException(
                "Dono de item não encontrado! " + id + " Tipo: " + DonoItem.class.getName()));
    }

    public DonoItem findByNome(String nome) {
        Optional<DonoItem> donoOptional = donoItemRepository.findByNome(nome);

        if (!donoOptional.isPresent())
            throw new ObjectNotFoundException("Dono de item não encontrado! " + nome + " Tipo: " + DonoItem.class.getName());

        DonoItem donoItem = donoOptional.get();
        return donoItem;
    }

    public List<DonoItem> findAll() {
        return donoItemRepository.findAll();
    }

    public DonoItem create(DonoItem dono) {
        dono.setId(null);
        return donoItemRepository.save(dono);
    }

    public DonoItem update(Integer id, DonoItem novoDono) {
        Optional<DonoItem> donoItemOptional = donoItemRepository.findById(id);

        if (!donoItemOptional.isPresent())
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + DonoItem.class.getName());

        DonoItem donoItemAtualizado = updateData(donoItemOptional.get(), novoDono);
        return this.donoItemRepository.save(donoItemAtualizado);
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
