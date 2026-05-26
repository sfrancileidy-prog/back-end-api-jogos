package com.backend.apiJogos.services.impls;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.backend.apiJogos.dtos.UserDto;
import com.backend.apiJogos.exceptionHandler.exceptions.InvalidUserDataException;
import com.backend.apiJogos.exceptionHandler.exceptions.UserNotFoundException;
import com.backend.apiJogos.models.User;
import com.backend.apiJogos.repositorys.UserRepository;
import com.backend.apiJogos.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDto me(Jwt jwt) {
    String supabaseId = jwt.getSubject();
    User user = userRepository.findBySupabaseUserId(supabaseId)
        .orElseGet(() -> {
          User novo = new User();
          novo.setSupabaseUserId(supabaseId);
          String email = jwt.getClaimAsString("email");
          String nome = (email != null && !email.isBlank())
              ? email.split("@")[0]
              : supabaseId.substring(0, 8);
          novo.setNome(nome);
          return userRepository.save(novo);
        });
    return new UserDto(
        user.getId(),
        user.getNome(),
        user.getSupabaseUserId());
  }

  @Override
  public UserDto atualizarMeuPerfil(Jwt jwt, UserDto dto) {
    if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
      throw new InvalidUserDataException(
          "Nome não pode ser vazio");
    }
    String supabaseId = jwt.getSubject();
    User user = userRepository.findBySupabaseUserId(supabaseId)
        .orElseThrow(UserNotFoundException::new);
    user.setNome(dto.getNome().trim());
    userRepository.save(user);
    return new UserDto(
        user.getId(),
        user.getNome(),
        user.getSupabaseUserId());
  }

  @Override
  public void deletarMinhaConta(Jwt jwt) {
    String supabaseId = jwt.getSubject();
    User user = userRepository.findBySupabaseUserId(supabaseId)
        .orElseThrow(UserNotFoundException::new);
    userRepository.delete(user);
  }
}
