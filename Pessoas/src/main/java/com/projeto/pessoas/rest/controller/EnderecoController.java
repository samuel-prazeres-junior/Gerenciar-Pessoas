package com.projeto.pessoas.rest.controller;

import com.projeto.pessoas.domain.entity.Endereco;
import com.projeto.pessoas.rest.dto.request.EnderecoDTORequest;
import com.projeto.pessoas.rest.dto.request.FavoritarEnderecoRequest;
import com.projeto.pessoas.rest.dto.response.EnderecoDTOResponse;
import com.projeto.pessoas.rest.service.impl.EnderecoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoServiceImpl service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Page<EnderecoDTOResponse> findEnderecoByPessoa(@PathVariable Integer id, Pageable pageable){
        return service.findEnderecoByPessoa(id, pageable);
    }

    @PostMapping("/principal")
    @ResponseStatus(HttpStatus.OK)
    public void savenMainAddres(@Valid @RequestBody FavoritarEnderecoRequest endereco){
        service.saveMainAddress(endereco.getIdEndereco(), endereco.getIdPessoa());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDTOResponse save(@Valid @RequestBody EnderecoDTORequest endereco){
        return service.save(endereco);
    }

}
