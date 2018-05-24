package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Adocao;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import ifsp.edu.br.Model.Pessoas.Usuario;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdocaoDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    

    public void add(Adocao a) {
        try {
            sql = "INSERT INTO Adocao (user_id, data) VALUES (?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,a.getUser().getId());
            stmt.setDate(2, (Date) a.getData());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na inserção de adoção.", e);
        }
    }

    public Adocao read(Adocao a){
        try {
            sql = "SELECT * FROM Adocao WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,a.getId());
            ResultSet rs = stmt.executeQuery();
            Adocao ad = new Adocao();
            ad.setId(rs.getInt("id"));
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user;
            user = userDAO.read(rs.getInt("user_id"));
            ad.setUser(user);
            ad.setData(rs.getDate("data"));

            String sql2 = "SELECT * FROM Animal WHERE adocao = ?";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setInt(1,ad.getId());
            ResultSet reset = stmt2.executeQuery();
            List<Animal> animais = new ArrayList<>();
            CachorroDAO dogDao = new CachorroDAO();
            GatoDAO catDAO = new GatoDAO();
            while(reset.next()){
                if(reset.getString("tipo") == "Cachorro"){
                    Animal dog = new Cachorro();
                    dog = dogDao.read(rs.getInt("id"));
                    animais.add(dog);
                }else if(reset.getString("tipo") == "Gato"){
                    Animal gato = new Gato();
                    gato = catDAO.read(rs.getInt("id"));
                    animais.add(gato);
                }
            }
            ad.setAnimais(animais);
            rs.close();
            stmt.close();
            reset.close();
            stmt2.close();
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de adocao.", e);
        }
    }

    public Adocao read(int id){
        try {
            sql = "SELECT * FROM Adocao WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Adocao ad = new Adocao();
            ad.setId(rs.getInt("id"));
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user;
            user = userDAO.read(rs.getInt("user_id"));
            ad.setUser(user);
            ad.setData(rs.getDate("data"));

            String sql2 = "SELECT * FROM Animal WHERE adocao = ?";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setInt(1,ad.getId());
            ResultSet reset = stmt2.executeQuery();
            List<Animal> animais = new ArrayList<>();
            CachorroDAO dogDao = new CachorroDAO();
            GatoDAO catDAO = new GatoDAO();
            while(reset.next()){
                if(reset.getString("tipo") == "Cachorro"){
                    Animal dog = new Cachorro();
                    dog = dogDao.read(rs.getInt("id"));
                    animais.add(dog);
                }else if(reset.getString("tipo") == "Gato"){
                    Animal gato = new Gato();
                    gato = catDAO.read(rs.getInt("id"));
                    animais.add(gato);
                }
            }
            ad.setAnimais(animais);
            rs.close();
            stmt.close();
            reset.close();
            stmt2.close();
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de adocao.", e);
        }
    }

    public List<Adocao> list() {
        try {
            sql = "SELECT * FROM Adocao";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Adocao> adocoes = new ArrayList<>();
            while (rs.next()) {
                Adocao ad = new Adocao();
                ad.setId(rs.getInt("id"));
                Usuario user;
                UsuarioDAO userDao = new UsuarioDAO();
                user = userDao.read(rs.getInt("user_id"));
                ad.setUser(user);
                ad.setData(rs.getDate("data"));
                String sql2 = "SELECT * FROM Animal WHERE adocao_id = ?";
                PreparedStatement stmt2 = connection.prepareStatement(sql2);
                stmt2.setInt(1, ad.getId());
                ResultSet rs2 = stmt2.executeQuery();
                List<Animal> animais = new ArrayList<>();
                CachorroDAO dogDaoList = new CachorroDAO();
                GatoDAO gatoDAOList = new GatoDAO();
                while (rs2.next()) {
                    if (rs2.getString("tipo") == "Cachorro") {
                        Animal doglist = new Cachorro();
                        doglist = dogDaoList.read(rs2.getInt("id"));
                        animais.add(doglist);
                    } else if (rs2.getString("tipo") == "Gato") {
                        Animal gatolist = new Gato();
                        gatolist = gatoDAOList.read(rs.getInt("id"));
                        animais.add(gatolist);
                    }
                }
                ad.setAnimais(animais);
            }
            return adocoes;

        } catch (SQLException e1) {
            throw new RuntimeException("Erro", e1);
        }
    }


    public int nextSeqAdocao(){
        try {
            int id;
            sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Adocao'";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt("seq");
            rs.close();
            stmt.close();
            return id++;
        } catch (SQLException e) {
            throw new RuntimeException("erro", e);
        }
    }

    public void remove(Adocao a){
        try {
            sql = "DELETE FROM Adocao WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,a.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão de adoção.", e);
        }
    }

}
