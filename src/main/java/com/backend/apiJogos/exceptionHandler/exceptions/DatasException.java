package com.backend.apiJogos.exceptionHandler.exceptions;

public class DatasException extends RuntimeException{

  public DatasException(){
    super("Conflito entre datas informadas!");
  }

  public DatasException(String mensagem){
    super(mensagem);
  }



}
