package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.dto.DisciplinaDTO;
import com.luxfacta.desafio.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> findAll() {
        List<Disciplina> list = disciplinaService.findAll();
        List<DisciplinaDTO> listDTO = list.stream().map(obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
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

    @GetMapping(value = "/page")
    public ResponseEntity<Page<DisciplinaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Disciplina> list = disciplinaService.findPage(page, linesPerPage, orderBy, direction);
        Page<DisciplinaDTO> listDTO = list.map(obj -> new DisciplinaDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }
}
