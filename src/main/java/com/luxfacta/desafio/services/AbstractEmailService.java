package com.luxfacta.desafio.services;

import com.luxfacta.desafio.domain.Aluno;
import com.luxfacta.desafio.domain.Professor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendProfessorConfirmationEmail(Professor obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromProfessor(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromProfessor(Professor obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Conta criada com sucesso! Código: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());

        return sm;
    }

    @Override
    public void sendAlunoConfirmationEmail(Aluno obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromAluno(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromAluno(Aluno obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Conta criada com sucesso! Código: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());

        return sm;
    }

    @Override
    public void sendProfessorNewPasswordEmail(Professor professor, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmailFromProfessor(professor, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmailFromProfessor(Professor professor, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(professor.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha!");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);

        return sm;
    }

    @Override
    public void sendAlunoNewPasswordEmail(Aluno aluno, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmailFromAluno(aluno, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmailFromAluno(Aluno aluno, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(aluno.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha!");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);

        return sm;
    }
}
