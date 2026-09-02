# Portal de Empregos para Jovens

Sistema desenvolvido em **Java + Maven + MySQL (JDBC)** seguindo a arquitetura de separação de responsabilidades (SoC) estudada em aula.

## Estrutura do Projeto

```
portal/
├── src/main/java/br/edu/ifrn/
│   ├── Main.java
│   └── portal/
│       ├── modelo/
│       │   ├── Vaga.java
│       │   └── Candidato.java
│       ├── repositorio/
│       │   ├── GerenciadorDeConexao.java
│       │   ├── VagaRepositorio.java
│       │   └── CandidatoRepositorio.java
│       └── servico/
│           ├── VagaService.java
│           └── CandidatoService.java
└── pom.xml
```

## Requisitos Implementados

| Requisito | Descrição |
|-----------|-----------|
| REQ.001 | Publicação de vagas com validações obrigatórias |
| REQ.002 | Listagem e filtro de vagas por área e tipo de contrato |
| REQ.003 | Cadastro de candidatos jovens (14 a 29 anos) com email único |
| REQ.004 | Visualização e filtro de candidatos por área de interesse |

## Configuração do Banco de Dados

Execute o script `docs/schema.sql` no MySQL Workbench para criar o banco `portal_jovens_db`.

## Tecnologias

- Java 17
- Maven
- MySQL 8.0
- JDBC (Driver mysql-connector-j 9.1.0)
