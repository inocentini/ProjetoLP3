package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.ProdutoDAO;
import ifsp.edu.br.Model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CRUDEstoqueController implements Initializable {

    @FXML
    private Label labId;

    @FXML
    private Label labDesc;

    @FXML
    private Label labPreco;

    @FXML
    private Label labQuantidade;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQtd;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label labGerenProd;

    private Stage dialogStage;
    private boolean btnClickedConfirm = false;
    private Produto produto = null;
    private ProdutoDAO produtoDAO = new ProdutoDAO();

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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.txtId.setText(String.valueOf(produto.getId()));
        this.txtId.setDisable(true);
        this.txtDesc.setText(produto.getDescricao());
        this.txtPreco.setText(String.valueOf(produto.getPreco()));
        this.txtQtd.setText(String.valueOf(produto.getQtd()));
        this.labGerenProd.setText("ALTERAÇÃO DE PRODUTO");
        this.btnAdd.setText("Alterar");
        this.btnCancelar.setText("Voltar");
    }

    @FXML
    void handleBtnAdicionar(ActionEvent event) {
        if(produto == null && isComplete()){
            produto = new Produto();
            produto.setDescricao(txtDesc.getText());
            produto.setPreco(Double.parseDouble(txtPreco.getText()));
            produto.setQtd(Integer.parseInt(txtQtd.getText()));
            produtoDAO.add(produto);
            btnClickedConfirm = true;
            dialogStage.close();
        }else if(produto != null && isComplete()){
            produto.setDescricao(txtDesc.getText());
            produto.setPreco(Double.parseDouble(txtPreco.getText()));
            produto.setQtd(Integer.parseInt(txtQtd.getText()));
            produtoDAO.update(produto);
            btnClickedConfirm = true;
            dialogStage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dados incompletos");
            alert.setContentText("Por favor, preencher dados corretamente.");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    void handleBtnCancelar(ActionEvent event) {
        this.dialogStage.close();
    }

    public boolean isComplete(){
        if(txtId.getText().isEmpty()
                || txtDesc.getText().isEmpty()
                || txtPreco.getText().isEmpty()
                || txtQtd.getText().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtId.setText(String.valueOf(produtoDAO.nextSeqProduto()));
        txtId.setEditable(false);
    }
}
