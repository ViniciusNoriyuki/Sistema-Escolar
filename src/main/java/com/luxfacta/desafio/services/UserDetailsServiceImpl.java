package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.repositories.ProfessorRepository;
import com.luxfacta.desafio.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Professor professor = professorRepository.findByEmail(email);
        Aluno aluno = alunoRepository.findByEmail(email);

        if (professor != null) {
            return new UserSS(professor.getId(), professor.getEmail(), professor.getSenha(), professor.getPerfis());
        } else if (aluno != null) {
            return new UserSS(aluno.getId(), aluno.getEmail(), aluno.getSenha(), aluno.getPerfis());
        } else {
            throw new UsernameNotFoundException(email);
        }
    }
}