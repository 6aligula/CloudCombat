package dad.cloudcombat.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import dad.cloudcombat.engine.Board;
import dad.cloudcombat.engine.Game;
import dad.cloudcombat.engine.Point;
import dad.cloudcombat.engine.Ship;

public class GameController implements Initializable {

	private EventHandler<ActionEvent> onBack;

	private Game game;

	@FXML
	private VBox view;
	// Referencias a los GridPanes o componentes de la UI
	@FXML
	private GridPane playerGrid;
	@FXML
	private GridPane aiGrid;

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
		try {
			// Lógica del método
			onBack.handle(event);
		} catch (Exception e) {
			e.printStackTrace(); // O usa un logger si prefieres
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public VBox getView() {
		return view;
	}

	public void setOnBack(EventHandler<ActionEvent> onBack) {
		try {
			// Lógica del método
			this.onBack = onBack;
		} catch (Exception e) {
			e.printStackTrace(); // O usa un logger si prefieres
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initializing GameController");
		System.out.println("playerGrid: " + playerGrid);
		System.out.println("aiGrid: " + aiGrid);
		game = new Game();
		// Coloca los barcos en el tablero, etc.
		placePlayerShips();
		placeAIShips();
		updateBoardView();
	}

	private void placePlayerShips() {
		// Ejemplo de colocación manual de barcos
		List<Ship> ships = game.getPlayerShips();
		// Definir las posiciones y orientaciones manualmente
		int[][] positions = { { 0, 0 }, { 2, 2 }, { 4, 4 }, { 6, 6 }, { 8, 8 } }; // Ejemplo de posiciones
		boolean[] orientations = { false, true, false, true, false }; // false para horizontal en el tablero, false para
																		// vertical en el tablero
		for (int i = 0; i < ships.size(); i++) {
			Ship ship = ships.get(i);
			int x = positions[i][0];
			int y = positions[i][1];
			boolean isHorizontal = orientations[i];

			try {
				game.placeShip(game.getPlayerBoard(), ship, new Point(x, y), isHorizontal);
				System.out.println("Barco colocado manualmente: " + ship + " en (" + x + ", " + y + ") "
						+ (isHorizontal ? "Horizontal" : "Vertical"));
			} catch (IllegalArgumentException e) {
				System.out.println("Error al colocar barco manualmente en (" + x + ", " + y + ") "
						+ (isHorizontal ? "Horizontal" : "Vertical"));
			}
		}
	}

	// método para colocar un barco en el tablero de la IA
	private void placeAIShips() {
		// Definir las posiciones y orientaciones manualmente para la IA
		// Esto es solo un ejemplo, puedes elegir las posiciones que quieras
		int[][] aiPositions = { { 1, 1 }, { 3, 3 }, { 5, 5 }, { 7, 6 }, { 9, 8 } };
		boolean[] aiOrientations = { false, true, false, true, false }; // false para horizontal, true para vertical

		List<Ship> aiShips = game.getAIShips();
		for (int i = 0; i < aiShips.size(); i++) {
			Ship ship = aiShips.get(i);
			int x = aiPositions[i][0];
			int y = aiPositions[i][1];
			boolean isHorizontal = aiOrientations[i];

			try {
				game.placeShip(game.getAIBoard(), ship, new Point(x, y), isHorizontal);
				// No necesitas imprimir esto a menos que estés depurando la colocación de la IA
				System.out.println("Barco de IA colocado manualmente: " + ship + " en (" + x + ", " + y + ") "
						+ (isHorizontal ? "Horizontal" : "Vertical"));
			} catch (IllegalArgumentException e) {
				System.out.println("Error al colocar barco de IA manualmente en (" + x + ", " + y + ") "
						+ (isHorizontal ? "Horizontal" : "Vertical"));
				// En un escenario real, podrías querer manejar este error de forma diferente
			}
		}
	}

	private void updateBoardView() {
		try {
			if (playerGrid == null) {
				System.out.println("playerGrid is null al inicio de updateBoardView!");
				return; // Sale del método si playerGrid es null para evitar NullPointerException
			} else {
				// System.out.println("llega aqui? ");
				String buttonId = "playerBtn";
				// System.out.println("playerGrid: " + buttonId);
				Button btn = (Button) playerGrid.lookup("#" + buttonId);
				// Actualizar el tablero del jugador
				for (int i = 0; i < game.getPlayerBoard().getSize(); i++) {
					for (int j = 0; j < game.getPlayerBoard().getSize(); j++) {
						buttonId = "playerBtn" + i + j;

						btn = (Button) playerGrid.lookup("#" + buttonId);
						if (btn != null && game.getPlayerBoard().hasShipAt(i, j)) {
							// Cambia el estilo del botón para indicar la presencia del barco
							btn.setStyle("-fx-background-color: Green;");
							System.out.println("Barco visualmente actualizado en " + i + ", " + j);
						}
					}
				}
			}
			// Resto de tu lógica para updateBoardView...
		} catch (Exception e) {
			System.out.println("Intento fallido de colocar barco del Player ");
			e.printStackTrace(); // Captura cualquier otra excepción inesperada.
		}

		try {
			if (aiGrid == null) {
				System.out.println("aiGrid is null al inicio de updateBoardView!");
				return; // Sale del método si aiGrid es null para evitar NullPointerException
			} else {
				String buttonId = "btn";
				// System.out.println("aiGrid: " + buttonId);
				Button btn = (Button) aiGrid.lookup("#" + buttonId);
				// Actualizar el tablero del jugador
				for (int i = 0; i < game.getAIBoard().getSize(); i++) {
					for (int j = 0; j < game.getAIBoard().getSize(); j++) {
						buttonId = "btn" + i + j;

						btn = (Button) aiGrid.lookup("#" + buttonId);
						if (btn != null && game.getAIBoard().hasShipAt(i, j)) {
							// Cambia el estilo del botón para indicar la presencia del barco
							btn.setStyle("-fx-background-color: Green;");
							System.out.println("Barco visualmente actualizado en " + i + ", " + j);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Intento fallido de colocar barco de la IA");
			e.printStackTrace(); // Captura cualquier otra excepción inesperada.
		}

	
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		try {
			// Lógica del método
			if (event.getSource() instanceof Button) {
				Button btn = (Button) event.getSource();
				String buttonId = btn.getId();
				// System.out.println("Botón presionado: " + buttonId);

				// Determina si el botón pertenece al jugador o a la IA y actúa en consecuencia
				if (isPlayerButton(buttonId)) {
					System.out.println("Botón presionado: " + buttonId);
					// Lógica para el botón del jugador
					// processPlayerTurn(btn);
				} else {
					System.out.println("Botón presionado: " + buttonId);
					// Lógica para el botón de la IA
					// processAITurn(btn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // O usa un logger si prefieres
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
