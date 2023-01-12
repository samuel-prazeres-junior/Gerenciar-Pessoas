package com.projeto.pessoas.rest.dto;

import com.projeto.pessoas.domain.entity.Pessoa;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @NotEmpty
    private String logradouro;

    @NotEmpty
    private String cep;

    @NotEmpty
    private String numero;


    private String cidade;

    private Integer idPessoa;
}
