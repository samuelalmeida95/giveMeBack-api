package projects.givemebackapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.repositories.ItemEmprestadoRepository;

@Service
public class ItemEmprestadoService {

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepository;

    public ItemEmprestado findById(Integer idItemEmprestado) {
        Optional<ItemEmprestado> itemEmprestadoOptional = itemEmprestadoRepository.findById(idItemEmprestado);

        return itemEmprestadoOptional.orElseThrow(() -> new RuntimeException(
                "Dono de item não encontrado! " + idItemEmprestado + " Tipo: " + ItemEmprestado.class.getName()));
    }

    public ItemEmprestado findByNomeItem(String nomeItem) {
        Optional<ItemEmprestado> itemEmprestadoOptional = itemEmprestadoRepository.findByNomeItem(nomeItem);

        if (itemEmprestadoOptional.isPresent()) {
            ItemEmprestado itemEmprestadoEncontrado = itemEmprestadoOptional.get();
            return itemEmprestadoEncontrado;
        }

        throw new RuntimeException("Deu ruim, não tem esse nome");
    }

}
