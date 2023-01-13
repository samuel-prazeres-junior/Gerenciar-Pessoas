package com.projeto.pessoas.rest.service;

import com.projeto.pessoas.domain.entity.Endereco;
import com.projeto.pessoas.domain.repository.EnderecoRepository;
import com.projeto.pessoas.rest.exception.RecursoNotFoundException;
import com.projeto.pessoas.rest.service.impl.EnderecoServiceImpl;
import com.projeto.pessoas.rest.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    @InjectMocks // criar um mock da classe a ser testada
    private EnderecoServiceImpl enderecoService;

    @Mock
    private PessoaServiceImpl pessoaService;

    @Mock
    private EnderecoRepository enderecoRepository;


    @BeforeEach // antes de fazer qualquer metodo execute o que tiver aqui dentro
    void setUp() {
        MockitoAnnotations.openMocks(this); // basicamente fala que as anotações irão mockar automaticamente
    }

    @Test
    void personNotFound(){ // teste sempre void
        when(pessoaService.existsPessoa(anyInt())).thenReturn(false) ; // mockito.any ele fala que aceita qualquer valor do tipo int
        Assertions.assertThrows(RecursoNotFoundException.class, ()-> enderecoService.saveMainAddress(1,1));

    }

    @Test
    void personFound(){ // teste sempre void
        when(pessoaService.existsPessoa(anyInt())).thenReturn(true) ; // mockito.any ele fala que aceita qualquer valor do tipo int
        when(enderecoRepository.findByIdAndPessoaId(anyInt(), anyInt())).thenThrow(RecursoNotFoundException.class); // ele verifica se a exceção que foi dada é a mesma passada
        Assertions.assertThrows(RecursoNotFoundException.class, ()-> enderecoService.saveMainAddress(1,1)); // assertThrows verifica se em algum lugar da execução do metodo de o erro especificado

    }

    @Test
    void saveMainAddresFound(){ // teste sempre void
//        Endereco endereco = enderecoMockado.novoEnderecoPrincipal();
        Endereco endereco = new Endereco();
        when(pessoaService.existsPessoa(anyInt())).thenReturn(true) ; // mockito.any ele fala que aceita qualquer valor do tipo int
        when(enderecoRepository.findByIdAndPessoaId(anyInt(), anyInt())).thenReturn(Optional.of(endereco)); // ele verifica se a exceção que foi dada é a mesma passada
        when(enderecoRepository.findByPrincipalTrueAndPessoaId(anyInt())).thenReturn(Optional.of(endereco));
        enderecoService.saveMainAddress(1,1);
//        verify(enderecoRepository).save(endereco); // verifica se o metodo save esta sendo chamado
        verify(enderecoRepository, times(2)).save(ArgumentMatchers.any(Endereco.class));
        // ArgumentMatchers fala basicamente que não importa os argumentos passado desde que seja do tipo Endereco ele aceitara
        // verify é para verificar se algo foi chamado no caso a repositori o metodo save, times é quantas vezes o metodo foi invicado

    }

    @Test
    void saveMainAddressNotFound(){ // teste sempre void
//        Endereco endereco = enderecoMockado.novoEnderecoPrincipal();
        Endereco endereco = new Endereco();
        when(pessoaService.existsPessoa(anyInt())).thenReturn(true) ; // mockito.any ele fala que aceita qualquer valor do tipo int
        when(enderecoRepository.findByIdAndPessoaId(anyInt(), anyInt())).thenReturn(Optional.of(endereco)); // ele verifica se a exceção que foi dada é a mesma passada
        when(enderecoRepository.findByPrincipalTrueAndPessoaId(anyInt())).thenReturn(Optional.empty());
        enderecoService.saveMainAddress(1,1);
        verify(enderecoRepository).save(endereco); // verifica se o metodo save esta sendo chamado

    }



}