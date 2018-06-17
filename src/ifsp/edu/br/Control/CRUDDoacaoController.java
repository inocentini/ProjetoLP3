package ifsp.edu.br.Control;

import ifsp.edu.br.DAO.DoacaoDAO;
import ifsp.edu.br.DAO.UsuarioDAO;
import ifsp.edu.br.Model.Animais.Animal;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CRUDDoacaoController implements Initializable {

    @FXML
    private Label labGerenDoa;

    @FXML
    private TableView<Animal> tableAnimal;

    @FXML
    private TableColumn<Animal, Integer> tableAnimalId;

    @FXML
    private TableColumn<Animal,String> tableAnimalApelido;

    @FXML
    private TableView<Produto> tableProduto;

    @FXML
    private TableColumn<Produto,Integer> tableProdutoId;

    @FXML
    private TableColumn<Produto, String> tableProdutoDesc;

    @FXML
    private Label labProdutos;

    @FXML
    private Button btnInserirProd;

    @FXML
    private Label labUsers;

    @FXML
    private ComboBox<Usuario> cbUser;

    @FXML
    private Label labDate;

    @FXML
    private Label labDataDoa;

    @FXML
    private Label labAnimais;

    @FXML
    private Button btnInserirAnimal;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnVoltar;



    private List<Usuario> usuarios;
    private ObservableList<Usuario> observableList;
    private List<Animal> animalList = new ArrayList<>();
    private List<Produto> produtoList = new ArrayList<>();
    private Stage dialogStage;
    private boolean btnClickedConfirm = false;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private Doacao doacao = null;
    private DoacaoDAO doacaoDAO = new DoacaoDAO();
    private UsuarioDAO userDAO = new UsuarioDAO();


    @FXML
    void handleAdicionarDoa(ActionEvent event) {
        Date data = new Date(System.currentTimeMillis());
        if(doacao == null && isComplete()){
           doacao = new Doacao();
           doacao.setUser(cbUser.getValue());
           doacao.setData(data);
           doacao.setAnimais(animalList);
           doacao.setProdutos(produtoList);
           doacaoDAO.add(doacao);
       }else if(doacao != null && isComplete()){
           doacao.setUser(cbUser.getValue());
           doacao.setData(data);
           doacao.setAnimais(animalList);
           doacao.setProdutos(produtoList);
           doacaoDAO.update(doacao);
       }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dados Incompletos");
            alert.setContentText("Por favor, preencher dados corretamente.");
            alert.showAndWait();
            return;
        }
        btnClickedConfirm = true;
        dialogStage.close();
    }

    @FXML
    void handleBtnInserirAnimal(ActionEvent event) throws IOException {
        Animal animal = showCRUDAnimal();
        if(animal != null){
            animalList.add(animal);
            fillTableAnimal();
        }
    }

    @FXML
    void handleBtnInserirProduto(ActionEvent event) throws IOException {
        Produto produto = showCRUDEstoque();
        if(produto != null){
            produtoList.add(produto);
            fillTableProduto();
        }
    }

    @FXML
    void handleBtnVoltar(ActionEvent event) {
        this.dialogStage.close();
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

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
        this.cbUser.setValue(doacao.getUser());
        this.labDataDoa.setText(String.valueOf(doacao.getData()));
        List<Animal> animais = doacao.getAnimais();
        for(Animal a : animais){
            animalList.add(a);
        }
        List<Produto> produtos = doacao.getProdutos();
        for(Produto p : produtos){
            produtoList.add(p);
        }
        fillTableAnimal();
        fillTableProduto();
        this.labGerenDoa.setText("ALTERAÇÃO DE DOAÇÃO");
        this.btnAdicionar.setText("Alterar");
    }

    public void fillComboBoxCliente(){
        usuarios = userDAO.list();
        observableList = FXCollections.observableArrayList(usuarios);
        cbUser.setItems(observableList);
    }

    public void fillTableAnimal(){
        ObservableList<Animal> observableList;

        tableAnimalId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableAnimalApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));

        observableList = FXCollections.observableArrayList(animalList);

        tableAnimal.setItems(observableList);
    }

    public void fillTableProduto(){
        ObservableList<Produto> observableList;

        tableProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableProdutoDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        observableList = FXCollections.observableArrayList(produtoList);

        tableProduto.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboBoxCliente();
    }

    public boolean isComplete(){
        if(cbUser.getValue() == null
                || tableAnimal.getItems().isEmpty()
                || tableProduto.getItems().isEmpty()){
            return false;
        }
        return true;
    }

    public Animal showCRUDAnimal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ifsp/edu/br/View/CRUDAnimal.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de PET");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CRUDAnimalController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.getAnimal();
    }

    public Produto showCRUDEstoque() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
                "ifsp/edu/br/View/CRUDEstoque.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Produto");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        CRUDEstoqueController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
        return controller.getProduto();
    }


}