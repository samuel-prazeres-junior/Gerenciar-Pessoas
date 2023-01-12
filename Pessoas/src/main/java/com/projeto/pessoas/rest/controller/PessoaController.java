package com.projeto.pessoas.rest.controller;

import com.projeto.pessoas.rest.dto.PessoaDTO;
import com.projeto.pessoas.domain.entity.Pessoa;
import com.projeto.pessoas.rest.service.impl.PessoaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaServiceImpl service;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PessoaDTO> findAllPessoa(Pageable pageable){
        return service.findAllPessoa(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
   public Pessoa findPessoa(@PathVariable Integer id){
        return service.findPessoa(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa save(@Valid @RequestBody PessoaDTO pessoa){
        return service.save(pessoa);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa update(@Valid @RequestBody PessoaDTO pessoa, @PathVariable Integer id){
         return service.update(pessoa, id);
    }
}
