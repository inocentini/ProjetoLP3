package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Animais.Cachorro;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CachorroDAO {


    public void add(Cachorro c){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
       try {
           sql = "INSERT INTO Animal(apelido,raca,idade,sexo,vacinado,castrado,tipo) VALUES (?,?,?,?,?,?,?)";
           stmt = connection.prepareStatement(sql);
           stmt.setString(1,c.getApelido());
           stmt.setString(2,c.getRaca());
           stmt.setInt(3,c.getIdade());
           stmt.setBoolean(4,c.isSexo());
           stmt.setBoolean(5,c.isVacinado());
           stmt.setBoolean(6,c.isCastrado());
           stmt.setString(7,"Cachorro");
//           stmt.setInt(8,c.getAdocao().getId());
           stmt.execute();
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           Database.closeConnection(connection,stmt);
       }
    }

    public void update(Cachorro c){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try{
            sql = "UPDATE Animal SET apelido = ?, idade = ?, vacinado = ?, castrado = ?, adocao_id = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,c.getApelido());
            stmt.setInt(2,c.getIdade());
            stmt.setBoolean(3,c.isVacinado());
            stmt.setBoolean(4,c.isCastrado());
            stmt.setInt(5,c.getAdocao());
            stmt.setInt(6,c.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public Cachorro read(Cachorro c){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Animal WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,c.getId());
            ResultSet rs = stmt.executeQuery();
            Cachorro dog = new Cachorro();
            dog.setId(rs.getInt("id"));
            dog.setApelido(rs.getString("apelido"));
            dog.setRaca(rs.getString("raca"));
            dog.setIdade(rs.getInt("idade"));
            dog.setSexo(rs.getBoolean("sexo"));
            dog.setVacinado(rs.getBoolean("vacinado"));
            dog.setCastrado(rs.getBoolean("castrado"));
            dog.setAdocao(rs.getInt("adocao_id"));
            rs.close();
            return dog;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
        return null;
    }

    public Cachorro read(int id){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Animal WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Cachorro dog = new Cachorro();
            dog.setId(rs.getInt("id"));
            dog.setApelido(rs.getString("apelido"));
            dog.setRaca(rs.getString("raca"));
            dog.setIdade(rs.getInt("idade"));
            dog.setSexo(rs.getBoolean("sexo"));
            dog.setVacinado(rs.getBoolean("vacinado"));
            dog.setAdocao(rs.getInt("adocao_id"));
            rs.close();
            return dog;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
        return null;
    }

    public List<Cachorro> list(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Animal WHERE tipo like 'Cachorro' and adocao_id like '0'";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Cachorro> cachorros = new ArrayList<>();
            while(rs.next()){
                Cachorro dog = new Cachorro();
                dog.setId(rs.getInt("id"));
                dog.setApelido(rs.getString("apelido"));
                dog.setRaca(rs.getString("raca"));
                dog.setIdade(rs.getInt("idade"));
                dog.setSexo(rs.getBoolean("sexo"));
                dog.setVacinado(rs.getBoolean("vacinado"));
                dog.setCastrado(rs.getBoolean("castrado"));
                dog.setAdocao(rs.getInt("adocao_id"));
                cachorros.add(dog);
            }
            rs.close();
            return cachorros;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
        return null;
    }

    public void remove(Cachorro c){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "DELETE FROM Animal WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,c.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }
    public int nextSeqAnimal(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            int id;
            sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Animal'";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt("seq");
            id++;
            rs.close();
            stmt.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
        return Integer.parseInt(null);
    }
}
