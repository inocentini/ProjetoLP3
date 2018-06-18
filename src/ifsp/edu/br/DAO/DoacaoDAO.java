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
            for(Produto p : d.getProdutos()){
                ProdutoDAO produtoDAO = new ProdutoDAO();
                PreparedStatement stmtProd = null;
                Connection connection2 = Database.getConnection();
                String sqlProd = "";
                sqlProd = "INSERT INTO DoacaoProduto (doacao_id, produto_id) VALUES(?,?)";
                stmtProd = connection2.prepareStatement(sqlProd);
                stmtProd.setInt(1,nextSeqDoacao());
                stmtProd.setInt(2,produtoDAO.idSequenceProd());
                stmtProd.execute();
                Database.closeConnection(connection2,stmtProd);
            }
            for(Animal a : d.getAnimais()){
                int id = 0;
                if(a.getClass() == Cachorro.class){
                    CachorroDAO dogDAO = new CachorroDAO();
                    id = dogDAO.idSequenceAnimal();
                }else if(a.getClass() == Gato.class){
                    GatoDAO catDAO = new GatoDAO();
                    id = catDAO.idSequenceAnimal();
                }
                PreparedStatement stmtAnimal = null;
                Connection connection3 = Database.getConnection();
                String sqlAnimal = "";
                sqlAnimal = "INSERT INTO DoacaoAnimal (doacao_id, animal_id) VALUES(?,?)";
                stmtAnimal = connection3.prepareStatement(sqlAnimal);
                stmtAnimal.setInt(1,nextSeqDoacao());
                stmtAnimal.setInt(2,id);
                stmtAnimal.execute();
                Database.closeConnection(connection3,stmtAnimal);
            }
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
            sql = "SELECT * FROM Doacao WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,d.getId());
            ResultSet rs = stmt.executeQuery();
            Doacao doa = new Doacao();
            doa.setId(rs.getInt("id"));
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user = userDAO.read(rs.getInt("user_id"));
            doa.setUser(user);
            doa.setData(rs.getDate("data"));

            Connection connection2 = Database.getConnection();
            String sql2 = "SELECT * FROM Animal as a JOIN DoacaoAnimal as d on a.id = d.animal_id";
            PreparedStatement stmt2 = connection2.prepareStatement(sql2);
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

            Connection connection3 = Database.getConnection();
            String sql3 = "SELECT * FROM Produto as p JOIN DoacaoProduto as d on p.id = d.produto_id";
            PreparedStatement stmt3 = connection3.prepareStatement(sql3);
            ResultSet rsProd = stmt3.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            while(rsProd.next()){
                Produto p = new Produto();
                p = produtoDAO.read(rsProd.getInt("id"));
                produtos.add(p);
            }
            doa.setProdutos(produtos);

            rs.close();
            stmt.close();
            reset.close();
            stmt2.close();
            stmt3.close();
            rsProd.close();
            connection2.close();
            connection3.close();
            return doa;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de doacao.", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public Doacao read(int id){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Doacao WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Doacao doa = new Doacao();
            doa.setId(rs.getInt("id"));
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user = userDAO.read(rs.getInt("user_id"));
            doa.setUser(user);
            doa.setData(rs.getDate("data"));

            Connection connection2 = Database.getConnection();
            String sql2 = "SELECT * FROM Animal as a JOIN DoacaoAnimal as d on a.id = d.animal_id";
            PreparedStatement stmt2 = connection2.prepareStatement(sql2);
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

            Connection connection3 = Database.getConnection();
            String sql3 = "SELECT * FROM Produto as p JOIN DoacaoProduto as d on p.id = d.produto_id";
            PreparedStatement stmt3 = connection3.prepareStatement(sql3);
            ResultSet rsProd = stmt3.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            while(rsProd.next()){
                Produto p = produtoDAO.read(rsProd.getInt("id"));
                produtos.add(p);
            }
            doa.setProdutos(produtos);

            rs.close();
            stmt.close();
            reset.close();
            stmt2.close();
            stmt3.close();
            rsProd.close();
            connection2.close();
            connection3.close();
            return doa;
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa de doacao.", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public List<Doacao> list() {
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "SELECT * FROM Doacao";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Doacao> adocoes = new ArrayList<>();
            while (rs.next()) {
                Doacao doa = new Doacao();
                doa.setId(rs.getInt("id"));
                Usuario user;
                UsuarioDAO userDao = new UsuarioDAO();
                user = userDao.read(rs.getInt("user_id"));
                doa.setUser(user);
                doa.setData(rs.getDate("data"));

                Connection connection2 = Database.getConnection();
                String sql2 = "SELECT * FROM Animal as a JOIN DoacaoAnimal as d on a.id = d.animal_id";
                PreparedStatement stmt2 = connection2.prepareStatement(sql2);
                ResultSet rsAnimal = stmt2.executeQuery();
                List<Animal> animais = new ArrayList<>();
                CachorroDAO dogDaoList = new CachorroDAO();
                GatoDAO gatoDAOList = new GatoDAO();
                while (rsAnimal.next()) {
                    String tipo = rsAnimal.getString("tipo");
                    int id = rsAnimal.getInt("id");
                    if (tipo.equals("Cachorro")) {
                        Animal doglist = dogDaoList.read(rsAnimal.getInt("id"));
                        animais.add(doglist);
                    } else if (tipo.equals("Gato")) {
                        Animal gatolist = gatoDAOList.read(id);
                        animais.add(gatolist);
                    }
                }
                doa.setAnimais(animais);

                Connection connection3 = Database.getConnection();
                String sql3 = "SELECT * FROM Produto as p JOIN DoacaoProduto as d on p.id = d.produto_id";
                PreparedStatement stmt3 = connection3.prepareStatement(sql3);
                ResultSet rsProd = stmt3.executeQuery();
                List<Produto> produtos = new ArrayList<>();
                ProdutoDAO produtoDAO = new ProdutoDAO();
                while(rsProd.next()){
                    int id = rsProd.getInt("id");
                    Produto p = produtoDAO.read(id);
                    produtos.add(p);
                }
                doa.setProdutos(produtos);

                adocoes.add(doa);
                stmt2.close();
                rsAnimal.close();
                connection2.close();
                stmt3.close();
                rsProd.close();
                connection3.close();
            }
            return adocoes;

        } catch (SQLException e1) {
            throw new RuntimeException("Erro", e1);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public void remove(Doacao d){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "DELETE FROM Doacao WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,d.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão de doacao.", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }

    public int nextSeqDoacao(){
        Connection connection = Database.getConnection();
        String sql = "";
        PreparedStatement stmt = null;
        try {
            int id;
            sql = "SELECT seq FROM sqlite_sequence WHERE name = 'Doacao'";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt("seq");
            rs.close();
            stmt.close();
            return id++;
        } catch (SQLException e) {
            throw new RuntimeException("erro", e);
        }finally {
            Database.closeConnection(connection,stmt);
        }
    }


}
