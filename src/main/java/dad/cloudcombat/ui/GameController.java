package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {

	private EventHandler<ActionEvent> onBack;

	@FXML
	private VBox view;

	public GameController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	void onGoingBackToMenuAction(ActionEvent event) {
		onBack.handle(event);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public VBox getView() {
		return view;
	}

	public void setOnBack(EventHandler<ActionEvent> onBack) {
		this.onBack = onBack;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		if (event.getSource() instanceof Button) {
			Button btn = (Button) event.getSource();
			String buttonId = btn.getId();
			System.out.println("Botón presionado: " + buttonId);
			// Aquí puedes agregar más lógica basada en el ID del botón
		}

		// Aquí puedes agregar más lógica basada en el ID del botón
        // Por ejemplo, verificar si esa posición contiene una parte del barco
        /*if (esParteDelBarco(buttonId)) {
            btn.setStyle("-fx-background-color: #ff0000;"); // Marcar como golpeado
            System.out.println("¡Tocado!");
        } else {
            btn.setStyle("-fx-background-color: #bfbfbf;"); // Marcar como agua
            System.out.println("Agua.");
        }*/
		
	}
	// Método de ejemplo para determinar si el botón es parte de un barco
	private boolean esParteDelBarco(String buttonId) {
		// Implementa tu lógica aquí, por ejemplo:
		// return losBarcos.contains(buttonId);
		return false; // Sustituye esto por tu lógica real
	}

}
