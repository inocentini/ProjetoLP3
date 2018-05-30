package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.UsuarioDAO;
import ifsp.edu.br.Model.Pessoas.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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

public class UserViewController implements Initializable {

    @FXML
    private SplitPane spliUsers;

    @FXML
    private TableView<Usuario> tableUser;

    @FXML
    private TableColumn<Usuario, Integer> tableUserId;

    @FXML
    private TableColumn<Usuario, String> tableUserNome;

    @FXML
    private TableColumn<Usuario, String> tableUserCPF;

    @FXML
    private Label labUsers;

    @FXML
    private Label labDesc;

    @FXML
    private GridPane gridDesc;

    @FXML
    private Label labCPF;

    @FXML
    private Label labEndereco;

    @FXML
    private Label labEmail;

    @FXML
    private Label labId;

    @FXML
    private Label labNome;

    @FXML
    private Label labTelefone;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEndereço;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

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
    void handleBtnAlterar(ActionEvent event) throws IOException {
        Usuario user = new Usuario();
        user = tableUser.getSelectionModel().getSelectedItem();
        if(user != null){
            boolean btnAlterarClicked = showGerenciamentoUser(user);
            if(btnAlterarClicked){
                fillTableUsers();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selecionar Usuário");
            alert.setContentText("Por favor, selecione um usuário na tabela");
            alert.show();
        }
    }

    @FXML
    void handleBtnInserir(ActionEvent event) throws IOException {
        boolean btnAddClicked = showGerenciamentoUser();
        if(btnAddClicked){
            fillTableUsers();
        }
    }

    @FXML
    void handleBtnRemover(ActionEvent event) {
        Usuario user = new Usuario();
        user = tableUser.getSelectionModel().getSelectedItem();

        if(user != null ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removendo Usuário.");
            alert.setContentText("Você deseja remover este usuário?");
            alert.showAndWait();
            UsuarioDAO userDAO = new UsuarioDAO();
            userDAO.remove(user);
            fillTableUsers();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seleção de usuário");
            alert.setContentText("Por favor, selecione um usuário.");
            alert.show();
        }
    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {

    }

    @FXML
    void pesquisaUser(KeyEvent event) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableUsers();
        tableUser.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> selectItemTableViewUser(newValue));

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
            txtId.setDisable(true);
            txtNome.setText(user.getNome());
            txtNome.setEditable(false);
            txtCPF.setText(user.getCpf());
            txtCPF.setEditable(false);
            txtEndereço.setText(user.getEndereco());
            txtEndereço.setEditable(false);
            txtTelefone.setText(user.getTelefone());
            txtTelefone.setEditable(false);
            txtEmail.setText(user.getEmail());
            txtEmail.setEditable(false);
        }else{
            txtId.setText("");
            txtNome.setText("");
            txtCPF.setText("");
            txtEndereço.setText("");
            txtTelefone.setText("");
            txtEmail.setText("");
        }
    }

    public boolean showGerenciamentoUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDUser.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Usuários");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CRUDUserController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isBtnConfirmClicked();
    }

    public boolean showGerenciamentoUser(Usuario user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDUser.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alteração de Usuários");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CRUDUserController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUser(user);

        dialogStage.showAndWait();

        return controller.isBtnConfirmClicked();
    }
}
