package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.dto.ProfessorDTO;
import com.luxfacta.desafio.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Professor> find(@PathVariable Integer id) {
        Professor professor = professorService.find(id);

        return ResponseEntity.ok().body(professor);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ProfessorDTO professorDTO) {
        Professor professor = professorService.fromDTO(professorDTO);
        professor = professorService.insert(professor);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professor.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{professorId}/disciplina/{disciplinaId}")
    public ResponseEntity<Void> insertDisciplinaInProfessor(@PathVariable Integer professorId, @PathVariable Integer disciplinaId) {
        Professor professor = professorService.find(professorId);
        professor = professorService.insertDisciplina(professor, disciplinaId);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professor.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ProfessorDTO professorDTO, @PathVariable Integer id) {
        Professor professor = professorService.fromDTO(professorDTO);
        professor.setId(id);
        professor = professorService.update(professor);

        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        professorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        List<Professor> list = professorService.findAll();
        List<ProfessorDTO> listDTO = list.stream().map(obj -> new ProfessorDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }
    
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ProfessorDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Professor> list = professorService.findPage(page, linesPerPage, orderBy, direction);
        Page<ProfessorDTO> listDTO = list.map(obj -> new ProfessorDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }
}
