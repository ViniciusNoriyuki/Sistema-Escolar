package com.luxfacta.desafio.dto;

import com.luxfacta.desafio.domain.Aluno;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AlunoCompleteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private List<NotaViewDTO> notas = new ArrayList<>();

    public AlunoCompleteDTO(Aluno aluno) {
        id = aluno.getId();
        nome = aluno.getNome();
        email = aluno.getEmail();
    }
}
