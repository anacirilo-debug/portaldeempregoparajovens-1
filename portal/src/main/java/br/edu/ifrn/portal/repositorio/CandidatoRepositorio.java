package br.edu.ifrn.portal.repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.portal.modelo.Candidato;

/**
 * Repositório responsável pela persistência de Candidatos no MySQL.
 * Implementa as operações CRUD para REQ.003 e REQ.004.
 */
public class CandidatoRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    // [C] - INSERIR CANDIDATO
    public void inserir(Candidato candidato) {
        String sql = "INSERT INTO candidato (nome, email, telefone, idade, area_interesse, nivel_escolaridade, buscando_emprego) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getEmail());
            stmt.setString(3, candidato.getTelefone());
            stmt.setInt(4, candidato.getIdade());
            stmt.setString(5, candidato.getAreaInteresse());
            stmt.setString(6, candidato.getNivelEscolaridade());
            stmt.setBoolean(7, candidato.isBuscandoEmprego());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) candidato.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir candidato no MySQL", e);
        }
    }

    // [R] - LISTAR TODOS OS CANDIDATOS
    public List<Candidato> selecionarTodos() {
        List<Candidato> lista = new ArrayList<>();
        String sql = "SELECT * FROM candidato ORDER BY nome ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar candidatos", e);
        }
        return lista;
    }

    // [R] - BUSCAR POR ID
    public Candidato selecionarPorId(Long id) {
        String sql = "SELECT * FROM candidato WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidato por ID", e);
        }
        return null;
    }

    // [R] - BUSCAR POR EMAIL (verificar duplicidade)
    public Candidato selecionarPorEmail(String email) {
        String sql = "SELECT * FROM candidato WHERE LOWER(email) = LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar candidato por email", e);
        }
        return null;
    }

    // [R] - FILTRAR POR AREA DE INTERESSE (REQ.004)
    public List<Candidato> selecionarPorAreaInteresse(String area) {
        List<Candidato> lista = new ArrayList<>();
        String sql = "SELECT * FROM candidato WHERE LOWER(area_interesse) = LOWER(?) AND buscando_emprego = true ORDER BY nome ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, area);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao filtrar candidatos por area", e);
        }
        return lista;
    }

    // [U] - ATUALIZAR CANDIDATO
    public void atualizar(Candidato candidato) {
        String sql = "UPDATE candidato SET nome=?, email=?, telefone=?, idade=?, area_interesse=?, nivel_escolaridade=?, buscando_emprego=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getEmail());
            stmt.setString(3, candidato.getTelefone());
            stmt.setInt(4, candidato.getIdade());
            stmt.setString(5, candidato.getAreaInteresse());
            stmt.setString(6, candidato.getNivelEscolaridade());
            stmt.setBoolean(7, candidato.isBuscandoEmprego());
            stmt.setLong(8, candidato.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar candidato no MySQL", e);
        }
    }

    // [D] - EXCLUIR CANDIDATO
    public void excluir(Long id) {
        String sql = "DELETE FROM candidato WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir candidato do MySQL", e);
        }
    }

    private Candidato mapear(ResultSet rs) throws SQLException {
        Candidato c = new Candidato();
        c.setId(rs.getLong("id"));
        c.setNome(rs.getString("nome"));
        c.setEmail(rs.getString("email"));
        c.setTelefone(rs.getString("telefone"));
        c.setIdade(rs.getInt("idade"));
        c.setAreaInteresse(rs.getString("area_interesse"));
        c.setNivelEscolaridade(rs.getString("nivel_escolaridade"));
        c.setBuscandoEmprego(rs.getBoolean("buscando_emprego"));
        return c;
    }
}
