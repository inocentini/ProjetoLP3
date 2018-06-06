package ifsp.edu.br.Control;

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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CRUDAdocaoController implements Initializable {


    @FXML
    private TableView<Cachorro> tableDog;

    @FXML
    private TableColumn<Cachorro, Integer> tableDogId;

    @FXML
    private TableColumn<Cachorro, String> tableDogApelido;

    @FXML
    private TableView<Gato> tableCat;

    @FXML
    private TableColumn<Gato, Integer> tableCatId;

    @FXML
    private TableColumn<Gato, String> tableCatApelido;

    @FXML
    private Button btnInserir;

    @FXML
    private TableView<Animal> tableAnimalOut;

    @FXML
    private TableColumn<Animal, Integer> tableAnimalOutId;

    @FXML
    private TableColumn<Animal, String> tableAnimalOutApelido;

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
    }

    @FXML
    void handleCadastrarAdocao(ActionEvent event) {

    }

    @FXML
    void handleInserirAnimal(ActionEvent event) {

    }

    @FXML
    void handleVoltar(ActionEvent event) {

    }

    @FXML
    void pesquisarAnimal(KeyEvent event) {
        if(rbtnCachorro.isSelected()) {
            ObservableList<Cachorro> observableList = tableDog.getItems();
            FilteredList<Cachorro> filteredList = new FilteredList<>(observableList, cachorro -> true);
            txtPesquisarPet.setOnKeyReleased(cachorro -> {
                txtPesquisarPet.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredList.setPredicate((Predicate<? super Cachorro>) dog -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (newValue == String.valueOf(dog.getId())) {
                            return true;
                        } else if (dog.getApelido().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Cachorro> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tableDog.comparatorProperty());
                tableDog.setItems(sortedList);
            });
        }else if(rbtnGato.isSelected()){
            ObservableList<Gato> observableListCat = tableCat.getItems();
            FilteredList<Gato> filteredListCat = new FilteredList<>(observableListCat, gato -> true);
            txtPesquisarPet.setOnKeyReleased(cachorro -> {
                txtPesquisarPet.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredListCat.setPredicate((Predicate<? super Gato>) cat -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (newValue == String.valueOf(cat.getId())) {
                            return true;
                        } else if (cat.getApelido().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Gato> sortedList = new SortedList<>(filteredListCat);
                sortedList.comparatorProperty().bind(tableCat.comparatorProperty());
                tableCat.setItems(sortedList);
            });
        }
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
                    if (newValue == String.valueOf(user.getId())) {
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
            txtUser.setText(user.getNome());
            txtUser.setEditable(false);
        }else{
            txtUser.setText("");
        }
    }

    public void fillTableCachorro(){
        CachorroDAO dogDAO = new CachorroDAO();
        List<Cachorro> cachorroList;
        ObservableList<Cachorro> cachorroObservableList;
        tableDogId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableDogApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));

        cachorroList = dogDAO.list();

        cachorroObservableList = FXCollections.observableArrayList(cachorroList);
        tableDog.setItems(cachorroObservableList);
    }

    public void fillTableGato(){
        GatoDAO catDAO = new GatoDAO();
        List<Gato> gatoList;
        ObservableList<Gato> gatoObservableList;
        tableCatId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCatApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));

        gatoList = catDAO.list();

        gatoObservableList = FXCollections.observableArrayList(gatoList);
        tableCat.setItems(gatoObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableCachorro();
        fillTableGato();
        fillTableUsers();
        tableUser.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> selectItemTableViewUser(newValue));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(System.currentTimeMillis());
        labDate.setText(String.valueOf(dateFormat.format(data)));
    }
}
