package ifsp.edu.br.DAO;

import ifsp.edu.br.Database;
import ifsp.edu.br.Modelo.Adocao;
import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdocaoDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;

    public void add(Adocao a) {
        try {
            sql = "INSERT INTO Adocao (id, user_id, data) VALUES (?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,a.getId());
            stmt.setInt(2,a.getUser().getId());
            stmt.setDate(3, (Date) a.getData());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na inserção de adoção.", e);
        }
    }

    public void update(Adocao adocao){
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
    }
}
