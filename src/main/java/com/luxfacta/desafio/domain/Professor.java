package com.luxfacta.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luxfacta.desafio.domain.enums.Perfil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;

    @JsonIgnore
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS_PROFESSOR")
    private Set<Integer> perfis = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    public Professor() {
        addPerfil(Perfil.ADMIN);
    }

    public Professor(Integer id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.ADMIN);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nDetalhes do Usuário - Professor:\n");
        builder.append("Nome: ");
        builder.append(getNome());
        builder.append("\n");
        builder.append("Email: ");
        builder.append(getEmail());
        return builder.toString();
    }
}
