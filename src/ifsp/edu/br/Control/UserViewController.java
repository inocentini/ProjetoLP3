package ifsp.edu.br.Control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class UserViewController {

    @FXML
    private SplitPane spliUsers;

    @FXML
    private TableView<?> tableUsers;

    @FXML
    private TableColumn<?, ?> tableUsersId;

    @FXML
    private TableColumn<?, ?> tableDogNome;

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
    private RadioButton rbtnNome;

    @FXML
    private RadioButton rbtnId;

    @FXML
    void inserirUser(MouseEvent event) {

    }

    @FXML
    void removeUser(MouseEvent event) {

    }

    @FXML
    void updateUser(MouseEvent event) {

    }

    @FXML
    void voltarPrincipal(MouseEvent event) {

    }

}
