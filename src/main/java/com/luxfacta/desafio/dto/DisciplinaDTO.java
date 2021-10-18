package com.luxfacta.desafio.dto;

import com.luxfacta.desafio.domain.Disciplina;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DisciplinaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public DisciplinaDTO(Disciplina disciplina){
        id = disciplina.getId();
        nome = disciplina.getNome();
    }
}
