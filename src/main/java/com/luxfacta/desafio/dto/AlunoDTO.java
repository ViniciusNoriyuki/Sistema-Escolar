package com.luxfacta.desafio.dto;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.services.validation.AlunoUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AlunoUpdate
public class AlunoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre 3 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public AlunoDTO(Aluno aluno) {
        id = aluno.getId();
        nome = aluno.getNome();
        email = aluno.getEmail();
    }
}
