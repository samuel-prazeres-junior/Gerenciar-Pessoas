package com.projeto.pessoas.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    @NotEmpty
    private String nome;

    @Past
    private LocalDate dataNascimento;
}
