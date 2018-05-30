package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.FuncionarioDAO;
import ifsp.edu.br.Model.Pessoas.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CRUDFuncController implements Initializable {

    @FXML
    private Label labId;

    @FXML
    private Label labNome;

    @FXML
    private Label labCPF;

    @FXML
    private Label labEndereco;

    @FXML
    private Label labTelefone;

    @FXML
    private Label labEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label labSalario;

    @FXML
    private TextField txtSalario;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label labGerenFunc;

    @FXML
    void handleBtnAdicionar(ActionEvent event) {
        if(func == null){
            func = new Funcionario();
            func.setNome(txtNome.getText());
            func.setCpf(txtCPF.getText());
            func.setEndereco(txtEndereco.getText());
            func.setTelefone(txtTel.getText());
            func.setEmail(txtEmail.getText());
            func.setSalario(Double.parseDouble(txtSalario.getText()));
            funcDAO.add(func);
            btnConfirmClicked = true;
            dialogStage.close();
        }else{
            func.setNome(txtNome.getText());
            func.setEndereco(txtEndereco.getText());
            func.setTelefone(txtTel.getText());
            func.setEmail(txtEmail.getText());
            func.setSalario(Double.parseDouble(txtSalario.getText()));
            funcDAO.update(func);
            btnConfirmClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    void handleBtnCancelar(ActionEvent event) {
        this.dialogStage.close();
    }
    private Stage dialogStage;
    private boolean btnConfirmClicked;
    private Funcionario func;
    private FuncionarioDAO funcDAO = new FuncionarioDAO();

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmClicked() {
        return btnConfirmClicked;
    }

    public void setBtnConfirmClicked(boolean btnConfirmClicked) {
        this.btnConfirmClicked = btnConfirmClicked;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
        this.txtId.setText(String.valueOf(func.getId()));
        this.txtId.setDisable(true);
        this.txtNome.setText(func.getNome());
        this.txtCPF.setText(func.getCpf());
        this.txtCPF.setDisable(true);
        this.txtEndereco.setText(func.getEndereco());
        this.txtTel.setText(func.getTelefone());
        this.txtEmail.setText(func.getEmail());
        this.txtSalario.setText(String.valueOf(func.getSalario()));
        this.labGerenFunc.setText("ALTERAÇÃO DE FUNCIONÁRIO");
        this.btnAdd.setText("Alterar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtId.setText(String.valueOf(funcDAO.nextSeqFunc()));
        txtId.setEditable(false);
    }
}
