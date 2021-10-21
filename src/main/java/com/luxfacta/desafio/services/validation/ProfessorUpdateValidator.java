package com.luxfacta.desafio.services.validation;

import com.luxfacta.desafio.domain.Professor;
import com.luxfacta.desafio.dto.ProfessorDTO;
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

public class ProfessorUpdateValidator implements ConstraintValidator<ProfessorUpdate, ProfessorDTO> {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ProfessorUpdate ann) {}

    @Override
    public boolean isValid(ProfessorDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Professor aux = professorRepository.findByEmail(objDto.getEmail());
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
