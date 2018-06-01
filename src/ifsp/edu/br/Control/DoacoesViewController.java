package ifsp.edu.br.Control;

import ifsp.edu.br.Model.Adocao;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import ifsp.edu.br.Model.Doacao;
import ifsp.edu.br.Model.Pessoas.Usuario;
import ifsp.edu.br.Model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.Date;

public class DoacoesViewController {

    @FXML
    private Tab tabAdo;

    @FXML
    private SplitPane spliUsers;

    @FXML
    private TableView<Adocao> tableAdocao;

    @FXML
    private TableColumn<Adocao, Integer> tableAdocaoId;

    @FXML
    private TableColumn<Adocao, String> tableAdocaoUser; /* Obs: Usuário?*/

    @FXML
    private TableColumn<Adocao, Date> tableAdocaoDate;

    @FXML
    private Label labAdocao;

    @FXML
    private Label labDesc;

    @FXML
    private GridPane gridDesc;

    @FXML
    private Label labData;

    @FXML
    private Label labId;

    @FXML
    private Label labUser;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtUser;

    @FXML
    private DatePicker dtAdo;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Cachorro> tableDog;

    @FXML
    private TableColumn<Cachorro, String> tableDogColun;

    @FXML
    private TableView<Gato> tableCat;

    @FXML
    private TableColumn<Gato, String> tableCatColun;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Label labPesquisar;

    @FXML
    private SplitPane spliUsers1;

    @FXML
    private TableView<Doacao> tableDoacao;

    @FXML
    private TableColumn<Doacao, Integer> tableDoacaoId;

    @FXML
    private TableColumn<Doacao, String> tableDoacaoUser; //Usuário?

    @FXML
    private TableColumn<Doacao, Date> tableDoacaoDate;

    @FXML
    private Label labDoacao;

    @FXML
    private Label labDesc1;

    @FXML
    private GridPane gridDesc1;

    @FXML
    private Label labDataDoa;

    @FXML
    private Label labIdDoa;

    @FXML
    private Label labUserDoa;

    @FXML
    private TextField txtIdDoa;

    @FXML
    private TextField txtUserDoa;

    @FXML
    private DatePicker dtDoa;

    @FXML
    private Button btnInserirDoa;

    @FXML
    private Button btnAlterarDoa;

    @FXML
    private Button btnDeletarDoa;

    @FXML
    private Button btnVoltarDoa;

    @FXML
    private TableView<Animal> tableAnimais;

    @FXML
    private TableColumn<Animal, String> tableAnimaisColun;

    @FXML
    private TableView<Produto> tableProdutos;

    @FXML
    private TableColumn<Produto, String> tableProdutoColun;

    @FXML
    private TextField txtPesquisarDoa;

    @FXML
    private Label labPesquisarDoa;

    @FXML
    void handleBtnAlterar(ActionEvent event) {

    }

    @FXML
    void handleBtnAlterarDoa(ActionEvent event) {

    }

    @FXML
    void handleBtnInserir(ActionEvent event) {

    }

    @FXML
    void handleBtnInserirDoa(ActionEvent event) {

    }

    @FXML
    void handleBtnRemover(ActionEvent event) {

    }

    @FXML
    void handleBtnRemoverDoa(ActionEvent event) {

    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {

    }

    @FXML
    void handleBtnVoltarDoa(ActionEvent event) {

    }

    @FXML
    void pesquisaFunc(KeyEvent event) {

    }

}
