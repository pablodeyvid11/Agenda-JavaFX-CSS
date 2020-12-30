package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
	private ChoiceBox<String> operadora;

	@FXML
	private ChoiceBox<String> grupo;

	@FXML
	private Button submit;

	@FXML
	public void submitAction() {
		List<String> camposVazios = new ArrayList<String>();
		if (nome.getText().equals("")) {
			camposVazios.add("nome");
		}
		if (numero.getText().equals("")) {
			camposVazios.add("número");
		}
		if (email.getText().equals("")) {
			camposVazios.add("email");
		}
//		if (operadora.getSelectionModel().getSelectedItem().equals("")) {
//			camposVazios.add("operadora");
//		}
//		if (grupo.getSelectionModel().getSelectedItem().equals("")) {
//			camposVazios.add("grupo");
//		}
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
			Alerts.showAlert("Sucesso", "Usuário cadastrado com sucesso no noss banco de dados", null,
					AlertType.CONFIRMATION);
		} else {
			Alerts.showAlert("Erro", "Não foi possível concluir o cadastro do contato!",
					"Campos vazios: " + camposVaziosString + ".", AlertType.ERROR);
		}
	}

	//
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
