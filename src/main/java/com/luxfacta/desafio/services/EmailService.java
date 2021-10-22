package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Professor;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendProfessorConfirmationEmail(Professor obj);

    void sendAlunoConfirmationEmail(Aluno obj);

    void sendEmail(SimpleMailMessage msg);

    void sendProfessorNewPasswordEmail(Professor professor, String newPass);

    void sendAlunoNewPasswordEmail(Aluno aluno, String newPass);
}
