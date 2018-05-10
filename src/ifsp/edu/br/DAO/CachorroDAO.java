package ifsp.edu.br.DAO;

import ifsp.edu.br.Database;
import ifsp.edu.br.Modelo.Animais.Cachorro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CachorroDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;

    public void add(Cachorro c){
       try {
           sql = "INSERT INTO Cachorro(id,apelido,idade,sexo,vacinado,castrado) VALUES (?,?,?,?,?,?)";
           stmt = connection.prepareStatement(sql);
           stmt.setInt(1,c.getId());
           stmt.setString(2,c.getApelido());
           stmt.setInt(3,c.getIdade());
           stmt.setBoolean(4,c.isSexo());
           stmt.setBoolean(5,c.isVacinado());
           stmt.setBoolean(6,c.isCastrado());
           stmt.execute();
       } catch (SQLException e) {
           throw new RuntimeException("Erro no cadastro de cachorro", e);
       }
    }

    public void update(Cachorro c){
        try{
            sql = "UPDATE Cachorro SET apelido = ?, idade = ?, vacinado = ?, castrado = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,c.getApelido());
            stmt.setInt(2,c.getIdade());
            stmt.setBoolean(3,c.isVacinado());
            stmt.setBoolean(4,c.isCastrado());
            stmt.setInt(5,c.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em atualizar cachorro.", e);
        }
    }

    public Cachorro read(int id){
        try {
            sql = "SELECT * FROM Cachorro WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Cachorro dog = new Cachorro();
            dog.setId(rs.getInt("id"));
            dog.setApelido(rs.getString("apelido"));
            dog.setIdade(rs.getInt("idade"));
            dog.setSexo(rs.getBoolean("sexo"));
            dog.setVacinado(rs.getBoolean("vacinado"));
            dog.setCastrado(rs.getBoolean("castrado"));
            return dog;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de cachorro.", e);
        }
    }

    public List<Cachorro> list(){
        try {
            sql = "SELECT * FROM Cachorro";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Cachorro> cachorros = new ArrayList<>();
            while(rs.next()){
                Cachorro dog = new Cachorro();
                dog.setId(rs.getInt("id"));
                dog.setApelido(rs.getString("apelido"));
                dog.setIdade(rs.getInt("idade"));
                dog.setSexo(rs.getBoolean("sexo"));
                dog.setVacinado(rs.getBoolean("vacinado"));
                dog.setCastrado(rs.getBoolean("castrado"));
                cachorros.add(dog);
            }
            rs.close();
            return cachorros;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na listagem dos cachorros.", e);
        }
    }

    public void remove(Cachorro c){
        try {
            sql = "DELETE FROM Cachorro WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,c.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão de cachorro.", e);
        }
    }

}
