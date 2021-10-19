package com.luxfacta.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    public Professor(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
