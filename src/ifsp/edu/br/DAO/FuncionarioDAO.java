package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Pessoas.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {



    public void add(Funcionario f){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "INSERT INTO Funcionario(nome,cpf,endereco,telefone,email,salario) VALUES (?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sql);
//            stmt.setInt(1, f.getId());
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getCpf());
            stmt.setString(3,f.getEndereco());
            stmt.setString(4,f.getTelefone());
            stmt.setString(5, f.getEmail());
            stmt.setDouble(6,f.getSalario());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro no cadastro de funcionário", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public void update(Funcionario f){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "UPDATE Funcionario SET nome = ?, endereco = ?, telefone = ?, email = ?, salario = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getEndereco());
            stmt.setString(3,f.getTelefone());
            stmt.setString(4,f.getEmail());
            stmt.setDouble(5,f.getSalario());
            stmt.setInt(6,f.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na atualização do funcionário", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public Funcionario read(Funcionario f){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Funcionario WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            ResultSet rs = stmt.executeQuery();
            Funcionario func = new Funcionario();
            func.setId(rs.getInt("id"));
            func.setNome(rs.getString("nome"));
            func.setCpf(rs.getString("cpf"));
            func.setEndereco(rs.getString("endereco"));
            func.setTelefone(rs.getString("telefone"));
            func.setEmail(rs.getString("email"));
            func.setSalario(rs.getDouble("salario"));
            rs.close();
            stmt.close();
            return func;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de funcionário", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }
    public Funcionario read(int id){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Funcionario WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Funcionario func = new Funcionario();
            func.setId(rs.getInt("id"));
            func.setNome(rs.getString("nome"));
            func.setCpf(rs.getString("cpf"));
            func.setEndereco(rs.getString("endereco"));
            func.setTelefone(rs.getString("telefone"));
            func.setEmail(rs.getString("email"));
            func.setSalario(rs.getDouble("salario"));
            rs.close();
            stmt.close();
            return func;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de funcionário", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public List<Funcionario> list(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Funcionario";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Funcionario> funcionarios = new ArrayList<>();
            while(rs.next()){
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setEndereco(rs.getString("endereco"));
                func.setTelefone(rs.getString("telefone"));
                func.setEmail(rs.getString("email"));
                func.setSalario(rs.getDouble("salario"));
                funcionarios.add(func);
            }
            rs.close();
            stmt.close();
            return funcionarios;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na listagem dos funcionários", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public void remove(Funcionario f){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "DELETE FROM Funcionario WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,f.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão do funcionário.", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public int nextSeqFunc(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            int id;
            sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Funcionario'";
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
