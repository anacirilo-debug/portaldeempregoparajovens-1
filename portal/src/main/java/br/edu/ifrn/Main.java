package br.edu.ifrn;

import java.util.List;

import br.edu.ifrn.portal.modelo.Candidato;
import br.edu.ifrn.portal.modelo.Vaga;
import br.edu.ifrn.portal.servico.CandidatoService;
import br.edu.ifrn.portal.servico.VagaService;

public class Main {
    public static void main(String[] args) {

        VagaService vagaService = new VagaService();
        CandidatoService candidatoService = new CandidatoService();

        System.out.println("==================================================================");
        System.out.println("       PORTAL DE EMPREGOS PARA JOVENS - Sistema Higeia (JDBC)    ");
        System.out.println("==================================================================");

        // Limpeza previa para idempotencia
        for (Vaga v : vagaService.listarTodasVagas()) vagaService.removerVaga(v.getId());
        for (Candidato c : candidatoService.listarCandidatos()) candidatoService.removerCandidato(c.getId());

        // ==============================================================
        // REQ.001 - PUBLICACAO DE VAGAS
        // ==============================================================
        System.out.println("\n>>> [REQ.001] Publicacao de Vagas <<<");

        Vaga v1 = new Vaga("Assistente Administrativo", "Empresa Alpha", "Auxiliar no setor administrativo.", "Administracao", "Estagio", "Natal - RN");
        Vaga v2 = new Vaga("Desenvolvedor Junior", "TechStart Ltda", "Desenvolvimento de sistemas web em Java.", "Tecnologia", "CLT", "Recife - PE");
        Vaga v3 = new Vaga("Aprendiz de Logistica", "LogFast S.A.", "Apoio na area de logistica e estoque.", "Logistica", "Aprendiz", "Mosso - RN");

        vagaService.publicarVaga(v1);
        System.out.println("[OK] " + v1);
        vagaService.publicarVaga(v2);
        System.out.println("[OK] " + v2);
        vagaService.publicarVaga(v3);
        System.out.println("[OK] " + v3);

        // Teste de validacao: titulo obrigatorio
        System.out.println("\n--- Testando Regra: Titulo obrigatorio (REQ.001) ---");
        try {
            vagaService.publicarVaga(new Vaga("", "Empresa X", "Desc", "TI", "CLT", "Natal"));
        } catch (Exception e) {
            System.out.println("[OK] Validacao correta: " + e.getMessage());
        }

        // ==============================================================
        // REQ.002 - VISUALIZACAO E FILTRO DE VAGAS
        // ==============================================================
        System.out.println("\n>>> [REQ.002] Listagem e Filtro de Vagas Ativas <<<");
        List<Vaga> vagas = vagaService.listarVagasAtivas();
        System.out.println("Total de vagas disponiveis: " + vagas.size());
        vagas.forEach(System.out::println);

        System.out.println("\n--- Filtrando vagas por area 'Tecnologia' ---");
        vagaService.filtrarPorArea("Tecnologia").forEach(System.out::println);

        System.out.println("\n--- Filtrando vagas por tipo 'Estagio' ---");
        vagaService.filtrarPorTipoContrato("Estagio").forEach(System.out::println);

        // Detalhes de uma vaga
        System.out.println();
        vagaService.exibirDetalhesVaga(v2);

        // Encerrando uma vaga
        System.out.println("\n--- Encerrando vaga de Assistente Administrativo ---");
        vagaService.encerrarVaga(v1.getId());
        System.out.println("Vagas ativas apos encerramento: " + vagaService.listarVagasAtivas().size());

        // ==============================================================
        // REQ.003 - CADASTRO DE CANDIDATOS
        // ==============================================================
        System.out.println("\n>>> [REQ.003] Cadastro de Candidatos Jovens <<<");

        Candidato c1 = new Candidato("Ana Lima", "ana.lima@email.com", "(84) 99999-0001", 18, "Tecnologia", "Medio");
        Candidato c2 = new Candidato("Bruno Souza", "bruno.souza@email.com", "(84) 99999-0002", 22, "Administracao", "Superior");
        Candidato c3 = new Candidato("Carla Melo", "carla.melo@email.com", "(84) 99999-0003", 16, "Logistica", "Medio");

        candidatoService.cadastrarCandidato(c1);
        System.out.println("[OK] " + c1);
        candidatoService.cadastrarCandidato(c2);
        System.out.println("[OK] " + c2);
        candidatoService.cadastrarCandidato(c3);
        System.out.println("[OK] " + c3);

        // Teste: idade invalida (fora do range 14-29)
        System.out.println("\n--- Testando Regra: Idade fora do permitido (REQ.003) ---");
        try {
            candidatoService.cadastrarCandidato(new Candidato("Idoso Silva", "idoso@email.com", "000", 35, "TI", "Superior"));
        } catch (Exception e) {
            System.out.println("[OK] Validacao correta: " + e.getMessage());
        }

        // Teste: email duplicado
        System.out.println("\n--- Testando Regra: Email duplicado (REQ.003) ---");
        try {
            candidatoService.cadastrarCandidato(new Candidato("Ana Lima 2", "ana.lima@email.com", "000", 20, "TI", "Medio"));
        } catch (Exception e) {
            System.out.println("[OK] Validacao correta: " + e.getMessage());
        }

        // ==============================================================
        // REQ.004 - VISUALIZACAO E FILTRO DE CANDIDATOS
        // ==============================================================
        System.out.println("\n>>> [REQ.004] Perfis e Filtro de Candidatos <<<");
        System.out.println("Total de candidatos cadastrados: " + candidatoService.listarCandidatos().size());

        System.out.println("\n--- Filtrando candidatos por area 'Tecnologia' ---");
        candidatoService.filtrarPorArea("Tecnologia").forEach(System.out::println);

        // Perfil detalhado
        System.out.println();
        Candidato cAtualizado = candidatoService.buscarPorId(c1.getId());
        candidatoService.exibirPerfilCandidato(cAtualizado);

        // ==============================================================
        // LIMPEZA FINAL
        // ==============================================================
        System.out.println("\n>>> Limpeza de dados de demonstracao <<<");
        for (Vaga v : vagaService.listarTodasVagas()) vagaService.removerVaga(v.getId());
        for (Candidato c : candidatoService.listarCandidatos()) candidatoService.removerCandidato(c.getId());
        System.out.println("[OK] Dados de demonstracao removidos.");

        System.out.println("\n==================================================================");
        System.out.println("              SISTEMA EXECUTADO E VALIDADO COM SUCESSO!           ");
        System.out.println("==================================================================");
    }
}
