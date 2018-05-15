package ifsp.edu.br.DAO;

import ifsp.edu.br.Database;
import ifsp.edu.br.Modelo.Adocao;
import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AdocaoDAO {
    private static List<Adocao> adocoes = new ArrayList<>();
    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;
    
    public void add(Adocao a) {

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
