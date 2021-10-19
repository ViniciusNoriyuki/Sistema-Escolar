package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno find(Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        return aluno.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
    }
}
