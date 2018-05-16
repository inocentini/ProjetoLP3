package ifsp.edu.br.Modelo;

import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Pessoas.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adocao {

    private int id;
    private List<Animal> animais = new ArrayList<>();
    private Usuario user;
    private Date data;

    public Adocao(int id, List<Animal> animais, Usuario user, Date data) {
        this.id = id;
        this.animais = animais;
        this.user = user;
        this.data = data;
    }

    public Adocao(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ID:" + getId() +
                "\nUsuário:" + getUser().getNome() +
                "\nData:" + getData();
    }
}
