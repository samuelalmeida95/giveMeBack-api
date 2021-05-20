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
        Optional<DonoItem> donoOptional = donoItemRepository.findByNomeDono(nomeDono);

        if (donoOptional.isPresent()){
             DonoItem donoItem = donoOptional.get();
             return donoItem;
        }

        throw new RuntimeException(
            "Dono de item não encontrado! " + nomeDono + " Tipo: " + DonoItem.class.getName());
    }


    public List<DonoItem> findAll() {
        return donoItemRepository.findAll();
    }

    public DonoItem createNewDono(DonoItem dono) {
        dono.setIdDono(null);
        return donoItemRepository.save(dono);
    }

    public DonoItem updateDono(Integer idDonoItem, DonoItem novoDonoItem) {
       Optional<DonoItem> donoItemOptional = donoItemRepository.findById(idDonoItem);

       if(donoItemOptional.isPresent()) {
           DonoItem donoItemAtualizado = updateData(donoItemOptional.get(), novoDonoItem);
           return this.donoItemRepository.save(donoItemAtualizado);
       }

       throw new RuntimeException(
        "Objeto não encontrado! Id: " + idDonoItem + ", Tipo: " + DonoItem.class.getName());    
    }

    public DonoItem updateData(DonoItem novoDonoItem, DonoItem donoItem) {
        novoDonoItem.setNomeDono(donoItem.getNomeDono());
        novoDonoItem.setWhatsappDono(donoItem.getWhatsappDono());
        return novoDonoItem;
    }
}
