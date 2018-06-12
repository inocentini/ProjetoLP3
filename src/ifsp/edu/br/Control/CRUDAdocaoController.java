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

    static List<Animal> animalList = new ArrayList<>();

    @FXML
    void handleCadastrarAdocao(ActionEvent event) {

    }

    @FXML
    void handleInserirAnimal() {
        Animal animal = (Animal) tableAnimais.getSelectionModel().getSelectedItem();
        List<Animal> addPet = animalList;
        if(!animalList.isEmpty()){
            for(Iterator<Animal> iterator = animalList.iterator() ; iterator.hasNext();){
                Animal a = null;
                a = iterator.next();
                if(a.getId() == animal.getId()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Animal ja Inserido!");
                    alert.setContentText("Animal ja adicionado na lista!");
                    alert.showAndWait();
                    return;
                }else{
                    animalList.add(animal);
                }
            }
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
                        int id = Integer.parseInt(newValue);
                        if (id == pet.getId()) {
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
        fillTableUsers();
        filltableAnimal();
        tableUser.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> selectItemTableViewUser(newValue));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(System.currentTimeMillis());
        labDate.setText(String.valueOf(dateFormat.format(data)));
    }
}
