package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import ifsp.edu.br.Model.Doacao;
import ifsp.edu.br.Model.Pessoas.Usuario;
import ifsp.edu.br.Model.Produto;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public void add(Doacao d){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "INSERT INTO Doacao (user_id, data) VALUES (?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,d.getUser().getId());
            String data = df.format(d.getData());
            stmt.setDate(2, Date.valueOf(data));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public void update(Doacao d){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "UPDATE Doacao SET user_id = ?, data = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,d.getUser().getId());
            String data = df.format(d.getData());
            stmt.setDate(2, java.sql.Date.valueOf(data));
            stmt.setInt(3,d.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public Doacao read(Doacao d){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Adocao WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,d.getId());
            ResultSet rs = stmt.executeQuery();
            Doacao doa = new Doacao();
            doa.setId(rs.getInt("id"));
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user;
            user = userDAO.read(rs.getInt("user_id"));
            doa.setUser(user);
            doa.setData(rs.getDate("data"));

            String sql2 = "SELECT * FROM Animal WHERE adocao = ?";
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setInt(1,doa.getId());
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
            doa.setAnimais(animais);

            rs.close();
            stmt.close();
            reset.close();
            stmt2.close();
            return doa;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de adocao.", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }
}
