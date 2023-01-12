package com.projeto.pessoas.rest.service.impl;

import com.projeto.pessoas.rest.dto.PessoaDTO;
import com.projeto.pessoas.domain.entity.Pessoa;
import com.projeto.pessoas.domain.repository.PessoaRepository;
import com.projeto.pessoas.rest.exception.RecursoNotFoundException;
import com.projeto.pessoas.rest.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    private final ModelMapper modelMapper;

    public Page<PessoaDTO> findAllPessoa(Pageable pageable) {
        Page<PessoaDTO> page = repository.findAll(pageable).map(this::toPessoaDTO);
        return page;
    }


    public Pessoa findPessoa(Integer id){
        return repository.findById(id).orElseThrow(() -> new RecursoNotFoundException());
    }

    @Transactional
    public Pessoa save(PessoaDTO pessoa){
        return repository.save(convertPessoaDTOInPessoa(pessoa));
    }

    @Transactional
    public Pessoa update(PessoaDTO pessoaDTO, Integer id){
        Pessoa pessoa = convertPessoaDTOInPessoa(pessoaDTO);
        return repository.findById(id).map(pessoaBanco -> {
            pessoa.setId(pessoaBanco.getId());
            return repository.save(pessoa);
        }).orElseThrow(() ->  new RecursoNotFoundException());

    }

    public boolean existsPessoa(Integer idPessoa){
        return repository.existsById(idPessoa);
    }

    private Pessoa convertPessoaDTOInPessoa(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        return pessoa;
    }

    public PessoaDTO toPessoaDTO(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDTO.class);
    }
}
