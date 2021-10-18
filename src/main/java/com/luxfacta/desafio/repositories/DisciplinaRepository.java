package com.luxfacta.desafio.repositories;

import com.luxfacta.desafio.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
