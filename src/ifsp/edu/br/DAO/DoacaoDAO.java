package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;
import ifsp.edu.br.Modelo.Doacao;
import ifsp.edu.br.Modelo.Produto;

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

    public Doacao read(Doacao d){
        for(Doacao doacao : doacoes){
            if(doacao.getId() == d.getId()){
                return doacao;
            }
        }
        return null;
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
            System.out.println("ID: "+doacao.getId()+
                    "\nUsuário: "+doacao.getUser().getNome()+
                    "\nData: "+doacao.getData());
            for(Animal animal : doacao.getAnimais()){
                if(animal.getClass() == Cachorro.class){
                    System.out.println("Cachorro:");
                    System.out.println("ID:"+animal.getId()+
                            "\nApelido: "+animal.getApelido()+
                            "\nIdade: "+animal.getIdade()+
                            "\nVacinado?"+animal.isVacinado()+
                            "\nCastrado?"+animal.isCastrado());
                }else if(animal.getClass() == Gato.class){
                    System.out.println("Gato:");
                    System.out.println("ID:"+animal.getId()+
                            "\nApelido: "+animal.getApelido()+
                            "\nIdade: "+animal.getIdade()+
                            "\nVacinado?"+animal.isVacinado()+
                            "\nCastrado?"+animal.isCastrado());
                }
            }
            for(Produto produto : doacao.getProdutos()){
                System.out.println("ID:"+produto.getId()+
                        "\nDescrição:"+produto.getDescricao()+
                        "\nPreço:"+produto.getPreco()+
                        "\nQuantidade:"+produto.getQtd());
            }
        }
    }

    public void remove(Doacao d){
        doacoes.remove(d);
    }
}
