package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Contato;
import entities.dao.ContatoDAO;
import entities.util.ContatoSelecionado;
import gui.admin.Page;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ViewControllerUpdate implements Initializable {

	private Page page;
	ContatoSelecionado contatoSelecionado;
	private final ContatoDAO dao = new ContatoDAO();

	@FXML
	private Button fechar;

	@FXML
	private Pane paneEsquerda;

	@FXML
	private Button delete;

	@FXML
	public void acaoDelete() {
		Integer id = contatoSelecionado.getC().getId();
		Contato c = dao.findById(id);
		int decisao = Alerts.showAlert("Confirmação",
				"Você tem certeza que deseja excluir o contato: " + c.getNome() + "? ",
				"Se deseja proceguir, presse 'OK', caso não apenas feche essa notificação.", AlertType.WARNING);
		if (decisao == 1) {
			dao.deleteById(id);
			Alerts.showAlert("Confirmação", "Contato deletado com sucesso", null, AlertType.CONFIRMATION);
			acaoFechar();
		}
	}

	@FXML
	public void acaoFechar() {
		limparCampos();
		page.close(true);
	}

	@FXML
	private Button editNome;

	@FXML
	public void acaoEditNome() {
		String novoNome = "";
		try {
			novoNome = Alerts.showAlertTextField(LabelNome.getText(), "Alterar nome", "Insira o novo valor", null,
					false);
		} catch (Exception e) {
		}
		if (novoNome.equals(LabelNome.getText())|| novoNome.equals("")) {
			Alerts.showAlert("Erro", "Nada mudou", null, AlertType.ERROR);
		} else {
			ContatoDAO dao = new ContatoDAO();
			Contato c = dao.findById(contatoSelecionado.getC().getId());
			c.setNome(novoNome);
			contatoSelecionado.setC(c);
			dao.update(c);
			preencherAcao();
			Alerts.showAlert("Sucesso", "Nome alterado com sucesso", null, AlertType.CONFIRMATION);
		}
	}

	@FXML
	private Button editNumero;

	@FXML
	public void acaoEditNumero() {
		String novoNum = "";
		try {
			novoNum = Alerts.showAlertTextField(labelNumero.getText(), "Alterar número", "Insira o novo valor", null,
					true);
		} catch (Exception e) {
		}
		if (novoNum.equals(labelNumero.getText()) || novoNum.equals("")) {
			Alerts.showAlert("Erro", "Nada mudou", null, AlertType.ERROR);
		} else {
			ContatoDAO dao = new ContatoDAO();
			Contato c = dao.findById(contatoSelecionado.getC().getId());
			c.setNumero(novoNum);
			contatoSelecionado.setC(c);
			dao.update(c);
			preencherAcao();
			Alerts.showAlert("Sucesso", "Número alterado com sucesso", null, AlertType.CONFIRMATION);
		}
	}

	@FXML
	private Button editEmail;

	@FXML
	public void acaoEditEmail() {
		String novoEmail = "";
		try {
			novoEmail = Alerts.showAlertTextField(labelEmail.getText(), "Alterar email", "Insira o novo valor", null,
					false);
		} catch (Exception e) {
		}
		if (novoEmail.equals(labelEmail.getText()) || novoEmail.equals("")) {
			Alerts.showAlert("Erro", "Nada mudou", null, AlertType.ERROR);
		} else {

			Matcher m = Pattern.compile("^\\S+@\\w+(\\.\\w+)+$").matcher(novoEmail);
			if (!m.find()) {
				Alerts.showAlert("Erro", "Formato de email inválido", null, AlertType.ERROR);
			} else {
				ContatoDAO dao = new ContatoDAO();
				Contato c = dao.findById(contatoSelecionado.getC().getId());
				c.setEmail(novoEmail);
				contatoSelecionado.setC(c);
				dao.update(c);
				preencherAcao();
				Alerts.showAlert("Sucesso", "Email alterado com sucesso", null, AlertType.CONFIRMATION);
			}
		}
	}

	@FXML
	private Button editOperadora;

	@FXML
	public void acaoEditOperadora() {
		List<String> operadoras = new ArrayList<>();
		operadoras.add("TIM");
		operadoras.add("VIVO");
		operadoras.add("CLARO");
		operadoras.add("OI");
		operadoras.add("OUTROS");

		String novaOperadora = "";
		try {
			novaOperadora = Alerts.showAlertComboBox(operadoras, "Alterar Operadora", "Insira o novo valor", null);
		} catch (Exception e) {
		}
		if (novaOperadora.equals(labelOperadora.getText())) {
			Alerts.showAlert("Erro", "Nada mudou", null, AlertType.ERROR);
		} else {
			ContatoDAO dao = new ContatoDAO();
			Contato c = dao.findById(contatoSelecionado.getC().getId());
			c.setOperadora(novaOperadora);
			contatoSelecionado.setC(c);
			dao.update(c);
			preencherAcao();
			Alerts.showAlert("Sucesso", "Operadora alterado com sucesso", null, AlertType.CONFIRMATION);
		}
	}

	@FXML
	private Button editGrupo;

	@FXML
	public void acaoEditGrupo() {
		List<String> grupos = new ArrayList<>();
		grupos.add("Família");
		grupos.add("Amigos");
		grupos.add("Trabalho");
		grupos.add("Outros");

		String novoGrupo = "";
		try {
			novoGrupo = Alerts.showAlertComboBox(grupos, "Alterar Grupo", "Insira o novo valor", null);
		} catch (Exception e) {
		}
		if (novoGrupo.equals(labelGrupo.getText())) {
			Alerts.showAlert("Erro", "Nada mudou", null, AlertType.ERROR);
		} else {
			ContatoDAO dao = new ContatoDAO();
			Contato c = dao.findById(contatoSelecionado.getC().getId());
			c.setGrupo(novoGrupo);
			contatoSelecionado.setC(c);
			dao.update(c);
			preencherAcao();
			Alerts.showAlert("Sucesso", "Grupo alterado com sucesso", null, AlertType.CONFIRMATION);
		}
	}

	@FXML
	private Label LabelNome;
	@FXML
	private Label labelNumero;
	@FXML
	private Label labelEmail;
	@FXML
	private Label labelOperadora;
	@FXML
	private Label labelGrupo;

	public void preencherAcao() {
		limparCampos();
		preencherCampos();
	}

	private void limparCampos() {
		LabelNome.setText("");
		labelNumero.setText("");
		labelEmail.setText("");
		labelOperadora.setText("");
		labelGrupo.setText("");
	}

	private synchronized void preencherCampos() {
		LabelNome.setText(contatoSelecionado.getC().getNome());
		labelNumero.setText(contatoSelecionado.getC().getNumero());
		labelEmail.setText(contatoSelecionado.getC().getEmail());
		labelOperadora.setText(contatoSelecionado.getC().getOperadora());
		labelGrupo.setText(contatoSelecionado.getC().getGrupo());
	}
	
	private void setLabels() {
		page.setLabelEmail(labelEmail);
		page.setLabelGrupo(labelGrupo);
		page.setLabelNome(LabelNome);
		page.setLabelNumero(labelNumero);
		page.setLabelOperadora(labelOperadora);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contatoSelecionado = ContatoSelecionado.contatoSelecionado();
		page = Page.createPage(null);
		setLabels();
	}
}
