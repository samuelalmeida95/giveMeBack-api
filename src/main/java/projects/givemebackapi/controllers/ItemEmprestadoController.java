package projects.givemebackapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import projects.givemebackapi.dtos.CustomItemEmprestadoDTO;
import projects.givemebackapi.dtos.ItemEmprestadoDTO;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.services.ItemEmprestadoService;

@RestController
@RequestMapping(value = "/itens")
public class ItemEmprestadoController {

    @Autowired
    private ItemEmprestadoService itemEmprestadoService;

    @GetMapping(value = "/{idItemEmprestado}")
    public ResponseEntity<ItemEmprestadoDTO> findById(@PathVariable Integer idItemEmprestado) {
        ItemEmprestado itemEmprestado = itemEmprestadoService.findById(idItemEmprestado);
        ItemEmprestadoDTO itemDTO = new ItemEmprestadoDTO(itemEmprestado);

        return ResponseEntity.ok().body(itemDTO);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ItemEmprestadoDTO> findByNome(@RequestParam String nomeItem) {
        ItemEmprestado itemEmprestado = itemEmprestadoService.findByNome(nomeItem);
        ItemEmprestadoDTO itemDTO = new ItemEmprestadoDTO(itemEmprestado);

        return ResponseEntity.ok().body(itemDTO);
    }

    @GetMapping(value = "/emprestados_para{idAmigo}")
    public ResponseEntity<List<CustomItemEmprestadoDTO>> findByEmprestadoPara(@RequestParam Integer idAmigo) {
        List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findByEmprestadoPara(idAmigo);
      
       List<CustomItemEmprestadoDTO> listDTO = listItensEmprestado
       .stream()
       .map(item -> new CustomItemEmprestadoDTO(item))
       .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }


    @GetMapping(value = "/status_item")
    public ResponseEntity<List<ItemEmprestadoDTO>> findByStatus(@RequestParam TipoStatus status) {
        List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findByStatus(status);

        List<ItemEmprestadoDTO> listDTO = listItensEmprestado
                .stream()
                .map(item -> new ItemEmprestadoDTO(item))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }


    @GetMapping(value = "/list")
    public ResponseEntity<List<ItemEmprestadoDTO>> findAll() {
        List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findAll();

        List<ItemEmprestadoDTO> listDTO = listItensEmprestado
                .stream()
                .map(item -> new ItemEmprestadoDTO(item))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemEmprestadoDTO> update(
        @PathVariable Integer id, 
        @Valid 
        @RequestBody ItemEmprestado item) {

        ItemEmprestado novoItem = itemEmprestadoService.update(id, item);
        ItemEmprestadoDTO itemDTO = new ItemEmprestadoDTO(novoItem);

        return ResponseEntity.ok().body(itemDTO);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemEmprestadoDTO emprestarItem(
        @RequestParam Integer dono, 
        @RequestParam Integer idAmigoEmprestimo,
        @Valid 
        @RequestBody ItemEmprestado item) {

        ItemEmprestado novoItemEmprestado = itemEmprestadoService.emprestarItem(item, dono, idAmigoEmprestimo);
        ItemEmprestadoDTO itemDTO = new ItemEmprestadoDTO(novoItemEmprestado);

        return itemDTO;
    }


    @PutMapping(value = "/devolver/{idItem}")
    public ResponseEntity<ItemEmprestadoDTO> devolver(@PathVariable Integer idItem) {
        ItemEmprestado novoItem = itemEmprestadoService.devolver(idItem);
        ItemEmprestadoDTO itemDTO = new ItemEmprestadoDTO(novoItem);

        return ResponseEntity.ok().body(itemDTO);
    }
    

    @PutMapping(value = "/giveInAgain")
    public ResponseEntity<ItemEmprestadoDTO> giveInAgain(
        @RequestParam Integer idItem,  
        @RequestParam Integer idAmigoEmprestimo) {

        ItemEmprestado novoItemEmprestado = itemEmprestadoService.giveInAgain(idItem, idAmigoEmprestimo);
        ItemEmprestadoDTO itemDTO = new ItemEmprestadoDTO(novoItemEmprestado);

        return ResponseEntity.ok().body(itemDTO);
    }
}
