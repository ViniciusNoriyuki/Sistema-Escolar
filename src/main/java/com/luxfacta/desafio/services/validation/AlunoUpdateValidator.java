package com.luxfacta.desafio.services.validation;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.dto.AlunoDTO;
import com.luxfacta.desafio.dto.ProfessorDTO;
import com.luxfacta.desafio.repositories.AlunoRepository;
import com.luxfacta.desafio.repositories.ProfessorRepository;
import com.luxfacta.desafio.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlunoUpdateValidator implements ConstraintValidator<AlunoUpdate, AlunoDTO> {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(AlunoUpdate ann) {}

    @Override
    public boolean isValid(AlunoDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Aluno aux = alunoRepository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
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
