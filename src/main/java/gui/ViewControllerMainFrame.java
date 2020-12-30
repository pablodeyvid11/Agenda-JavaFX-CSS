package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import services.Limitacoes;

public class ViewControllerMainFrame implements Initializable {
	@FXML
	private Pane paneEsquerda;
	@FXML
	private ImageView imgLogo;

	// Form

	@FXML
	private TextField nome;

	@FXML
	private TextField numero;

	@FXML
	private TextField email;
	@FXML
	private Label verificarEmailLabel;

	@FXML
	private ChoiceBox<String> ChoiceBoxOperadora;

	@FXML
	private ChoiceBox<String> ChoiceBoxGrupo;

	private ObservableList<String> obsOperadora;
	private ObservableList<String> obsGrupo;
	
	
	@FXML
	private Button submit;

	@FXML
	public void submitAction() {
		List<String> camposVazios = new ArrayList<String>();
		if (nome.getText().equals("")) {
			camposVazios.add("nome");
		}
		if (numero.getText().equals("")) {
			camposVazios.add("n�mero");
		}
		if (email.getText().equals("")) {
			camposVazios.add("email");
		}
		if (ChoiceBoxOperadora.getSelectionModel().getSelectedItem() == null) {
			camposVazios.add("operadora");
		}
		if (ChoiceBoxGrupo.getSelectionModel().getSelectedItem() == null) {
			camposVazios.add("grupo");
		}
		String camposVaziosString = "";
		try {
			try {
				for (int i = 0; i < camposVazios.size() - 2; i++) {
					camposVaziosString += camposVazios.get(i) + ", ";
				}
			} catch (Exception e) {

			}
			camposVaziosString += camposVazios.get(camposVazios.size() - 2);
			camposVaziosString += " e ";
			camposVaziosString += camposVazios.get(camposVazios.size() - 1);
		} catch (Exception e) {
		}
		if(camposVazios.size()==1) {
			camposVaziosString+= camposVazios.get(0);
		}
		if (camposVaziosString.equals("")) {
			Alerts.showAlert("Sucesso", "Usu�rio cadastrado com sucesso no noss banco de dados", null,
					AlertType.CONFIRMATION);
		} else {
			Alerts.showAlert("Erro", "N�o foi poss�vel concluir o cadastro do contato!",
					"Campos vazios: " + camposVaziosString + ".", AlertType.ERROR);
		}
	}

	//
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> operadora = new ArrayList<>();
		operadora.add("TIM");
		operadora.add("VIVO");
		operadora.add("CLARO");
		operadora.add("OI");
		operadora.add("OUTROS");
		
		List<String> grupo = new ArrayList<>();
		grupo.add("Fam�lia");
		grupo.add("Amigos");
		grupo.add("Trabalho");
		grupo.add("Outros");
		
		obsOperadora = FXCollections.observableArrayList(operadora);
		obsGrupo = FXCollections.observableArrayList(grupo);
		
		ChoiceBoxOperadora.setItems(obsOperadora);
		ChoiceBoxGrupo.setItems(obsGrupo);
		
		
		Limitacoes.LimitarTextoSoComNumeros(numero);
		Limitacoes.regularTamanhoDoQueFoiDigitado(numero, 12);
		Limitacoes.formatarEmail(email, verificarEmailLabel);
	}
}
