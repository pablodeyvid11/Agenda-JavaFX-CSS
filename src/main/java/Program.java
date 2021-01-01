import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application{
	
	public static Stage mainStage;
	private static Scene MainFrame;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		Parent parentMainFrame = FXMLLoader.load(getClass().getResource("./gui/ViewMainFrame.fxml"));
		MainFrame = new Scene(parentMainFrame);
		MainFrame.getStylesheets().add(getClass().getResource("./gui/application.css").toExternalForm());
		mainStage.setScene(MainFrame);
		mainStage.setTitle("Main Frame");
		mainStage.setResizable(false);
		mainStage.show();
	}
}
