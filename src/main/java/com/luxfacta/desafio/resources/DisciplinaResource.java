package com.luxfacta.desafio.resources;

import com.luxfacta.desafio.domain.Disciplina;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {

    @GetMapping
    public List<Disciplina> listar() {

        Disciplina d1 = new Disciplina(1, "Matemática");
        Disciplina d2 = new Disciplina(2, "Português");

        List<Disciplina> lista = new ArrayList<>();
        lista.add(d1);
        lista.add(d2);

        return lista;
    }
}
