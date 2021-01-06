package gui;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Contato;
import entities.dao.ContatoDAO;
import entities.util.ContatoSelecionado;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import services.Page;

public class ViewControllerUpdate implements Initializable{
	
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
		page.close(true);
	}

	@FXML
	private Button editNome;
	@FXML
	private Button editNumero;
	@FXML
	private Button editEmail;
	@FXML
	private Button editOperadora;
	@FXML
	private Button editGrupo;
	
	
	@FXML
	private Label LabelNomePrincipal;
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
	
	
	private void preencherCampos() {
		LabelNomePrincipal.setText(contatoSelecionado.getC().getNome());
		LabelNome.setText(contatoSelecionado.getC().getNome());
		labelNumero.setText(contatoSelecionado.getC().getNumero());
		labelEmail.setText(contatoSelecionado.getC().getEmail());
		labelOperadora.setText(contatoSelecionado.getC().getOperadora());
		labelGrupo.setText(contatoSelecionado.getC().getGrupo());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contatoSelecionado = ContatoSelecionado.contatoSelecionado();
		preencherCampos();
		page = Page.createPage(null);
	}
}
