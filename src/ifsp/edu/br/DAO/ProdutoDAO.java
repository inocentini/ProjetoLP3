package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public static List<Produto> produtos = new ArrayList<>();

    public void add(Produto p){
        produtos.add(p);
    }

    public void update(Produto p){
        for(Produto produto : produtos){
            if(produto.getId() == p.getId()){
                produto.setDescricao(p.getDescricao());
                produto.setPreco(p.getPreco());
                produto.setQtd(p.getQtd());
            }
        }
    }

    public Produto read(int id){
        for(Produto produto : produtos){
            if(produto.getId() == id){
                return produto;
            }
        }
        return null;
    }

    public void list(){
        for(Produto produto : produtos){
            System.out.println(produto.toString());
        }
    }

    public void remove(Produto p){
        produtos.remove(p);
    }
}
