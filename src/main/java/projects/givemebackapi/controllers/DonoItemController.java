package projects.givemebackapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import projects.givemebackapi.dtos.DonoItemDTO;
import projects.givemebackapi.model.DonoItem;
import projects.givemebackapi.services.DonoItemService;

@RestController
@RequestMapping(value = "/donos")
public class DonoItemController {

  @Autowired
  private DonoItemService service;

  @GetMapping(value = "/buscar_por_id/{idDonoItem}")
  @ResponseStatus(HttpStatus.OK)
  public DonoItemDTO findById(@PathVariable Integer idDonoItem) {
    return new DonoItemDTO(service.findById(idDonoItem));
  }

  @GetMapping(value = "/buscar_por_nome")
  @ResponseStatus(HttpStatus.OK)
  public DonoItemDTO findByNome(@RequestParam String nomeDono) {
    return new DonoItemDTO(service.findByNome(nomeDono));
  }

  @GetMapping(value = "/listar_todos")
  @ResponseStatus(HttpStatus.OK)
  public List<DonoItemDTO> findAll() {
    List<DonoItem> listDonos = service.findAll();

    List<DonoItemDTO> listDTO = listDonos
      .stream()
      .map(dono -> new DonoItemDTO(dono))
      .collect(Collectors.toList());

    return listDTO;
  }

  @PostMapping(value = "/adicionar")
  @ResponseStatus(HttpStatus.CREATED)
  public DonoItemDTO create(@RequestBody DonoItem dono) {
    DonoItem novoDonoItem = service.create(dono);
    return new DonoItemDTO(novoDonoItem);
  }

  @PutMapping(value = "/alterar/{id}")
  @ResponseStatus(HttpStatus.OK)
  public DonoItemDTO update(@PathVariable Integer id, @RequestBody DonoItem dono) {
    return new DonoItemDTO(service.update(id, dono));
  }

  @DeleteMapping(value = "/deletar/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id) {
    service.delete(id);
  }
}
