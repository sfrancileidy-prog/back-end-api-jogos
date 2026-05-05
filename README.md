# API Jogos - Backend

Uma API REST desenvolvida em Spring Boot para gerenciar usuários e jogos com tratamento robusto de exceções.

---

## Estrutura do Projeto

```
src/main/java/com/backend/apiJogos/
├── controllers/          # Endpoints da API
│   ├── UserController.java
│   └── GameController.java
├── dtos/                 # Data Transfer Objects
│   ├── UserDto.java
│   └── GameDto.java
├── models/               # Entidades do banco de dados
│   ├── User.java
│   └── Game.java
├── services/             # Lógica de negócio
│   ├── interfaces/
│   │   ├── UserService.java
│   │   └── GameService.java
│   └── impls/
│       ├── UserServiceImpl.java
│       └── GameServiceImpl.java
├── repositorys/          # Acesso a dados
│   ├── UserRepository.java
│   └── GameRepository.java
└── exceptionHandler/     # Tratamento centralizado de exceções
    ├── exceptions/       # Classes de exceção customizadas
    │   ├── UserJaCadastradoException.java
    │   ├── UserNotFoundException.java
    │   ├── GameNotFoundException.java
    │   └── InvalidUserDataException.java
    ├── formatter/        # Formatação de respostas de erro
    │   └── RestErrorMensagem.java
    └── treatment/        # Handler global de exceções
        └── GlobalExceptionHandler.java
```

---

## Tratamento de Exceções

### Exceções Customizadas Criadas

#### 1. **UserJaCadastradoException**
- **Cenário**: Usuário tenta criar ou editar com um nome que já existe
- **Status HTTP**: `409 Conflict`
- **Mensagem padrão**: "Este nome ja esta em uso!!!"

#### 2. **UserNotFoundException**
- **Cenário**: Busca, edição ou deleção de usuário inexistente
- **Status HTTP**: `404 Not Found`
- **Mensagem padrão**: "Usuário não encontrado"

#### 3. **GameNotFoundException**
- **Cenário**: Busca, edição ou deleção de jogo inexistente
- **Status HTTP**: `404 Not Found`
- **Mensagem padrão**: "Game não encontrado"

#### 4. **InvalidUserDataException** *Nova*
- **Cenário**: Dados inválidos (valores nulos, vazios, etc.)
- **Status HTTP**: `400 Bad Request`
- **Casos de uso**:
  - Nome do usuário nulo ou vazio
  - ID do usuário nulo
  - Parâmetros de busca inválidos

---

## Validações Implementadas

### UserServiceImpl

| Método | Validações | Exceções Lançadas |
|--------|-----------|-------------------|
| **criarUsuario()** | ✓ Valida se userDto é nulo<br>✓ Valida se nome é nulo/vazio<br>✓ Verifica duplicidade de nome | `InvalidUserDataException`<br>`UserJaCadastradoException` |
| **editarPorId()** | ✓ Valida se ID é nulo<br>✓ Valida se nome é nulo/vazio<br>✓ Verifica existência do usuário<br>✓ Verifica duplicidade (exceto se mesmo nome) | `InvalidUserDataException`<br>`UserNotFoundException`<br>`UserJaCadastradoException` |
| **buscarPorId()** | ✓ Verifica existência do usuário | `UserNotFoundException` |
| **deletarUsuario()** | ✓ Valida se ID é nulo<br>✓ Verifica existência antes de deletar | `InvalidUserDataException`<br>`UserNotFoundException` |
| **buscarPorNome()** | ✓ Valida se nome é nulo/vazio | `InvalidUserDataException` |
| **listarUsuarios()** | ✓ Sem validações (retorna lista vazia) | Nenhuma |

---

## GlobalExceptionHandler

Classe centralizada que intercepta todas as exceções e formata respostas consistentes.

```java
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(UserJaCadastradoException.class)
    // Retorna 409 Conflict
    
    @ExceptionHandler(UserNotFoundException.class)
    // Retorna 404 Not Found
    
    @ExceptionHandler(GameNotFoundException.class)
    // Retorna 404 Not Found
    
    @ExceptionHandler(InvalidUserDataException.class)
    // Retorna 400 Bad Request
    
    @ExceptionHandler(RuntimeException.class)
    // Retorna 500 Internal Server Error (catch-all)
}
```

---

## Formato de Resposta de Erro

Todas as exceções retornam um objeto `RestErrorMensagem`:

```json
{
    "status": 400,
    "mensagem": "Nome do usuário não pode ser nulo ou vazio"
}
```

---

## Endpoints Principais

### Usuários
- `POST /users` - Criar usuário (com validações)
- `GET /users` - Listar todos
- `GET /users/buscar-id/{id}` - Buscar por ID
- `GET /users/buscar-nome/{nome}` - Buscar por nome
- `PUT /users/{id}` - Editar usuário
- `DELETE /users/{id}` - Deletar usuário

### Jogos
- `POST /games` - Criar jogo
- `GET /games` - Listar todos
- `GET /games/buscar-id/{id}` - Buscar por ID
- `GET /games/buscar-nome/{nome}` - Buscar por nome
- `PUT /games/{id}` - Editar jogo
- `DELETE /games/{id}` - Deletar jogo

---

## Como Executar

```bash
# Compilar o projeto
./mvnw clean compile

# Executar a aplicação
./mvnw clean spring-boot:run

# Executar testes
./mvnw test
```

---

## Dependências Principais

- Spring Boot Web
- Spring Data JPA
- Lombok
- Jakarta Validation

---


