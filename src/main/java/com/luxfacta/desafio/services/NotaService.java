package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Nota;
import com.luxfacta.desafio.repositories.NotaRepository;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota find(Integer id) {
        Optional<Nota> nota = notaRepository.findById(id);

        return nota.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Nota.class.getName()));
    }
}
