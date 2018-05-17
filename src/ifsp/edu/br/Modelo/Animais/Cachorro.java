package ifsp.edu.br.Modelo.Animais;

public class Cachorro extends Animal {

    public Cachorro(){
    }

    public Cachorro(int id, String apelido,String raca, int idade, boolean sexo, boolean vacinado, boolean castrado) {
        super(id, apelido,raca, idade, sexo, vacinado, castrado);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
