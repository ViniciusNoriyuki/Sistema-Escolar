package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.repositories.ProfessorRepository;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor find(Integer id) {
        Optional<Professor> professor = professorRepository.findById(id);

        return professor.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Professor.class.getName()));
    }
}
