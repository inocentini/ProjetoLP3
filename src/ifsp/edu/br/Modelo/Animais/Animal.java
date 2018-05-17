package ifsp.edu.br.Modelo.Animais;


public abstract class Animal {
    private int id;
    private String apelido;
    private String raca;
    private int idade;
    private boolean sexo;
    private boolean vacinado;
    private  boolean castrado;

    public Animal(){}

    public Animal(int id,String apelido,String raca, int idade, boolean sexo, boolean vacinado, boolean castrado) {
        this.setId(id);
        this.setApelido(apelido);
        this.raca = raca;
        this.setIdade(idade);
        this.setSexo(sexo);
        this.setVacinado(vacinado);
        this.setCastrado(castrado);
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
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

    @Override
    public String toString(){
        String sex = isSexo() ? "Macho" : "Fêmea";
        String vacinado = isVacinado() ? "Sim" : "Não";
        String castrado = isVacinado() ? "Sim" : "Não";
        return ("\nID: " + getId()+
                "\nApelido: " + getApelido()+
                "\nRaça: " + getRaca()+
                "\nIdade: " + getIdade()+
                "\nSexo: " + sex+
                "\nVacinado? " + vacinado+
                "\nCastrado? " + castrado);
    }
}
