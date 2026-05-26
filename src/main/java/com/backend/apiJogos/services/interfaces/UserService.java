package com.backend.apiJogos.services.interfaces;

import org.springframework.security.oauth2.jwt.Jwt;

import com.backend.apiJogos.dtos.UserDto;

public interface UserService {
    UserDto me(Jwt jwt);
    UserDto atualizarMeuPerfil(Jwt jwt, UserDto dto);
    void deletarMinhaConta(Jwt jwt);
}
