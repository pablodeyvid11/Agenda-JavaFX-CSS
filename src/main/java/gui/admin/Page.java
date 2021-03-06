package gui.admin;

import java.io.IOException;

import entities.Contato;
import entities.util.ContatoSelecionado;
import gui.admin.enums.PageType;
import gui.util.Alerts;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Page {

	private static Page page;

	private Stage mainStage;

	private static Scene MainFrame;
	private static Scene updateScene;

	private Label LabelNome;
	private Label labelNumero;
	private Label labelEmail;
	private Label labelOperadora;
	private Label labelGrupo;

	private Page(Stage primaryStage) {
		if (primaryStage != null) {
			this.mainStage = primaryStage;
		}
	}

	public void carregar() {
		try {
			carregarMainFrame();
			carregarUpdate();
			carregarMainStage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void carregarContatoSelecionado() {
		ContatoSelecionado c = ContatoSelecionado.contatoSelecionado();
		c.setC(new Contato(0, "", "", "", "", ""));
	}

	public static Page createPage(Stage primaryStage) {
		if (page == null && primaryStage != null) {
			carregarContatoSelecionado();
			page = new Page(primaryStage);
		}
		return page;
	}

	private void carregarMainStage() throws IOException {
		mainStage.setScene(MainFrame);
		mainStage.setTitle("Main Frame");
		mainStage.setResizable(false);

		mainStage.initStyle(StageStyle.UNDECORATED);
		mainStage.show();
	}

	public void hide() {
		mainStage.hide();
	}

	public void close(boolean mandatory) {
		int escolha = 0;
		if (mandatory) {
			escolha = 1;
		} else {
			escolha = Alerts.showAlert("Confirmação", "Tem certeza que deseja sair? ",
					"Pressione OK para sair, caso queira continuar nessa tela, pressione Cancell ou feche essa notificação",
					AlertType.CONFIRMATION);
		}
		if (escolha == 1) {
			if (mainStage.getScene().equals(MainFrame)) {
				Platform.exit();
				System.exit(0);
			} else {
				try {
					mainStage.close();
					carregarMainFrame();
					mainStage.setScene(MainFrame);
					mainStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void carregarUpdate() throws IOException {
		Parent parentUpdate = FXMLLoader.load(getClass().getResource("../ViewUpdate.fxml"));
		updateScene = new Scene(parentUpdate);
		updateScene.getStylesheets().add(getClass().getResource("../styles/style.css").toExternalForm());
	}

	public void carregarMainFrame() throws IOException {
		Parent parentMainFrame = FXMLLoader.load(getClass().getResource("../ViewMainFrame.fxml"));
		MainFrame = new Scene(parentMainFrame);
		MainFrame.getStylesheets().add(getClass().getResource("../styles/style.css").toExternalForm());
	}

	public void show(PageType pt) {
		System.out.println(mainStage.getScene());
		mainStage.close();
		if (pt.equals(PageType.MAIN)) {
			if (!mainStage.getScene().equals(MainFrame)) {
				mainStage.setScene(MainFrame);
			}
		} else if (pt.equals(PageType.UPDATE)) {
			if (!mainStage.getScene().equals(updateScene)) {
				mainStage.setScene(updateScene);
			}
		}
		mainStage.show();
	}

	public Label getLabelNome() {
		return LabelNome;
	}

	public void setLabelNome(Label labelNome) {
		LabelNome = labelNome;
	}

	public Label getLabelNumero() {
		return labelNumero;
	}

	public void setLabelNumero(Label labelNumero) {
		this.labelNumero = labelNumero;
	}

	public Label getLabelEmail() {
		return labelEmail;
	}

	public void setLabelEmail(Label labelEmail) {
		this.labelEmail = labelEmail;
	}

	public Label getLabelOperadora() {
		return labelOperadora;
	}

	public void setLabelOperadora(Label labelOperadora) {
		this.labelOperadora = labelOperadora;
	}

	public Label getLabelGrupo() {
		return labelGrupo;
	}

	public void setLabelGrupo(Label labelGrupo) {
		this.labelGrupo = labelGrupo;
	}
}
