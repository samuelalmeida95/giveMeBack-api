package projects.givemebackapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import projects.givemebackapi.dtos.AmigoEmprestimoDTO;
import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.services.AmigoEmprestimoService;

@RestController
@RequestMapping(value = "/amigos")
public class AmigoEmprestimoController {

    @Autowired
    private AmigoEmprestimoService amigoEmprestimoService;

    @GetMapping(value = "/buscar_por_id/{id}")
    public ResponseEntity<AmigoEmprestimoDTO> findById(@PathVariable Integer id) {
        AmigoEmprestimo amigoEmprestimo = amigoEmprestimoService.findById(id);
        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(amigoEmprestimo);
        
        return ResponseEntity.ok(amigoDTO);
    }

    @GetMapping(value = "/buscar_por_nome")
    public ResponseEntity<AmigoEmprestimoDTO> findByNome(@RequestParam String amigoEmprestimo) {
        AmigoEmprestimo amigo = amigoEmprestimoService.findByNome(amigoEmprestimo);
        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(amigo);

        return ResponseEntity.ok().body(amigoDTO);
    }

    @GetMapping(value = "/buscar_melhores_avaliados")
    public ResponseEntity<List<AmigoEmprestimoDTO>> findByAvaliacao() {
        List<AmigoEmprestimo> listAmigos = amigoEmprestimoService.findByAvaliacao();

        List<AmigoEmprestimoDTO> listDTO = listAmigos
                .stream()
                .map(amigo -> new AmigoEmprestimoDTO(amigo))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/listar_todos")
    public ResponseEntity<List<AmigoEmprestimoDTO>> findAll() {
        List<AmigoEmprestimo> listAmigos = amigoEmprestimoService.findAll();

        List<AmigoEmprestimoDTO> listDTO = listAmigos
                .stream()
                .map(amigo -> new AmigoEmprestimoDTO(amigo))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping(value="/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public AmigoEmprestimoDTO create(@RequestBody AmigoEmprestimo amigoEmprestimo) {
        AmigoEmprestimo novoAmigoEmprestimo = amigoEmprestimoService.create(amigoEmprestimo);
        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(novoAmigoEmprestimo);

        return amigoDTO;
    }

    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<AmigoEmprestimoDTO> update(
        @PathVariable Integer id,
        @RequestBody AmigoEmprestimo amigoEmprestimo) {

        AmigoEmprestimo novoAmigoEmprestimo = amigoEmprestimoService.update(id, amigoEmprestimo);
        AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(novoAmigoEmprestimo);

        return ResponseEntity.ok().body(amigoDTO);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        amigoEmprestimoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
