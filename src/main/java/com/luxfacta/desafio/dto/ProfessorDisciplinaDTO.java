package com.luxfacta.desafio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProfessorDisciplinaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Min(value = 1, message = "Id do professor deve ser maior que zero")
    private Integer professorId;

    @Min(value = 1, message = "Id da disciplina deve ser maior que zero")
    private Integer disciplinaId;
}
