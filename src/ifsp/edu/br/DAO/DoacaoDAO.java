package ifsp.edu.br.DAO;

import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import ifsp.edu.br.Model.Doacao;
import ifsp.edu.br.Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {
    public static List<Doacao> doacoes = new ArrayList<>();


    public void add(Doacao d){
        doacoes.add(d);
    }

    public void update(Doacao doacao){
        for(Doacao d : doacoes){
            if(d.getId() == doacao.getId()){
                d.setUser(doacao.getUser());
                d.setAnimais(doacao.getAnimais());
                d.setData(doacao.getData());
            }
        }
    }

    public Doacao read(int id){
        for(Doacao doacao : doacoes){
            if(doacao.getId() == id){
                return doacao;
            }
        }
        return null;
    }

    public void list(){
        for(Doacao doacao : doacoes){
            System.out.println(doacao.toString());
            for(Animal animal : doacao.getAnimais()){
                if(animal.getClass() == Cachorro.class)
                    System.out.println("Cachorro:");
                else if(animal.getClass() == Gato.class)
                    System.out.println("Gato:");

                System.out.println(animal.toString());
            }
            for(Produto produto : doacao.getProdutos()){
                System.out.println(produto.toString());
            }
        }
    }

    public void remove(Doacao d){
        doacoes.remove(d);
    }
}
