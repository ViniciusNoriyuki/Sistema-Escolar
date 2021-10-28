package com.luxfacta.desafio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AlunoDisciplinaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Min(value = 1, message = "Id do aluno deve ser maior que zero")
    private Integer alunoId;

    @Min(value = 1, message = "Id da disciplina deve ser maior que zero")
    private Integer disciplinaId;
}
