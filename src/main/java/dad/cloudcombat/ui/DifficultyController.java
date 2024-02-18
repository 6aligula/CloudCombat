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

public class DifficultyController implements Initializable {
	

	private EventHandler<ActionEvent> onEasyMode;

	
	@FXML
    private VBox view;

    
    public DifficultyController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DifficultyView.fxml"));
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
	void onSelectEasyDifficultyAction(ActionEvent event) {
		onEasyMode.handle(event);
	}
	
	@FXML
	void onSelectStandardDifficultyAction(ActionEvent event) {
		
	}
	
	public VBox getView() {
		return view;
	}

	public void setOnEasy(EventHandler<ActionEvent> onEasyMode) {
		this.onEasyMode = onEasyMode;
		
	}

}
