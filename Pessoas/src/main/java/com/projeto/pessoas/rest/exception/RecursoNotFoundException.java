package com.projeto.pessoas.rest.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNotFoundException  extends RuntimeException{
    public RecursoNotFoundException() {
        super("Recurso Não Encontrado");
    }

    public RecursoNotFoundException(String message) {
        super(message);
    }
}
