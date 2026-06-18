package org.example.cookingbrain.handler;

import org.example.cookingbrain.exception.RecursoDuplicadoException;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.exception.RegraNegocioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                erro -> erros.put(
                        erro.getField(),
                        erro.getDefaultMessage()
                )
        );

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratarRecursoNaoEncontrado(
            RecursoNaoEncontradoException ex) {

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(erro);
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Map<String, String>> tratarRecursoDuplicado(
            RecursoDuplicadoException ex) {

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(erro);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, String>> tratarRegraNegocio(
            RegraNegocioException ex) {

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());

        return ResponseEntity.badRequest()
                .body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> tratarErroGenerico(
            Exception ex) {

        logger.error("Erro interno:", ex);

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getClass().getName());
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }
}