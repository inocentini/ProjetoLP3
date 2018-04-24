package ifsp.edu.br.DAO;

import ifsp.edu.br.Modelo.Pessoas.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public static List<Usuario> usuarios = new ArrayList<>();

    public void add(Usuario u){
        usuarios.add(u);
    }

    public void update(Usuario u){
        for (Usuario user : usuarios) {
            if(user.getId() == u.getId()){
                user.setId(u.getId());
                user.setNome(u.getNome());
                user.setCpf(u.getCpf());
                user.setEndereco(u.getEndereco());
                user.setTelefone(u.getTelefone());
                user.setEmail(u.getEmail());
        }
    }
}

    public Usuario read(Usuario u){
        for(Usuario user : usuarios){
            if(user.getId() == u.getId()) {
                user = u;
                return user;
            }
        }
        return null;
    }

    public void list(){
        for(Usuario user : usuarios){
            System.out.println("\nID: "+user.getId()+
                    "\nNome: "+user.getNome()+
                    "\nCPF: "+user.getCpf()+
                    "\nEndereço: "+user.getEndereco()+
                    "\nTelefone: "+user.getTelefone()+
                    "\nEmail: "+user.getEmail());
        }
    }

    public void remove(Usuario u){
        usuarios.remove(u);
    }
}
