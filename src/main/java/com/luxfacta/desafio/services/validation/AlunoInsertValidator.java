package com.luxfacta.desafio.services.validation;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.dto.AlunoNewDTO;
import com.luxfacta.desafio.dto.ProfessorNewDTO;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.repositories.ProfessorRepository;
import com.luxfacta.desafio.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunoNewDTO> {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void initialize(AlunoInsert ann) {}

    @Override
    public boolean isValid(AlunoNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Aluno aux = alunoRepository.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
