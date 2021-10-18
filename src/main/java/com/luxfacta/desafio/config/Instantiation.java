package com.luxfacta.desafio.config;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void run(String... args) throws Exception {

        Disciplina d1 = new Disciplina(null, "Matemática");
        Disciplina d2 = new Disciplina(null, "Geografia");

        Aluno a1 = new Aluno(null, "Vinicius", "vinicius123@gmail.com");
        Aluno a2 = new Aluno(null, "Pedro", "pedro123@gmail.com");

        d1.getAlunos().addAll(Arrays.asList(a1, a2));
        d2.getAlunos().add((a1));

        a1.getDisciplinas().addAll(Arrays.asList(d1, d2));
        a2.getDisciplinas().add(d1);

        disciplinaRepository.saveAll(Arrays.asList(d1, d2));
        alunoRepository.saveAll(Arrays.asList(a1, a2));
    }
}
