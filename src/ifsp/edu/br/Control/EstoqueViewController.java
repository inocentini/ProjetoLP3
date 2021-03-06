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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
    private ImageView imageMenu;

    private PrincipalController menu;

    public PrincipalController getMenu() {
        return menu;
    }

    public void setMenu(PrincipalController menu) {
        this.menu = menu;
    }

    @FXML
    void handleBtnAlterar(ActionEvent event) throws IOException {
        Produto produto = tableProd.getSelectionModel().getSelectedItem();
        if(produto != null){
            boolean btnAlterarClicked = showGerenciamentoEstoque(produto);
            if(btnAlterarClicked){
                fillTableProd();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selecionar Produto");
            alert.setContentText("Por favor, selecione uma conta na tabela");
            alert.show();
            return;
        }

    }

    @FXML
    void handleBtnInserir(ActionEvent event) throws IOException {
        boolean btnAddClicked = showGerenciamentoEstoque();
        if(btnAddClicked){
            fillTableProd();
        }

    }

    @FXML
    void handleBtnRemover(ActionEvent event) {
        Produto produto = tableProd.getSelectionModel().getSelectedItem();
        if(produto != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removendo Produto.");
            alert.setContentText("Você deseja remover este produto?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.CANCEL){
                return;
            }else if(result.get() == ButtonType.OK){
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.remove(produto);
                fillTableProd();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Seleção de Produto");
            alert.setContentText("Por favor, selecione um produto.");
            alert.show();
            return;
        }

    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {
        Image image = menu.getIvCenter().getImage();
        this.imageMenu.setDisable(false);
        this.imageMenu.setImage(image);
        this.imageMenu.setVisible(true);
    }

    @FXML
    void pesquisaProduto(KeyEvent event) {
        ObservableList<Produto> observableList = tableProd.getItems();
        FilteredList<Produto> filteredList = new FilteredList<>(observableList, produto -> true);
        txtPesquisar.setOnKeyPressed(produto -> {
            txtPesquisar.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Produto>) prod -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(prod.getId()) == newValue) {
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
