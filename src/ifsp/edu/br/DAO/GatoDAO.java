package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Animais.Gato;

import java.util.ArrayList;
import java.util.List;

public class GatoDAO {
    private static List<Gato> gatos = new ArrayList<>();

    public void add(Gato g){
        gatos.add(g);
    }

    public void update(Gato g){
        for (Gato cat : gatos) {
            if(cat.getId() == g.getId()){
                cat.setId(g.getId());
                cat.setApelido(g.getApelido());
                cat.setIdade(g.getIdade());
                cat.setSexo(g.isSexo());
                cat.setVacinado(g.isVacinado());
                cat.setCastrado(g.isVacinado());
            }
        }
    }

    public Gato read(int id){
        for(Gato cat : gatos){
            if(cat.getId() == id)
                return cat;
        }
        return null;
    }

    public void list(){
        for(Gato cat : gatos)
            System.out.println(cat.toString());
    }

    public void remove(Gato g){
        gatos.remove(g);
    }
}
