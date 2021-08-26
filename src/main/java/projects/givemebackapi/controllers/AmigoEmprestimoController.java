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
  private AmigoEmprestimoService amigoEmprestimoService;

  @GetMapping(value = "/buscar_por_id/{id}")
  public ResponseEntity<AmigoEmprestimoDTO> findById(@PathVariable Integer id) {

    AmigoEmprestimo amigoEmprestimo = amigoEmprestimoService.findById(id);

    return ResponseEntity.ok(new AmigoEmprestimoDTO(amigoEmprestimo));
  }

  @GetMapping(value = "/buscar_por_nome")
  public ResponseEntity<AmigoEmprestimoDTO> findByNome(
    @RequestParam String amigoEmprestimo) {

    AmigoEmprestimo amigo = amigoEmprestimoService.findByNome(amigoEmprestimo);

    return ResponseEntity.ok().body(new AmigoEmprestimoDTO(amigo));
  }

  @GetMapping(value = "/buscar_por_dono_item")
  public ResponseEntity<AmigoEmprestimoDTO> findyByIdDonoAndIdAmigoEmprestimo(
    @RequestParam Integer idAmigo,
    @RequestParam Integer idDono) {

    AmigoEmprestimo amigo = amigoEmprestimoService.findyByIdDonoAndIdAmigoEmprestimo(idAmigo,idDono);

    return ResponseEntity.ok().body(new AmigoEmprestimoDTO(amigo));
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

  @GetMapping(value = "/buscar_piores_avaliados")
  public ResponseEntity<List<AmigoEmprestimoDTO>> findByPioresAvaliados() {
    List<AmigoEmprestimo> listAmigos = amigoEmprestimoService.findByPioresAvaliados();

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

  @PostMapping(value = "/adicionar")
  public ResponseEntity<AmigoEmprestimoDTO> create(
    @RequestBody AmigoEmprestimo amigoEmprestimo,
    @RequestParam String nomeDono) {

    AmigoEmprestimo novoAmigoEmprestimo = amigoEmprestimoService.create(amigoEmprestimo,nomeDono);
    AmigoEmprestimoDTO amigoDTO = new AmigoEmprestimoDTO(novoAmigoEmprestimo);

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
    @RequestBody AmigoEmprestimo amigoEmprestimo) {

    AmigoEmprestimo novoAmigoEmprestimo = amigoEmprestimoService.update(id, amigoEmprestimo);

    return ResponseEntity.ok().body(new AmigoEmprestimoDTO(novoAmigoEmprestimo));
  }

  @DeleteMapping(value = "/deletar/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    amigoEmprestimoService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
