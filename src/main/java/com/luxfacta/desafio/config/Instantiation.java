package com.luxfacta.desafio.config;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.repositories.DisciplinaRepository;
import com.luxfacta.desafio.repositories.ProfessorRepository;
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
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void run(String... args) throws Exception {

        Professor p1 = new Professor(null, "Prof João", "profjoao@gmail.com");
        Professor p2 = new Professor(null, "Prof Paula", "profpaula@gmail.com");

        Disciplina d1 = new Disciplina(null, "Matemática");
        Disciplina d2 = new Disciplina(null, "Geografia");

        p1.setDisciplina(d1);
        p2.setDisciplina(d2);

        d1.setProfessor(p1);
        d2.setProfessor(p2);

        Aluno a1 = new Aluno(null, "Vinicius", "vinicius123@gmail.com");
        Aluno a2 = new Aluno(null, "Pedro", "pedro123@gmail.com");

        d1.getAlunos().addAll(Arrays.asList(a1, a2));
        d2.getAlunos().add((a1));

        a1.getDisciplinas().addAll(Arrays.asList(d1, d2));
        a2.getDisciplinas().add(d1);

        disciplinaRepository.saveAll(Arrays.asList(d1, d2));
        alunoRepository.saveAll(Arrays.asList(a1, a2));
        professorRepository.saveAll(Arrays.asList(p1, p2));
    }
}
