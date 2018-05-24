package ifsp.edu.br.DAO;

import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    Connection connection = Database.getConnection();
    String sql = "";
    PreparedStatement stmt = null;

    public void add(Produto p){
        try {
            sql = "INSERT INTO Produto(id,descricao,preco,qtd) VALUES(?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,p.getId());
            stmt.setString(2,p.getDescricao());
            stmt.setDouble(3,p.getPreco());
            stmt.setInt(4,p.getQtd());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na adição de um produto.", e);
        }
    }

    public void update(Produto p){
        try {
            sql = "UPDATE Produto SET descricao = ?,preco = ?,qtd = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,p.getDescricao());
            stmt.setDouble(2,p.getPreco());
            stmt.setInt(3,p.getQtd());
            stmt.setInt(4,p.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na atualização de produto.",e);
        }
    }

    public Produto read(Produto p){
        try {
            sql = "SELECT * FROM Produto WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,p.getId());
            ResultSet rs = stmt.executeQuery();
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQtd(rs.getInt("qtd"));
            rs.close();
            stmt.close();
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na leitura de produto.",e);
        }
    }
    public Produto read(int id){
        try {
            sql = "SELECT * FROM Produto WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQtd(rs.getInt("qtd"));
            rs.close();
            stmt.close();
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na leitura de produto.",e);
        }
    }

    public List<Produto> list(){
        try {
            sql = "SELECT * FROM Produto";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtd(rs.getInt("qtd"));
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na listagem de produto.",e);
        }
    }

    public void remove(Produto p){
        try {
            sql = "DELETE FROM Produto WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,p.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão de produto.",e);
        }
    }
}
