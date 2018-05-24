package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Conta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;

    public void add(Conta c){
        try {
            sql = "INSERT INTO Conta(id,descricao,valor,vencimento) VALUES(?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,c.getId());
            stmt.setString(2,c.getDescricao());
            stmt.setDouble(3,c.getValor());
            stmt.setDate(4, (Date) c.getVencimento());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na adição de uma conta.", e);
        }
    }

    public void update(Conta c){
        try {
            sql = "UPDATE Conta SET descricao = ?, valor = ?, vencimento = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,c.getDescricao());
            stmt.setDouble(2,c.getValor());
            stmt.setDate(3, (Date) c.getVencimento());
            stmt.setInt(4,c.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na atualização de uma conta.", e);
        }

    }

    public Conta read(Conta c){
        try {
            sql = "SELECT * FROM Conta WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,c.getId());
            ResultSet rs = stmt.executeQuery();
            Conta conta = new Conta();
            conta.setId(rs.getInt("id"));
            conta.setDescricao(rs.getString("descricao"));
            conta.setValor(rs.getDouble("valor"));
            conta.setVencimento(rs.getDate("vencimento"));
            rs.close();
            stmt.close();
            return conta;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na leitura de uma conta.", e);
        }
    }

    public Conta read(int id){
        try {
            sql = "SELECT * FROM Conta WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Conta conta = new Conta();
            conta.setId(rs.getInt("id"));
            conta.setDescricao(rs.getString("descricao"));
            conta.setValor(rs.getDouble("valor"));
            conta.setVencimento(rs.getDate("vencimento"));
            rs.close();
            stmt.close();
            return conta;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na leitura de uma conta.", e);
        }
    }

    public List<Conta> list(){
        try {
            sql = "SELECT * FROM Conta";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Conta> contas = new ArrayList<>();
            while (rs.next()){
                Conta conta = new Conta();
                conta.setId(rs.getInt("id"));
                conta.setDescricao(rs.getString("descricao"));
                conta.setValor(rs.getDouble("valor"));
                conta.setVencimento(rs.getDate("vencimento"));
                contas.add(conta);
            }
            rs.close();
            stmt.close();
            return contas;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na listagem das contas.", e);
        }
    }

    public void remove(Conta c){
        try {
            sql = "DELETE FROM Conta WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,c.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão de uma conta.", e);
        }
    }
}
