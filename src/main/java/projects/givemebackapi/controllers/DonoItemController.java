package projects.givemebackapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import projects.givemebackapi.dtos.DonoItemDTO;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.services.DonoItemService;

@RestController
@RequestMapping(value = "/donos")
public class DonoItemController {

    @Autowired
    private DonoItemService donoItemService;

    @GetMapping(value = "/{idDonoItem}")
    public ResponseEntity<DonoItemDTO> findById(@PathVariable Integer idDonoItem) {
        DonoItem donoItem = donoItemService.findById(idDonoItem);
        DonoItemDTO donoDTO = new DonoItemDTO(donoItem);

        return ResponseEntity.ok(donoDTO);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<DonoItemDTO> findByNomeDono(@RequestParam String nomeDono) {
        DonoItem donoItem = donoItemService.findByNomeDono(nomeDono);
        DonoItemDTO donoDTO = new DonoItemDTO(donoItem);

        return ResponseEntity.ok().body(donoDTO);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<DonoItemDTO>> findAll() {
        List<DonoItem> listDonos = donoItemService.findAll();
        List<DonoItemDTO> listDTO = listDonos.stream().map(dono -> new DonoItemDTO(dono)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DonoItemDTO createNewDono(@RequestBody DonoItem dono) {
        DonoItem novoDonoItem = donoItemService.createNewDono(dono);
        DonoItemDTO donoDTO = new DonoItemDTO(novoDonoItem);

        return donoDTO;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DonoItemDTO> update(@PathVariable Integer id, @RequestBody DonoItem dono) {
        DonoItem novoDonoItem = donoItemService.updateDono(id, dono);
        DonoItemDTO donoDTO = new DonoItemDTO(novoDonoItem);

        return ResponseEntity.ok().body(donoDTO);
    }
}
