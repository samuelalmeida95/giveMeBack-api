package projects.givemebackapi.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
import projects.givemebackapi.dtos.CustomItemEmprestadoDTO;
import projects.givemebackapi.dtos.ItemEmprestadoDTO;
import projects.givemebackapi.model.AvaliacaoStatus;
import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;
import projects.givemebackapi.services.ItemEmprestadoService;

@RestController
@RequestMapping(value = "/itens")
public class ItemEmprestadoController {

  @Autowired
  private ItemEmprestadoService itemEmprestadoService;

  @GetMapping(value = "/buscar_por_id/{idItemEmprestado}")
  public ResponseEntity<ItemEmprestadoDTO> findById(
    @PathVariable Integer idItemEmprestado
  ) {
    ItemEmprestado itemEmprestado = itemEmprestadoService.findById(
      idItemEmprestado
    );
    return ResponseEntity.ok().body(new ItemEmprestadoDTO(itemEmprestado));
  }

  @GetMapping(value = "/buscar_por_nome")
  public ResponseEntity<ItemEmprestadoDTO> findByNome(
    @RequestParam String nomeItem
  ) {
    ItemEmprestado itemEmprestado = itemEmprestadoService.findByNome(nomeItem);
    return ResponseEntity.ok().body(new ItemEmprestadoDTO(itemEmprestado));
  }

  @GetMapping(value = "/emprestados_para{idAmigo}")
  public ResponseEntity<List<CustomItemEmprestadoDTO>> findByEmprestadoPara(
    @RequestParam Integer idAmigo
  ) {
    List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findByEmprestadoPara(
      idAmigo
    );

    List<CustomItemEmprestadoDTO> listDTO = listItensEmprestado
      .stream()
      .map(item -> new CustomItemEmprestadoDTO(item))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDTO);
  }

  @GetMapping(value = "/meus_itens{idDono}")
  public ResponseEntity<List<ItemEmprestadoDTO>> findByDono(
    @RequestParam Integer idDono
  ) {
    List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findByDono(
      idDono
    );

    List<ItemEmprestadoDTO> listDTO = listItensEmprestado
      .stream()
      .map(item -> new ItemEmprestadoDTO(item))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDTO);
  }

  @GetMapping(value = "/status_itens")
  public ResponseEntity<List<ItemEmprestadoDTO>> findByStatus(
    @RequestParam TipoStatus status
  ) {
    List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findByStatus(
      status
    );

    List<ItemEmprestadoDTO> listDTO = listItensEmprestado
      .stream()
      .map(item -> new ItemEmprestadoDTO(item))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDTO);
  }

  @GetMapping(value = "/listar_itens")
  public ResponseEntity<List<ItemEmprestadoDTO>> findAll() {
    List<ItemEmprestado> listItensEmprestado = itemEmprestadoService.findAll();

    List<ItemEmprestadoDTO> listDTO = listItensEmprestado
      .stream()
      .map(item -> new ItemEmprestadoDTO(item))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDTO);
  }

  @PutMapping(value = "/alterar/{id}")
  public ResponseEntity<ItemEmprestadoDTO> update(
    @PathVariable Integer id,
    @Valid @RequestBody ItemEmprestado item
  ) {
    ItemEmprestado novoItem = itemEmprestadoService.update(id, item);
    return ResponseEntity.ok().body(new ItemEmprestadoDTO(novoItem));
  }

  @PostMapping(value = "/emprestar_item")
  @ResponseStatus(HttpStatus.CREATED)
  public ItemEmprestadoDTO emprestarItem(
    @RequestParam Integer dono,
    @RequestParam Integer idAmigoEmprestimo,
    @Valid 
    @RequestBody ItemEmprestado item
  ) {
    return new ItemEmprestadoDTO(
      itemEmprestadoService.criarEmprestarNovoItem(
        item,
        dono,
        idAmigoEmprestimo
      )
    );
  }

  @PutMapping(value = "/devolver/{idItem}")
  public ResponseEntity<ItemEmprestadoDTO> devolverAvaliar(
    @PathVariable Integer idItem,
    @RequestParam String nomeAmigo,
    @RequestParam AvaliacaoStatus avaliacao
  ) {
    ItemEmprestado novoItem = itemEmprestadoService.devolverAvaliar(
      idItem,
      nomeAmigo,
      avaliacao
    );
    return ResponseEntity.ok().body(new ItemEmprestadoDTO(novoItem));
  }

  @PutMapping(value = "/emprestar_novamente")
  public ResponseEntity<ItemEmprestadoDTO> giveInAgain(
    @RequestParam Integer idItem,
    @RequestParam Integer idAmigoEmprestimo
  ) {
    ItemEmprestado novoItemEmprestado = itemEmprestadoService.emprestarItemNovamente(
      idItem,
      idAmigoEmprestimo
    );
    return ResponseEntity.ok().body(new ItemEmprestadoDTO(novoItemEmprestado));
  }

  @DeleteMapping(value = "/deletar/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    itemEmprestadoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
