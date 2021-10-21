package com.luxfacta.desafio.repositories;

import com.luxfacta.desafio.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Transactional(readOnly = true)
    Aluno findByEmail(String email);
}
