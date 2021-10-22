package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.repositories.ProfessorRepository;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {

        Professor professor = professorRepository.findByEmail(email);
        Aluno aluno = alunoRepository.findByEmail(email);

        if (professor != null) {
            String newPass = newPassword();
            professor.setSenha(pe.encode(newPass));
            professorRepository.save(professor);
            emailService.sendProfessorNewPasswordEmail(professor, newPass);
        } else if (aluno != null) {
            String newPass = newPassword();
            aluno.setSenha(pe.encode(newPass));
            alunoRepository.save(aluno);
            emailService.sendAlunoNewPasswordEmail(aluno, newPass);
        } else {
            throw new ObjectNotFoundException("Email não encontrado!");
        }
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) {
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) {
            return (char) (rand.nextInt(26) + 65);
        } else {
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
