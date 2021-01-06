import gui.admin.Page;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	private Page page;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		page = Page.createPage(primaryStage);
		page.carregar();
	}
}
