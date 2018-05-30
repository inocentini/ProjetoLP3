package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.UsuarioDAO;
import ifsp.edu.br.Model.Pessoas.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CRUDUserController implements Initializable {

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
    private Button btnAdd;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label labGerenUser;

    private Stage dialogStage;
    private boolean btnConfirmClicked = false;
    private Usuario user;
    private UsuarioDAO userDAO = new UsuarioDAO();

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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
        this.txtId.setText(String.valueOf(user.getId()));
        this.txtId.setDisable(true);
        this.txtNome.setText(user.getNome());
        this.txtCPF.setText(user.getCpf());
        this.txtCPF.setDisable(true);
        this.txtEndereco.setText(user.getEndereco());
        this.txtTel.setText(user.getTelefone());
        this.txtEmail.setText(user.getEmail());
        this.labGerenUser.setText("ALTERAÇÃO DE USUÁRIO");
        this.btnAdd.setText("Alterar");
    }

    @FXML
    void handleBtnAdicionar(ActionEvent event) {
        if(user == null){
            user = new Usuario();
            user.setNome(txtNome.getText());
            user.setCpf(txtCPF.getText());
            user.setEndereco(txtEndereco.getText());
            user.setTelefone(txtTel.getText());
            user.setEmail(txtEmail.getText());
            userDAO.add(user);
            btnConfirmClicked = true;
            dialogStage.close();
        }else{
            user.setNome(txtNome.getText());
            user.setEndereco(txtEndereco.getText());
            user.setTelefone(txtTel.getText());
            user.setEmail(txtEmail.getText());
            userDAO.update(user);
            btnConfirmClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    void handleBtnCancelar(ActionEvent event) {
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtId.setText(String.valueOf(userDAO.nextSeqUser()));
        txtId.setEditable(false);
    }
}
