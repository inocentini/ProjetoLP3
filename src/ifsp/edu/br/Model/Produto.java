package ifsp.edu.br.Model;

public class Produto {
    private int id;
    private String descricao;
    private double preco;
    private int qtd;

    public Produto(int id, String descricao, double preco, int qtd) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.qtd = qtd;
    }

    public Produto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "ID:" + getId() +
                "\nDescrição:" + getDescricao() +
                "\nPreço:" + getPreco() +
                "\nQuantidade:" + getQtd();
    }
}
