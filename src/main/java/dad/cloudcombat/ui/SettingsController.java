package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXToggleButton;

import dad.cloudcombat.engine.Music;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SettingsController implements Initializable {

	private Music menuMusic;
	private Music gameMusic;

	// actions

	private EventHandler<ActionEvent> onSaveChanges;
	private EventHandler<ActionEvent> onBack;

	@FXML
	private Slider fxSlider;

	@FXML
	private JFXToggleButton fxToggle;

	@FXML
	private Slider generalSlider;

	@FXML
	private JFXToggleButton generalToggle;

	@FXML
	private ComboBox<String> songCombo;

	@FXML
	private Slider songSlider;

	@FXML
	private JFXToggleButton songToggle;

	@FXML
	private ComboBox<?> themeCombo;

	@FXML
	private VBox view;

	public SettingsController(Music menuMusic, Music gameMusic) {

		this.menuMusic = menuMusic;
		this.gameMusic = gameMusic;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//COMBO BOX 
		songCombo.getItems().addAll("Canción 1", "Canción 2", "Canción 3");
		songCombo.setValue("Canción 3");

		songCombo.setOnAction(e -> {
			String selectedSong = songCombo.getSelectionModel().getSelectedItem();
			switch (selectedSong) {
			case "Canción 1":
				gameMusic.changeSong("/music/GameSong1.mp3");
				break;
			case "Canción 2":
				gameMusic.changeSong("/music/GameSong2.mp3");
				break;
			case "Canción 3":
				gameMusic.changeSong("/music/GameSong3.mp3");
				break;
			}
			gameMusic.setPlayOnSelect(true);
		});

		// SLIDER MUSICA
		songSlider.setValue(menuMusic.getVolume());
		songSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			menuMusic.setVolume(newValue.doubleValue());
			gameMusic.setVolume(newValue.doubleValue());
		});

		generalSlider.valueProperty().bindBidirectional(songSlider.valueProperty());

		generalSlider.setValue(5);

		// VINCULANDO TOGGLES A LOS SLIDERS
		fxSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.doubleValue() == fxSlider.getMin()) {
				fxToggle.setSelected(false);
			} else {
				fxToggle.setSelected(true);
			}
		});

		generalSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.doubleValue() == generalSlider.getMin()) {
				generalToggle.setSelected(false);
			} else {
				generalToggle.setSelected(true);
			}
		});

		songSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.doubleValue() == songSlider.getMin()) {
				songToggle.setSelected(false);
			} else {
				songToggle.setSelected(true);
			}
		});

		fxToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				fxSlider.setValue(fxSlider.getMax());
			} else {
				fxSlider.setValue(fxSlider.getMin());
			}
		});

		generalToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				generalSlider.setValue(generalSlider.getMax());
			} else {
				generalSlider.setValue(generalSlider.getMin());
			}
		});

		songToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				songSlider.setValue(songSlider.getMax());
			} else {
				songSlider.setValue(songSlider.getMin());

			}
		});

	}

	@FXML
	void onSaveChangesAction(ActionEvent event) {
		onSaveChanges.handle(event);
	}

	@FXML
	void onBackToMenuAction(ActionEvent event) {
		fxSlider.setValue(5);
		generalSlider.setValue(5);
		songSlider.setValue(5);
		gameMusic.changeSong("/music/GameSong3.mp3");

		onBack.handle(event);
	}

	public VBox getView() {
		return view;
	}

	public void setOnSaveChanges(EventHandler<ActionEvent> onSaveChanges) {
		this.onSaveChanges = onSaveChanges;
	}

	public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

}
