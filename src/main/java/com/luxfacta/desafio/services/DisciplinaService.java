package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.repositories.DisciplinaRepository;
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

    public Disciplina update(Disciplina disciplina) {
        find(disciplina.getId());

        return disciplinaRepository.save(disciplina);
    }

    public void delete(Integer id) {
        find(id);
        try {
            disciplinaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma disciplina que possui alunos, disciplinas e/ou notas.");
        }
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public Page<Disciplina> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return disciplinaRepository.findAll(pageRequest);
    }
}
