package ifsp.edu.br.Control;

import ifsp.edu.br.Model.Animais.Animal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.text.View;
import java.io.IOException;
import java.security.Principal;

public class PrincipalController {

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
    private Pane secPane;

    @FXML
    void gerenciaAnimal(MouseEvent event) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource("ifsp/edu/br/View/AnimalView.fxml"));
        secPane.getChildren().add(newLoadedPane);
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
        Pane newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource("ifsp/edu/br/View/FuncionarioView.fxml"));
        secPane.getChildren().add(newLoadedPane);
        ivCenter.setVisible(false);
    }

    @FXML
    void gerenciaUsuario(MouseEvent event) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource("ifsp/edu/br/View/UserView.fxml"));
        secPane.getChildren().add(newLoadedPane);
        ivCenter.setVisible(false);

    }

}