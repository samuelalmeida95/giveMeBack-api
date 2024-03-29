package projects.givemebackapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projects.givemebackapi.dtos.AmigoEmprestimoDTO;
import projects.givemebackapi.model.AmigoEmprestimo;
import projects.givemebackapi.services.AmigoEmprestimoService;

@RestController
@RequestMapping(value = "/amigos")
public class AmigoEmprestimoController {

  @Autowired
  private AmigoEmprestimoService service;

  @GetMapping(value = "/buscar_por_id/{id}")
  public ResponseEntity<AmigoEmprestimoDTO> findById(@PathVariable Integer id) {
    AmigoEmprestimo amigoEmprestimo = service.findById(id);

    return ResponseEntity.ok(new AmigoEmprestimoDTO(amigoEmprestimo));
  }

  @GetMapping(value = "/buscar_por_nome")
  public ResponseEntity<AmigoEmprestimoDTO> findByNome(
    @RequestParam String amigoEmprestimo
  ) {
    return ResponseEntity
      .ok()
      .body(new AmigoEmprestimoDTO(service.findByNome(amigoEmprestimo)));
  }

  @GetMapping(value = "/buscar_por_dono_item")
  public ResponseEntity<AmigoEmprestimoDTO> findyByIdDonoAndIdAmigoEmprestimo(
    @RequestParam Integer idAmigo,
    @RequestParam Integer idDono
  ) {
    return ResponseEntity
      .ok()
      .body(
        new AmigoEmprestimoDTO(
          service.findyByIdDonoAndIdAmigoEmprestimo(idAmigo, idDono)
        )
      );
  }

  @GetMapping(value = "/buscar_melhores_avaliados")
  public ResponseEntity<List<AmigoEmprestimoDTO>> findByAvaliacao() {
    List<AmigoEmprestimo> listAmigos = service.findByAvaliacao();

    List<AmigoEmprestimoDTO> listDTO = listAmigos
      .stream()
      .map(AmigoEmprestimoDTO::new)
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDTO);
  }

  @GetMapping(value = "/buscar_piores_avaliados")
  public ResponseEntity<List<AmigoEmprestimoDTO>> findByPioresAvaliados() {
    List<AmigoEmprestimo> listAmigos = service.findByPioresAvaliados();

    List<AmigoEmprestimoDTO> listDTO = listAmigos
      .stream()
      .map(AmigoEmprestimoDTO::new)
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDTO);
  }

  @GetMapping(value = "/listar_todos")
  public ResponseEntity<List<AmigoEmprestimoDTO>> findAll() {
    List<AmigoEmprestimo> listAmigos = service.findAll();

    List<AmigoEmprestimoDTO> listDTO = listAmigos
      .stream()
      .map(AmigoEmprestimoDTO::new)
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDTO);
  }

  @PostMapping(value = "/adicionar")
  public ResponseEntity<AmigoEmprestimoDTO> create(
    @RequestBody AmigoEmprestimo amigoEmprestimo,
    @RequestParam String nomeDono
  ) {
    AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(
      service.create(amigoEmprestimo, nomeDono)
    );

    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(amigoDTO.getId())
      .toUri();

    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/alterar/{id}")
  public ResponseEntity<AmigoEmprestimoDTO> update(
    @PathVariable Integer id,
    @RequestBody AmigoEmprestimo amigoEmprestimo
  ) {
    return ResponseEntity
      .ok()
      .body(new AmigoEmprestimoDTO(service.update(id, amigoEmprestimo)));
  }

  @DeleteMapping(value = "/deletar/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
