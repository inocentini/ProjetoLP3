package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Pessoas.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public static List<Funcionario> funcionarios = new ArrayList<>();

    public void add(Funcionario f){
        funcionarios.add(f);
    }

    public void update(Funcionario f){
        for (Funcionario func : funcionarios) {
            if(func.getId() == f.getId()){
                func.setId(f.getId());
                func.setNome(f.getNome());
                func.setCpf(f.getCpf());
                func.setEndereco(f.getEndereco());
                func.setTelefone(f.getTelefone());
                func.setEmail(f.getEmail());
            }
        }
    }

    public Funcionario read(Funcionario f){
        for(Funcionario func : funcionarios){
            if(func.getId() == f.getId()) {
                func = f;
                return func;
            }
        }
        return null;
    }

    public void list(){
        for(Funcionario func : funcionarios){
            System.out.println("\nID: "+func.getId()+
                    "\nNome: "+func.getNome()+
                    "\nCPF: "+func.getCpf()+
                    "\nEndereço: "+func.getEndereco()+
                    "\nTelefone: "+func.getTelefone()+
                    "\nEmail: "+func.getEmail());
        }
    }

    public void remove(Funcionario f){
        funcionarios.remove(f);
    }
}
