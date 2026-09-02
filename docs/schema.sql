-- ==========================================================
-- Script DDL: Portal de Empregos para Jovens (MySQL)
-- ==========================================================

CREATE DATABASE IF NOT EXISTS portal_jovens_db;
USE portal_jovens_db;

-- Tabela de Vagas (REQ.001 e REQ.002)
CREATE TABLE IF NOT EXISTS vaga (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    empresa VARCHAR(255) NOT NULL,
    descricao TEXT,
    area VARCHAR(100) NOT NULL,
    tipo_contrato VARCHAR(50) NOT NULL,
    localizacao VARCHAR(150),
    ativa BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabela de Candidatos (REQ.003 e REQ.004)
CREATE TABLE IF NOT EXISTS candidato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    idade INT NOT NULL,
    area_interesse VARCHAR(100),
    nivel_escolaridade VARCHAR(50),
    buscando_emprego BOOLEAN NOT NULL DEFAULT TRUE
);
