package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.ProdutoDAO;
import ifsp.edu.br.Model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class EstoqueViewController implements Initializable {

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
    void handleBtnInserir(ActionEvent event) throws IOException {
        boolean btnAddClicked = showGerenciamentoEstoque();
        if(btnAddClicked){

        }

    }

    @FXML
    void handleBtnRemover(ActionEvent event) {

    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {

    }

    @FXML
    void pesquisaFunc(KeyEvent event) {
        ObservableList<Produto> observableList = tableProd.getItems();
        FilteredList<Produto> filteredList = new FilteredList<>(observableList, produto -> true);
        txtPesquisar.setOnKeyReleased(produto -> {
            txtPesquisar.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Produto>) prod -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(prod.getId()).contains(newValue)) {
                        return true;
                    } else if (prod.getDescricao().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produto> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableProd.comparatorProperty());
            tableProd.setItems(sortedList);
        });
    }

    public void selectItemTableViewProd(Produto produto){
        if(produto != null){
            txtId.setText(String.valueOf(produto.getId()));
            txtId.setDisable(true);
            txtDesc.setText(produto.getDescricao());
            txtDesc.setEditable(false);
            txtPreco.setText(String.valueOf(produto.getPreco()));
            txtPreco.setEditable(false);
            txtQnt.setText(String.valueOf(produto.getQtd()));
            txtQnt.setEditable(false);
        }else{
            txtId.setText("");
            txtDesc.setText("");
            txtPreco.setText("");
            txtQnt.setText("");
        }
    }

    public void fillTableProd(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos;
        ObservableList<Produto> observableList;
        tableProdId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableProdDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableProdPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableProdQnt.setCellValueFactory(new PropertyValueFactory<>("qtd"));

        produtos = produtoDAO.list();

        observableList = FXCollections.observableArrayList(produtos);
        tableProd.setItems(observableList);
    }

    public boolean showGerenciamentoEstoque() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDEstoque.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Estoque");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        CRUDEstoqueController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
        return controller.isBtnClickedConfirm();
    }
    public boolean showGerenciamentoEstoque(Produto produto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDEstoque.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Estoque");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        CRUDEstoqueController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduto(produto);
        dialogStage.showAndWait();
        return controller.isBtnClickedConfirm();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableProd();
        tableProd.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> selectItemTableViewProd(newValue));
    }
}
