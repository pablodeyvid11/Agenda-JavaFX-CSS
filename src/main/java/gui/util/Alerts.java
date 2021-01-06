package gui.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

	public static int showAlert(String titulo, String cabecalho, String conteudo, AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);

		Optional<ButtonType> result = alert.showAndWait();
		try {
			if (result.get() == ButtonType.OK) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static void showUpdateAlert() {
		
	}
}