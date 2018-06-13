package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.AdocaoDAO;
import ifsp.edu.br.DAO.CachorroDAO;
import ifsp.edu.br.DAO.GatoDAO;
import ifsp.edu.br.DAO.UsuarioDAO;
import ifsp.edu.br.Model.Adocao;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import ifsp.edu.br.Model.Pessoas.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

public class CRUDAdocaoController implements Initializable {


    @FXML
    private TableView<Animal> tableAnimais;

    @FXML
    private TableColumn<Animal, Integer> tableAnimaisId;

    @FXML
    private TableColumn<Animal, String> tableAnimaisApelido;

    @FXML
    private TableColumn<Animal, String> tableAnimaisRaca;

    @FXML
    private Button btnInserir;

    @FXML
    private TableView<Animal> tableAnimalAd;

    @FXML
    private TableColumn<Animal, Integer> tableAnimalIdAd;

    @FXML
    private TableColumn<Animal, String> tableAnimalApelidoAd;

    @FXML
    private TableColumn<Animal, String> tableAnimalRacaAd;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Usuario> tableUser;

    @FXML
    private TableColumn<Usuario, Integer> tableUserId;

    @FXML
    private TableColumn<Usuario, String> tableUserNome;

    @FXML
    private TableColumn<Usuario, String> tableUserCPF;

    @FXML
    private Label labPesquisar;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Label labUser;

    @FXML
    private Label labData;

    @FXML
    private Label labAnimais;

    @FXML
    private TextField txtUser;

    @FXML
    private Label labDate;

    @FXML
    private Label labPesquisarPet;

    @FXML
    private TextField txtPesquisarPet;

    @FXML
    private RadioButton rbtnCachorro;

    @FXML
    private RadioButton rbtnGato;

    @FXML
    private Label labId;

    @FXML
    private TextField txtId;

    @FXML
    private Label labAdocao;

    private Stage dialogStage;
    private boolean btnconfirm = false;
    private Adocao adocao;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnconfirm() {
        return btnconfirm;
    }

    public void setBtnconfirm(boolean btnconfirm) {
        this.btnconfirm = btnconfirm;
    }

    public Adocao getAdocao() {
        return adocao;
    }

    public void setAdocao(Adocao adocao) {
        this.adocao = adocao;
        this.txtId.setText(String.valueOf(adocao.getUser().getId()));
        this.txtUser.setText(adocao.getUser().getNome());
        this.labDate.setText(String.valueOf(adocao.getData()));
        List<Animal> animais = adocao.getAnimais();
        for(Animal a : animais){
            animalList.add(a);
        }
        fillTableAnimalAd(animalList);
        this.labAdocao.setText("Alteração de Adoção");
        this.btnCadastrar.setText("Alterar");
    }

    List<Animal> animalList = new ArrayList<>();

