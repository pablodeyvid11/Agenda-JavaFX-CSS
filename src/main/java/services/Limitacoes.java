package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Limitacoes {
	public static void LimitarTextoSoComNumeros(TextField txt) {
		txt.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo != null && !valorNovo.matches("\\d*")) {
				txt.setText(valorAntigo);
			}
		});
	}
	
	
	public static void verificarEntradas(TextField nome, Label nomeAlerta, TextField dataNascimento, Label dataAlerta, TextField email, Label emailAlerta) {
		nome.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo.length() == 0) {
				nomeAlerta.setText("*");
			} else {
				nomeAlerta.setText("");
			}
		});
		dataNascimento.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo.length() == 0) {
				dataAlerta.setText("*");
			} else {
				dataAlerta.setText("");
			}
		});
		email.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo.length() == 0) {
				emailAlerta.setText("*");
			} else {
				emailAlerta.setText("");
			}
		});
	}
	
	
	public static void formatarEmail(TextField txt, Label label) {
		txt.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			String email = txt.getText();
			Matcher m = Pattern.compile("^\\S+@\\w+(\\.\\w+)+$").matcher(email);
			if(m.find()) {
				label.setText("");
			} else {
				label.setText("*");
			}
		});
	}
	
	public static void limitarFormatarData(TextField txt) {
		txt.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo.length() >= valorAntigo.length() && valorNovo.length() == 2) {
				txt.setText(valorAntigo + "/");
			}
			if (valorNovo.length() >= valorAntigo.length() && valorNovo.length() == 5) {
				txt.setText(valorAntigo + "/");
			}
		});
	}
	
	public static void regularTamanhoDoQueFoiDigitado(TextField txt, int tamanhoMaximo) {
		txt.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
			if (valorNovo != null && valorNovo.length() > tamanhoMaximo) {
				txt.setText(valorAntigo);
			}
		});
	}
}
