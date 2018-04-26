package ifsp.edu.br;

import ifsp.edu.br.DAO.*;
import ifsp.edu.br.Menu.*;
import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;
import ifsp.edu.br.Modelo.Conta;
import ifsp.edu.br.Modelo.Pessoas.Funcionario;
import ifsp.edu.br.Modelo.Pessoas.Usuario;
import jdk.nashorn.internal.parser.DateParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        MainMenu mainMenu = new MainMenu();
        int idDog = 0;
        int idCat = 0;
        int idUser = 0;
        int idFunc = 0;
        int idConta = 0;

        CachorroDAO dogDAO = new CachorroDAO() ;
        GatoDAO catDAO = new GatoDAO();
        UsuarioDAO userDAO = new UsuarioDAO();
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        ContaDAO contaDAO = new ContaDAO();

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
                            case 0: // Sair
                                break;
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
                                Cachorro dogUp = dogDAO.read(scn.nextInt());
                                System.out.println("Informe o novo Apelido do animal:");
                                dogUp.setApelido(scn.next());
                                System.out.println("Informe a nova idade:");
                                dogUp.setIdade(scn.nextInt());
                                System.out.println("O animal é vacinado? true/false");
                                dogUp.setVacinado(scn.nextBoolean());
                                System.out.println("O animal é castrado? true/false");
                                dogUp.setCastrado(scn.nextBoolean());
                                dogDAO.update(dogUp);
                                break;
                            case 5:
                                System.out.println("informe o id do cachorro que deseja remover:");
                                dogDAO.remove(dogDAO.read(scn.nextInt()));
                                break;
                            default:
                                System.out.println("Opção Inválida");
                                break;
                        }
                    }else if(opAnimal == 2){
                        GatoMenu gatoMenu = new GatoMenu();
                        gatoMenu.show();
                        int opCat = scn.nextInt();
                        switch (opCat){
                            case 0: // Sair
                                break;
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
                            case 2: // Listar todos os gatos
                                catDAO.list();
                                break;
                            case 3: // Pesquisar gato pelo id
                                System.out.println("Informe o id do gato:");
                                catDAO.read(scn.nextInt());
                                break;
                            case 4: //Atualiza o gato pelo id
                                System.out.println("Informe o id do gato que deseja atualizar:");
                                Gato catUp = catDAO.read(scn.nextInt());
                                System.out.println("Informe o novo Apelido do animal:");
                                catUp.setApelido(scn.next());
                                System.out.println("Informe a nova idade:");
                                catUp.setIdade(scn.nextInt());
                                System.out.println("O animal é vacinado? true/false");
                                catUp.setVacinado(scn.nextBoolean());
                                System.out.println("O animal é castrado? true/false");
                                catUp.setCastrado(scn.nextBoolean());
                                catDAO.update(catDAO.read(scn.nextInt()));
                                break;
                            case 5: // Remove um gato pelo id
                                System.out.println("Informe o id do gato que deseja remover:");
                                catDAO.remove(catDAO.read(scn.nextInt()));
                                break;
                            default:
                                System.out.println("Opção Inválida");
                                break;
                        }
                    }else{
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 2: // Gerenciamento de Usuários
                    UsuarioMenu usuarioMenu = new UsuarioMenu();
                    usuarioMenu.show();
                    int opcUser = scn.nextInt();
                    switch (opcUser){
                        case 0: // Sair
                            break;
                        case 1: // Adicionar usuário
                            Usuario user = new Usuario();
                            user.setId(idUser++);
                            System.out.println("Informe o nome do usuário:");
                            user.setNome(scn.next());
                            System.out.println("Informe o CPF:");
                            user.setCpf(scn.next());
                            System.out.println("Informe o endereço:");
                            user.setEndereco(scn.next());
                            System.out.println("Informe o telefone:");
                            user.setTelefone(scn.next());
                            System.out.println("Informe o email:");
                            user.setEmail(scn.next());
                            userDAO.add(user);
                            break;
                        case 2: // Listar todos os usuarios
                            userDAO.list();
                            break;
                        case 3: //Pesquisar usuario pelo id
                            System.out.println("Informe o id do usuario:");
                            userDAO.read(scn.nextInt());
                        case 4: // Atualizar usuario pelo id
                            System.out.println("Informe o id do usuario que deseja atualizar:");
                            Usuario userUP = userDAO.read(scn.nextInt());
                            System.out.println("Informe o novo nome do usuário:");
                            userUP.setNome(scn.next());
                            System.out.println("Informe o novo endereço:");
                            userUP.setEndereco(scn.next());
                            System.out.println("Informe o novo telefone:");
                            userUP.setTelefone(scn.next());
                            System.out.println("Informe o novo email:");
                            userUP.setEmail(scn.next());
                            userDAO.update(userUP);
                        case 5:
                            System.out.println("Informe o id do usuario que deseja remover:");
                            userDAO.remove(userDAO.read(scn.nextInt()));
                        default:
                            System.out.println("Opção Inválida");
                            break;
                    }
                    break;
                case 3: // Gerenciamento de funcionarios
                    FuncionarioMenu funcionarioMenu = new FuncionarioMenu();
                    funcionarioMenu.show();
                    int opcFunc = scn.nextInt();
                    switch (opcFunc){
                        case 0: // Sair
                            break;
                        case 1: // Adicionar funcionário
                            Funcionario func = new Funcionario();
                            func.setId(idFunc++);
                            System.out.println("Informe o nome do funcionário:");
                            func.setNome(scn.next());
                            System.out.println("Informe o CPF:");
                            func.setCpf(scn.next());
                            System.out.println("Informe o endereço:");
                            func.setEndereco(scn.next());
                            System.out.println("Informe o telefone:");
                            func.setTelefone(scn.next());
                            System.out.println("Informe o email:");
                            func.setEmail(scn.next());
                            System.out.println("Informe o salário:");
                            func.setSalario(scn.nextDouble());
                            funcDAO.add(func);
                            break;
                        case 2: // Listar todos os funcionários
                            funcDAO.list();
                            break;
                        case 3: // Pesquisar funcionário pelo id
                            System.out.println("Informe o id do funcionário");
                            funcDAO.read(scn.nextInt());
                        case 4: // Atualizar funcionário pelo id
                            System.out.println("Informe o id do funcionário que deseja atualizar:");
                            Funcionario funcUp = funcDAO.read(scn.nextInt());
                            System.out.println("Informe o novo nome do funcionário:");
                            funcUp.setNome(scn.next());
                            System.out.println("Informe o novo endereço:");
                            funcUp.setEndereco(scn.next());
                            System.out.println("Informe o novo telefone:");
                            funcUp.setTelefone(scn.next());
                            System.out.println("Informe o novo email:");
                            funcUp.setEmail(scn.next());
                            System.out.println("Informe o novo salário:");
                            funcUp.setSalario(scn.nextDouble());
                            funcDAO.update(funcUp);
                        case 5: // Remover funcionário pelo id
                            System.out.println("Informe o id do funcionário que deseja remover:");
                            funcDAO.remove(funcDAO.read(scn.nextInt()));
                    }
                    break;
                case 4: // Gerenciamento de Contas
                    ContaMenu contaMenu = new ContaMenu();
                    contaMenu.show();
                    int opConta = scn.nextInt();
                    switch (opConta){
                        case 0:// Sair
                            break;
                        case 1: // Adicionar conta
                            Conta conta = new Conta();
                            conta.setId(idConta++);
                            System.out.println("Informe nome e descrição da conta:");
                            conta.setDescricao(scn.next());
                            System.out.println("Informe o valor da conta:");
                            conta.setValor(scn.nextDouble());
                            System.out.println("Informe a data de vencimento:");
                            String date = scn.next();
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            Date dt = df.parse(date);
                            conta.setVencimento(dt);
                            contaDAO.add(conta);
                            break;
                        case 2: // Listar todas as contas
                            contaDAO.list();
                            break;
                        case 3: // Pesquisar conta pelo id
                            System.out.println("Informe o id da conta");
                            contaDAO.read(scn.nextInt());
                            break;
                        case 4: // Alterar conta pelo id
                            System.out.println("Informe o id da conta que deseja alterar:");
                            Conta contaUp = contaDAO.read(scn.nextInt());
                            System.out.println("Informe o novo nome e descrição da conta:");
                            contaUp.setDescricao(scn.next());
                            System.out.println("Informe o novo valor da conta:");
                            contaUp.setValor(scn.nextDouble());
                            System.out.println("Informe a nova data de vencimento:");
                            String dateUP = scn.next();
                            DateFormat dfUP = new SimpleDateFormat("dd/MM/yyyy");
                            Date dtUP = df.parse(dateUP);
                            contaUp.setVencimento(dtUP);
                            contaDAO.update(contaUp);
                            break;
                        case 5: // Remover conta pelo id
                            System.out.println("Informar o id da conta que deseja remover:");
                            contaDAO.remove(contaDAO.read(scn.nextInt()));
                            break;
                    }
        }

        }while(opcMain != 0);
    }
}
