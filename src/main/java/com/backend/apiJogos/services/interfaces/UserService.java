package com.backend.apiJogos.services.interfaces;

import java.util.List;

import org.springframework.security.oauth2.jwt.Jwt;

import com.backend.apiJogos.dtos.UserDto;

public interface UserService {
    UserDto criarOuBuscar(Jwt jwt);
    UserDto editarPorId(UserDto userDto, Long id);
    List<UserDto> listarUsuarios();
    UserDto buscarPorId (Long id);
    List<UserDto>buscarPorNome(String nome);
    void deletarUsuario (Long id);
}
