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

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;

    public void add(Funcionario f){
        try {
            sql = "INSERT INTO Funcionario(id,nome,cpf,endereco,telefone,email,salario) VALUES (?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            stmt.setString(2,f.getNome());
            stmt.setString(3,f.getCpf());
            stmt.setString(4,f.getEndereco());
            stmt.setString(5,f.getTelefone());
            stmt.setString(6, f.getEmail());
            stmt.setDouble(7,f.getSalario());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro no cadastro de funcionário", e);
        }
    }

    public void update(Funcionario f){
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
        }
    }

    public Funcionario read(Funcionario f){
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
        }
    }
    public Funcionario read(int id){
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
        }
    }

    public List<Funcionario> list(){
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
        }
    }

    public void remove(Funcionario f){
        try {
            sql = "DELETE FROM Funcionario WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,f.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão do funcionário.", e);
        }
    }
}
