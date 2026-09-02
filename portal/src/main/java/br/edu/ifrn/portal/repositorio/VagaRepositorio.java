package br.edu.ifrn.portal.repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.portal.modelo.Vaga;

/**
 * Repositório responsável pela persistência de Vagas no MySQL.
 * Implementa as operações CRUD para REQ.001 e REQ.002.
 */
public class VagaRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    // [C] - INSERIR VAGA
    public void inserir(Vaga vaga) {
        String sql = "INSERT INTO vaga (titulo, empresa, descricao, area, tipo_contrato, localizacao, ativa) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, vaga.getTitulo());
            stmt.setString(2, vaga.getEmpresa());
            stmt.setString(3, vaga.getDescricao());
            stmt.setString(4, vaga.getArea());
            stmt.setString(5, vaga.getTipoContrato());
            stmt.setString(6, vaga.getLocalizacao());
            stmt.setBoolean(7, vaga.isAtiva());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) vaga.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vaga no MySQL", e);
        }
    }

    // [R] - LISTAR TODAS AS VAGAS ATIVAS
    public List<Vaga> selecionarAtivas() {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga WHERE ativa = true ORDER BY titulo ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) vagas.add(mapear(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas do MySQL", e);
        }
        return vagas;
    }

    // [R] - LISTAR TODAS AS VAGAS
    public List<Vaga> selecionarTodas() {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga ORDER BY titulo ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) vagas.add(mapear(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vagas do MySQL", e);
        }
        return vagas;
    }

    // [R] - BUSCAR POR ID
    public Vaga selecionarPorId(Long id) {
        String sql = "SELECT * FROM vaga WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vaga por ID", e);
        }
        return null;
    }

    // [R] - FILTRAR POR AREA (REQ.002)
    public List<Vaga> selecionarPorArea(String area) {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga WHERE LOWER(area) = LOWER(?) AND ativa = true ORDER BY titulo ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, area);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) vagas.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao filtrar vagas por area", e);
        }
        return vagas;
    }

    // [R] - FILTRAR POR TIPO DE CONTRATO (REQ.002)
    public List<Vaga> selecionarPorTipoContrato(String tipo) {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga WHERE LOWER(tipo_contrato) = LOWER(?) AND ativa = true ORDER BY titulo ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) vagas.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao filtrar vagas por tipo", e);
        }
        return vagas;
    }

    // [U] - ATUALIZAR VAGA
    public void atualizar(Vaga vaga) {
        String sql = "UPDATE vaga SET titulo=?, empresa=?, descricao=?, area=?, tipo_contrato=?, localizacao=?, ativa=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vaga.getTitulo());
            stmt.setString(2, vaga.getEmpresa());
            stmt.setString(3, vaga.getDescricao());
            stmt.setString(4, vaga.getArea());
            stmt.setString(5, vaga.getTipoContrato());
            stmt.setString(6, vaga.getLocalizacao());
            stmt.setBoolean(7, vaga.isAtiva());
            stmt.setLong(8, vaga.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar vaga no MySQL", e);
        }
    }

    // [D] - EXCLUIR VAGA
    public void excluir(Long id) {
        String sql = "DELETE FROM vaga WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir vaga do MySQL", e);
        }
    }

    private Vaga mapear(ResultSet rs) throws SQLException {
        Vaga v = new Vaga();
        v.setId(rs.getLong("id"));
        v.setTitulo(rs.getString("titulo"));
        v.setEmpresa(rs.getString("empresa"));
        v.setDescricao(rs.getString("descricao"));
        v.setArea(rs.getString("area"));
        v.setTipoContrato(rs.getString("tipo_contrato"));
        v.setLocalizacao(rs.getString("localizacao"));
        v.setAtiva(rs.getBoolean("ativa"));
        return v;
    }
}
