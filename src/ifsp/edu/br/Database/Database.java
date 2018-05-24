package ifsp.edu.br.Database;

import java.sql.*;

public class Database {

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:projeto.db");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão.", e);
        }
    }

   public static void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro no encerramento da conexão.", e);
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro no encerramento da Statement.", e);
            }
        }


    }

}
