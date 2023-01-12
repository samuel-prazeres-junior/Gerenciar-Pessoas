package com.projeto.pessoas.domain.repository;

import com.projeto.pessoas.domain.entity.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Page<Endereco> findByPessoaId(Integer id, Pageable pageable);
    Optional<Endereco> findByIdAndPessoaId(Integer id, Integer idPessoa);
    Optional<Endereco> findByPrincipalTrueAndPessoaId(Integer idPessoa);
}
