# BFF Agendador de Tarefas

Este é um projeto de Backend for Frontend (BFF) para um sistema de agendamento de tarefas, desenvolvido com Spring Boot. O BFF atua como uma interface unificada para os serviços de usuário, agendamento de tarefas e notificações por email.

## Descrição

O BFF Agendador de Tarefas é parte de uma arquitetura de microserviços que permite aos usuários agendar tarefas, gerenciar usuários e enviar notificações. Ele se comunica com outros serviços via OpenFeign e fornece uma API RESTful documentada com Swagger.

## Funcionalidades

- **Gerenciamento de Usuários**: Cadastro, login, atualização de dados, endereços e telefones.
- **Agendamento de Tarefas**: Criação, busca, atualização e exclusão de tarefas.
- **Notificações por Email**: Envio de emails para notificações de tarefas.
- **Integração com CEP**: Busca de dados de endereço via API ViaCEP.
- **Agendamento Automático**: Execução de tarefas agendadas via cron.

## Pré-requisitos

- Java 17
- Maven 3.8+
- Docker e Docker Compose (para execução com containers)
- PostgreSQL (para serviço de usuários)
- MongoDB (para serviço de agendamento)

## Instalação

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd bff-agendador-tarefas
   ```

2. Instale as dependências:
   ```bash
   mvn clean install
   ```

## Configuração

As configurações estão no arquivo `src/main/resources/application.properties`:

- `server.port=8083`: Porta do servidor
- `usuario.url=http://localhost:8080/usuario`: URL do serviço de usuários
- `agendador-tarefas.url=http://localhost:8081/tarefas`: URL do serviço de agendamento
- `notificacao.url=http://localhost:8082/email`: URL do serviço de notificações
- `cron.horario=0 0/5 * * * ?`: Cron para execução a cada 5 minutos
- `usuario.email=admin@admin.com`: Email padrão do admin
- `usuario.senha=admin123`: Senha padrão do admin

## Execução

### Localmente

1. Certifique-se de que os serviços dependentes estejam rodando.
2. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
3. Acesse a API em `http://localhost:8083`

### Com Docker

1. Execute o docker-compose:
   ```bash
   docker-compose up --build
   ```
2. A aplicação estará disponível em `http://localhost:8083`

## Documentação da API

A documentação da API está disponível via Swagger UI em `http://localhost:8083/swagger-ui.html`

### Endpoints Principais

#### Usuários (`/usuario`)
- `POST /usuario`: Cadastrar usuário
- `POST /usuario/login`: Login
- `GET /usuario`: Buscar usuário por email
- `PUT /usuario`: Atualizar usuário
- `DELETE /usuario/{email}`: Deletar usuário
- `POST /usuario/endereco`: Cadastrar endereço
- `PUT /usuario/endereco`: Atualizar endereço
- `POST /usuario/telefone`: Cadastrar telefone
- `PUT /usuario/telefone`: Atualizar telefone
- `GET /usuario/endereco/{cep}`: Buscar dados por CEP

#### Tarefas (`/tarefas`)
- `POST /tarefas`: Criar tarefa
- `GET /tarefas/eventos`: Buscar eventos por período
- `GET /tarefas`: Buscar tarefas por email
- `PUT /tarefas`: Atualizar tarefa
- `PATCH /tarefas`: Alterar status da tarefa
- `DELETE /tarefas`: Deletar tarefa

#### Email (`/email`)
- `POST /email`: Enviar notificação por email

## Arquitetura

O projeto utiliza:
- **Spring Boot**: Framework principal
- **Spring Cloud OpenFeign**: Para chamadas entre serviços
- **SpringDoc OpenAPI**: Documentação da API
- **Lombok**: Redução de boilerplate
- **Spring Security**: Autenticação e autorização (configurado em SecurityConfig)

## Testes

Execute os testes com:
```bash
mvn test
```

## Docker

O Dockerfile utiliza multi-stage build:
- Estágio 1: Build com Maven
- Estágio 2: Runtime com OpenJDK 17 Alpine

O docker-compose.yml define os serviços:
- `bff-agendador-tarefas`: Este BFF
- `usuario`: Serviço de usuários
- `agendador-tarefas`: Serviço de agendamento
- `notificacao`: Serviço de notificações
- `postgres`: Banco PostgreSQL
- `mongodb`: Banco MongoDB