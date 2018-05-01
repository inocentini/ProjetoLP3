package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    public static List<Conta> contas = new ArrayList<>();

    public void add(Conta c){
        contas.add(c);
    }

    public void update(Conta c){
        for (Conta conta : contas) {
            if(conta.getId() == c.getId()){
                conta.setId(c.getId());
                conta.setDescricao(c.getDescricao());
                conta.setValor(c.getValor());
                conta.setVencimento(c.getVencimento());
            }
        }
    }

    public Conta read(int id){
        for(Conta conta : contas){
            if(conta.getId() == id) {
                return conta;
            }
        }
        return null;
    }

    public void list(){
        for(Conta conta : contas){
            System.out.println(conta.toString());
        }
    }

    public void remove(Conta c){
        contas.remove(c);
    }
}
