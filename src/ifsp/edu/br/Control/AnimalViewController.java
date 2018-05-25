package ifsp.edu.br.Control;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AnimalViewController {

    @FXML
    private SplitPane spliAnimal;

    @FXML
    private TableView<?> tableDog;

    @FXML
    private TableColumn<?, ?> tableDogId;

    @FXML
    private TableColumn<?, ?> tableDogNick;

    @FXML
    private TableView<?> tableCat;

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
    private RadioButton rbtnNome;

    @FXML
    private ToggleGroup pesquisa;

    @FXML
    private RadioButton rbtnId;

    @FXML
    void insertAnimal(MouseEvent event) {

    }

    @FXML
    void removeAnimal(MouseEvent event) {

    }

    @FXML
    void updateAnimal(MouseEvent event) {

    }

    @FXML
    void voltarPrincipal(MouseEvent event) {

    }

}
