package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.dto.AlunoCompleteDTO;
import com.luxfacta.desafio.dto.AlunoDTO;
import com.luxfacta.desafio.dto.AlunoDisciplinaDTO;
import com.luxfacta.desafio.dto.AlunoNewDTO;
import com.luxfacta.desafio.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody AlunoNewDTO alunoNewDTO) {
        Aluno aluno = alunoService.fromDTO(alunoNewDTO);
        aluno = alunoService.insert(aluno);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/disciplina")
    public ResponseEntity<Void> insertDisciplinaInAluno(@Valid @RequestBody AlunoDisciplinaDTO alunoDisciplinaDTO) {
        Aluno aluno = alunoService.insertDisciplina(alunoDisciplinaDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody AlunoDTO alunoDTO, @PathVariable Integer id) {
        Aluno aluno = alunoService.fromDTO(alunoDTO);
        aluno.setId(id);
        aluno = alunoService.update(aluno);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        alunoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<Aluno> list = alunoService.findAll();
        List<AlunoDTO> listDTO = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<Aluno> find(@RequestParam(value = "value") String email) {
        Aluno aluno = alunoService.findByEmail(email);

        return ResponseEntity.ok().body(aluno);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<AlunoDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Aluno> list = alunoService.findPage(page, linesPerPage, orderBy, direction);
        Page<AlunoDTO> listDTO = list.map(obj -> new AlunoDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}/complete")
    public ResponseEntity<AlunoCompleteDTO> search(@PathVariable Integer id) {
        AlunoCompleteDTO alunoCompleteDTO = alunoService.search(id);

        return ResponseEntity.ok().body(alunoCompleteDTO);
    }

    @GetMapping(value = "/email/complete")
    public ResponseEntity<AlunoCompleteDTO> searchByEmail(@RequestParam(value = "value") String email) {
        AlunoCompleteDTO alunoCompleteDTO = alunoService.searchByEmail(email);

        return ResponseEntity.ok().body(alunoCompleteDTO);
    }
}
