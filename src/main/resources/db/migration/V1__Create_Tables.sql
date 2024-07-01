create TABLE IF NOT EXISTS usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(255),
    criado_em TIMESTAMP,
    atualizado_em TIMESTAMP,
    role VARCHAR(2),
    status VARCHAR(1)
);

create TABLE IF NOT EXISTS viagem (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    cidade VARCHAR(50),
    estado VARCHAR(2),
    pais VARCHAR(100),
    descricao TEXT,
    data_inicio DATE,
    data_fim DATE,
    custo_total DECIMAL(10, 2),
    hospedagem VARCHAR(100),
    numero_pessoas INT,
    avaliacao DECIMAL(3, 2),
    criado_em TIMESTAMP,
    atualizado_em TIMESTAMP,
    status VARCHAR(1)
);

CREATE TABLE IF NOT EXISTS viagem_meios_transporte (
    viagem_id BIGSERIAL NOT NULL,
    id_meio_transporte INT NOT NULL,
    PRIMARY KEY (viagem_id, id_meio_transporte),
    CONSTRAINT FK_vgmeios_vg FOREIGN KEY (viagem_id) REFERENCES viagem (id),
    UNIQUE (viagem_id, id_meio_transporte)
);

