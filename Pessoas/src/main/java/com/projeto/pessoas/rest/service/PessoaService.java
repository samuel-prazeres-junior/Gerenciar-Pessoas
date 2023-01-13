package com.projeto.pessoas.rest.service;

import com.projeto.pessoas.domain.entity.Pessoa;
import com.projeto.pessoas.rest.dto.request.PessoaDTORequest;
import com.projeto.pessoas.rest.dto.response.PessoaDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService {

    Page<PessoaDTOResponse> findAllPessoa(Pageable pageable);

    PessoaDTOResponse findPessoa(Integer id);

    PessoaDTOResponse save(PessoaDTORequest pessoa);

    PessoaDTOResponse update(PessoaDTORequest pessoaDTO, Integer id);

    boolean existsPessoa(Integer idPessoa);
}
