package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.domain.Nota;
import com.luxfacta.desafio.domain.enums.Perfil;
import com.luxfacta.desafio.dto.*;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.security.UserSS;
import com.luxfacta.desafio.services.exceptions.AuthorizationException;
import com.luxfacta.desafio.services.exceptions.DataIntegrityException;
import com.luxfacta.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;

    public Aluno find(Integer id) {
        UserSS user = UserService.authenticated();

        if (user == null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Aluno> aluno = alunoRepository.findById(id);

        return aluno.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
    }
    
    public Aluno insert(Aluno aluno) {
        aluno.setId(null);

        aluno = alunoRepository.save(aluno);
        emailService.sendAlunoConfirmationEmail(aluno);

        return aluno;
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

    public Aluno findByEmail(String email) {
        UserSS user = UserService.authenticated();

        if (user == null || !user.hasHole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Aluno obj = alunoRepository.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Aluno.class.getName());
        }

        return obj;
    }

    public Page<Aluno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return alunoRepository.findAll(pageRequest);
    }

    public Aluno fromDTO(AlunoDTO alunoDTO) {
        return new Aluno(alunoDTO.getId(), alunoDTO.getNome(), alunoDTO.getEmail(), null);
    }

    public Aluno fromDTO(AlunoNewDTO alunoNewDTO) {
        return new Aluno(alunoNewDTO.getId(), alunoNewDTO.getNome(), alunoNewDTO.getEmail(), bCryptPasswordEncoder.encode(alunoNewDTO.getSenha()));
    }

    public Aluno insertDisciplina(AlunoDisciplinaDTO alunoDisciplinaDTO) {
        Aluno aluno = find(alunoDisciplinaDTO.getAlunoId());
        Disciplina disciplina = disciplinaService.find(alunoDisciplinaDTO.getDisciplinaId());
        aluno.getDisciplinas().add(disciplina);

        return update(aluno);
    }

    public AlunoCompleteDTO search(Integer id) {
        Aluno aluno = find(id);
        AlunoCompleteDTO alunoCompleteDTO = new AlunoCompleteDTO(aluno);

        for (Nota nota : aluno.getNotas()) {
            alunoCompleteDTO.getNotas().add(new NotaViewDTO(nota));
        }

        for (Disciplina disciplina : aluno.getDisciplinas()) {
            alunoCompleteDTO.getDisciplinas().add(disciplina);
        }

        return alunoCompleteDTO;
    }

    public AlunoCompleteDTO searchByEmail(String email) {
        Aluno aluno = findByEmail(email);

        return search(aluno.getId());
    }

    public List<Aluno> findByDisciplina(Integer disciplinaId) {
        Disciplina disciplina = disciplinaService.find(disciplinaId);
        List<Aluno> listAlunos = findAll();
        List<Aluno> listAlunoDisciplina = new ArrayList<>();

        for (Aluno aluno : listAlunos) {
            for (Disciplina disciplinaAluno : aluno.getDisciplinas()) {
                if (disciplinaAluno.equals(disciplina)) {
                    listAlunoDisciplina.add(aluno);
                }
            }
        }
        return listAlunoDisciplina;
    }
}
