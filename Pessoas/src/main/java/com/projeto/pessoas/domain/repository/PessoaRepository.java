package com.projeto.pessoas.domain.repository;

import com.projeto.pessoas.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    boolean existsById(Long id);
}
