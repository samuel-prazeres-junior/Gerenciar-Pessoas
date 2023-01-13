package com.projeto.pessoas.rest.service.impl;

import com.projeto.pessoas.domain.entity.Endereco;
import com.projeto.pessoas.domain.entity.Pessoa;
import com.projeto.pessoas.domain.repository.EnderecoRepository;
import com.projeto.pessoas.domain.repository.PessoaRepository;
import com.projeto.pessoas.rest.dto.request.EnderecoDTORequest;
import com.projeto.pessoas.rest.dto.response.EnderecoDTOResponse;
import com.projeto.pessoas.rest.exception.RecursoNotFoundException;
import com.projeto.pessoas.rest.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;

    private final ModelMapper modelMapper;

    private final PessoaRepository pessoaRepository;

    public Page<EnderecoDTOResponse> findEnderecoByPessoa(Integer id, Pageable pageable){
        Page<EnderecoDTOResponse> page = repository.findByPessoaId(id, pageable).map(this::toEnderecoDTOResponse);
        return page;
    }

    public EnderecoDTOResponse save(EnderecoDTORequest endereco){
        Endereco enderecoEntity = toEndereco(endereco);
        Pessoa pessoa = pessoaRepository.findById(endereco.getIdPessoa()).orElseThrow(() -> new RecursoNotFoundException());
        enderecoEntity.setPessoa(pessoa);
        return toEnderecoDTOResponse(repository.save(enderecoEntity));
    }


    @Transactional
    public void saveMainAddress(Integer idEndereco, Integer idPessoa){

        if (pessoaRepository.existsById(idPessoa)){
            Endereco novoPrincipal = repository.findByIdAndPessoaId(idEndereco, idPessoa)
                    .orElseThrow((() -> new RecursoNotFoundException("Endereço não encontrado")));

            repository.findByPrincipalTrueAndPessoaId(idPessoa).map(principalAtual ->{
                principalAtual.setPrincipal(false);
                return  repository.save(principalAtual);
            });
            novoPrincipal.setPrincipal(true);
            repository.save(novoPrincipal);
        }

        else {
            throw new RecursoNotFoundException("Pessoa não encontrada");
        }
    }

    public EnderecoDTORequest toEnderecoDTO(Endereco endereco){
        return modelMapper.map(endereco, EnderecoDTORequest.class);
    }
    public Endereco toEndereco(EnderecoDTORequest endereco){
        return modelMapper.map(endereco, Endereco.class);
    }

    public EnderecoDTOResponse toEnderecoDTOResponse(Endereco endereco){
        return modelMapper.map(endereco, EnderecoDTOResponse.class);
    }

}
