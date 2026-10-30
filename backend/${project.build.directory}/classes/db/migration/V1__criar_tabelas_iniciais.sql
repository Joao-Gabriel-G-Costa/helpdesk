-- HelpDesk: estrutura inicial para PostgreSQL.
-- Este arquivo será executado pelo Flyway, conectado ao banco helpdesk_db.
-- Não inclui CREATE DATABASE: o banco precisa existir antes da migration.

CREATE TABLE usuarios (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(20) NOT NULL
        CHECK (perfil IN ('ADMIN', 'TECNICO', 'SOLICITANTE')),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX uq_usuarios_email_lower ON usuarios (LOWER(email));

CREATE TABLE categorias (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    ativa BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE chamados (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ABERTO'
        CHECK (status IN ('ABERTO', 'EM_ANDAMENTO', 'RESOLVIDO', 'FECHADO')),
    prioridade VARCHAR(20) NOT NULL DEFAULT 'MEDIA'
        CHECK (prioridade IN ('BAIXA', 'MEDIA', 'ALTA', 'CRITICA')),
    solicitante_id BIGINT NOT NULL REFERENCES usuarios(id),
    tecnico_id BIGINT REFERENCES usuarios(id),
    categoria_id BIGINT NOT NULL REFERENCES categorias(id),
    criado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE comentarios (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    chamado_id BIGINT NOT NULL REFERENCES chamados(id),
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    mensagem TEXT NOT NULL,
    criado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE historico (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    chamado_id BIGINT NOT NULL REFERENCES chamados(id),
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    acao VARCHAR(50) NOT NULL,
    descricao TEXT,
    criado_em TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_chamados_solicitante ON chamados (solicitante_id);
CREATE INDEX idx_chamados_tecnico ON chamados (tecnico_id);
CREATE INDEX idx_chamados_categoria ON chamados (categoria_id);
CREATE INDEX idx_chamados_status ON chamados (status);
CREATE INDEX idx_comentarios_chamado ON comentarios (chamado_id);
CREATE INDEX idx_historico_chamado ON historico (chamado_id);