    @FXML
    void handleCadastrarAdocao(ActionEvent event) {
        AdocaoDAO adocaoDAO = new AdocaoDAO();
        UsuarioDAO userDAO = new UsuarioDAO();
        if(adocao == null && isComplete()){
            Usuario user = userDAO.read(Integer.parseInt(txtId.getText()));
            adocao = new Adocao();
            adocao.setUser(user);
            Date data = new Date(System.currentTimeMillis());
            adocao.setData(data);
            adocao.setAnimais(animalList);
            adocaoDAO.add(adocao);
            for(Animal a : animalList) {
                if (a.getClass() == Cachorro.class) {
                    CachorroDAO dogDAO = new CachorroDAO();
                    Cachorro dog = new Cachorro();
                    dog.setId(a.getId());
                    dog.setApelido(a.getApelido());
                    dog.setIdade(a.getIdade());
                    dog.setSexo(a.isSexo());
                    dog.setRaca(a.getRaca());
                    dog.setVacinado(a.isVacinado());
                    dog.setCastrado(a.isCastrado());
                    dog.setAdocao(adocaoDAO.nextSeqAdocao());
                    dogDAO.update(dog);
                } else if (a.getClass() == Gato.class) {
                    GatoDAO catDAO = new GatoDAO();
                    Gato cat = new Gato();
                    cat.setId(a.getId());
                    cat.setApelido(a.getApelido());
                    cat.setIdade(a.getIdade());
                    cat.setRaca(a.getRaca());
                    cat.setSexo(a.isSexo());
                    cat.setVacinado(a.isVacinado());
                    cat.setCastrado(a.isCastrado());
                    cat.setAdocao(adocaoDAO.nextSeqAdocao());
                    catDAO.update(cat);
                }
            }
        }else if (adocao != null && isComplete()){
            Usuario user = userDAO.read(Integer.parseInt(txtId.getText()));
            adocao.setUser(user);
            Date data = new Date(System.currentTimeMillis());
            adocao.setData(data);
            adocao.setAnimais(animalList);
            adocaoDAO.update(adocao);
            for(Animal a : animalList) {
                if (a.getClass() == Cachorro.class) {
                    CachorroDAO dogDAO = new CachorroDAO();
                    Cachorro dog = new Cachorro();
                    dog.setId(a.getId());
                    dog.setApelido(a.getApelido());
                    dog.setIdade(a.getIdade());
                    dog.setSexo(a.isSexo());
                    dog.setRaca(a.getRaca());
                    dog.setVacinado(a.isVacinado());
                    dog.setCastrado(a.isCastrado());
                    dog.setAdocao(adocao.getId());
                    dogDAO.update(dog);
                } else if (a.getClass() == Gato.class) {
                    GatoDAO catDAO = new GatoDAO();
                    Gato cat = new Gato();
                    cat.setId(a.getId());
                    cat.setApelido(a.getApelido());
                    cat.setIdade(a.getIdade());
                    cat.setRaca(a.getRaca());
                    cat.setSexo(a.isSexo());
                    cat.setVacinado(a.isVacinado());
                    cat.setCastrado(a.isCastrado());
                    cat.setAdocao(adocao.getId());
                    catDAO.update(cat);
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dados Incompletos");
            alert.setContentText("Por favor, preencher dados corretamente.");
            alert.showAndWait();
            return;
        }
        btnconfirm = true;

        dialogStage.close();

    }

    @FXML
    void handleInserirAnimal() {
        Animal animal = tableAnimais.getSelectionModel().getSelectedItem();
        if(!animalList.isEmpty()){
            for(Animal a : animalList){
                if(a.getId() == animal.getId()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Animal ja Inserido!");
                    alert.setContentText("Animal ja adicionado na lista!");
                    alert.showAndWait();
                    return;
                }
            }
            animalList.add(animal);
        }else
            animalList.add(animal);

        fillTableAnimalAd(animalList);
    }

    @FXML
    void handleVoltar(ActionEvent event) {
        this.dialogStage.close();
    }

    @FXML
    void pesquisarAnimal(KeyEvent event) {

        ObservableList<Animal> observableList = tableAnimais.getItems();
        FilteredList<Animal> filteredList = new FilteredList<>(observableList, animal -> true);
            txtPesquisarPet.setOnKeyReleased(animal -> {
                txtPesquisarPet.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredList.setPredicate((Predicate<? super Animal>) pet -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (newValue.equals(pet.getId())) {
                            return true;
                        } else if (pet.getApelido().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Animal> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tableAnimais.comparatorProperty());
                tableAnimais.setItems(sortedList);
            });

    }

    @FXML
    void pesquisarUser(KeyEvent event) {
        ObservableList<Usuario> observableList = tableUser.getItems();
        FilteredList<Usuario> filteredList = new FilteredList<>(observableList, usuario -> true);
        txtPesquisar.setOnKeyReleased(usario -> {
            txtPesquisar.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Usuario>) user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (newValue.equals(String.valueOf(user.getId()))) {
                        return true;
                    } else if (user.getNome().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Usuario> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableUser.comparatorProperty());
            tableUser.setItems(sortedList);
        });
    }

    public void fillTableUsers(){
        UsuarioDAO userDAO = new UsuarioDAO();
        List<Usuario> usuarioList;
        ObservableList<Usuario> usuarioObservableList;
        tableUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableUserNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableUserCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        usuarioList = userDAO.list();

        usuarioObservableList = FXCollections.observableArrayList(usuarioList);
        tableUser.setItems(usuarioObservableList);
    }

    public void selectItemTableViewUser(Usuario user){
        if(user != null){
            txtId.setText(String.valueOf(user.getId()));
            txtUser.setText(user.getNome());
            txtUser.setEditable(false);
        }else{
            txtUser.setText("");
        }
    }

    public void fillTableAnimalAd(List<Animal> animais){

        ObservableList<Animal> observableList = null;
        tableAnimalIdAd.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableAnimalApelidoAd.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        tableAnimalRacaAd.setCellValueFactory(new PropertyValueFactory<>("raca"));

        observableList = FXCollections.observableArrayList(animais);
        tableAnimalAd.setItems(observableList);
    }

    public void filltableAnimal(){
        CachorroDAO dogDAO = new CachorroDAO();
        GatoDAO catDAO = new GatoDAO();
        List<Gato> gatoList;
        List<Cachorro> dogList;
        List<Animal> animalList = new ArrayList<>();
        ObservableList<Animal> observableList = null;
        tableAnimaisId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableAnimaisApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        tableAnimaisRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));

        gatoList = catDAO.list();
        dogList = dogDAO.list();
        for (Animal a:gatoList) {
            animalList.add(a);
        }
        for(Animal a : dogList)
            animalList.add(a);


        observableList = FXCollections.observableArrayList(animalList);
        tableAnimais.setItems(observableList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labId.setVisible(false);
        txtId.setVisible(false);
        fillTableUsers();
        filltableAnimal();
        tableUser.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> selectItemTableViewUser(newValue));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(System.currentTimeMillis());
        labDate.setText(String.valueOf(dateFormat.format(data)));
    }

    public boolean isComplete(){
        if(txtUser.getText().isEmpty()
                || txtId.getText().isEmpty() ||
                labDate.getText().isEmpty() ||
                tableAnimalAd.getItems().isEmpty()){
            return false;
        }
        return true;
    }
}
