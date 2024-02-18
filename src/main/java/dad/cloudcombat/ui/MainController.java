package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.cloudcombat.App;
import dad.cloudcombat.engine.Music;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	
	private Music menuMusic = new Music("/music/MenuSong.mp3");
	private Music gameMusic = new Music("/music/GameSong3.mp3");
	
	//controllers
	
	private MenuController menuController;
	private SettingsController settingsController;
	private DifficultyController difficultyController;
	private GameController gameController;
	
	@FXML
    private BorderPane view;
	
	 public MainController() {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
				loader.setController(this);
				loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		menuController = new  MenuController();
		settingsController = new SettingsController(menuMusic, gameMusic);
		difficultyController = new DifficultyController();
		gameController = new GameController();
		
		view.setCenter(menuController.getView());
		menuMusic.play();
		
		
		menuController.setOnStartGame(e -> {
			view.setCenter(difficultyController.getView());
		});
		
		menuController.setOnSettings(e -> {
			view.setCenter(settingsController.getView());
		});
		
		menuController.setOnExit(e -> {
			 App.stage.close();
			  
		});
		
		settingsController.setOnSaveChanges(e -> {
			view.setCenter(menuController.getView());
		});
		
		settingsController.setOnBack(e -> {
			view.setCenter(menuController.getView());
		});
		
		difficultyController.setOnEasy(e -> {
			view.setCenter(gameController.getView());
			menuMusic.stop();
			gameMusic.play();
			
		});
		
		gameController.setOnBack(e -> {
            view.setCenter(menuController.getView());
            gameMusic.stop();
            menuMusic.play();
            gameMusic.setPlayOnSelect(false); 
        });
		
	}
	
	
	
	public BorderPane getView() {
		return view;
	}

}
