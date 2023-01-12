package com.projeto.pessoas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FavoritarEndereco {

    private Integer idEndereco;
    private Integer idPessoa;
}
