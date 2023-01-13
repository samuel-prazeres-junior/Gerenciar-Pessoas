package com.projeto.pessoas.rest.service;

import com.projeto.pessoas.domain.entity.Endereco;
import com.projeto.pessoas.rest.dto.request.EnderecoDTORequest;
import com.projeto.pessoas.rest.dto.response.EnderecoDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoService {

    Page<EnderecoDTOResponse> findEnderecoByPessoa(Integer id, Pageable pageable);

    EnderecoDTOResponse save(EnderecoDTORequest endereco);

    void saveMainAddress(Integer idEndereco, Integer idPessoa);
}
