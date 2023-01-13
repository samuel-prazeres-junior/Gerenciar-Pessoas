package com.projeto.pessoas.rest.service;

import com.projeto.pessoas.domain.entity.Endereco;
import com.projeto.pessoas.domain.repository.EnderecoRepository;
import com.projeto.pessoas.domain.repository.PessoaRepository;
import com.projeto.pessoas.rest.exception.RecursoNotFoundException;
import com.projeto.pessoas.rest.service.impl.EnderecoServiceImpl;
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

    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void personNotFound() {
        when(pessoaRepository.existsById(anyInt())).thenReturn(false);
        Assertions.assertThrows(RecursoNotFoundException.class, () -> enderecoService.saveMainAddress(1, 1));
    }

    @Test
    void personFound() {
        when(pessoaRepository.existsById(anyInt())).thenReturn(true);
        when(enderecoRepository.findByIdAndPessoaId(anyInt(), anyInt())).thenThrow(RecursoNotFoundException.class);
        Assertions.assertThrows(RecursoNotFoundException.class, () -> enderecoService.saveMainAddress(1, 1));
    }

    @Test
    void saveMainAddresFound() {
        Endereco endereco = new Endereco();
        when(pessoaRepository.existsById(anyInt())).thenReturn(true);
        when(enderecoRepository.findByIdAndPessoaId(anyInt(), anyInt())).thenReturn(Optional.of(endereco));
        when(enderecoRepository.findByPrincipalTrueAndPessoaId(anyInt())).thenReturn(Optional.of(endereco));
        enderecoService.saveMainAddress(1, 1);
        verify(enderecoRepository, times(2)).save(ArgumentMatchers.any(Endereco.class));
    }

    @Test
    void saveMainAddressNotFound() {
        Endereco endereco = new Endereco();
        when(pessoaRepository.existsById(anyInt())).thenReturn(true);
        when(enderecoRepository.findByIdAndPessoaId(anyInt(), anyInt())).thenReturn(Optional.of(endereco));
        when(enderecoRepository.findByPrincipalTrueAndPessoaId(anyInt())).thenReturn(Optional.empty());
        enderecoService.saveMainAddress(1, 1);
        verify(enderecoRepository).save(endereco);
    }
}