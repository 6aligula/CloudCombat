package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {


	// actions

	private EventHandler<ActionEvent> onStartGame;
	private EventHandler<ActionEvent> onSettings;
	private EventHandler<ActionEvent> onExit;

	@FXML
	private VBox view;

	public MenuController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void onStartGameAction(ActionEvent event) {
		onStartGame.handle(event);
	}

	@FXML
	void onExitAction(ActionEvent event) {
		onExit.handle(event);
	}

	@FXML
	void onSettingsAction(ActionEvent event) {
		onSettings.handle(event);

	}

	public VBox getView() {
		return view;
	}

	public void setOnStartGame(EventHandler<ActionEvent> onStartGame) {
		this.onStartGame = onStartGame;
	}

	public void setOnSettings(EventHandler<ActionEvent> onSettings) {
		this.onSettings = onSettings;
	}
	
	public void setOnExit(EventHandler<ActionEvent> onExit) {
		this.onExit = onExit;
	}

}
