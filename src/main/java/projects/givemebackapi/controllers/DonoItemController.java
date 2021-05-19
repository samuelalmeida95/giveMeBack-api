package projects.givemebackapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
