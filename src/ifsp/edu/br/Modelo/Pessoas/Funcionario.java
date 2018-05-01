package ifsp.edu.br.Modelo.Pessoas;

import ifsp.edu.br.Modelo.Animais.Animal;

public class Funcionario extends Pessoa {

    private double salario;

    public Funcionario(){

    }

    public Funcionario(int id, String nome, String cpf, String endereco, String telefone, String email, double salario) {
        super(id, nome, cpf, endereco, telefone, email);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "ID:"+getId()+
                "\nNome:"+getNome()+
                "\nCPF:"+getCpf()+
                "\nEndereço:"+getEndereco()+
                "\nTelefone:"+getTelefone()+
                "\nEmail:"+getEmail()+
                "\nSalario:"+getSalario();
    }
}
