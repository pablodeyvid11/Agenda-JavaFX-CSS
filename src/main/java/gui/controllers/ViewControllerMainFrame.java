package gui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entities.Contato;
import entities.dao.ContatoDAO;
import entities.util.ContatoSelecionado;
import gui.admin.Page;
import gui.admin.enums.PageType;
import gui.util.Alerts;
import gui.util.Limitacoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ViewControllerMainFrame implements Initializable {

	private final ContatoDAO dao = new ContatoDAO();
	
	private Page page;
	private ContatoSelecionado contatoSelecionado;
	
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
		if (camposVazios.size() == 1) {
			camposVaziosString += camposVazios.get(0);
		}
		if (!camposVaziosString.equals("")) {
			Alerts.showAlert("Erro", "Não foi possível concluir o cadastro do contato!",
					"Campos vazios: " + camposVaziosString + ".", AlertType.ERROR);
		} else {
			Contato contato = new Contato(null, nome.getText(), numero.getText(), email.getText(),
					ChoiceBoxOperadora.getSelectionModel().getSelectedItem(),
					ChoiceBoxGrupo.getSelectionModel().getSelectedItem());
			if (dao.addContato(contato)) {
				Alerts.showAlert("Sucesso", "Usuário cadastrado com sucesso no nosso banco de dados", null,
						AlertType.CONFIRMATION);
				atualizarTabela();
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

	// Área Lista de contatos

	@FXML
	private Button delete;

	@FXML
	public void acaoDelete() {
		Integer id = table.getSelectionModel().getSelectedItem().getId();
		Contato c = dao.findById(id);
		int decisao = Alerts.showAlert("Confirmação",
				"Você tem certeza que deseja excluir o contato: " + c.getNome() + "? ",
				"Se deseja proceguir, presse 'OK', caso não apenas feche essa notificação.", AlertType.WARNING);
		if (decisao == 1) {
			dao.deleteById(id);
			atualizarTabela();
			Alerts.showAlert("Confirmação", "Contato deletado com sucesso", null, AlertType.INFORMATION);
		}
	}

	@FXML
	private TableView<Contato> table;
	private ObservableList<Contato> obsContatos;

	private void organizar(List<Contato> contatos) {
		contatos.sort((c1, c2) -> {
			return c1.getNome().compareTo(c2.getNome());
		});
	}

	private void atualizarTabela() {

		List<Contato> contatos = dao.getAll();
		obsContatos = FXCollections.observableArrayList(contatos);
		organizar(obsContatos);

		table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
		table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("numero"));
		table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
		table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("operadora"));
		table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("grupo"));

		table.setItems(obsContatos);
	}
	
	@FXML
	private Button abrirContatoButton;
	
	@FXML
	public void abrirContato() {
		System.out.println("Abrir contato");
		contatoSelecionado.setC((table.getSelectionModel().getSelectedItem()));
		page.show(PageType.UPDATE);
	}
	
	@FXML
	public void botaoFechar() {
		page.close(false);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		page = Page.createPage(null);
		contatoSelecionado = ContatoSelecionado.contatoSelecionado();
		System.out.println("ControlMainFrame: " + page);
		organizarCheckBox();
		atualizarTabela();
		
//		Thread verificaContatoSelecionado = new Thread() {
//			@Override
//			public void run() {
//				while (true) {
//					if(table.getSelectionModel().getSelectedItem() == null) {
//						abrirContatoButton.setDisable(true);
//					} else {
//						abrirContatoButton.setDisable(false);
//					}
//				}
//			}
//		};
//		
//		verificaContatoSelecionado.start();
		
		Limitacoes.LimitarTextoSoComNumeros(numero);
		Limitacoes.regularTamanhoDoQueFoiDigitado(numero, 12);
		Limitacoes.formatarEmail(email, verificarEmailLabel);
	}
}
