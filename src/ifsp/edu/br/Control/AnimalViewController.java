package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.CachorroDAO;
import ifsp.edu.br.DAO.GatoDAO;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AnimalViewController implements Initializable {

    @FXML
    private SplitPane spliAnimal;

    @FXML
    private TableView<Cachorro> tableDog;

    @FXML
    private TableColumn<?, ?> tableDogId;

    @FXML
    private TableColumn<?, ?> tableDogNick;

    @FXML
    private TableView<Gato> tableCat;

    @FXML
    private TableColumn<?, ?> tableCatId;

    @FXML
    private TableColumn<?, ?> tableCatNick;

    @FXML
    private Label labDog;

    @FXML
    private Label labCat;

    @FXML
    private Label labDesc;

    @FXML
    private GridPane gridDesc;

    @FXML
    private Label labRaca;

    @FXML
    private Label labIdade;

    @FXML
    private Label labVacinado;

    @FXML
    private Label labId;

    @FXML
    private Label labNick;

    @FXML
    private Label labSex;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNick;

    @FXML
    private TextField txtRaca;

    @FXML
    private TextField txtIdade;

    @FXML
    private Label labCastrado;

    @FXML
    private TextField txtVacinado;

    @FXML
    private TextField txtCastrado;

    @FXML
    private RadioButton rbtnSexF;

    @FXML
    private ToggleGroup sexo;

    @FXML
    private RadioButton rbtnSexM;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Label labPesquisar;

    @FXML
    private RadioButton rbtnDog;

    @FXML
    private ToggleGroup pesquisa;

    @FXML
    private RadioButton rbtnGato;

    @FXML
    public void handleBtnInserir() throws IOException {
        boolean btnAddClicked = showGerenciamentoAnimal();
        if(btnAddClicked) {
            fillTableGato();
            fillTableCachorro();
        }
    }

    @FXML
    public void hanldeBtnAlterar() throws IOException {
        Animal animal = null;
        if(tableDog.getSelectionModel().getSelectedItem() != null){
            animal = tableDog.getSelectionModel().getSelectedItem();
        }else if(tableCat.getSelectionModel().getSelectedItem() != null){
            animal = tableCat.getSelectionModel().getSelectedItem();
        }
        if(animal != null){
            boolean btnAlterarClicked = showGerenciamentoAnimal(animal);
            if(btnAlterarClicked && animal.getClass() == Cachorro.class){
//                CachorroDAO dogDao = new CachorroDAO();
//                dogDao.update((Cachorro) animal);
                fillTableCachorro();
            }else if(btnAlterarClicked && animal.getClass() == Gato.class){
//                GatoDAO catDAO = new GatoDAO();
//                catDAO.update((Gato) animal);
                fillTableGato();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um pet na tabela.");
            alert.show();
        }
    }

    @FXML
    public void handleBtnRemover(){
        Animal animal = null;
        if(tableDog.getSelectionModel().getSelectedItem() != null){
            animal = tableDog.getSelectionModel().getSelectedItem();
        }else if(tableCat.getSelectionModel().getSelectedItem() != null){
            animal = tableCat.getSelectionModel().getSelectedItem();
        }
        if(animal != null && animal.getClass() == Cachorro.class){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removendo Animal!");
            alert.setContentText("Você deseja remover este animal?");
            alert.showAndWait();
            CachorroDAO dogDAO = new CachorroDAO();
            dogDAO.remove((Cachorro) animal);
            fillTableCachorro();
        }else if(animal != null && animal.getClass() == Gato.class){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removendo Animal!");
            alert.setContentText("Você deseja remover este animal?");
            alert.showAndWait();
            GatoDAO catDAO = new GatoDAO();
            catDAO.remove((Gato) animal);
            fillTableGato();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seleção de animal");
            alert.setContentText("Por favor, selecione um animal.");
            alert.show();
        }
    }

    public boolean showGerenciamentoAnimal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDAnimal.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de PET");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CRUDAnimalController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isBtnconfirm();
    }

    public boolean showGerenciamentoAnimal(Animal animal) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDAnimal.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alteração de PET");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CRUDAnimalController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAnimal(animal);

        dialogStage.showAndWait();

        return controller.isBtnconfirm();
    }

    @FXML
    void pesquisaAnimal(KeyEvent event){
        if(rbtnDog.isSelected()) {
            ObservableList<Cachorro> observableList = tableDog.getItems();
            FilteredList<Cachorro> filteredList = new FilteredList<>(observableList, cachorro -> true);
            txtPesquisar.setOnKeyReleased(cachorro -> {
                txtPesquisar.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
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
            txtPesquisar.setOnKeyReleased(cachorro -> {
                txtPesquisar.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableCachorro();
        tableDog.getSelectionModel().selectedItemProperty().addListener((
                observable, oldValue, newValue) -> selectItemTableViewCachorro(newValue));

        fillTableGato();
        tableCat.getSelectionModel().selectedItemProperty().addListener((
                observable, oldValue, newValue) -> selectItemTableViewGato(newValue));
    }

    public void fillTableCachorro(){
        CachorroDAO dogDAO = new CachorroDAO();
        List<Cachorro> cachorroList;
        ObservableList<Cachorro> cachorroObservableList;
        tableDogId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableDogNick.setCellValueFactory(new PropertyValueFactory<>("apelido"));

        cachorroList = dogDAO.list();

        cachorroObservableList = FXCollections.observableArrayList(cachorroList);
        tableDog.setItems(cachorroObservableList);
    }

    public void fillTableGato(){
        GatoDAO catDAO = new GatoDAO();
        List<Gato> gatoList;
        ObservableList<Gato> gatoObservableList;
        tableCatId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCatNick.setCellValueFactory(new PropertyValueFactory<>("apelido"));

        gatoList = catDAO.list();

        gatoObservableList = FXCollections.observableArrayList(gatoList);
        tableCat.setItems(gatoObservableList);
    }

    public void selectItemTableViewCachorro(Cachorro dog){
        if(dog != null) {
            txtId.setDisable(true);
            txtId.setText(String.valueOf(dog.getId()));
            txtNick.setEditable(false);
            txtNick.setText(dog.getApelido());
            txtRaca.setEditable(false);
            txtRaca.setText(dog.getRaca());
            txtIdade.setEditable(false);
            txtIdade.setText(String.valueOf(dog.getIdade()));
            rbtnSexF.setDisable(true);
            rbtnSexM.setDisable(true);
            if (dog.isSexo())
                rbtnSexM.setSelected(true);
            else
                rbtnSexF.setSelected(true);

            txtVacinado.setEditable(false);
            if (dog.isVacinado())
                txtVacinado.setText("Sim");
            else
                txtVacinado.setText("Não");
            txtCastrado.setEditable(false);
            if (dog.isCastrado())
                txtCastrado.setText("Sim");
            else
                txtCastrado.setText("Não");
        }else{
            txtId.setText("");
            txtNick.setText("");
            txtRaca.setText("");
            txtIdade.setText("");
            rbtnSexM.setSelected(false);
            rbtnSexF.setSelected(false);
            txtVacinado.setText("");
            txtCastrado.setText("");
        }
    }

    public void selectItemTableViewGato(Gato cat){
        if(cat != null) {
            txtId.setEditable(false);
            txtId.setText(String.valueOf(cat.getId()));
            txtNick.setEditable(false);
            txtNick.setText(cat.getApelido());
            txtRaca.setEditable(false);
            txtRaca.setText(cat.getRaca());
            txtIdade.setEditable(false);
            txtIdade.setText(String.valueOf(cat.getIdade()));
            rbtnSexM.setDisable(true);
            rbtnSexF.setDisable(true);
            if (cat.isSexo())
                rbtnSexM.setSelected(true);
            else
                rbtnSexF.setSelected(true);
            txtVacinado.setEditable(false);
            if (cat.isVacinado())
                txtVacinado.setText("Sim");
            else
                txtVacinado.setText("Não");
            txtCastrado.setEditable(false);
            if (cat.isCastrado())
                txtCastrado.setText("Sim");
            else
                txtCastrado.setText("Não");
        }else{
            txtId.setText("");
            txtNick.setText("");
            txtRaca.setText("");
            txtIdade.setText("");
            rbtnSexM.setSelected(false);
            rbtnSexF.setSelected(false);
            txtVacinado.setText("");
            txtCastrado.setText("");
        }
    }

}
