package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.dto.ProfessorDTO;
import com.luxfacta.desafio.repositories.ProfessorRepository;
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
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DisciplinaService disciplinaService;

    public Professor find(Integer id) {
        Optional<Professor> professor = professorRepository.findById(id);

        return professor.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Professor.class.getName()));
    }

    public Professor insert(Professor professor) {
        professor.setId(null);

        return professorRepository.save(professor);
    }
    
    public Professor update(Professor professor) {
        Professor newProfessor = find(professor.getId());
        updateData(newProfessor, professor);

        return professorRepository.save(newProfessor);
    }

    private void updateData(Professor newProfessor, Professor professor) {
        newProfessor.setNome(professor.getNome());
        newProfessor.setEmail(professor.getEmail());
    }

    public void delete(Integer id) {
        Professor professor = find(id);
        try {
            professorRepository.deleteById(professor.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um professor que possui disciplina.");
        }
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Page<Professor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return professorRepository.findAll(pageRequest);
    }

    public Professor fromDTO(ProfessorDTO professorDTO) {
        return new Professor(professorDTO.getId(), professorDTO.getNome(), professorDTO.getEmail());
    }

    public Professor insertDisciplina(Professor professor, Integer disciplinaId) {
        Disciplina disciplina = disciplinaService.find(disciplinaId);
        professor.setDisciplina(disciplina);

        return update(professor);
    }
}
