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

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static Scanner scn = new Scanner(System.in);


    //Propriedades ID estático
    private static int idDog = 0;
    private static int idCat = 0;
    private static int idUser = 0;
    private static int idFunc = 0;
    private static int idConta = 0;
    private static int idAdocao = 0;
    private static int idDoacao = 0;
    private static int idProduto = 0;

    //DAO estáticos
    private static CachorroDAO dogDAO = new CachorroDAO() ;
    private static GatoDAO catDAO = new GatoDAO();
    private static UsuarioDAO userDAO = new UsuarioDAO();
    private static FuncionarioDAO funcDAO = new FuncionarioDAO();
    private static ContaDAO contaDAO = new ContaDAO();
//    private static AdocaoDAO adocaoDAO = new AdocaoDAO();
    private static DoacaoDAO doacaoDAO = new DoacaoDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) throws ParseException {
        Menu menu = new Menu();


        int opcMain;

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
                                List<Cachorro> cachorros = dogDAO.list();
                                for(Cachorro c : cachorros){
                                    System.out.println(c.toString());
                                }
                                break;
                            case 3: // Pesquisar pelo id
                                System.out.println("Informe o id do cachorro:");
                                Cachorro dogFound = dogDAO.read(scn.nextInt());
                                if (dogFound != null)
                                    System.out.println(dogFound.toString());
                                else
                                    System.out.println("Cachorro não encontrado.");
                                break;
                            case 4: // Atualizar cachorro
                                System.out.println("Informe o id do cachorro que deseja atualizar:");
                                Cachorro dogUp = dogDAO.read(scn.nextInt());
                                if (dogUp != null) {
                                    scn.nextLine();
                                    System.out.println("Informe o novo Apelido do animal:");
                                    dogUp.setApelido(scn.nextLine());
                                    System.out.println("Informe a nova idade:");
                                    dogUp.setIdade(scn.nextInt());
                                    System.out.println("O animal é vacinado? true/false");
                                    dogUp.setVacinado(scn.nextBoolean());
                                    System.out.println("O animal é castrado? true/false");
                                    dogUp.setCastrado(scn.nextBoolean());
                                } else
                                    System.out.println("Cachorro não encontrado.");
                                break;
                            case 5:
                                System.out.println("Informe o id do cachorro que deseja remover:");
                                Cachorro dogRemove = dogDAO.read(scn.nextInt());
                                if(dogRemove != null)
                                    dogDAO.remove(dogRemove);
                                else
                                    System.out.println("Cachorro não encontrado!");
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
                                List<Gato> gatos = catDAO.list();
                                for(Gato g : gatos){
                                    System.out.println(g.toString());
                                }
                                break;
                            case 3: // Pesquisar gato pelo id
                                System.out.println("Informe o id do gato:");
                                Gato catRead = catDAO.read(scn.nextInt());
                                if (catRead != null)
                                    System.out.println(catRead.toString());
                                else
                                    System.out.println("Gato não encontrado!");
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
                                Gato gatoRemove = catDAO.read(scn.nextInt());
                                if(gatoRemove != null)
                                    catDAO.remove(gatoRemove);
                                else
                                    System.out.println("Gato não encontrado!");
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
                            List<Usuario> users = userDAO.list();
                            for(Usuario u : users){
                                System.out.println(u.toString());
                            }
                            break;
                        case 3: //Pesquisar usuario pelo id
                            System.out.println("Informe o id do usuario:");
                            Usuario userRead = userDAO.read(scn.nextInt());
                            if (userRead != null)
                                System.out.println(userRead.toString());
                            else
                                System.out.println("Usuário não encontrado!");
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
                            List<Funcionario> funcionarios = funcDAO.list();
                            for(Funcionario f : funcionarios)
                                System.out.println(f.toString());
                            break;
                        case 3: // Pesquisar funcionário pelo id
                            System.out.println("Informe o id do funcionário");
                            Funcionario funcRead = funcDAO.read(scn.nextInt());
                            if (funcRead != null)
                                System.out.println(funcRead.toString());
                            else
                                System.out.println("Funcionário não encontrado!");
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
                            if (contaRead != null)
                                System.out.println(contaRead.toString());
                            else
                                System.out.println("Conta não encontrada!");
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
                /*case 5: //Gerenciamento de Adoções
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
                                        if (dogAd != null) {
                                            animais.add(dogAd);
                                            dogDAO.remove(dogAd);
                                        } else
                                            System.out.println("Cachorro não encontrado!");
                                        break;
                                    case 2: // Adicionar gato na lista
                                        System.out.println("Informe o id do gato que deseja adotar:");
                                        catDAO.list();
                                        Gato catAd = catDAO.read(scn.nextInt());
                                        if (catAd != null) {
                                            animais.add(catAd);
                                            catDAO.remove(catAd);
                                        } else
                                            System.out.println("Gato não encontrado!");
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
                            if (adocaoRead != null) {
                                System.out.println(adocaoDAO.toString());
                                for (Animal a : adocaoRead.getAnimais()) {
                                    if (a.getClass() == Cachorro.class)
                                        System.out.println("Cachorro:");
                                    else if (a.getClass() == Gato.class)
                                        System.out.println("Gato:");

                                    System.out.println(a.toString());
                                }
                            } else
                                System.out.println("Adoção não encontrada!");

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
                                        if (dogAd != null) {
                                            animaisUp.add(dogAd);
                                            dogDAO.remove(dogAd);
                                        } else
                                            System.out.println("Cachorro não encontrado!");
                                        break;
                                    case 2: // Adicionar gato na lista
                                        System.out.println("Informe o id do novo gato que deseja adotar:");
                                        catDAO.list();
                                        Gato catAd = catDAO.read(scn.nextInt());
                                        if (catAd != null) {
                                            animaisUp.add(catAd);
                                            catDAO.remove(catAd);
                                        } else
                                            System.out.println("Gato não encontrado!");
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
                    break;*/
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
                                        produtoDoa.setPreco(0.00);
                                        System.out.println("Informe a quantidade doada do produto:");
                                        produtoDoa.setQtd(scn.nextInt());
                                        produtoDAO.add(produtoDoa);
                                        produtosDoa.add(produtoDoa);
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
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
                            if (doacaoRead != null) {
                                System.out.println(doacaoRead.toString());
                                for (Animal ad : doacaoRead.getAnimais()) {
                                    if (ad.getClass() == Cachorro.class)
                                        System.out.println("Cachorro:");
                                    else if (ad.getClass() == Gato.class)
                                        System.out.println("Gato:");

                                    System.out.println(ad.toString());
                                }
                                for (Produto pd : doacaoRead.getProdutos()) {
                                    System.out.println(pd.toString());
                                }
                            } else
                                System.out.println("Não encontrado!");
                            break;
                        case 4:// Alterar doação pelo id
                            System.out.println("Informe o id da doação que deseja alterar");
                            Doacao doacaoUp = doacaoDAO.read(scn.nextInt());
                            System.out.println("Informe o id do novo usuário da doação:");
                            userDAO.list();
                            doacaoUp.setUser(userDAO.read(scn.nextInt()));
                            scn.nextLine();
                            System.out.println("Informe a data da doação:");
                            String dateDoaUp = scn.nextLine();
                            DateFormat dfDoaUp = new SimpleDateFormat("dd/MM/yyyy");
                            Date dtDoaUp = dfDoaUp.parse(dateDoaUp);
                            doacaoUp.setData(dtDoaUp);
                            opDoa = true;
                            List<Animal> animaisDoaUp = doacaoUp.getAnimais();
                            List<Produto> produtosDoaUp = doacaoUp.getProdutos();
                            while(opDoa){
                                System.out.println("Deseja adicionar:" +
                                        "\n1 - Cachorro." +
                                        "\n2 - Gato." +
                                        "\n3 - Produto." +
                                        "\n4 - Remover Cachorro." +
                                        "\n5 - Remover Gato." +
                                        "\n6 - Remover Produto." +
                                        "\n0 - Sair");
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
                                        animaisDoaUp.add(dogDoa);
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
                                        animaisDoaUp.add(catDoa);
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
                                        produtosDoaUp.add(produtoDoa);
                                        break;
                                    case 4: //Remover cachorro;
                                        System.out.println("Informe o id do cachorro que deseja remover da lista:");
                                        for(Animal a : animaisDoaUp) {
                                            if (a.getClass() == Cachorro.class) {
                                                System.out.println("Cachorro:");
                                                System.out.println("ID:" + a.getId() + "\nApelido:" + a.getApelido());
                                            }
                                        }
                                        animaisDoaUp.remove(dogDAO.read(scn.nextInt()));
                                        break;
                                    case 5: // Remover gato
                                        System.out.println("Informe o id do gato que deseja remover da lista:");
                                        for(Animal a : animaisDoaUp){
                                            if(a.getClass() == Gato.class){
                                                System.out.println("Gato:");
                                                System.out.println("ID:" + a.getId() + "\nApelido:" + a.getApelido());
                                            }
                                        }
                                        animaisDoaUp.remove(catDAO.read(scn.nextInt()));
                                        break;
                                    case 6: // Remover Produto
                                        System.out.println("Informe o id do produto que deseja remover da lista:");
                                        for(Produto p : produtosDoaUp){
                                            System.out.println("ID:"+p.getId()+"\nDescrição:"+p.getDescricao());
                                        }
                                        produtosDoaUp.remove(produtoDAO.read(scn.nextInt()));
                                        break;
                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }
                            }
                            doacaoUp.setProdutos(produtosDoaUp);
                            doacaoUp.setAnimais(animaisDoaUp);
                            doacaoDAO.add(doacaoUp);
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
                            Produto produtoRead = produtoDAO.read(scn.nextInt());
                            if (produtoRead != null)
                                System.out.println(produtoRead.toString());
                            else
                                System.out.println("Produto não encontrado!");
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
