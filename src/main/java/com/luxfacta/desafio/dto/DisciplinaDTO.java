package com.luxfacta.desafio.dto;

import com.luxfacta.desafio.domain.Disciplina;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DisciplinaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre 3 e 80 caracteres")
    private String nome;

    public DisciplinaDTO(Disciplina disciplina){
        id = disciplina.getId();
        nome = disciplina.getNome();
    }
}
