package ifsp.edu.br.Modelo.Animais;


public abstract class Animal {
    private int id;
    private String apelido;
    private int idade;
    private boolean sexo;
    private boolean vacinado;
    private  boolean castrado;

    public Animal(){

    }

    public Animal(int id,String apelido, int idade, boolean sexo, boolean vacinado, boolean castrado) {
        this.id = id;
        this.apelido = apelido;
        this.idade = idade;
        this.sexo = sexo;
        this.vacinado = vacinado;
        this.castrado = castrado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isVacinado() {
        return vacinado;
    }

    public void setVacinado(boolean vacinado) {
        this.vacinado = vacinado;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }
}
