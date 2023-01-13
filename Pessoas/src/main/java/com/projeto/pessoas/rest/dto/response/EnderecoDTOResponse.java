package com.projeto.pessoas.rest.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTOResponse {

    @Positive
    private Integer idEndereco;

    @NotEmpty
    private String logradouro;

    @NotEmpty
    private String cep;

    @NotEmpty
    private String numero;

    @NotEmpty
    private String cidade;

    private PessoaDTOResponse pessoa;
}
