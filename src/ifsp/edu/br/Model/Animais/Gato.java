package ifsp.edu.br.Model.Animais;

public class Gato extends Animal {

    public Gato() {}

    public Gato(int id, String apelido, String raca, int idade, boolean sexo, boolean vacinado, boolean castrado, int adocao) {
        super(id, apelido, raca, idade, sexo, vacinado, castrado, adocao);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
