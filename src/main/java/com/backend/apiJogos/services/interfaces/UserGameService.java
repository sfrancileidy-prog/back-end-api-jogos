package com.backend.apiJogos.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.backend.apiJogos.dtos.UserGameDto;

public interface UserGameService {

    UserGameDto criar(UserGameDto dto);

    List<UserGameDto> listar();

    UserGameDto buscarPorId(UUID id);

    UserGameDto editar(UUID id, UserGameDto dto);

    void deletar(UUID id);
}
