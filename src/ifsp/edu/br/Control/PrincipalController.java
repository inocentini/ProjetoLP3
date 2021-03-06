package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.CachorroDAO;
import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Conta;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox vboxLeft;

    @FXML
    private Button btnAnimal;

    @FXML
    private Button btnUser;

    @FXML
    private Button btnFunc;

    @FXML
    private Button btnConta;

    @FXML
    private Button btnDoa;

    @FXML
    private ImageView ivCenter;

    @FXML
    private AnchorPane paneCenter;

    @FXML
    private Button btnEstoque;



    @FXML
    void gerenciaAnimal(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().
                getClassLoader().getResource("ifsp/edu/br/View/AnimalView.fxml"));
        AnchorPane root = loader.load();
        AnimalViewController controller = loader.getController();
        controller.setMenu(this);
        paneCenter.getChildren().setAll(root);
        paneCenter.getChildren().remove(paneCenter);
    }

    @FXML
    void gerenciaConta(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("ifsp/edu/br/View/ContaView.fxml"));
        AnchorPane root = loader.load();
        ContaViewController controller = loader.getController();
        controller.setMenu(this);
        paneCenter.getChildren().setAll(root);
        ivCenter.setVisible(false);
    }

    @FXML
    void gerenciaDoacao(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/DoacoesView.fxml"));
        AnchorPane root = loader.load();
        DoacoesViewController controller = loader.getController();
        controller.setMenu(this);
        paneCenter.getChildren().setAll(root);
    }

    @FXML
    void gerenciaFuncionario(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ifsp/edu/br/View/FuncionarioView.fxml"));
        AnchorPane root = loader.load();
        FuncionarioViewController controller = loader.getController();
        controller.setMenu(this);
        paneCenter.getChildren().setAll(root);
    }

    @FXML
    void gerenciaUsuario(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ifsp/edu/br/View/UserView.fxml"));
        AnchorPane root = loader.load();
        UserViewController controller = loader.getController();
        controller.setMenu(this);
        paneCenter.getChildren().setAll(root);

    }

    @FXML
    void gerenciaEstoque(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ifsp/edu/br/View/EstoqueView.fxml"));
        AnchorPane root = loader.load();
        EstoqueViewController controller = loader.getController();
        controller.setMenu(this);
        paneCenter.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ImageView getIvCenter() {
        return ivCenter;
    }

    public void setIvCenter(ImageView ivCenter) {
        this.ivCenter = ivCenter;
    }
}