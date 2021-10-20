package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.domain.Nota;
import com.luxfacta.desafio.domain.Nota;
import com.luxfacta.desafio.dto.NotaDTO;
import com.luxfacta.desafio.repositories.NotaRepository;
import com.luxfacta.desafio.services.exceptions.DataIntegrityException;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota find(Integer id) {
        Optional<Nota> nota = notaRepository.findById(id);

        return nota.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Nota.class.getName()));
    }

    public Nota update(Nota nota) {
        Nota newNota = find(nota.getId());
        updateData(newNota, nota);

        return notaRepository.save(newNota);
    }

    private void updateData(Nota newNota, Nota nota) {
        newNota.setId(nota.getId());
        newNota.setValor(nota.getValor());
    }

    public void delete(Integer id) {
        Nota nota = find(id);
        try {
            notaRepository.deleteById(nota.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir a nota.");
        }
    }

    public List<Nota> findAll() {
        return notaRepository.findAll();
    }

    public Nota fromDTO(NotaDTO notaDTO) {
        return new Nota(notaDTO.getId(), notaDTO.getValor());
    }
}
