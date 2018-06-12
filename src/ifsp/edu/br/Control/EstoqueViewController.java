package ifsp.edu.br.Control;

import ifsp.edu.br.Model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class EstoqueViewController {

    @FXML
    private SplitPane spliUsers;

    @FXML
    private TableView<Produto> tableProd;

    @FXML
    private TableColumn<Produto, Integer> tableProdId;

    @FXML
    private TableColumn<Produto, String> tableProdDesc;

    @FXML
    private TableColumn<Produto, Integer> tableProdQnt;

    @FXML
    private TableColumn<Produto, Double> tableProdPreco;

    @FXML
    private Label labProdutos;

    @FXML
    private Label labDesc;

    @FXML
    private GridPane gridDesc;

    @FXML
    private Label labPreco;

    @FXML
    private Label labVencimento;

    @FXML
    private Label labId;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtQnt;

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
    void handleBtnAlterar(ActionEvent event) {

    }

    @FXML
    void handleBtnInserir(ActionEvent event) {

    }

    @FXML
    void handleBtnRemover(ActionEvent event) {

    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {

    }

    @FXML
    void pesquisaFunc(KeyEvent event) {

    }

}
