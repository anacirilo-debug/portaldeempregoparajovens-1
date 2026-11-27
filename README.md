# PORTAL DE EMPREGO PARA JOVENS

Um site de emprego para jovens é uma plataforma criada para ajudar adolescentes e jovens adultos a encontrarem oportunidades de trabalho, como estágios, jovem aprendiz e primeiro emprego. O objetivo é facilitar o acesso ao mercado de trabalho, conectando jovens a empresas que oferecem oportunidades, os jovens podem conquistar sua independência financeira e começar a construir seu futuro profissional.

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

Execute o script `docs/schema.sql` no MySQL para criar o banco `portal_jovens_db` e as tabelas `vaga` e `candidato`.

## Tecnologias

- Java 17+
- Maven 3.9.14
- MySQL 8.0
- JDBC (`mysql-connector-j` 9.1.0)

## Configuração do Ambiente de Desenvolvimento (VS Code)

1. Ferramentas localizadas em `C:\dev\tools`:
   - Apache Maven 3.9.14 (`C:\dev\tools\apache-maven-3.9.14`)
   - PortableGit (`C:\dev\tools\PortableGit`)
2. Extensão no VS Code:
   - **Extension Pack for Java** (Microsoft)
3. Configurações de usuário e workspace salvas em `settings.json` / `.vscode/settings.json` para mapeamento automático do Maven, Git e terminal Git Bash Portable.
