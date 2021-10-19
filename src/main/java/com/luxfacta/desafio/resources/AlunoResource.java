package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.dto.AlunoDTO;
import com.luxfacta.desafio.dto.ProfessorDTO;
import com.luxfacta.desafio.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> find(@PathVariable Integer id) {
        Aluno aluno = alunoService.find(id);

        return ResponseEntity.ok().body(aluno);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = alunoService.fromDTO(alunoDTO);
        aluno = alunoService.insert(aluno);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{alunoId}/disciplina/{disciplinaId}")
    public ResponseEntity<Void> insertDisciplinaInAluno(@PathVariable Integer alunoId, @PathVariable Integer disciplinaId) {
        Aluno aluno = alunoService.find(alunoId);
        aluno = alunoService.insertDisciplina(aluno, disciplinaId);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody AlunoDTO alunoDTO, @PathVariable Integer id) {
        Aluno aluno = alunoService.fromDTO(alunoDTO);
        aluno.setId(id);
        aluno = alunoService.update(aluno);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        alunoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<Aluno> list = alunoService.findAll();
        List<AlunoDTO> listDTO = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }
}
