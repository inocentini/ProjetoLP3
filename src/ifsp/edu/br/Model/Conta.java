package ifsp.edu.br.Model;

import java.util.Date;

public class Conta {
    private int id;
    private String descricao;
    private double valor;
    private Date vencimento;

    public Conta(){

    }

    public Conta(int id, String descricao, double valor, Date vencimento) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.vencimento = vencimento;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    @Override
    public String toString() {
        return "ID:"+getId()+
                "\nDescrição:"+getDescricao()+
                "\nPreço:"+getValor()+
                "\nVencimento:"+getVencimento();
    }
}
