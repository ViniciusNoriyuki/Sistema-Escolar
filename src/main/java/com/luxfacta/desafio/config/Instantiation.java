package com.luxfacta.desafio.config;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public void run(String... args) throws Exception {

        Disciplina d1 = new Disciplina(null, "Matemática");
        Disciplina d2 = new Disciplina(null, "Geografia");
        Disciplina d3 = new Disciplina(null, "História");

        disciplinaRepository.saveAll(Arrays.asList(d1, d2, d3));
    }
}
