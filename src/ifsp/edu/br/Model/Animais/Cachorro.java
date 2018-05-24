package ifsp.edu.br.Model.Animais;

public class Cachorro extends Animal {

    public Cachorro(){
    }

    public Cachorro(int id, String apelido, String raca, int idade, boolean sexo, boolean vacinado, boolean castrado, int adocao) {
        super(id, apelido, raca, idade, sexo, vacinado, castrado, adocao);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
