package ifsp.edu.br.Modelo;

import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Pessoas.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Doacao {
    private int id;
    private Usuario user;
    private List<Animal> animais =  new ArrayList<>();
    private Date data;

    public Doacao(int id, Usuario user, List<Animal> animais, Date data) {
        this.id = id;
        this.user = user;
        this.animais = animais;
        this.data = data;
    }

    public Doacao(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
