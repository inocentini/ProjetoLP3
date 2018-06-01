package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.ContaDAO;
import ifsp.edu.br.Model.Conta;
import ifsp.edu.br.Model.Pessoas.Funcionario;
import javafx.beans.Observable;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ContaViewController implements Initializable {

    @FXML
    private SplitPane spliUsers;

    @FXML
    private TableView<Conta> tableConta;

    @FXML
    private TableColumn<Conta, Integer> tableContaId;

    @FXML
    private TableColumn<Conta, String> tableContaDesc;

    @FXML
    private TableColumn<Conta, Date> tableContaVencimento;

    @FXML
    private TableColumn<Conta, Double> tableContaValor;

    @FXML
    private Label labFunc;

    @FXML
    private Label labDesc;

    @FXML
    private GridPane gridDesc;

    @FXML
    private Label labCPF;

    @FXML
    private Label labEndereco;

    @FXML
    private Label labId;

    @FXML
    private Label labNome;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtVencimento;

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
        Conta conta = new Conta();
        conta = tableConta.getSelectionModel().getSelectedItem();
        if(conta != null){
            boolean btnAlterarClicked = showGerenciamentoConta(conta);
            if(btnAlterarClicked){
                fillTableConta();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selecionar Conta");
            alert.setContentText("Por favor, selecione uma conta na tabela");
            alert.show();
        }

    }

    @FXML
    void handleBtnInserir(ActionEvent event) throws IOException {
        boolean btnAddClicked = showGerenciamentoConta();
        if(btnAddClicked)
            fillTableConta();
    }

    @FXML
    void handleBtnRemover(ActionEvent event) {
        Conta conta = new Conta();
        conta = tableConta.getSelectionModel().getSelectedItem();

        if(conta != null ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removendo Conta.");
            alert.setContentText("Você deseja remover esta conta?");
            alert.showAndWait();
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.remove(conta);
            fillTableConta();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seleção de conta");
            alert.setContentText("Por favor, selecione um conta.");
            alert.show();
        }
    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {

    }

    @FXML
    void pesquisaFunc(KeyEvent event) {
        ObservableList<Conta> observableList = tableConta.getItems();
        FilteredList<Conta> filteredList = new FilteredList<>(observableList, conta -> true);
        txtPesquisar.setOnKeyReleased(conta -> {
            txtPesquisar.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Conta>) taxe -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(taxe.getId()).contains(newValue)) {
                        return true;
                    } else if (taxe.getDescricao().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Conta> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableConta.comparatorProperty());
            tableConta.setItems(sortedList);
        });
    }

    public void selectItemTableViewConta(Conta conta){
        if(conta != null){
            txtId.setText(String.valueOf(conta.getId()));
            txtId.setDisable(true);
            txtDesc.setText(conta.getDescricao());
            txtDesc.setEditable(false);
            txtVencimento.setText(String.valueOf(conta.getVencimento()));
            txtVencimento.setEditable(false);
            txtValor.setText(String.valueOf(conta.getValor()));
            txtValor.setEditable(false);
        }else{
            txtId.setText("");
            txtDesc.setText("");
            txtVencimento.setText("");
            txtValor.setText("");
        }
    }

    public void fillTableConta(){
        ContaDAO contaDAO = new ContaDAO();
        List<Conta> contaList;
        ObservableList<Conta> contaObservableList;
        tableContaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableContaDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableContaVencimento.setCellValueFactory(new PropertyValueFactory<>("vencimento"));
        tableContaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        contaList = contaDAO.list();

        contaObservableList = FXCollections.observableArrayList(contaList);
        tableConta.setItems(contaObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableConta();
        tableConta.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> selectItemTableViewConta(newValue));
    }

    public boolean showGerenciamentoConta() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDConta.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Conta");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CRUDContaController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isBtnClickedConfirm();
    }

    public boolean showGerenciamentoConta(Conta conta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDConta.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alteração de Conta");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CRUDContaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setConta(conta);

        dialogStage.showAndWait();

        return controller.isBtnClickedConfirm();
    }
}
