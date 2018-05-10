package ifsp.edu.br;

import java.sql.*;

public class Database {

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:projeto.db");
            System.out.println("Conexão Estabelecida!");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão.", e);
        }
    }

}
