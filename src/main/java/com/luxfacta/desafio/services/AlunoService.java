package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.dto.AlunoDTO;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.services.exceptions.DataIntegrityException;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private DisciplinaService disciplinaService;

    public Aluno find(Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        return aluno.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
    }
    
    public Aluno insert(Aluno aluno) {
        aluno.setId(null);

        return alunoRepository.save(aluno);
    }

    public Aluno update(Aluno aluno) {
        Aluno newAluno = find(aluno.getId());
        updateData(newAluno, aluno);

        return alunoRepository.save(newAluno);
    }

    private void updateData(Aluno newAluno, Aluno aluno) {
        newAluno.setNome(aluno.getNome());
        newAluno.setEmail(aluno.getEmail());
    }

    public void delete(Integer id) {
        Aluno aluno = find(id);
        try {
            alunoRepository.deleteById(aluno.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um aluno que possui disciplinas ou notas.");
        }
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno fromDTO(AlunoDTO alunoDTO) {
        return new Aluno(alunoDTO.getId(), alunoDTO.getNome(), alunoDTO.getEmail());
    }

    public Aluno insertDisciplina(Aluno aluno, Integer disciplinaId) {
        Disciplina disciplina = disciplinaService.find(disciplinaId);
        aluno.getDisciplinas().add(disciplina);

        return update(aluno);
    }
}
