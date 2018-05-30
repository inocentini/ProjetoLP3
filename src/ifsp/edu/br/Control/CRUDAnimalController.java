package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.CachorroDAO;
import ifsp.edu.br.DAO.GatoDAO;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CRUDAnimalController implements Initializable {

    @FXML
    private ComboBox<String> choiceboxAnimal;

    @FXML
    private Label labId;

    @FXML
    private Label labNick;

    @FXML
    private Label labRaca;

    @FXML
    private Label labIdade;

    @FXML
    private Label labSex;

    @FXML
    private Label labVacinado;

    @FXML
    private Label labCastrado;

    @FXML
    private TextField txtNick;

    @FXML
    private TextField txtRaca;

    @FXML
    private TextField txtIdade;

    @FXML
    private RadioButton rbtnSexF;

    @FXML
    private RadioButton rbtnSexM;

    @FXML
    private RadioButton rbtnVacN;

    @FXML
    private RadioButton rbtnVacS;

    @FXML
    private RadioButton rbtnCasS;

    @FXML
    private RadioButton rbtnCasN;

    @FXML
    private TextField txtId;

    @FXML
    private Label labTipo;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label labGerenPet;

    @FXML
    public void handleBtnAdicionar(){
        if(animal == null) {
            if (choiceboxAnimal.getValue() == "Cachorro") {
                CachorroDAO dogDAO = new CachorroDAO();
                animal = new Cachorro();
//                animal.setId(dogDAO.nextSeqAnimal());
                animal.setApelido(txtNick.getText());
                animal.setRaca(txtRaca.getText());
                animal.setIdade(Integer.parseInt(txtIdade.getText()));
                if (rbtnSexM.isSelected())
                    animal.setSexo(true);
                else if (rbtnSexF.isSelected())
                    animal.setSexo(false);
                if (rbtnVacS.isSelected())
                    animal.setVacinado(true);
                else if (rbtnVacN.isSelected())
                    animal.setVacinado(false);
                if (rbtnCasS.isSelected())
                    animal.setCastrado(true);
                else if (rbtnCasN.isSelected())
                    animal.setCastrado(false);
                dogDAO.add((Cachorro) animal);
            } else if (choiceboxAnimal.getValue() == "Gato") {
                GatoDAO catDAO = new GatoDAO();
                animal = new Gato();
//                animal.setId(catDAO.nextSeqAnimal());
                animal.setApelido(txtNick.getText());
                animal.setRaca(txtRaca.getText());
                animal.setIdade(Integer.parseInt(txtIdade.getText()));
                if (rbtnSexM.isSelected())
                    animal.setSexo(true);
                else if (rbtnSexF.isSelected())
                    animal.setSexo(false);
                if (rbtnVacS.isSelected())
                    animal.setVacinado(true);
                else if (rbtnVacN.isSelected())
                    animal.setVacinado(false);
                if (rbtnCasS.isSelected())
                    animal.setCastrado(true);
                else if (rbtnCasN.isSelected())
                    animal.setCastrado(false);
                catDAO.add((Gato) animal);
            }
            btnconfirm = true;
            dialogStage.close();
        }else{
            if(animal.getClass() == Cachorro.class){
                CachorroDAO dogDAO = new CachorroDAO();
                animal.setApelido(txtNick.getText());
                animal.setIdade(Integer.parseInt(txtIdade.getText()));
                if(rbtnVacS.isSelected())
                    animal.setVacinado(true);
                else if(rbtnVacN.isSelected())
                    animal.setVacinado(false);
                if(rbtnCasS.isSelected())
                    animal.setCastrado(true);
                else if(rbtnCasN.isSelected())
                    animal.setCastrado(false);
                dogDAO.update((Cachorro) animal);
            }else if(animal.getClass() == Gato.class){
                GatoDAO catDAO = new GatoDAO();
                animal.setApelido(txtNick.getText());
                animal.setIdade(Integer.parseInt(txtIdade.getText()));
                if(rbtnVacS.isSelected())
                    animal.setVacinado(true);
                else if(rbtnVacN.isSelected())
                    animal.setVacinado(false);
                if(rbtnCasS.isSelected())
                    animal.setCastrado(true);
                else if(rbtnCasN.isSelected())
                    animal.setCastrado(false);
                catDAO.update((Gato) animal);
            }
            btnconfirm = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleBtnCancelar(){
        dialogStage.close();
    }

    ObservableList<String> tipoAnimal = FXCollections.observableArrayList("Cachorro","Gato");
    private Stage dialogStage;
    private boolean btnconfirm = false;
    private Animal animal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CachorroDAO dogDAO = new CachorroDAO();
        txtId.setText(String.valueOf(dogDAO.nextSeqAnimal()));
        txtId.setEditable(false);
        choiceboxAnimal.setValue(tipoAnimal.get(0));
        choiceboxAnimal.setItems(tipoAnimal);
    }


    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnconfirm() {
        return btnconfirm;
    }

    public void setBtnconfirm(boolean btnconfirm) {
        this.btnconfirm = btnconfirm;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        this.txtId.setText(String.valueOf(animal.getId()));
        this.txtId.setEditable(false);
        this.txtId.setDisable(true);
        this.txtNick.setText(animal.getApelido());
        this.txtRaca.setText(animal.getRaca());
        this.txtRaca.setDisable(true);
        this.txtIdade.setText(String.valueOf(animal.getIdade()));
        this.rbtnSexM.setDisable(true);
        this.rbtnSexF.setDisable(true);
        if(animal.isSexo())
            this.rbtnSexM.setSelected(true);
        else
            this.rbtnSexF.setSelected(true);
        if(animal.isVacinado())
            this.rbtnVacS.setSelected(true);
        else
            this.rbtnVacN.setSelected(true);
        if(animal.isCastrado())
            this.rbtnCasS.setSelected(true);
        else
            this.rbtnCasN.setSelected(true);
        this.choiceboxAnimal.setDisable(true);
        if(animal.getClass() == Cachorro.class) {
            this.choiceboxAnimal.setValue("Cachorro");
        }else {
            this.choiceboxAnimal.setValue("Gato");
        }
        this.labGerenPet.setText("ALTERAÇÃO DE PET");
        btnAdd.setText("Alterar");

    }
}
