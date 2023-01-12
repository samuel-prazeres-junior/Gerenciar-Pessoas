package com.projeto.pessoas.rest.service;

import com.projeto.pessoas.domain.entity.Pessoa;
import com.projeto.pessoas.rest.dto.PessoaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService {

    Page<PessoaDTO> findAllPessoa(Pageable pageable);

    Pessoa findPessoa(Integer id);

    Pessoa save(PessoaDTO pessoa);

    Pessoa update(PessoaDTO pessoaDTO, Integer id);

    boolean existsPessoa(Integer idPessoa);
}
