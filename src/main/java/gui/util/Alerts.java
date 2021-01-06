package gui.util;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

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

	public static String showAlertTextField(String valorAntigo, String titulo, String cabecalho, String conteudo,
			Boolean isNumerico) throws Exception {
		TextInputDialog dialog = new TextInputDialog(valorAntigo);
		if (isNumerico) {
			Limitacoes.LimitarTextoSoComNumeros(dialog.getEditor());
			Limitacoes.regularTamanhoDoQueFoiDigitado(dialog.getEditor(), 12);
		}
		dialog.setTitle(titulo);
		dialog.setHeaderText(cabecalho);
		dialog.setContentText(conteudo);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new Exception("Erro");
		}
	}

	public static String showAlertComboBox(List<String> choices, String titulo, String cabecalho, String conteudo)
			throws Exception {

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle(titulo);
		dialog.setHeaderText(cabecalho);
		dialog.setContentText(conteudo);

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new Exception("Erro");
		}
	}
}