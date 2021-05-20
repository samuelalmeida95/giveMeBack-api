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

import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.services.DonoItemService;

@RestController
@RequestMapping(value = "/donos")
public class DonoItemController {
    
    @Autowired
    private DonoItemService donoItemService;


    @GetMapping(value = "/{idDonoItem}")
    public ResponseEntity<DonoItem> findById(@PathVariable Integer idDonoItem) {
        DonoItem dono = donoItemService.findById(idDonoItem);
        return ResponseEntity.ok(dono);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<DonoItem> findByNomeDono(@RequestParam String nomeDono) {
        DonoItem dono = donoItemService.findByNomeDono(nomeDono);
        return ResponseEntity.ok().body(dono);
    }

    @GetMapping
    public ResponseEntity<List<DonoItem>> findAll() {
        List<DonoItem> listDonos = donoItemService.findAll();
        return ResponseEntity.ok().body(listDonos);
    }

    @PostMapping
    public ResponseEntity<DonoItem> createNewDono(@RequestBody DonoItem dono) {
        dono = donoItemService.createNewDono(dono);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dono.getIdDono()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DonoItem> update(@PathVariable Integer id, @RequestBody DonoItem dono) {
        DonoItem newDono = donoItemService.updateDono(id, dono);
        return ResponseEntity.ok().body(newDono);
    }
}
