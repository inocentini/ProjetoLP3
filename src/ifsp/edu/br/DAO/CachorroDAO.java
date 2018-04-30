package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Animais.Cachorro;

import java.util.ArrayList;
import java.util.List;

public class CachorroDAO {
    public static List<Cachorro> cachorros = new ArrayList<>();

    public void add(Cachorro c){
        cachorros.add(c);
    }

    public void update(Cachorro c){
        for (Cachorro dog : cachorros) {
            if(dog.getId() == c.getId()){
                dog.setId(c.getId());
                dog.setApelido(c.getApelido());
                dog.setIdade(c.getIdade());
                dog.setSexo(c.isSexo());
                dog.setVacinado(c.isVacinado());
                dog.setCastrado(c.isVacinado());
            }
        }
    }

    public Cachorro read(Cachorro c){
        for(Cachorro dog : cachorros){
            if(dog.getId() == c.getId());
                return dog;
        }
        return null;
    }

    public Cachorro read(int id){
        for(Cachorro dog : cachorros){
            if(dog.getId() == id);
            return dog;
        }
        return null;
    }

    public void list(){
        for(Cachorro dog : cachorros){
            String sex = dog.isSexo()? "macho" : "femea";
            System.out.println("\nID: "+dog.getId()+
                    "\nApelido: "+dog.getApelido()+
                    "\nIdade: "+dog.getIdade()+
                    "\nSexo: "+sex+
                    "\nVacinado? "+dog.isVacinado()+
                    "\nCastrado? "+dog.isCastrado());
        }
    }

    public void remove(Cachorro c){
        cachorros.remove(c);
    }
}
