package projects.givemebackapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.services.ItemEmprestadoService;

@RestController
@RequestMapping(value = "/itens")
public class ItemEmprestadoController {

    @Autowired
    private ItemEmprestadoService itemEmprestadoService;


    @GetMapping(value = "/{idItemEmprestado}")
    public ResponseEntity<ItemEmprestado> findById(@PathVariable Integer idItemEmprestado) {
        ItemEmprestado itemEmprestado = itemEmprestadoService.findById(idItemEmprestado);
        
        return ResponseEntity.ok().body(itemEmprestado);
    }


    @GetMapping(value = "/search")
    public ResponseEntity<ItemEmprestado> searchByNomeItem(@RequestParam String nomeItem) {
        ItemEmprestado itemEmprestado = itemEmprestadoService.findByNomeItem(nomeItem);

        return ResponseEntity.ok().body(itemEmprestado);
    }


    @GetMapping(value = "/list")
    public ResponseEntity<List<ItemEmprestado>> findAll(){
        List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findAll();

        return ResponseEntity.ok().body(listItensEmprestado);
    }


    @PostMapping
    public ResponseEntity<ItemEmprestado> emprestarItem(
        @RequestParam Integer dono, 
        @RequestParam Integer idAmigoEmprestimo, 
        @RequestBody ItemEmprestado item) {

        ItemEmprestado novoItemEmprestado = itemEmprestadoService.emprestarItem(item, dono, idAmigoEmprestimo);

        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(novoItemEmprestado.getIdItem())
        .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemEmprestado> update(
        @PathVariable Integer id, 
        @RequestBody ItemEmprestado item) {

        ItemEmprestado novoitem = itemEmprestadoService.update(id, item);

        return ResponseEntity.ok().body(novoitem);
    }


    @PutMapping(value = "/devolver/{idItem}")
    public ResponseEntity<ItemEmprestado> devolver(@PathVariable Integer idItem) {
        ItemEmprestado novoitem = itemEmprestadoService.devolver(idItem);

        return ResponseEntity.ok().body(novoitem);
    }
}
