package com.backend.apiJogos.exceptionHandler.exceptions;

public class UserJaCadastradoException extends RuntimeException {

  public UserJaCadastradoException() {
    super("Usuario ja cadastrado!");
  }

  public UserJaCadastradoException(String mensagem) {
    super(mensagem);

  }

}
