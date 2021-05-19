package projects.givemebackapi.services;

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
}
