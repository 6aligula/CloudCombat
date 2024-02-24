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
			//System.out.println("Botón presionado: " + buttonId);
			
			// Determina si el botón pertenece al jugador o a la IA y actúa en consecuencia
			if (isPlayerButton(buttonId)) {
				System.out.println("Botón presionado: " + buttonId);
				// Lógica para el botón del jugador
				//processPlayerTurn(btn);
			} else {
				System.out.println("Botón presionado: " + buttonId);
				// Lógica para el botón de la IA
				//processAITurn(btn);
			}
		}
	}
	
	// Función auxiliar para verificar si el botón pertenece al jugador
	private boolean isPlayerButton(String buttonId) {
		return buttonId.startsWith("playerBtn");
	}
	
	// Procesa el turno del jugador
	private void processPlayerTurn(Button btn) {
		// Aquí tu lógica para cuando el jugador hace un movimiento
		// Por ejemplo, actualizar el modelo y la vista
	}
	
	// Procesa el turno de la IA (si la IA usa la interfaz para hacer movimientos)
	private void processAITurn(Button btn) {
		// Aquí tu lógica para cuando la IA hace un movimiento
		// Dependiendo del juego, puede que no necesites esta parte
	}
	
	// Método de ejemplo para determinar si el botón es parte de un barco
	private boolean esParteDelBarco(String buttonId) {
		// Implementa tu lógica aquí, por ejemplo:
		// return losBarcos.contains(buttonId);
		return false; // Sustituye esto por tu lógica real
	}

}
