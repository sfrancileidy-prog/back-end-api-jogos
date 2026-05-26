package com.backend.apiJogos.exceptionHandler.exceptions;

public class HorasException extends RuntimeException{

  public HorasException(){
    super("Quantidade de horas invalida!");
  }

  public HorasException(String mensagem){
    super(mensagem);
  }

  
}
