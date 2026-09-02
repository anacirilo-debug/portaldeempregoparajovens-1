package br.edu.ifrn.portal.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de Infraestrutura responsável pela gerência da conexão com o MySQL.
 */
public class GerenciadorDeConexao {

    private static final String URL = "jdbc:mysql://localhost:3306/portal_jovens_db?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
