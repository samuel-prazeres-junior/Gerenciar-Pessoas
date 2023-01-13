package com.projeto.pessoas.rest.service.impl;

import com.projeto.pessoas.domain.entity.Pessoa;
import com.projeto.pessoas.domain.repository.PessoaRepository;
import com.projeto.pessoas.rest.dto.request.PessoaDTORequest;
import com.projeto.pessoas.rest.dto.response.PessoaDTOResponse;
import com.projeto.pessoas.rest.exception.RecursoNotFoundException;
import com.projeto.pessoas.rest.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    private final ModelMapper modelMapper;

    private final EnderecoServiceImpl enderecoService;

    //    private final EnderecoRepository enderecoRepository;
    public Page<PessoaDTOResponse> findAllPessoa(Pageable pageable) {
        Page<PessoaDTOResponse> page = repository.findAll(pageable).map(this::toPessoaDTOResponse);
        return page;
    }


    public PessoaDTOResponse findPessoa(Integer id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RecursoNotFoundException());
        return toPessoaDTOResponse(pessoa);
    }

    @Transactional
    public PessoaDTOResponse save(PessoaDTORequest pessoa) {
        Pessoa newPessoa = repository.save(toPessoa(pessoa));
        pessoa.getEnderecoDTO().stream().map(enderecoDTO -> {
            enderecoDTO.setIdPessoa(newPessoa.getId());
            return enderecoService.save(enderecoDTO);
        }).collect(Collectors.toList());

        return toPessoaDTOResponse(newPessoa);
    }

    @Transactional
    public PessoaDTOResponse update(PessoaDTORequest pessoaDTO, Integer id) {
        Pessoa pessoa = toPessoa(pessoaDTO);
        return repository.findById(id).map(pessoaBanco -> {
            pessoa.setId(pessoaBanco.getId());
            return toPessoaDTOResponse(repository.save(pessoa));
        }).orElseThrow(() -> new RecursoNotFoundException());

    }

    @Override
    public boolean existsPessoa(Integer idPessoa) {
        return repository.existsById(idPessoa);
    }


    public PessoaDTORequest toPessoaDTO(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTORequest.class);
    }

    public PessoaDTOResponse toPessoaDTOResponse(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTOResponse.class);
    }

    public Pessoa toPessoa(PessoaDTORequest pessoa) {
        return modelMapper.map(pessoa, Pessoa.class);
    }
}
