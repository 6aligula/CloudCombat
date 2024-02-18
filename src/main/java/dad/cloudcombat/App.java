package dad.cloudcombat;

import dad.cloudcombat.ui.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	MainController mainController;
	
	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		stage = primaryStage;
		mainController = new MainController();
		
		primaryStage.setTitle("CloudCombat");
		primaryStage.setScene(new Scene(mainController.getView()));
		primaryStage.show();

	}

}
