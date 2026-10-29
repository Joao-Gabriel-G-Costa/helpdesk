# Modelagem inicial — HelpDesk

## Objetivo
Sistema web para abertura e acompanhamento de chamados de suporte técnico. O cadastro de usuários é **controlado pelo administrador**; não existe cadastro público no MVP.

## Perfis
- **ADMIN:** cadastra/desativa usuários, gerencia categorias, atribui técnicos e acompanha chamados.
- **TECNICO:** atende chamados atribuídos e registra atualizações.
- **SOLICITANTE:** abre e acompanha seus próprios chamados.

As permissões são validadas no backend. O banco armazena os dados e impõe integridade referencial.

## Entidades
| Tabela | Finalidade |
| --- | --- |
| `usuarios` | Identidade, credenciais (somente hash), perfil e situação |
| `categorias` | Classificação dos chamados |
| `chamados` | Dados, status, prioridade, solicitante, técnico e categoria |
| `comentarios` | Mensagens dos participantes |
| `historico` | Registro de ações e mudanças relevantes |

## Relacionamentos
- `usuarios.id` → `chamados.solicitante_id` (1:N, obrigatório)
- `usuarios.id` → `chamados.tecnico_id` (1:N, opcional)
- `categorias.id` → `chamados.categoria_id` (1:N)
- `chamados.id` → `comentarios.chamado_id` (1:N)
- `usuarios.id` → `comentarios.usuario_id` (1:N)
- `chamados.id` → `historico.chamado_id` (1:N)
- `usuarios.id` → `historico.usuario_id` (1:N)

## Valores permitidos
- Perfis: `ADMIN`, `TECNICO`, `SOLICITANTE`.
- Status: `ABERTO`, `EM_ANDAMENTO`, `RESOLVIDO`, `FECHADO`.
- Prioridades: `BAIXA`, `MEDIA`, `ALTA`, `CRITICA`.

## Regras de negócio para implementar no Spring Boot
1. Somente administradores podem cadastrar usuários ou atribuir técnicos.
2. Somente usuários ativos podem autenticar.
3. O técnico atribuído deve estar ativo e ter perfil `TECNICO` (ou `ADMIN`, se a regra for aprovada).
4. Solicitantes só podem consultar e comentar seus próprios chamados; técnicos consultam os atribuídos; administradores consultam todos.
5. Mudanças de status, prioridade e atribuição devem gerar histórico na mesma transação.
6. O campo `atualizado_em` deve ser atualizado pelo backend.
7. Senhas nunca são armazenadas em texto puro; o backend utilizará um algoritmo de hash adequado, como BCrypt.
8. Usuários e categorias devem ser desativados em vez de excluídos quando possuírem registros vinculados.
9. O primeiro administrador deverá ser provisionado de forma controlada, sem credenciais padrão fixas no repositório.

## Decisões e limitações
- O script inicial usa `TIMESTAMPTZ` para registrar instantes com fuso horário.
- E-mails são únicos sem distinção de maiúsculas/minúsculas por meio de índice em `LOWER(email)`.
- As chaves estrangeiras garantem a existência dos registros, **não** a autorização dos perfis.
- Os campos de texto e status têm restrições básicas; a política completa de transição de status será definida na camada de serviço.
- Este é o **modelo inicial**, ainda não validado por execução em PostgreSQL.

## Próximas etapas
1. Executar a primeira migration em um PostgreSQL local.
2. Criar o projeto Spring Boot com JPA, Validation, PostgreSQL, Flyway e Security.
3. Implementar entidades, repositórios e serviços com testes.
4. Criar frontend React + TypeScript.
