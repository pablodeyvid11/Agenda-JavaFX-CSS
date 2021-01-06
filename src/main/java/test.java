import entities.dao.ContatoDAO;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class test {
	public static void main(String[] args) {
		int decisao = Alerts.showAlert("Confirmação", "Você tem certeza que deseja excluir o contato? ",
				"Se deseja proceguir, presse 'OK', caso não apenas feche essa notificação.", AlertType.WARNING);
	}
}
