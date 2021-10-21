package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Nota;
import com.luxfacta.desafio.dto.NotaDTO;
import com.luxfacta.desafio.dto.NotaNewDTO;
import com.luxfacta.desafio.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notas")
public class NotaResource {

    @Autowired
    private NotaService notaService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Nota> find(@PathVariable Integer id) {
        Nota nota = notaService.find(id);

        return ResponseEntity.ok().body(nota);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody NotaNewDTO notaNewDTO) {
        Nota nota = notaService.fromDTO(notaNewDTO);
        nota = notaService.insert(nota);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nota.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody NotaDTO notaDTO, @PathVariable Integer id) {
        Nota nota = notaService.fromDTO(notaDTO);
        nota.setId(id);
        nota = notaService.update(nota);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        notaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll() {
        List<Nota> list = notaService.findAll();
        List<NotaDTO> listDTO = list.stream().map(obj -> new NotaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }
}
