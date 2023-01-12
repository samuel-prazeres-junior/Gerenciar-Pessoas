package com.projeto.pessoas.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean principal;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
