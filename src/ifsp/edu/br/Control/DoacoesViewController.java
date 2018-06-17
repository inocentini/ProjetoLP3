package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.AdocaoDAO;
import ifsp.edu.br.DAO.CachorroDAO;
import ifsp.edu.br.DAO.DoacaoDAO;
import ifsp.edu.br.DAO.GatoDAO;
import ifsp.edu.br.Model.Adocao;
import ifsp.edu.br.Model.Animais.Animal;
import ifsp.edu.br.Model.Animais.Cachorro;
import ifsp.edu.br.Model.Animais.Gato;
import ifsp.edu.br.Model.Doacao;
import ifsp.edu.br.Model.Pessoas.Usuario;
import ifsp.edu.br.Model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DoacoesViewController  implements Initializable {

    @FXML
    private Tab tabAdo;

    @FXML
    private SplitPane spliUsers;

    @FXML
    private TableView<Adocao> tableAdocao;

    @FXML
    private TableColumn<Adocao, Integer> tableAdocaoId;

    @FXML
    private TableColumn<Adocao, String> tableAdocaoUser; /* Obs: Usuário?*/

    @FXML
    private TableColumn<Adocao, Date> tableAdocaoDate;

    @FXML
    private Label labAdocao;

    @FXML
    private Label labDesc;

    @FXML
    private GridPane gridDesc;

    @FXML
    private Label labData;

    @FXML
    private Label labId;

    @FXML
    private Label labUser;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtUser;

    @FXML
    private DatePicker dtAdo;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Animal> tableAnimaisAd;

    @FXML
    private TableColumn<Animal, Integer> tableAnimaisAdId;

    @FXML
    private TableColumn<Animal, String> tableAnimaisAdNick;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Label labPesquisar;

    @FXML
    private SplitPane spliUsers1;

    @FXML
    private TableView<Doacao> tableDoacao;

    @FXML
    private TableColumn<Doacao, Integer> tableDoacaoId;

    @FXML
    private TableColumn<Doacao, String> tableDoacaoUser; //Usuário?

    @FXML
    private TableColumn<Doacao, Date> tableDoacaoDate;

    @FXML
    private Label labDoacao;

    @FXML
    private Label labDesc1;

    @FXML
    private GridPane gridDesc1;

    @FXML
    private Label labDataDoa;

    @FXML
    private Label labIdDoa;

    @FXML
    private Label labUserDoa;

    @FXML
    private TextField txtIdDoa;

    @FXML
    private TextField txtUserDoa;

    @FXML
    private DatePicker dtDoa;

    @FXML
    private Button btnInserirDoa;

    @FXML
    private Button btnAlterarDoa;

    @FXML
    private Button btnDeletarDoa;

    @FXML
    private Button btnVoltarDoa;

    @FXML
    private TableView<Animal> tableAnimais;

    @FXML
    private TableColumn<Animal, String> tableAnimaisColun;

    @FXML
    private TableView<Produto> tableProdutos;

    @FXML
    private TableColumn<Produto, String> tableProdutoColun;

    @FXML
    private TextField txtPesquisarDoa;

    @FXML
    private Label labPesquisarDoa;

    @FXML
    void handleBtnAlterar(ActionEvent event) throws IOException {
        Adocao adocaoup = tableAdocao.getSelectionModel().getSelectedItem();
        if(adocaoup != null){
            boolean btnAlterarClicked = showGerenciamentoAdocao(adocaoup);
            if(btnAlterarClicked){
                fillTableAdocao();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seleção de Adoção");
            alert.setContentText("Por favor, selecione uma adoção.");
            alert.show();
        }
    }

    @FXML
    void handleBtnAlterarDoa(ActionEvent event) throws IOException {
        Doacao doacaoup = tableDoacao.getSelectionModel().getSelectedItem();
        if(doacaoup != null){
            boolean btnAddClicked = showGerenciamentoDoacao(doacaoup);
            if(btnAddClicked){
                fillTableDoacao();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seleção de Doação");
            alert.setContentText("Por favor, selecione uma doação.");
            alert.show();
            return;
        }
    }

    @FXML
    void handleBtnInserir(ActionEvent event) throws IOException {
        boolean btnAddClicked = showGerenciamentoAdocao();
        if(btnAddClicked) {
            fillTableAdocao();
        }
    }

    @FXML
    void handleBtnInserirDoa(ActionEvent event) throws IOException {
        boolean btnAddClicked = showGerenciamentoDoacao();
        if(btnAddClicked){
            fillTableDoacao();
        }
    }

    @FXML
    void handleBtnRemover(ActionEvent event) {
        Adocao adocao = tableAdocao.getSelectionModel().getSelectedItem();
        if(adocao != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removendo Adoção.");
            alert.setContentText("Você deseja remover esta adoção?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.CANCEL){
                return;
            }else if(result.get() == ButtonType.OK){
                AdocaoDAO adocaoDAO = new AdocaoDAO();
                for(Animal a : adocao.getAnimais()){
                    if(a.getClass() == Cachorro.class){
                        CachorroDAO dogDAO = new CachorroDAO();
                        a.setAdocao(0);
                        dogDAO.update((Cachorro) a);
                    }else if(a.getClass() == Gato.class){
                        GatoDAO catDAO = new GatoDAO();
                        a.setAdocao(0);
                        catDAO.update((Gato) a);
                    }
                }
                adocaoDAO.remove(adocao);
                fillTableAdocao();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Seleção de Adoção");
            alert.setContentText("Por favor, selecione uma adoção.");
            alert.show();
        }
    }

    @FXML
    void handleBtnRemoverDoa(ActionEvent event) {

    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {

    }

    @FXML
    void handleBtnVoltarDoa(ActionEvent event) {

    }

    @FXML
    void pesquisaFunc(KeyEvent event) {

    }

    public void fillTableAnimaisAd(Adocao adocao){
        tableAnimaisAd.getItems().clear();
        List<Animal> animais = adocao.getAnimais();
        ObservableList<Animal> animals;

        tableAnimaisAdId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableAnimaisAdNick.setCellValueFactory(new PropertyValueFactory<>("apelido"));

        animals = FXCollections.observableArrayList(animais);
        tableAnimaisAd.setItems(animals);

    }

    public void fillTableAnimaisDoa(Doacao doacao){
        tableAnimais.getItems().clear();
        List<Animal> animals = doacao.getAnimais();
        ObservableList<Animal> observableList;

        tableAnimaisColun.setCellValueFactory(new PropertyValueFactory<>("apelido"));

        observableList = FXCollections.observableArrayList(animals);
        tableAnimais.setItems(observableList);
    }

    public void fillTableProdutoDoa(Doacao doacao){
        tableProdutos.getItems().clear();
        List<Produto> produtos = doacao.getProdutos();
        ObservableList<Produto> observableList;

        tableProdutoColun.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        observableList = FXCollections.observableArrayList(produtos);
        tableProdutos.setItems(observableList);
    }

    public void fillTableDoacao(){
        tableDoacao.getItems().clear();

        DoacaoDAO doacaoDAO = new DoacaoDAO();
        List<Doacao> doacoes = doacaoDAO.list();
        ObservableList<Doacao> observableList = FXCollections.observableArrayList();
        for(Doacao d : doacoes){
            observableList.add(new Doacao(d.getId(),d.getUser(),d.getAnimais(),d.getProdutos(),d.getData()));
        }
        tableDoacaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableDoacaoUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        tableDoacaoDate.setCellValueFactory(new PropertyValueFactory<>("data"));

        tableDoacao.setItems(observableList);
    }

    public void fillTableAdocao(){
        tableAdocao.getItems().clear();

        AdocaoDAO adocaoDAO = new AdocaoDAO();
        List<Adocao> adocoes = adocaoDAO.list();
        ObservableList<Adocao> observableList = FXCollections.observableArrayList();
        for(Adocao ad : adocoes){
            observableList.add(new Adocao(ad.getId(),ad.getAnimais(),ad.getUser(),ad.getData()));
        }
        tableAdocaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableAdocaoUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        tableAdocaoDate.setCellValueFactory(new PropertyValueFactory<>("data"));

        tableAdocao.setItems(observableList);

    }

    public void selectItemTableDoacao(Doacao doacao){
        if(doacao != null){
            txtIdDoa.setDisable(true);
            txtIdDoa.setText(String.valueOf(doacao.getId()));
            txtUserDoa.setText(doacao.getUser().getNome());
            txtUserDoa.setEditable(false);
            dtDoa.setValue(LOCAL_DATE(doacao.getData().toString()));
            fillTableAnimaisDoa(doacao);
            fillTableProdutoDoa(doacao);
        }else{
            txtIdDoa.setText("");
            txtUserDoa.setText("");
            tableAnimais.getItems().clear();
            tableProdutos.getItems().clear();
        }
    }

    public void selectItemTableAdocao(Adocao adocao){
        if(adocao != null){
            txtId.setDisable(true);
            txtId.setText(String.valueOf(adocao.getId()));
            txtUser.setEditable(false);
            txtUser.setText(adocao.getUser().getNome());
            dtAdo.setEditable(false);
            dtAdo.setValue(LOCAL_DATE(adocao.getData().toString()));
            fillTableAnimaisAd(adocao);
        }else{
            txtId.setText("");
            txtUser.setText("");
            tableAnimaisAd.getItems().clear();
        }
    }

    public boolean showGerenciamentoDoacao() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDDoacao.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Doação");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CRUDDoacaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isBtnClickedConfirm();
    }

    public boolean showGerenciamentoDoacao(Doacao doacao) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDDoacao.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Doação");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CRUDDoacaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setDoacao(doacao);

        dialogStage.showAndWait();

        return controller.isBtnClickedConfirm();
    }

    public boolean showGerenciamentoAdocao() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDAdocao.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Adoção");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CRUDAdocaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isBtnconfirm();
    }

    public boolean showGerenciamentoAdocao(Adocao adocao) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDAdocao.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alteração de Adoção");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CRUDAdocaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAdocao(adocao);

        dialogStage.showAndWait();

        return controller.isBtnconfirm();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableAdocao();
        tableAdocao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableAdocao(newValue));

        fillTableDoacao();
        tableDoacao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectItemTableDoacao(newValue)
        );
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
