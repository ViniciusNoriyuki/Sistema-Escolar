package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Nota;
import com.luxfacta.desafio.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notas")
public class NotaResource {

    @Autowired
    private NotaService notaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Nota> find(@PathVariable Integer id) {
        Nota nota = notaService.find(id);

        return ResponseEntity.ok().body(nota);
    }
}
