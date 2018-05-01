package ifsp.edu.br.Modelo.Pessoas;

import ifsp.edu.br.Modelo.Animais.Animal;

public class Usuario extends Pessoa {

    public Usuario(){

    }

    public Usuario(int id, String nome, String cpf, String endereco, String telefone, String email) {
        super(id, nome, cpf, endereco, telefone, email);
    }

    @Override
    public String toString() {
        return "\nID: "+getId()+
                "\nNome: "+getNome()+
                "\nCPF: "+getCpf()+
                "\nEndereço: "+getEndereco()+
                "\nTelefone: "+getTelefone()+
                "\nEmail: "+getEmail();
    }
}
