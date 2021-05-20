package projects.givemebackapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
