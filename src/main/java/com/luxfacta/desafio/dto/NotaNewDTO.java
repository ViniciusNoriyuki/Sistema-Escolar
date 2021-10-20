package com.luxfacta.desafio.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class NotaNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double valor;
    private Integer alunoId;
    private Integer disciplinaId;
}
