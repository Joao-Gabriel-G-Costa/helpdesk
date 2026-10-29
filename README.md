# HelpDesk — Sistema de Gestão de Chamados de TI

Projeto de portfólio em desenvolvimento: aplicação web para abertura, acompanhamento e resolução de chamados de suporte técnico.

## Tecnologias planejadas
- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA, Flyway e Maven.
- **Frontend:** React, TypeScript e Tailwind CSS.
- **Banco:** PostgreSQL.
- **Testes:** JUnit e testes de integração da API.

## Escopo inicial (MVP)
- Cadastro de usuários **somente por administrador**.
- Perfis `ADMIN`, `TECNICO` e `SOLICITANTE`.
- Abertura e acompanhamento de chamados.
- Atribuição de técnico, categoria, prioridade e status.
- Comentários e histórico de alterações.
- Listagem com filtros e controle de acesso.

## Estrutura inicial

```text
helpdesk/
├── backend/
│   └── src/
│       ├── main/resources/db/migration/
│       │   └── V1__criar_tabelas_iniciais.sql
│       └── test/
├── frontend/
├── docs/
│   └── modelagem.md
├── .gitignore
└── README.md
```

## Banco de dados
O banco PostgreSQL deverá se chamar `helpdesk_db`. Sua criação ocorre fora da migration, por exemplo com `CREATE DATABASE helpdesk_db;` em uma conexão administrativa. A migration `V1__criar_tabelas_iniciais.sql` contém as tabelas, relacionamentos e índices; será aplicada pelo Flyway quando o backend estiver configurado.

Veja [modelagem e regras de negócio](docs/modelagem.md).

## Situação atual
**Etapa 1 — modelagem e organização do repositório.** O backend Spring Boot e o frontend React **ainda não foram implementados**. O SQL foi preparado, mas **ainda não foi executado/testado** em uma instância PostgreSQL.

## Próximos passos
1. Instalar/configurar PostgreSQL no ambiente pessoal.
2. Criar a aplicação Spring Boot e configurar conexão e Flyway.
3. Validar a migration e implementar entidades, autenticação e endpoints.
4. Criar frontend e testes.

**Observação:** projeto independente, com dados fictícios. Não utilizar código, credenciais ou dados internos de empresas.
