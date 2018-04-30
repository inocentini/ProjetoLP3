package ifsp.edu.br;

import ifsp.edu.br.DAO.*;
import ifsp.edu.br.Menu.*;
import ifsp.edu.br.Modelo.Adocao;
import ifsp.edu.br.Modelo.Animais.Animal;
import ifsp.edu.br.Modelo.Animais.Cachorro;
import ifsp.edu.br.Modelo.Animais.Gato;
import ifsp.edu.br.Modelo.Conta;
import ifsp.edu.br.Modelo.Doacao;
import ifsp.edu.br.Modelo.Pessoas.Funcionario;
import ifsp.edu.br.Modelo.Pessoas.Usuario;
import ifsp.edu.br.Modelo.Produto;
import jdk.nashorn.internal.parser.DateParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        Menu menu = new Menu();
        int idDog = 0;
        int idCat = 0;
        int idUser = 0;
        int idFunc = 0;
        int idConta = 0;
        int idAdocao = 0;
        int idDoacao = 0;
        int idProduto = 0;

        CachorroDAO dogDAO = new CachorroDAO() ;
        GatoDAO catDAO = new GatoDAO();
        UsuarioDAO userDAO = new UsuarioDAO();
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        ContaDAO contaDAO = new ContaDAO();
        AdocaoDAO adocaoDAO = new AdocaoDAO();
        DoacaoDAO doacaoDAO = new DoacaoDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();

        int opcMain = -1;
        Scanner scn = new Scanner(System.in);

        // Menu Inicial
        do{
            menu.mainMenu();
            opcMain = scn.nextInt();

            switch (opcMain){
                case 0: // Sair
                    opcMain = 0;
                    break;
                case 1: // Gerenciamento de animais
                    menu.animalMenu();
                    int opAnimal = scn.nextInt();
                    if(opAnimal == 1){
                        menu.cachorroMenu();
                        int opdog = scn.nextInt();
                        switch (opdog){
                            case 0: // Sair
                                break;
                            case 1: // Cadastrar cachorro
                                Animal dog = new Cachorro();
                                dog.setId(idDog++);
                                scn.nextLine();
                                System.out.println("Informe o Apelido do animal:");
                                dog.setApelido(scn.nextLine());
                                System.out.println("Informe a idade:");
                                dog.setIdade(scn.nextInt());
                                System.out.println("Informe o sexo: True = Macho, false = femea");
                                dog.setSexo(scn.nextBoolean());
                                System.out.println("O animal é vacinado? true/false");
                                dog.setVacinado(scn.nextBoolean());
                                System.out.println("O animal é castrado? true/false");
                                dog.setCastrado(scn.nextBoolean());
                                dogDAO.add((Cachorro) dog);
                                break;
                            case 2: // Listar todos cachorros
                                dogDAO.list();
                                break;
                            case 3: // Pesquisar pelo id
                                System.out.println("Informe o id do cachorro:");
                                Cachorro dogRead = dogDAO.read(scn.nextInt());
                                String sex = dogRead.isSexo() ? "macho" : "femea";
                                System.out.println("\nID:"+dogRead.getId()+
                                        "\nApelido:"+dogRead.getApelido()+
                                        "\nIdade:"+dogRead.getIdade()+
                                        "\nSexo:"+sex+
                                        "\nVacinado?"+dogRead.isVacinado()+
                                        "\nCastrado?"+dogRead.isCastrado());
                                break;
                            case 4: // Atualizar cachorro
                                System.out.println("Informe o id do cachorro que deseja atualizar:");
                                Cachorro dogUp = dogDAO.read(scn.nextInt());
                                scn.nextLine();
                                System.out.println("Informe o novo Apelido do animal:");
                                dogUp.setApelido(scn.nextLine());
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
                        menu.gatoMenu();
                        int opCat = scn.nextInt();
                        switch (opCat){
                            case 0: // Sair
                                break;
                            case 1: // Adicionar Gato
                                Animal cat = new Gato();
                                cat.setId(idCat++);
                                scn.nextLine();
                                System.out.println("Informe o Apelido do animal:");
                                cat.setApelido(scn.nextLine());
                                System.out.println("Informe a idade:");
                                cat.setIdade(scn.nextInt());
                                System.out.println("Informe o sexo: M= True, F=false");
                                cat.setSexo(scn.nextBoolean());
                                System.out.println("O animal é vacinado?");
                                cat.setVacinado(scn.nextBoolean());
                                System.out.println("O animal é castrado? true/false");
                                cat.setCastrado(scn.nextBoolean());
                                catDAO.add((Gato) cat);
                                break;
                            case 2: // Listar todos os gatos
                                catDAO.list();
                                break;
                            case 3: // Pesquisar gato pelo id
                                System.out.println("Informe o id do gato:");
                                Gato catRead = catDAO.read(scn.nextInt());
                                String sex = catRead.isSexo() ? "macho" : "femea";
                                System.out.println("\nID:"+catRead.getId()+
                                        "\nApelido:"+catRead.getApelido()+
                                        "\nIdade:"+catRead.getIdade()+
                                        "\nSexo:"+sex+
                                        "\nVacinado?"+catRead.isVacinado()+
                                        "\nCastrado?"+catRead.isCastrado());
                                break;
                            case 4: //Atualiza o gato pelo id
                                System.out.println("Informe o id do gato que deseja atualizar:");
                                Gato catUp = catDAO.read(scn.nextInt());
                                scn.nextLine();
                                System.out.println("Informe o novo Apelido do animal:");
                                catUp.setApelido(scn.nextLine());
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
                    menu.usuarioMenu();
                    int opcUser = scn.nextInt();
                    switch (opcUser){
                        case 0: // Sair
                            break;
                        case 1: // Adicionar usuário
                            Usuario user = new Usuario();
                            user.setId(idUser++);
                            scn.nextLine();
                            System.out.println("Informe o nome do usuário:");
                            user.setNome(scn.nextLine());
                            System.out.println("Informe o CPF:");
                            user.setCpf(scn.nextLine());
                            System.out.println("Informe o endereço:");
                            user.setEndereco(scn.nextLine());
                            System.out.println("Informe o telefone:");
                            user.setTelefone(scn.nextLine());
                            System.out.println("Informe o email:");
                            user.setEmail(scn.nextLine());
                            userDAO.add(user);
                            break;
                        case 2: // Listar todos os usuarios
                            userDAO.list();
                            break;
                        case 3: //Pesquisar usuario pelo id
                            System.out.println("Informe o id do usuario:");
                            Usuario userRead = userDAO.read(scn.nextInt());
                            System.out.println("ID:"+userRead.getId()+
                                    "\nNome:"+userRead.getNome()+
                                    "\nCPF:"+userRead.getCpf()+
                                    "\nEndereço:"+userRead.getEndereco()+
                                    "\nTelefone:"+userRead.getTelefone()+
                                    "\nEmail:"+userRead.getEmail());
                            break;
                        case 4: // Atualizar usuario pelo id
                            scn.nextLine();
                            System.out.println("Informe o id do usuario que deseja atualizar:");
                            Usuario userUP = userDAO.read(scn.nextInt());
                            System.out.println("Informe o novo nome do usuário:");
                            userUP.setNome(scn.nextLine());
                            System.out.println("Informe o novo endereço:");
                            userUP.setEndereco(scn.nextLine());
                            System.out.println("Informe o novo telefone:");
                            userUP.setTelefone(scn.nextLine());
                            System.out.println("Informe o novo email:");
                            userUP.setEmail(scn.nextLine());
                            userDAO.update(userUP);
                            break;
                        case 5:
                            System.out.println("Informe o id do usuario que deseja remover:");
                            userDAO.remove(userDAO.read(scn.nextInt()));
                            break;
                        default:
                            System.out.println("Opção Inválida");
                            break;
                    }
                    break;
                case 3: // Gerenciamento de funcionarios
                    menu.funcionarioMenu();
                    int opcFunc = scn.nextInt();
                    switch (opcFunc){
                        case 0: // Sair
                            break;
                        case 1: // Adicionar funcionário
                            Funcionario func = new Funcionario();
                            func.setId(idFunc++);
                            scn.nextLine();
                            System.out.println("Informe o nome do funcionário:");
                            func.setNome(scn.nextLine());
                            System.out.println("Informe o CPF:");
                            func.setCpf(scn.nextLine());
                            System.out.println("Informe o endereço:");
                            func.setEndereco(scn.nextLine());
                            System.out.println("Informe o telefone:");
                            func.setTelefone(scn.nextLine());
                            System.out.println("Informe o email:");
                            func.setEmail(scn.nextLine());
                            System.out.println("Informe o salário:");
                            func.setSalario(scn.nextDouble());
                            funcDAO.add(func);
                            break;
                        case 2: // Listar todos os funcionários
                            funcDAO.list();
                            break;
                        case 3: // Pesquisar funcionário pelo id
                            System.out.println("Informe o id do funcionário");
                            Funcionario funcRead = funcDAO.read(scn.nextInt());
                            System.out.println("ID:"+funcRead.getId()+
                                    "\nNome:"+funcRead.getNome()+
                                    "\nCPF:"+funcRead.getCpf()+
                                    "\nEndereço:"+funcRead.getEndereco()+
                                    "\nTelefone:"+funcRead.getTelefone()+
                                    "\nEmail:"+funcRead.getEmail()+
                                    "\nSalario:"+funcRead.getSalario());
                            break;
                        case 4: // Atualizar funcionário pelo id
                            System.out.println("Informe o id do funcionário que deseja atualizar:");
                            Funcionario funcUp = funcDAO.read(scn.nextInt());
                            scn.nextLine();
                            System.out.println("Informe o novo nome do funcionário:");
                            funcUp.setNome(scn.nextLine());
                            System.out.println("Informe o novo endereço:");
                            funcUp.setEndereco(scn.nextLine());
                            System.out.println("Informe o novo telefone:");
                            funcUp.setTelefone(scn.nextLine());
                            System.out.println("Informe o novo email:");
                            funcUp.setEmail(scn.nextLine());
                            System.out.println("Informe o novo salário:");
                            funcUp.setSalario(scn.nextDouble());
                            funcDAO.update(funcUp);
                            break;
                        case 5: // Remover funcionário pelo id
                            System.out.println("Informe o id do funcionário que deseja remover:");
                            funcDAO.remove(funcDAO.read(scn.nextInt()));
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;
                case 4: // Gerenciamento de Contas
                    menu.contaMenu();
                    int opConta = scn.nextInt();
                    switch (opConta){
                        case 0:// Sair
                            break;
                        case 1: // Adicionar conta
                            Conta conta = new Conta();
                            conta.setId(idConta++);
                            scn.nextLine();
                            System.out.println("Informe nome e descrição da conta:");
                            conta.setDescricao(scn.nextLine());
                            System.out.println("Informe o valor da conta:");
                            conta.setValor(scn.nextDouble());
                            scn.nextLine();
                            System.out.println("Informe a data de vencimento:");
                            String date = scn.nextLine();
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
                            Conta contaRead = contaDAO.read(scn.nextInt());
                            System.out.println("ID:"+contaRead.getId()+
                                    "\nDescrição:"+contaRead.getDescricao()+
                                    "\nPreço:"+contaRead.getValor()+
                                    "\nVencimento:"+contaRead.getVencimento());
                            break;
                        case 4: // Alterar conta pelo id
                            System.out.println("Informe o id da conta que deseja alterar:");
                            Conta contaUp = contaDAO.read(scn.nextInt());
                            scn.nextLine();
                            System.out.println("Informe o novo nome e descrição da conta:");
                            contaUp.setDescricao(scn.nextLine());
                            System.out.println("Informe o novo valor da conta:");
                            contaUp.setValor(scn.nextDouble());
                            System.out.println("Informe a nova data de vencimento:");
                            String dateUP = scn.nextLine();
                            DateFormat dfUP = new SimpleDateFormat("dd/MM/yyyy");
                            Date dtUP = dfUP.parse(dateUP);
                            contaUp.setVencimento(dtUP);
                            contaDAO.update(contaUp);
                            break;
                        case 5: // Remover conta pelo id
                            System.out.println("Informar o id da conta que deseja remover:");
                            contaDAO.remove(contaDAO.read(scn.nextInt()));
                            break;
                        default:
                            System.out.println("Opção Inválida");
                            break;
                    }
                    break;
                case 5: //Gerenciamento de Adoções
                    menu.adocaoMenu();
                    int opcAd = scn.nextInt();
                    switch (opcAd){
                        case 0: // Sair
                            break;
                        case 1: // Adicionar adoção
                            Adocao adocao = new Adocao();
                            adocao.setId(idAdocao++);
                            System.out.println("Informe o id do usuário que fará a adoção:");
                            userDAO.list();
                            adocao.setUser(userDAO.read(scn.nextInt()));
                            scn.nextLine();
                            System.out.println("Informe a data da adoção:");
                            String dateAd = scn.nextLine();
                            DateFormat dfAd = new SimpleDateFormat("dd/MM/yyyy");
                            Date dtAd = dfAd.parse(dateAd);
                            adocao.setData(dtAd);
                            boolean opAd = true;
                            List<Animal> animais = new ArrayList<>();
                            while(opAd){
                                System.out.println("Deseja adicionar:\n1 - Cachorro.\n2 - Gato.\n0 - Sair");
                                int opAdSw = scn.nextInt();
                                switch (opAdSw){
                                    case 0://Sair
                                        opAd = false;
                                        break;
                                    case 1: // Adicionar cachorro na lista
                                        System.out.println("Informe o id do cachorro que deseja adotar:");
                                        dogDAO.list();
                                        Cachorro dogAd = dogDAO.read(scn.nextInt());
                                        animais.add(dogDAO.read(dogAd));
                                        dogDAO.remove(dogDAO.read(dogAd));
                                        break;
                                    case 2: // Adicionar gato na lista
                                        System.out.println("Informe o id do gato que deseja adotar:");
                                        catDAO.list();
                                        Gato catAd = catDAO.read(scn.nextInt());
                                        animais.add(catDAO.read(catAd));
                                        catDAO.remove(catDAO.read(catAd));
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                            }
                            adocao.setAnimais(animais);
                            adocaoDAO.add(adocao);
                            break;
                        case 2: // Listar todas as adoções
                            adocaoDAO.list();
                            break;
                        case 3: //Pesquisar adoção pelo id.
                            System.out.println("Informe o id da adoção.");
                            Adocao adocaoRead = adocaoDAO.read(scn.nextInt());
                            System.out.println("ID:"+adocaoRead.getId()+
                                    "\nUsuário"+adocaoRead.getUser().getNome()+
                                    "\nData:"+adocaoRead.getData());
                            for(Animal a : adocaoRead.getAnimais()){
                                if(a.getClass() == Cachorro.class){
                                    System.out.println("Cachorro:");
                                    System.out.println("ID:"+a.getId()+
                                            "\nApelido: "+a.getApelido()+
                                            "\nIdade: "+a.getIdade()+
                                            "\nVacinado?"+a.isVacinado()+
                                            "\nCastrado?"+a.isCastrado());
                                }else if(a.getClass() == Gato.class){
                                    System.out.println("Gato:");
                                    System.out.println("ID:"+a.getId()+
                                            "\nApelido: "+a.getApelido()+
                                            "\nIdade: "+a.getIdade()+
                                            "\nVacinado?"+a.isVacinado()+
                                            "\nCastrado?"+a.isCastrado());
                                }
                            }

                            break;
                        case 4:// Alterar adoção pelo id
                            System.out.println("Informe o id da adoção que deseja alterar:");
                            Adocao adocaoUp = adocaoDAO.read(scn.nextInt());
                            scn.nextLine();
                            System.out.println("Informe o id do novo Usuário da adoção:");
                            userDAO.list();
                            adocaoUp.setUser(userDAO.read(scn.nextInt()));
                            System.out.println("Informe a nova data da adoção:");
                            String dateAdUp = scn.nextLine();
                            DateFormat dfAdUp = new SimpleDateFormat("dd/MM/yyyy");
                            Date dtAdUp = dfAdUp.parse(dateAdUp);
                            adocaoUp.setData(dtAdUp);
                            boolean opAdUp = true;
                            List<Animal> animaisUp = new ArrayList<>();
                            while(opAdUp){
                                System.out.println("Deseja adicionar:\n1 - Cachorro.\n2 - Gato.\n0 - Sair");
                                int opAdSwUp = scn.nextInt();
                                switch (opAdSwUp){
                                    case 0://Sair
                                        opAdUp = false;
                                        break;
                                    case 1: // Adicionar cachorro na lista
                                        System.out.println("Informe o id do novo cachorro que deseja adotar:");
                                        dogDAO.list();
                                        Cachorro dogAd = dogDAO.read(scn.nextInt());
                                        animaisUp.add(dogAd);
                                        dogDAO.remove(dogDAO.read(dogAd));
                                        break;
                                    case 2: // Adicionar gato na lista
                                        System.out.println("Informe o id do novo gato que deseja adotar:");
                                        catDAO.list();
                                        Gato catAd = catDAO.read(scn.nextInt());
                                        animaisUp.add(catDAO.read(catAd));
                                        catDAO.remove(catDAO.read(catAd));
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                            }
                            adocaoUp.setAnimais(animaisUp);
                            adocaoDAO.update(adocaoUp);
                            break;
                        case 5: // Remover Adoção
                            adocaoDAO.remove(adocaoDAO.read(scn.nextInt()));
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 6: // Gerenciamento de Doações
                    menu.doacaoMenu(); // Menu Doação
                    int opcDoa = scn.nextInt();
                    switch (opcDoa){
                        case 0: //Sair
                            break;
                        case 1: // Adicionar doação
                            Doacao doacao = new Doacao();
                            doacao.setId(idDoacao++);
                            System.out.println("Informe o id do usuário que fará a doação:");
                            userDAO.list();
                            doacao.setUser(userDAO.read(scn.nextInt()));
                            scn.nextLine();
                            System.out.println("Informe a data da doação:");
                            String dateDoa = scn.nextLine();
                            DateFormat dfDoa = new SimpleDateFormat("dd/MM/yyyy");
                            Date dtDoa = dfDoa.parse(dateDoa);
                            doacao.setData(dtDoa);
                            boolean opDoa = true;
                            List<Animal> animaisDoa = new ArrayList<>();
                            List<Produto> produtosDoa = new ArrayList<>();
                            while(opDoa){
                                System.out.println("Deseja adicionar:\n1 - Cachorro.\n2 - Gato.\n3 - Produto.\n0 - Sair");
                                int opDoaSw = scn.nextInt();
                                switch (opDoaSw){
                                    case 0://Sair
                                        opDoa = false;
                                        break;
                                    case 1: // Adicionar cachorro na lista
                                        System.out.println("Informe as informações do cachorro que irá doar:");
                                        Animal dogDoa = new Cachorro();
                                        dogDoa.setId(idDog++);
                                        scn.nextLine();
                                        System.out.println("Informe o Apelido do animal:");
                                        dogDoa.setApelido(scn.nextLine());
                                        System.out.println("Informe a idade:");
                                        dogDoa.setIdade(scn.nextInt());
                                        System.out.println("Informe o sexo: True = Macho, false = femea");
                                        dogDoa.setSexo(scn.nextBoolean());
                                        System.out.println("O animal é vacinado? true/false");
                                        dogDoa.setVacinado(scn.nextBoolean());
                                        System.out.println("O animal é castrado? true/false");
                                        dogDoa.setCastrado(scn.nextBoolean());
                                        dogDAO.add((Cachorro) dogDoa);
                                        animaisDoa.add(dogDoa);
                                        break;
                                    case 2: // Adicionar gato na lista
                                        System.out.println("Informe as informações do gato que deseja doar:");
                                        Animal catDoa = new Gato();
                                        catDoa.setId(idCat++);
                                        scn.nextLine();
                                        System.out.println("Informe o Apelido do animal:");
                                        catDoa.setApelido(scn.nextLine());
                                        System.out.println("Informe a idade:");
                                        catDoa.setIdade(scn.nextInt());
                                        System.out.println("Informe o sexo: M= True, F=false");
                                        catDoa.setSexo(scn.nextBoolean());
                                        System.out.println("O animal é vacinado?");
                                        catDoa.setVacinado(scn.nextBoolean());
                                        System.out.println("O animal é castrado? true/false");
                                        catDoa.setCastrado(scn.nextBoolean());
                                        catDAO.add((Gato) catDoa);
                                        animaisDoa.add(catDoa);
                                        break;
                                    case 3:// Adicionar produto
                                        Produto produtoDoa = new Produto();
                                        produtoDoa.setId(idProduto++);
                                        scn.nextLine();
                                        System.out.println("Informe o nome e descrição do produto:");
                                        produtoDoa.setDescricao(scn.nextLine());
                                        System.out.println("Informe o quanto custou a unidade do produto:");
                                        produtoDoa.setPreco(scn.nextDouble());
                                        System.out.println("Informe a quantidade comprada do produto:");
                                        produtoDoa.setQtd(scn.nextInt());
                                        produtoDAO.add(produtoDoa);
                                        produtosDoa.add(produtoDoa);
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                            }
                            doacao.setProdutos(produtosDoa);
                            doacao.setAnimais(animaisDoa);
                            doacaoDAO.add(doacao);
                            break;
                        case 2: // Listar todas as doações
                            doacaoDAO.list();
                            break;
                        case 3: //Pesquisar doação pelo id.
                            System.out.println("Informe o id da adoção.");
                            Doacao doacaoRead = doacaoDAO.read(scn.nextInt());
                            System.out.println("ID:"+doacaoRead.getId()+
                                    "\nUsuário:"+doacaoRead.getUser().getNome()+
                                    "\nData:"+doacaoRead.getData());
                            for(Animal ad : doacaoRead.getAnimais()){
                                if(ad.getClass() == Cachorro.class){
                                    System.out.println("Cachorro:");
                                    System.out.println("ID:"+ad.getId()+
                                            "\nApelido: "+ad.getApelido()+
                                            "\nIdade: "+ad.getIdade()+
                                            "\nVacinado?"+ad.isVacinado()+
                                            "\nCastrado?"+ad.isCastrado());
                                }else if(ad.getClass() == Gato.class){
                                    System.out.println("Gato:");
                                    System.out.println("ID:"+ad.getId()+
                                            "\nApelido: "+ad.getApelido()+
                                            "\nIdade: "+ad.getIdade()+
                                            "\nVacinado?"+ad.isVacinado()+
                                            "\nCastrado?"+ad.isCastrado());
                                }
                            }
                            for(Produto pd : doacaoRead.getProdutos()){
                                System.out.println("ID:"+pd.getId()+
                                        "\nDescrição:"+pd.getDescricao()+
                                        "\nPreço:"+pd.getPreco()+
                                        "\nQuantidade:"pd.getQtd());
                            }
                            break;
                        case 4:// Alterar doação pelo id
                            /*System.out.println("Informe o id da adoção que deseja alterar:");
                            Doacao doacaoUp = doacaoDAO.read(scn.nextInt());
                            scn.nextLine();
                            System.out.println("Informe o id do novo Usuário da adoção:");
                            userDAO.list();
                            doacaoUp.setUser(userDAO.read(scn.nextInt()));
                            System.out.println("Informe a nova data da adoção:");
                            String dateDoaUp = scn.nextLine();
                            DateFormat dfDoaUp = new SimpleDateFormat("dd/MM/yyyy");
                            Date dtDoaUp = dfDoaUp.parse(dateDoaUp);
                            doacaoUp.setData(dtDoaUp);
                            boolean opDoaUp = true;
                            List<Animal> animaisDoaUp = new ArrayList<>();
                            while(opDoaUp){
                                System.out.println("Deseja adicionar:\n1 - Cachorro.\n2 - Gato.\n0 - Sair");
                                int opDoaSwUp = scn.nextInt();
                                switch (opDoaSwUp){
                                    case 0://Sair
                                        opDoaUp = false;
                                        break;
                                    case 1: // Adicionar cachorro na lista
                                        System.out.println("Informe o id do novo cachorro que deseja adotar:");
                                        dogDAO.list();
                                        animaisDoaUp.add(dogDAO.read(scn.nextInt()));
                                        break;
                                    case 2: // Adicionar gato na lista
                                        System.out.println("Informe o id do novo gato que deseja adotar:");
                                        catDAO.list();
                                        animaisDoaUp.add(catDAO.read(scn.nextInt()));
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                }
                            }
                            doacaoUp.setAnimais(animaisDoaUp);
                            doacaoDAO.update(doacaoUp);
                            break;*/
                            System.out.println("Ainda a implementar");
                            break;
                        case 5: // Remover doação
                            doacaoDAO.remove(doacaoDAO.read(scn.nextInt()));
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;
                case 7: // Gerenciamento de Estoque Implementar
                    menu.estoqueMenu();
                    int opcEst = scn.nextInt();
                    switch (opcEst){
                        case 0: // Sair
                            break;
                        case 1: // Adicionar produto em estoque.
                            Produto produto = new Produto();
                            produto.setId(idProduto++);
                            scn.nextLine();
                            System.out.println("Informe o nome e descrição do produto:");
                            produto.setDescricao(scn.nextLine());
                            System.out.println("Informe o quanto custou a unidade do produto:");
                            produto.setPreco(scn.nextDouble());
                            System.out.println("Informe a quantidade comprada do produto:");
                            produto.setQtd(scn.nextInt());
                            produtoDAO.add(produto);
                            break;
                        case 2: // Listar Estoque
                            produtoDAO.list();
                            break;
                        case 3: //Pesquisar produto em estoque pelo id
                            produtoDAO.read(scn.nextInt());
                            break;
                        case 4: // Alterar produto pelo id
                            Produto produtoUp = produtoDAO.read(scn.nextInt());
                            System.out.println("Informe a nova descrição do produto:");
                            produtoUp.setDescricao(scn.nextLine());
                            System.out.println("Informe o novo valor do produto:");
                            produtoUp.setPreco(scn.nextDouble());
                            System.out.println("Informe a nova quantidade do produto:");
                            produtoUp.setQtd(scn.nextInt());
                            produtoDAO.update(produtoUp);
                            break;
                        case 5: // Remover produto pelo id
                            produtoDAO.remove(produtoDAO.read(scn.nextInt()));
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        }while(opcMain != 0);
    }
}
