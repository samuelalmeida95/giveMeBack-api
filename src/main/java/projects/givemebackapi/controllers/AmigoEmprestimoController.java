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

import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.services.AmigoEmprestimoService;

@RestController
@RequestMapping(value = "/amigos")
public class AmigoEmprestimoController {

    @Autowired
    private AmigoEmprestimoService amigoEmprestimoService;


    @GetMapping(value = "/{idAmigoEmprestimo}")
    public ResponseEntity<AmigoEmprestimo> findById(@PathVariable Integer idAmigoEmprestimo) {
        AmigoEmprestimo amigoEmprestimo = amigoEmprestimoService.findById(idAmigoEmprestimo);
        return ResponseEntity.ok(amigoEmprestimo);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<AmigoEmprestimo> findByNome(@RequestParam String amigoEmprestimo) {
        AmigoEmprestimo amigo = amigoEmprestimoService.findByNome(amigoEmprestimo);
        return ResponseEntity.ok().body(amigo);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<AmigoEmprestimo>> findAll() {
        List<AmigoEmprestimo> listAmigos = amigoEmprestimoService.findAll();
        return ResponseEntity.ok().body(listAmigos);
    }

    @PostMapping
    public ResponseEntity<AmigoEmprestimo> create(@RequestBody AmigoEmprestimo amigoEmprestimo) {

        AmigoEmprestimo novoAmigoEmprestimo = amigoEmprestimoService.create(amigoEmprestimo);

        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(novoAmigoEmprestimo.getId())
        .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AmigoEmprestimo> update(@PathVariable Integer id, @RequestBody AmigoEmprestimo amigoEmprestimo) {
        AmigoEmprestimo novoAmigoEmprestimo = amigoEmprestimoService.update(id, amigoEmprestimo);
        return ResponseEntity.ok().body(novoAmigoEmprestimo);
    }
    
}
