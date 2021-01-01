package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entities.Contato;
import entities.dao.ContatoDAO;
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
	
	private final ContatoDAO dao = new ContatoDAO();
	
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
			camposVazios.add("número");
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
		if (!camposVaziosString.equals("")) {
			Alerts.showAlert("Erro", "Não foi possível concluir o cadastro do contato!",
					"Campos vazios: " + camposVaziosString + ".", AlertType.ERROR);
		} else {
			Contato contato = new Contato(null, nome.getText(), numero.getText(), email.getText(), ChoiceBoxOperadora.getSelectionModel().getSelectedItem(), ChoiceBoxGrupo.getSelectionModel().getSelectedItem());
			if(dao.addContato(contato)) {
				Alerts.showAlert("Sucesso", "Usuário cadastrado com sucesso no nosso banco de dados", null,
						AlertType.CONFIRMATION);	
				limparCampos();
			} else {
				Alerts.showAlert("Erro", "Não foi possível concluir o cadastro do contato!",
						"Consulte a documentação ou fale com o nosso desenvolvedor.", AlertType.ERROR);
			}
		}
	}

	private void limparCampos() {
		nome.setText("");
		numero.setText("");
		email.setText("");
		organizarCheckBox();
	}

	//
	
	private void organizarCheckBox() {
		List<String> operadora = new ArrayList<>();
		operadora.add("TIM");
		operadora.add("VIVO");
		operadora.add("CLARO");
		operadora.add("OI");
		operadora.add("OUTROS");
		
		List<String> grupo = new ArrayList<>();
		grupo.add("Família");
		grupo.add("Amigos");
		grupo.add("Trabalho");
		grupo.add("Outros");
		
		obsOperadora = FXCollections.observableArrayList(operadora);
		obsGrupo = FXCollections.observableArrayList(grupo);
		
		ChoiceBoxOperadora.setItems(obsOperadora);
		ChoiceBoxGrupo.setItems(obsGrupo);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		organizarCheckBox();
		
		Limitacoes.LimitarTextoSoComNumeros(numero);
		Limitacoes.regularTamanhoDoQueFoiDigitado(numero, 12);
		Limitacoes.formatarEmail(email, verificarEmailLabel);
	}
}
