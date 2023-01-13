package com.projeto.pessoas.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FavoritarEnderecoRequest {

    private Integer idEndereco;
    private Integer idPessoa;
}
