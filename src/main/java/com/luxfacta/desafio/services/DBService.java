package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.domain.Nota;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.repositories.DisciplinaRepository;
import com.luxfacta.desafio.repositories.NotaRepository;
import com.luxfacta.desafio.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDataBase() {

        Professor p1 = new Professor(null, "Prof João", "profjoao@gmail.com", bCryptPasswordEncoder.encode("123"));
        Professor p2 = new Professor(null, "Prof Paula", "profpaula@gmail.com", bCryptPasswordEncoder.encode("456"));

        Disciplina d1 = new Disciplina(null, "Matemática");
        Disciplina d2 = new Disciplina(null, "Geografia");
        Disciplina d3 = new Disciplina(null, "História");
        Disciplina d4 = new Disciplina(null, "Química");
        Disciplina d5 = new Disciplina(null, "Física");
        Disciplina d6 = new Disciplina(null, "Espanhol");
        Disciplina d7 = new Disciplina(null, "Ética e Cidadania");

        p1.setDisciplina(d1);
        p2.setDisciplina(d2);

        d1.setProfessor(p1);
        d2.setProfessor(p2);

        Aluno a1 = new Aluno(null, "Vinicius", "vinicius123@gmail.com", bCryptPasswordEncoder.encode("123"));
        Aluno a2 = new Aluno(null, "Pedro", "pedro123@gmail.com", bCryptPasswordEncoder.encode("456"));

        d1.getAlunos().addAll(Arrays.asList(a1, a2));
        d2.getAlunos().add((a1));

        a1.getDisciplinas().addAll(Arrays.asList(d1, d2));
        a2.getDisciplinas().add(d1);

        Nota n1 = new Nota(null, 9.0);
        Nota n2 = new Nota(null, 2.3);
        Nota n3 = new Nota(null, 4.5);
        Nota n4 = new Nota(null, 6.4);
        Nota n5 = new Nota(null, 3.2);
        Nota n6 = new Nota(null, 7.5);

        n1.setAluno(a1);
        n2.setAluno(a1);
        n3.setAluno(a1);
        n4.setAluno(a1);
        n5.setAluno(a2);
        n6.setAluno(a2);

        n1.setDisciplina(d1);
        n2.setDisciplina(d1);
        n3.setDisciplina(d2);
        n4.setDisciplina(d2);
        n5.setDisciplina(d1);
        n6.setDisciplina(d1);

        a1.getNotas().addAll(Arrays.asList(n1, n2, n3, n4));
        a2.getNotas().addAll(Arrays.asList(n5, n6));

        d1.getNotas().addAll(Arrays.asList(n1, n2));
        d2.getNotas().addAll(Arrays.asList(n3, n4));
        d1.getNotas().addAll(Arrays.asList(n5, n6));

        disciplinaRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5, d6, d7));
        alunoRepository.saveAll(Arrays.asList(a1, a2));
        professorRepository.saveAll(Arrays.asList(p1, p2));
        notaRepository.saveAll(Arrays.asList(n1, n2, n3, n4, n5, n6));
    }
}
