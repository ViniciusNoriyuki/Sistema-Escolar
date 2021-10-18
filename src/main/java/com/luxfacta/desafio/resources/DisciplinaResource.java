package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Disciplina> find(@PathVariable Integer id) {
        Disciplina disciplina = disciplinaService.find(id);

        return ResponseEntity.ok().body(disciplina);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Disciplina disciplina) {
        disciplina = disciplinaService.insert(disciplina);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(disciplina.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Disciplina disciplina) {
        disciplina.setId(id);
        disciplina = disciplinaService.update(disciplina);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        disciplinaService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
