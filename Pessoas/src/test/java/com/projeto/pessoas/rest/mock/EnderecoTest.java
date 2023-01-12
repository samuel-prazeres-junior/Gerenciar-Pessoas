package com.projeto.pessoas.rest.mock;

import com.projeto.pessoas.domain.entity.Endereco;


public class EnderecoTest {

    public Endereco novoEnderecoPrincipal(){
        Endereco endereco = new Endereco();
        endereco.setPrincipal(true);
        return endereco;
    }

    public Endereco novoEnderecoSecundario(){
        return  new Endereco();
    }
}
