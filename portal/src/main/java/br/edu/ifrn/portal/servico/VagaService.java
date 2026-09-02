package br.edu.ifrn.portal.servico;

import java.util.List;

import br.edu.ifrn.portal.modelo.Vaga;
import br.edu.ifrn.portal.repositorio.VagaRepositorio;

/**
 * Camada de Servico responsável pelas regras de negócio de Vagas.
 * Implementa os critérios de aceitação de REQ.001 e REQ.002.
 */
public class VagaService {

    private final VagaRepositorio repositorio = new VagaRepositorio();

    /**
     * Publica uma nova vaga no portal (REQ.001).
     * Criterios:
     * - Titulo e empresa sao obrigatorios
     * - Tipo de contrato deve ser valido
     */
    public void publicarVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("Erro: A vaga nao pode ser nula.");
        }
        if (vaga.getTitulo() == null || vaga.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: O titulo da vaga e obrigatorio.");
        }
        if (vaga.getEmpresa() == null || vaga.getEmpresa().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: O nome da empresa e obrigatorio.");
        }
        if (vaga.getArea() == null || vaga.getArea().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: A area da vaga e obrigatoria.");
        }
        if (vaga.getTipoContrato() == null || vaga.getTipoContrato().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: O tipo de contrato e obrigatorio.");
        }

        repositorio.inserir(vaga);
        System.out.println("LOG: Vaga '" + vaga.getTitulo() + "' publicada com sucesso.");
    }

    /**
     * Lista todas as vagas ativas disponíveis (REQ.002).
     */
    public List<Vaga> listarVagasAtivas() {
        return repositorio.selecionarAtivas();
    }

    /**
     * Lista todas as vagas (ativas e inativas).
     */
    public List<Vaga> listarTodasVagas() {
        return repositorio.selecionarTodas();
    }

    /**
     * Filtra vagas por area de atuacao (REQ.002).
     */
    public List<Vaga> filtrarPorArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Informe uma area para filtrar.");
        }
        return repositorio.selecionarPorArea(area.trim());
    }

    /**
     * Filtra vagas por tipo de contrato (REQ.002).
     */
    public List<Vaga> filtrarPorTipoContrato(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Informe o tipo de contrato para filtrar.");
        }
        return repositorio.selecionarPorTipoContrato(tipo.trim());
    }

    /**
     * Encerra (desativa) uma vaga do portal.
     */
    public void encerrarVaga(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro de Regra: ID da vaga invalido.");
        }
        Vaga vaga = repositorio.selecionarPorId(id);
        if (vaga == null) {
            throw new IllegalArgumentException("Erro de Regra: Vaga de ID " + id + " nao encontrada.");
        }
        vaga.setAtiva(false);
        repositorio.atualizar(vaga);
        System.out.println("LOG: Vaga '" + vaga.getTitulo() + "' encerrada.");
    }

    /**
     * Remove uma vaga permanentemente.
     */
    public void removerVaga(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro de Regra: ID invalido para exclusao.");
        }
        repositorio.excluir(id);
    }

    /**
     * Exibe o detalhamento de uma vaga no console (REQ.002).
     */
    public void exibirDetalhesVaga(Vaga vaga) {
        System.out.println("==================================================");
        System.out.println("Detalhes da Vaga - Portal de Empregos para Jovens");
        System.out.println("==================================================");
        System.out.println("Titulo: " + vaga.getTitulo());
        System.out.println("Empresa: " + vaga.getEmpresa());
        System.out.println("Area: " + vaga.getArea());
        System.out.println("Tipo de Contrato: " + vaga.getTipoContrato());
        System.out.println("Localizacao: " + vaga.getLocalizacao());
        System.out.println("Descricao: " + vaga.getDescricao());
        System.out.println("Status: " + (vaga.isAtiva() ? "DISPONIVEL" : "ENCERRADA"));
        System.out.println("==================================================");
    }
}
