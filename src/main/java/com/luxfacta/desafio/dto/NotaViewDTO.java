package com.luxfacta.desafio.dto;

import com.luxfacta.desafio.domain.Disciplina;
import com.luxfacta.desafio.domain.Nota;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class NotaViewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Double valor;
    private Disciplina disciplina;

    public NotaViewDTO(Nota nota) {
        id = nota.getId();
        valor = nota.getValor();
        disciplina = nota.getDisciplina();
    }
}
