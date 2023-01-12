package com.projeto.pessoas.rest.service.impl;

import com.projeto.pessoas.rest.dto.EnderecoDTO;
import com.projeto.pessoas.domain.entity.Endereco;
import com.projeto.pessoas.domain.entity.Pessoa;
import com.projeto.pessoas.domain.repository.EnderecoRepository;
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

    private final PessoaServiceImpl pessoaService;

    private final ModelMapper modelMapper;
    public Page<EnderecoDTO> findEnderecoByPessoa(Integer id, Pageable pageable){
        Page<EnderecoDTO> page = repository.findByPessoaId(id, pageable).map(this::toEnderecoDTO);
        return page;
    }

    public Endereco save(EnderecoDTO endereco){
        Endereco enderecoEntity = new Endereco();
//        Endereco enderecoEntity = modelMapper.map(endereco, Endereco.class);
        Pessoa pessoa = pessoaService.findPessoa(endereco.getIdPessoa());
        enderecoEntity.setLogradouro(endereco.getLogradouro());
        enderecoEntity.setCep(endereco.getCep());
        enderecoEntity.setCidade(endereco.getCidade());
        enderecoEntity.setNumero(endereco.getNumero());
        enderecoEntity.setPessoa(pessoa);
        return repository.save(enderecoEntity);
    }


    @Transactional
    public void saveMainAddress(Integer idEndereco, Integer idPessoa){

        if (pessoaService.existsPessoa(idPessoa)){ // verificando se o id Existe no banco
            Endereco novoPrincipal = repository.findByIdAndPessoaId(idEndereco, idPessoa) // verificando se o id do endereco é um endereco do usuario
                    .orElseThrow((() -> new RecursoNotFoundException("Endereço não encontrado")));

            repository.findByPrincipalTrueAndPessoaId(idPessoa).map(principalAtual ->{ // se ja existir outro endereco como principal ira trocar ele para false
                principalAtual.setPrincipal(false);
                return  repository.save(principalAtual);
            });
            novoPrincipal.setPrincipal(true);
            repository.save(novoPrincipal);
        }

        else {
            throw new RecursoNotFoundException("Pessoa não encontrada");
        }
        // verificar se o id do endereco passado corresponde a um endereco cadastrado pelo usuario(se o endereco é do user)
        // verificar se o usuario ja nao tem um endereco favoritado, pq se tiver tem que desfavoritar o antigo para favoritar o novo
    }

    public EnderecoDTO toEnderecoDTO(Endereco endereco){
        return modelMapper.map(endereco, EnderecoDTO.class);
    }
}
