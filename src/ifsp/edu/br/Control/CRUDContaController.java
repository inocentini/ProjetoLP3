package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.ContaDAO;
import ifsp.edu.br.Model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class CRUDContaController implements Initializable {

    @FXML
    private Label labId;

    @FXML
    private Label labDesc;

    @FXML
    private Label labValor;

    @FXML
    private Label labVencimento;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtId;

    @FXML
    private DatePicker dtVencimento;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label labGerenConta;


    private Stage dialogStage;
    private boolean btnClickedConfirm = false;
    private Conta conta = null;
    private ContaDAO contaDAO = new ContaDAO();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @FXML
    void handleBtnAdicionar(ActionEvent event) {
        if(conta == null){
            conta = new Conta();
            conta.setDescricao(txtDesc.getText());
            conta.setValor(Double.parseDouble(txtValor.getText()));
            String dataVenc = dtVencimento.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            Date contaVenc = new Date(dataVenc);
            conta.setVencimento(contaVenc);
            contaDAO.add(conta);
            btnClickedConfirm = true;
            dialogStage.close();
        }else{
            conta.setDescricao(txtDesc.getText());
            conta.setValor(Double.parseDouble(txtValor.getText()));
            String dataVenc = dtVencimento.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            Date contaVenc = new Date(dataVenc);
            conta.setVencimento(contaVenc);
            contaDAO.update(conta);
            btnClickedConfirm = true;
            dialogStage.close();
        }
    }

    @FXML
    void handleBtnCancelar(ActionEvent event) {
        dialogStage.close();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnClickedConfirm() {
        return btnClickedConfirm;
    }

    public void setBtnClickedConfirm(boolean btnClickedConfirm) {
        this.btnClickedConfirm = btnClickedConfirm;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
        this.txtId.setText(String.valueOf(conta.getId()));
        this.txtId.setDisable(true);
        this.txtDesc.setText(conta.getDescricao());
        this.txtValor.setText(String.valueOf(conta.getValor()));
        this.dtVencimento.setValue(LOCAL_DATE(conta.getVencimento().toString()));
        this.labGerenConta.setText("ALTERAÇÃO DE CONTA");
        this.btnAdd.setText("Alterar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtId.setText(String.valueOf(contaDAO.nextSeqConta()));
        txtId.setEditable(false);
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
