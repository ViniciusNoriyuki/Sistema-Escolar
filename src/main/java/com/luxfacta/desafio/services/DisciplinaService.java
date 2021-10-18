package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.repositories.DisciplinaRepository;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina find(Integer id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

        return disciplina.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Disciplina.class.getName()));
    }

    public Disciplina insert(Disciplina disciplina) {
        disciplina.setId(null);

        return disciplinaRepository.save(disciplina);
    }
}
