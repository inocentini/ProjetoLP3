package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.CachorroDAO;
import ifsp.edu.br.Database.Database;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.text.View;
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
    void gerenciaAnimal(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ifsp/edu/br/View/AnimalView.fxml"));
        AnchorPane root = loader.load();
        AnimalViewController controller = loader.getController();
        paneCenter.getChildren().setAll(root);
        ivCenter.setVisible(false);
    }

    @FXML
    void gerenciaConta(MouseEvent event) {

    }

    @FXML
    void gerenciaDoacao(MouseEvent event) {

    }

    @FXML
    void gerenciaFuncionario(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ifsp/edu/br/View/FuncionarioView.fxml"));
        AnchorPane root = loader.load();
        FuncionarioViewController controller = loader.getController();
        paneCenter.getChildren().setAll(root);
//        AnchorPane newLoadedPane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("ifsp/edu/br/View/FuncionarioView.fxml"));
//        paneCenter.getChildren().setAll(newLoadedPane);
    }

    @FXML
    void gerenciaUsuario(MouseEvent event) throws IOException {
        AnchorPane newLoadedPane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("ifsp/edu/br/View/UserView.fxml"));
        paneCenter.getChildren().setAll(newLoadedPane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}