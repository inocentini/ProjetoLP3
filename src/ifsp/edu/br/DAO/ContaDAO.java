package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Conta;
import javafx.scene.input.DataFormat;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ContaDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;
    DateFormat dateFormatDB = new SimpleDateFormat("yyyy-MM-dd");

    public void add(Conta c){
        try {
            sql = "INSERT INTO Conta(descricao,valor,vencimento) VALUES(?,?,?)";
            stmt = connection.prepareStatement(sql);
//            stmt.setInt(1,c.getId());
            stmt.setString(1,c.getDescricao());
            stmt.setDouble(2,c.getValor());
            String date = dateFormatDB.format(c.getVencimento());
            stmt.setDate(3, java.sql.Date.valueOf(date));
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
            String dataConta = dateFormatDB.format(c.getVencimento());
            stmt.setDate(3, java.sql.Date.valueOf(dataConta));
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
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            ResultSet rs = stmt.executeQuery();
            Conta conta = new Conta();
            conta.setId(rs.getInt("id"));
            conta.setDescricao(rs.getString("descricao"));
            conta.setValor(rs.getDouble("valor"));
            String dataConta = dateFormat.format(rs.getDate("vencimento"));
            java.util.Date contaData = new java.util.Date(dataConta);
            conta.setVencimento(contaData);
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
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            ResultSet rs = stmt.executeQuery();
            Conta conta = new Conta();
            conta.setId(rs.getInt("id"));
            conta.setDescricao(rs.getString("descricao"));
            conta.setValor(rs.getDouble("valor"));
            String dataConta = dateFormat.format(rs.getDate("vencimento"));
            java.util.Date contaData = new java.util.Date(dataConta);
            conta.setVencimento(contaData);
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

    public int nextSeqConta(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            int id;
            sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Conta'";
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
