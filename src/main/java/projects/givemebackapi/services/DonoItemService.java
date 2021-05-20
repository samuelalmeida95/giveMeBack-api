package projects.givemebackapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.repositories.DonoItemRepository;

@Service
public class DonoItemService {
    

    @Autowired
    private DonoItemRepository donoItemRepository;

    public DonoItem findById(Integer idDonoItem) {
        Optional<DonoItem> donoItem = donoItemRepository.findById(idDonoItem);
        return donoItem.orElseThrow(() -> new RuntimeException(
            
            "Dono de item não encontrado! " + idDonoItem + " Tipo: " + DonoItem.class.getName()));
    }

    public DonoItem findByNomeDono(String nomeDono) {
        DonoItem dono = donoItemRepository.findByNomeDono(nomeDono);
        if (dono == null)
            throw new RuntimeException("Dono de item não encontrado! " + nomeDono + " Tipo: " + DonoItem.class.getName());
        return donoItemRepository.findByNomeDono(nomeDono);
    }

    public List<DonoItem> findAll() {
        return donoItemRepository.findAll();
    }

    public DonoItem createNewDono(DonoItem dono) {
        dono.setIdDono(null);
        return donoItemRepository.save(dono);
    }

    public DonoItem updateDono(Integer id, DonoItem newDono) {
        DonoItem dono = findById(id);
        dono.setNomeDono(newDono.getNomeDono());
        dono.setWhatsappDono(newDono.getWhatsappDono());
        return donoItemRepository.save(dono);
    }
}
