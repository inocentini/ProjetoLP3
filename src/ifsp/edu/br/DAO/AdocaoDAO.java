package ifsp.edu.br.DAO;

import ifsp.edu.br.Database;
import ifsp.edu.br.Modelo.Adocao;
import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;
import ifsp.edu.br.Modelo.Pessoas.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdocaoDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;

    public void add(Adocao a) {
        try {
            sql = "INSERT INTO Adocao (user_id, data) VALUES (?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,a.getUser().getId());
            stmt.setString(2, a.getData().toString());
            stmt.execute();
            stmt.close();
            for(Animal animal : a.getAnimais()){
                sql = "INSERT INTO AdocaoAnimal (adocao_id, animal_id) VALUES (?,?)";
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1,a.getId());
                stmt.setInt(2,animal.getId());
                stmt.execute();
            }
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
            Usuario user = new Usuario();
            user = userDAO.read(rs.getInt("user_id"));
            ad.setUser(user);
            ad.setData(rs.getDate("data"));
            rs.close();
            stmt.close();
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de adocao.", e);
        }
    }

    /*public void update(Adocao adocao){
        for(Adocao a : adocoes){
            if(a.getId() == adocao.getId()){
                a.setUser(adocao.getUser());
                a.setAnimais(adocao.getAnimais());
                a.setData(adocao.getData());
            }
        }
    }

    public Adocao read(int id){
        for(Adocao adocao : adocoes){
            if(adocao.getId() == id){
                return adocao;
            }
        }
        return null;
    }

    public void list(){
        for(Adocao adocao : adocoes){
            System.out.println(adocao.toString());
            for(Animal animal : adocao.getAnimais()){
                if(animal.getClass() == Cachorro.class)
                    System.out.println("Cachorro:");
                else if(animal.getClass() == Gato.class)
                    System.out.println("Gato:");

                System.out.println(animal.toString());
            }
        }
    }

    public void remove(Adocao a){
        adocoes.remove(a);
    }*/

}
