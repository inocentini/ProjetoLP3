package ifsp.edu.br.Modelo.Animais;

public class Gato extends Animal {

    public Gato() {}

    public Gato(int id, String apelido,String raca, int idade, boolean sexo, boolean vacinado, boolean castrado) {
        super(id, apelido,raca, idade, sexo, vacinado, castrado);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
