package com.projeto.pessoas.rest.service;

import com.projeto.pessoas.domain.entity.Endereco;
import com.projeto.pessoas.rest.dto.EnderecoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoService {

    Page<EnderecoDTO> findEnderecoByPessoa(Integer id, Pageable pageable);

    Endereco save(EnderecoDTO endereco);

    void saveMainAddress(Integer idEndereco, Integer idPessoa);
}
