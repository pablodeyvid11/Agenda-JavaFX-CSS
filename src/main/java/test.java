import entities.dao.ContatoDAO;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class test {
	public static void main(String[] args) {
		int decisao = Alerts.showAlert("Confirma��o", "Voc� tem certeza que deseja excluir o contato? ",
				"Se deseja proceguir, presse 'OK', caso n�o apenas feche essa notifica��o.", AlertType.WARNING);
	}
}
