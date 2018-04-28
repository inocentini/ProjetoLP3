package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Adocao;
import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;

import java.util.ArrayList;
import java.util.List;

public class AdocaoDAO {
    public static List<Adocao> adocoes = new ArrayList<>();


    public void add(Adocao a){
        adocoes.add(a);
    }

    public void update(Adocao adocao){
        for(Adocao a : adocoes){
            if(a.getId() == adocao.getId()){
                a.setUser(adocao.getUser());
                a.setAnimais(adocao.getAnimais());
                a.setData(adocao.getData());
            }
        }
    }

    public Adocao read(Adocao a){
        for(Adocao adocao : adocoes){
            if(adocao.getId() == a.getId()){
                return adocao;
            }
        }
        return null;
    }

    public Adocao read(int id){
        for(Adocao adocao : adocoes){
            if(adocao.getId() == id){
                return adocao;
            }
        }
        return null;
    }

    public void list(){
        for(Adocao adocao : adocoes){
            System.out.println("ID: "+adocao.getId()+
                    "\nUsuário: "+adocao.getUser().getNome()+
                    "\nData: "+adocao.getData());
            for(Animal animal : adocao.getAnimais()){
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
        }
    }

    public void remove(Adocao a){
        adocoes.remove(a);
    }
}
