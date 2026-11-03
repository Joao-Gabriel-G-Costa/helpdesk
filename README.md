# HelpDesk — gestão de chamados de TI

Projeto pessoal de portfólio em desenvolvimento, com foco em Java, APIs REST e regras de negócio de suporte técnico.

## Situação atual

O backend Spring Boot já possui cadastro e consulta de usuários, DTOs, validação de entrada, tratamento de exceções, hash de senha e autenticação HTTP Basic. As rotas de usuários exigem o perfil ADMIN.

Também estão presentes a entidade Categoria e a migration inicial com a estrutura do banco. O frontend ainda é uma pasta reservada; o fluxo de abertura, atribuição e resolução de chamados permanece planejado.

## Tecnologias

- Java 25, conforme o `pom.xml`; utilize um JDK compatível.
- Spring Boot, Spring Web MVC, Spring Security e Spring Data JPA.
- PostgreSQL e Flyway.
- Maven Wrapper.
- React e TypeScript previstos para o frontend.

## Executar o backend

1. Instale o JDK e o PostgreSQL.
2. Crie o banco `helpdesk_db` em sua instância local.
3. Defina a variável de ambiente `DB_PASSWORD` com a senha do banco. Confira o nome exato em [application.properties](backend/src/main/resources/application.properties); URL e usuário também devem corresponder ao seu ambiente.
4. Na pasta `backend`, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux/macOS, use `sh mvnw spring-boot:run`. O Flyway aplica as migrations ao iniciar e o Hibernate valida o esquema.

Não há cadastro público: as operações de usuários dependem de um administrador existente. O provisionamento inicial desse administrador ainda precisa de um fluxo documentado; não presuma que existe uma conta padrão.

## API implementada

| Método | Rota | Acesso |
| --- | --- | --- |
| GET | /api/usuarios | ADMIN |
| POST | /api/usuarios | ADMIN |

A autenticação atual usa HTTP Basic. Não publique a aplicação sem HTTPS e sem revisar sua configuração para o ambiente de produção.

## Organização

- `backend/src/main/java/`: controllers, DTOs, serviços, entidades, repositórios e segurança.
- `backend/src/main/resources/db/migration/`: migrations Flyway.
- `backend/src/test/`: estrutura de testes.
- `frontend/`: reservado para a interface.
- [Modelagem e regras](docs/modelagem.md).

## Verificação local

```powershell
cd backend
.\mvnw.cmd test
```

Os testes de contexto podem precisar de um PostgreSQL configurado. A existência de um arquivo de teste não significa cobertura funcional completa.

## Próximas entregas

- Documentar e implementar o provisionamento inicial do administrador.
- Completar categorias, chamados, comentários e histórico.
- Implementar frontend, testes de integração e demonstração.

Projeto independente. Utilize somente dados fictícios e mantenha credenciais fora do Git.
