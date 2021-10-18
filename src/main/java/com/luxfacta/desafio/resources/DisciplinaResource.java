package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
