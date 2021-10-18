package com.luxfacta.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos = new ArrayList<>();

    public Disciplina() {}

    public Disciplina(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
