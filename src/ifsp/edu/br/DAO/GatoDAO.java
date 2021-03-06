package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Animais.Gato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GatoDAO {


    public void add(Gato g){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            sql = "INSERT INTO Animal(apelido,raca,idade,sexo,vacinado,castrado,tipo) VALUES (?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,g.getApelido());
            stmt.setString(2,g.getRaca());
            stmt.setInt(3,g.getIdade());
            stmt.setBoolean(4,g.isSexo());
            stmt.setBoolean(5,g.isVacinado());
            stmt.setBoolean(6,g.isCastrado());
            stmt.setString(7,"Gato");
//            stmt.setInt(8,g.getAdocao().getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro no cadastro de gato.", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public void update(Gato g){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            sql = "UPDATE Animal SET apelido = ?,idade = ?,vacinado = ?,castrado = ?, adocao_id = ? WHERE id =?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,g.getApelido());
            stmt.setInt(2,g.getIdade());
            stmt.setBoolean(3,g.isVacinado());
            stmt.setBoolean(4,g.isCastrado());
            stmt.setInt(5,g.getAdocao());
            stmt.setInt(6,g.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na atualização do gato.", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public Gato read(Gato g){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            sql = "SELECT * FROM Animal WHERE id =?";
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
            cat.setAdocao(rs.getInt("adocao_id"));
            rs.close();
            stmt.close();
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de gato.",e );
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public Gato read(int id){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            sql = "SELECT * FROM Animal WHERE id =?";
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
            cat.setAdocao(rs.getInt("adocao_id"));
            rs.close();
            stmt.close();
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de gato.",e );
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public List<Gato> list(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            sql = "SELECT * FROM Animal WHERE tipo like 'Gato'  and adocao_id like '0'";
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
                cat.setAdocao(rs.getInt("adocao_id"));
                gatos.add(cat);
            }
            rs.close();
            stmt.close();
            return gatos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na listagem dos gatos.",e );
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public void remove(Gato g){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            sql = "DELETE FROM Animal WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,g.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão do gato.", e);
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
            throw new RuntimeException("erro", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public int idSequenceAnimal(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            int id;
            sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Animal'";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt("seq");
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
