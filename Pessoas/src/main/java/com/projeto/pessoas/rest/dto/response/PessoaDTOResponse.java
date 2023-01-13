package com.projeto.pessoas.rest.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTOResponse {

    @Positive
    private  Integer idPessoa;

    @NotEmpty
    private String nome;

    @Past
    private LocalDate dataNascimento;
}
