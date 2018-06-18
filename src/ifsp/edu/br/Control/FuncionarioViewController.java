package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.FuncionarioDAO;
import ifsp.edu.br.Model.Pessoas.Funcionario;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class FuncionarioViewController implements Initializable {

    @FXML
    private SplitPane spliUsers;

    @FXML
    private TableView<Funcionario> tableFunc;

    @FXML
    private TableColumn<Funcionario, Integer> tableFuncId;

    @FXML
    private TableColumn<Funcionario, String> tableFuncNome;

    @FXML
    private TableColumn<Funcionario, String> tableFuncCPF;

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
    private Label labSalario;

    @FXML
    private TextField txtSalario;

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
        Funcionario func = new Funcionario();
        func = tableFunc.getSelectionModel().getSelectedItem();
        if(func != null){
            boolean btnAlterarClicked = showGerenciamentoFunc(func);
            if(btnAlterarClicked){
                fillTableFunc();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selecionar Funcionário");
            alert.setContentText("Por favor, selecione um funcionário na tabela");
            alert.show();
        }
    }

    @FXML
    void handleBtnInserir(ActionEvent event) throws IOException {
        boolean btnAddClicked = showGerenciamentoFunc();
        if(btnAddClicked){
            fillTableFunc();
        }
    }

    @FXML
    void handleBtnRemover(ActionEvent event) {
        Funcionario func = new Funcionario();
        func = tableFunc.getSelectionModel().getSelectedItem();

        if(func != null ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removendo Funcionário.");
            alert.setContentText("Você deseja remover este funcionário?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.CANCEL){
                return;
            }else if(result.get() == ButtonType.OK){
                FuncionarioDAO funcDAO = new FuncionarioDAO();
                funcDAO.remove(func);
                fillTableFunc();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seleção de funcionário");
            alert.setContentText("Por favor, selecione um funcionário.");
            alert.show();
        }
    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableFunc();
        tableFunc.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> selectItemTableViewFunc(newValue));
    }

    @FXML
    void pesquisaFunc(KeyEvent event) {
        ObservableList<Funcionario> observableList = tableFunc.getItems();
        FilteredList<Funcionario> filteredList = new FilteredList<>(observableList, funcionario -> true);
        txtPesquisar.setOnKeyPressed(funcionario -> {
            txtPesquisar.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Funcionario>) func -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (newValue == String.valueOf(func.getId())) {
                        return true;
                    } else if (func.getNome().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Funcionario> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableFunc.comparatorProperty());
            tableFunc.setItems(sortedList);
        });
    }


    public void fillTableFunc(){
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        List<Funcionario> funcionarioList;
        ObservableList<Funcionario> funcionarioObservableList;
        tableFuncId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableFuncNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableFuncCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        funcionarioList = funcDAO.list();

        funcionarioObservableList = FXCollections.observableArrayList(funcionarioList);
        tableFunc.setItems(funcionarioObservableList);
    }


    public void selectItemTableViewFunc(Funcionario func){
        if(func != null){
            txtId.setText(String.valueOf(func.getId()));
            txtId.setDisable(true);
            txtNome.setText(func.getNome());
            txtNome.setEditable(false);
            txtCPF.setText(func.getCpf());
            txtCPF.setEditable(false);
            txtEndereço.setText(func.getEndereco());
            txtEndereço.setEditable(false);
            txtTelefone.setText(func.getTelefone());
            txtTelefone.setEditable(false);
            txtEmail.setText(func.getEmail());
            txtEmail.setEditable(false);
            txtSalario.setText(String.valueOf(func.getSalario()));
            txtSalario.setEditable(false);
        }else{
            txtId.setText("");
            txtNome.setText("");
            txtCPF.setText("");
            txtEndereço.setText("");
            txtTelefone.setText("");
            txtEmail.setText("");
            txtSalario.setText("");
        }
    }

    public boolean showGerenciamentoFunc() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDFunc.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funcionários");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CRUDFuncController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isBtnConfirmClicked();
    }

    public boolean showGerenciamentoFunc(Funcionario func) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDFunc.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alteração de Usuários");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CRUDFuncController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFunc(func);

        dialogStage.showAndWait();

        return controller.isBtnConfirmClicked();
    }
}
