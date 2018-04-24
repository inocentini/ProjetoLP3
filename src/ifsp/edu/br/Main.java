package ifsp.edu.br;

import ifsp.edu.br.DAO.CachorroDAO;
import ifsp.edu.br.DAO.FuncionarioDAO;
import ifsp.edu.br.DAO.GatoDAO;
import ifsp.edu.br.DAO.UsuarioDAO;
import ifsp.edu.br.Menu.AnimalMenu;
import ifsp.edu.br.Menu.CachorroMenu;
import ifsp.edu.br.Menu.GatoMenu;
import ifsp.edu.br.Menu.MainMenu;
import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;
import ifsp.edu.br.Modelo.Pessoas.Funcionario;
import ifsp.edu.br.Modelo.Pessoas.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        int idDog = 0;
        int idCat = 0;
        int idUser = 0;
        int idFunc = 0;

        CachorroDAO dogDAO = new CachorroDAO() ;
        GatoDAO catDAO = new GatoDAO();
        UsuarioDAO userDAO = new UsuarioDAO();
        FuncionarioDAO funcDAO = new FuncionarioDAO();

        int opcMain = -1;
        Scanner scn = new Scanner(System.in);

        do{
            mainMenu.show();
            opcMain = scn.nextInt();

            switch (opcMain){
                case 1: // Gerenciamento de animais
                    AnimalMenu animalMenu = new AnimalMenu();
                    animalMenu.show();
                    int opAnimal = scn.nextInt();
                    if(opAnimal == 1){
                        CachorroMenu cachorroMenu = new CachorroMenu();
                        cachorroMenu.show();
                        int opdog = scn.nextInt();
                        switch (opdog){
                            case 1: // Cadastrar cachorro
                                Cachorro dog = new Cachorro();
                                dog.setId(idDog++);
                                System.out.println("Informe o Apelido do animal:");
                                dog.setApelido(scn.next());
                                System.out.println("Informe a idade:");
                                dog.setIdade(scn.nextInt());
                                System.out.println("Informe o sexo: True = Macho, false = femea");
                                dog.setSexo(scn.nextBoolean());
                                System.out.println("O animal é vacinado? true/false");
                                dog.setVacinado(scn.nextBoolean());
                                System.out.println("O animal é castrado? true/false");
                                dog.setCastrado(scn.nextBoolean());
                                dogDAO.add(dog);
                                break;
                            case 2: // Listar todos cachorros
                                dogDAO.list();
                                break;
                            case 3: // Pesquisar pelo id
                                System.out.println("Informe o id do cachorro:");
                                dogDAO.read(scn.nextInt());
                                break;
                            case 4: // Atualizar cachorro
                                System.out.println("Informe o id do cachorro que deseja atualizar:");
                                dogDAO.update(dogDAO.read(scn.nextInt()));
                                break;
                            case 5:
                                System.out.println("informe o id do cachorro que deseja remover:");
                                dogDAO.remove(dogDAO.read(scn.nextInt()));
                                break;
                        }
                    }else if(opAnimal == 2){
                        GatoMenu gatoMenu = new GatoMenu();
                        gatoMenu.show();
                        int opCat = scn.nextInt();
                        switch (opCat){
                            case 1: // Adicionar Gato
                                Gato cat = new Gato();
                                cat.setId(idCat++);
                                System.out.println("Informe o Apelido do animal:");
                                cat.setApelido(scn.next());
                                System.out.println("Informe a idade:");
                                cat.setIdade(scn.nextInt());
                                System.out.println("Informe o sexo: M= True, F=false");
                                cat.setSexo(scn.nextBoolean());
                                System.out.println("O animal é vacinado?");
                                cat.setVacinado(scn.nextBoolean());
                                System.out.println("O animal é castrado? true/false");
                                cat.setCastrado(scn.nextBoolean());
                                catDAO.add(cat);
                                break;
                            case 2:
                                catDAO.list();
                                break;
                            case 3:
                                System.out.println("Informe o id do gato:");
                                catDAO.read(scn.nextInt());
                                break;
                            case 4:
                                System.out.println("Informe o id do gato que deseja atualizar:");
                                catDAO.update(catDAO.read(scn.nextInt()));
                                break;
                            case 5:
                                System.out.println("Informe o id do gato que deseja remover:");
                                catDAO.remove(catDAO.read(scn.nextInt()));
                                break;
                        }
                    }

        }

        }while(opcMain != 0);
    }
}
