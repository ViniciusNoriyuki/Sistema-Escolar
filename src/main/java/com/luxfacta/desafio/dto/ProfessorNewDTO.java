package com.luxfacta.desafio.dto;

import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.services.validation.ProfessorInsert;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ProfessorInsert
public class ProfessorNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre 3 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String senha;

    public ProfessorNewDTO(Professor professor) {
        id = professor.getId();
        nome = professor.getNome();
        email = professor.getEmail();
    }
}
