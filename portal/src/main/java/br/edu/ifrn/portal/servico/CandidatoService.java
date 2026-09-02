package br.edu.ifrn.portal.servico;

import java.util.List;

import br.edu.ifrn.portal.modelo.Candidato;
import br.edu.ifrn.portal.repositorio.CandidatoRepositorio;

/**
 * Camada de Servico responsável pelas regras de negócio de Candidatos.
 * Implementa os critérios de aceitação de REQ.003 e REQ.004.
 */
public class CandidatoService {

    private final CandidatoRepositorio repositorio = new CandidatoRepositorio();

    /**
     * Cadastra um jovem no portal (REQ.003).
     * Criterios:
     * - Nome e email sao obrigatorios
     * - Email deve ser unico
     * - Candidato deve ter entre 14 e 29 anos para ser elegivel
     */
    public void cadastrarCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException("Erro: O candidato nao pode ser nulo.");
        }
        if (candidato.getNome() == null || candidato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: O nome do candidato e obrigatorio.");
        }
        if (candidato.getEmail() == null || candidato.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: O email do candidato e obrigatorio.");
        }
        if (!candidato.isElegivelParaPortal()) {
            throw new IllegalArgumentException("Erro de Regra: O candidato deve ter entre 14 e 29 anos para usar o portal. Idade informada: " + candidato.getIdade());
        }

        // Verificar unicidade de email (REQ.003)
        Candidato existente = repositorio.selecionarPorEmail(candidato.getEmail().trim());
        if (existente != null) {
            throw new IllegalArgumentException("Erro de Regra: Ja existe um cadastro com o email '" + candidato.getEmail() + "'.");
        }

        repositorio.inserir(candidato);
        System.out.println("LOG: Candidato '" + candidato.getNome() + "' cadastrado com sucesso.");
    }

    /**
     * Lista todos os candidatos cadastrados.
     */
    public List<Candidato> listarCandidatos() {
        return repositorio.selecionarTodos();
    }

    /**
     * Busca um candidato por ID.
     */
    public Candidato buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro de Regra: ID invalido para busca.");
        }
        return repositorio.selecionarPorId(id);
    }

    /**
     * Filtra candidatos por area de interesse para conectar com vagas (REQ.004).
     */
    public List<Candidato> filtrarPorArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Informe uma area para filtrar.");
        }
        return repositorio.selecionarPorAreaInteresse(area.trim());
    }

    /**
     * Atualiza os dados de um candidato.
     */
    public void atualizarCandidato(Candidato candidato) {
        if (candidato == null || candidato.getId() == null) {
            throw new IllegalArgumentException("Erro de Regra: Candidato ou ID invalido para atualizacao.");
        }
        repositorio.atualizar(candidato);
    }

    /**
     * Remove um candidato do portal.
     */
    public void removerCandidato(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro de Regra: ID invalido para exclusao.");
        }
        repositorio.excluir(id);
    }

    /**
     * Exibe o perfil de um candidato no console (REQ.004).
     */
    public void exibirPerfilCandidato(Candidato c) {
        System.out.println("==================================================");
        System.out.println("Perfil do Candidato - Portal de Empregos");
        System.out.println("==================================================");
        System.out.println("Nome: " + c.getNome());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("Idade: " + c.getIdade() + " anos");
        System.out.println("Area de Interesse: " + c.getAreaInteresse());
        System.out.println("Escolaridade: " + c.getNivelEscolaridade());
        System.out.println("Buscando Emprego: " + (c.isBuscandoEmprego() ? "SIM" : "NAO"));
        System.out.println("==================================================");
    }
}
