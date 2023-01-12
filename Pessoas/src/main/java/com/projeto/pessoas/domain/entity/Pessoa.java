package com.projeto.pessoas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

//    @OneToMany(mappedBy = "pessoa")
//    private List<Endereco> endereco;

}
