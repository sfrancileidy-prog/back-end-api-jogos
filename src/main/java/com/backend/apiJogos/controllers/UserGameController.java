package com.backend.apiJogos.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.backend.apiJogos.dtos.UserGameDto;
import com.backend.apiJogos.services.interfaces.UserGameService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-games")
@RequiredArgsConstructor
public class UserGameController {

    private final UserGameService service;

    @PostMapping
    public UserGameDto criar(@RequestBody @Valid UserGameDto dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<UserGameDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public UserGameDto buscar(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public UserGameDto editar(@PathVariable UUID id,
                              @RequestBody @Valid UserGameDto dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
