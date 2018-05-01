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

    public Funcionario read(int id){
        for(Funcionario func : funcionarios){
            if(func.getId() == id) {
                return func;
            }
        }
        return null;
    }

    public void list(){
        for(Funcionario func : funcionarios){
            System.out.println(func.toString());
        }
    }

    public void remove(Funcionario f){
        funcionarios.remove(f);
    }
}
