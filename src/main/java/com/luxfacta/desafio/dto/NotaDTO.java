package com.luxfacta.desafio.dto;

import com.luxfacta.desafio.domain.Nota;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class NotaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Min(value = 0, message = "Nota deve ser maior ou igual a zero")
    @Max(value = 10, message = "Nota deve ser menor ou igual a dez")
    private Double valor;

    public NotaDTO(Nota nota) {
        id = nota.getId();
        valor = nota.getValor();
    }
}
