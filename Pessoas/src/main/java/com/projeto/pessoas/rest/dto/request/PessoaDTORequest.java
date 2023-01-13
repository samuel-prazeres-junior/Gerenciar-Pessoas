package com.projeto.pessoas.rest.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTORequest {

    @NotEmpty
    private String nome;

    @Past
    private LocalDate dataNascimento;

    private List<EnderecoDTORequest> enderecoDTO;
}
