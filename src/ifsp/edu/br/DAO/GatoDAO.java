package ifsp.edu.br.DAO;

import ifsp.edu.br.Database;
import ifsp.edu.br.Modelo.Animais.Gato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GatoDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;

    public void add(Gato g){
        try {
            connection = Database.getConnection();
            sql = "INSERT INTO Gato(id,apelido,raca,idade,sexo,vacinado,castrado) VALUES (?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,g.getId());
            stmt.setString(2,g.getApelido());
            stmt.setString(3,g.getRaca());
            stmt.setInt(3,g.getIdade());
            stmt.setBoolean(4,g.isSexo());
            stmt.setBoolean(5,g.isVacinado());
            stmt.setBoolean(6,g.isCastrado());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro no cadastro de gato.", e);
        }
    }

    public void update(Gato g){
        try {
            connection = Database.getConnection();
            sql = "UPDATE Gato SET apelido = ?,idade = ?,vacinado = ?,castrado = ? WHERE id =?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,g.getApelido());
            stmt.setInt(2,g.getIdade());
            stmt.setBoolean(3,g.isVacinado());
            stmt.setBoolean(4,g.isCastrado());
            stmt.setInt(5,g.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na atualização do gato.", e);
        }
    }

    public Gato read(Gato g){
        try {
            connection = Database.getConnection();
            sql = "SELECT * FROM Gato WHERE id =?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,g.getId());
            ResultSet rs = stmt.executeQuery();
            Gato cat = new Gato();
            cat.setId(rs.getInt("id"));
            cat.setApelido(rs.getString("apelido"));
            cat.setRaca(rs.getString("raca"));
            cat.setIdade(rs.getInt("idade"));
            cat.setSexo(rs.getBoolean("sexo"));
            cat.setVacinado(rs.getBoolean("vacinado"));
            cat.setCastrado(rs.getBoolean("castrado"));
            rs.close();
            stmt.close();
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de gato.",e );
        }
    }

    public Gato read(int id){
        try {
            connection = Database.getConnection();
            sql = "SELECT * FROM Gato WHERE id =?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Gato cat = new Gato();
            cat.setId(rs.getInt("id"));
            cat.setApelido(rs.getString("apelido"));
            cat.setRaca(rs.getString("raca"));
            cat.setIdade(rs.getInt("idade"));
            cat.setSexo(rs.getBoolean("sexo"));
            cat.setVacinado(rs.getBoolean("vacinado"));
            cat.setCastrado(rs.getBoolean("castrado"));
            rs.close();
            stmt.close();
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de gato.",e );
        }
    }

    public List<Gato> list(){
        try {
            connection = Database.getConnection();
            sql = "SELECT * FROM Gato";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Gato> gatos = new ArrayList<>();
            while(rs.next()){
                Gato cat = new Gato();
                cat.setId(rs.getInt("id"));
                cat.setApelido(rs.getString("apelido"));
                cat.setRaca(rs.getString("raca"));
                cat.setIdade(rs.getInt("idade"));
                cat.setSexo(rs.getBoolean("sexo"));
                cat.setVacinado(rs.getBoolean("vacinado"));
                cat.setCastrado(rs.getBoolean("castrado"));
                gatos.add(cat);
            }
            rs.close();
            stmt.close();
            return gatos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na listagem dos gatos.",e );
        }
    }

    public void remove(Gato g){
        try {
            connection = Database.getConnection();
            sql = "DELETE FROM Gato WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,g.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão do gato.", e);
        }
    }
}
