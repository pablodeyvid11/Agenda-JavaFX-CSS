package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
		
		System.out.println(contatoSelecionado.getC());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contatoSelecionado = ContatoSelecionado.contatoSelecionado();
		
		Thread verificarContatoSelecionado = new Thread() {
			@Override
			public void run() {
				while (true) {
					String nome = contatoSelecionado.getC().getNome();
					String numero = contatoSelecionado.getC().getNome();
					String email = contatoSelecionado.getC().getEmail();
					String operadora = contatoSelecionado.getC().getOperadora();
					String grupo = contatoSelecionado.getC().getGrupo();
					
					while (true) {
						//System.out.println(contatoSelecionado.getC());
						if(
								nome.equals(contatoSelecionado.getC().getNome()) && 
								numero.equals(contatoSelecionado.getC().getNumero()) &&
								email.equals(contatoSelecionado.getC().getEmail())  &&
								operadora.equals(contatoSelecionado.getC().getOperadora()) &&
								grupo.equals(contatoSelecionado.getC().getGrupo()) 
						) {} else {
							preencherCampos();
							break;
						}
					}
				}
			}
		};
		
		verificarContatoSelecionado.start();
		
		preencherCampos();
		page = Page.createPage(null);
	}
}
