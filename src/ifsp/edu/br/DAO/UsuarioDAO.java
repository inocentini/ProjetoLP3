package ifsp.edu.br.DAO;

import ifsp.edu.br.Database;
import ifsp.edu.br.Modelo.Pessoas.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    //public static List<Usuario> usuarios = new ArrayList<>();

    Connection connection = Database.getConnection();

    public void add(Usuario u){
//        usuarios.add(u);
        try {
            String sql = "INSERT INTO Usuario(id,nome,cpf,endereco,telefone,email) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, u.getId());
            stmt.setString(2,u.getNome());
            stmt.setString(3,u.getCpf());
            stmt.setString(4,u.getEndereco());
            stmt.setString(5,u.getTelefone());
            stmt.setString(6, u.getEmail());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro no cadastro de usuário", e);
        }
        //usuarios.add(u);
    }

    public void update(Usuario u){
//        for (Usuario user : usuarios) {
//            if(user.getId() == u.getId()){
//                user.setId(u.getId());
//                user.setNome(u.getNome());
//                user.setCpf(u.getCpf());
//                user.setEndereco(u.getEndereco());
//                user.setTelefone(u.getTelefone());
//                user.setEmail(u.getEmail());
//            }
//        }
        try {
            String sql = "UPDATE Usuario SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,u.getNome());
            stmt.setString(2,u.getEndereco());
            stmt.setString(3,u.getTelefone());
            stmt.setString(4,u.getEmail());
            stmt.setInt(5,u.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na atualização do usuário", e);
        }
    }

    public Usuario read(int id){
//        for(Usuario user : usuarios){
//            if(user.getId() == id) {
//                return user;
//            }
//        }
//        return null;
        try {
            String sql = "SELECT * FROM Usuario WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Usuario user = new Usuario();
            user.setId(rs.getInt("id"));
            user.setNome(rs.getString("nome"));
            user.setCpf(rs.getString("cpf"));
            user.setEndereco(rs.getString("endereco"));
            user.setTelefone(rs.getString("telefone"));
            user.setEmail(rs.getString("email"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de usuário", e);
        }
    }

    public List<Usuario> list(){
//        for(Usuario user : usuarios){
//            System.out.println(user.toString());
//        }
        try {
            String sql = "SELECT * FROM Usuario";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while(rs.next()){
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setEndereco(rs.getString("endereco"));
                user.setTelefone(rs.getString("telefone"));
                user.setEmail(rs.getString("email"));
                usuarios.add(user);
            }
            rs.close();
            stmt.close();
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na listagem dos usuários", e);
        }
    }

    public void remove(Usuario u){
//        usuarios.remove(u);
        try {
            String sql = "DELETE FROM Usuario WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,u.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão do usuário.", e);
        }
    }
}
