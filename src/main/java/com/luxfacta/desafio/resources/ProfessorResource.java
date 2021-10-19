package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
