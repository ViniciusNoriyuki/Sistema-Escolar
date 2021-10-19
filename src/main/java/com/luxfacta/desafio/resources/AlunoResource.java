package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
