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
		updateBoardView();
	}

	private void placePlayerShips() {
		// Ejemplo de colocación automática de barcos
		List<Ship> ships = game.getPlayerShips();
		for (Ship ship : ships) {
			boolean placed = false;
			while (!placed) {
				int x = (int) (Math.random() * game.getPlayerBoard().getSize());
				int y = (int) (Math.random() * game.getPlayerBoard().getSize());
				boolean isHorizontal = Math.random() < 0.5;

				// Intenta colocar el barco en el tablero
				try {
					game.placeShip(game.getPlayerBoard(), ship, new Point(x, y), isHorizontal);
					placed = true;
					System.out.println("Barco colocado: " + ship + " en (" + x + ", " + y + ") " + (isHorizontal ? "Horizontal" : "Vertical"));
					System.out.println("Coordenadas del barco: " + ship.getCoordinates().toString());
				} catch (IllegalArgumentException e) {
					// Si el intento de colocación falla, intenta de nuevo
				}
			}
		}
	}

	private void updateBoardView() {
		try {
			if (playerGrid == null) {
				System.out.println("playerGrid is null al inicio de updateBoardView!");
				return; // Sale del método si playerGrid es null para evitar NullPointerException
			} else {
				System.out.println("llega aqui? ");
				String buttonId = "playerBtn";
				System.out.println("playerGrid: " + buttonId);	
				Button btn = (Button) playerGrid.lookup("#" + buttonId);	
				// Actualizar el tablero del jugador
				for (int i = 0; i < game.getPlayerBoard().getSize(); i++) {
					for (int j = 0; j < game.getPlayerBoard().getSize(); j++) {
						 buttonId = "playerBtn" + i + j;

						 btn = (Button) playerGrid.lookup("#" + buttonId);
						if (btn != null && game.getPlayerBoard().hasShipAt(i, j)) {
							// Cambia el estilo del botón para indicar la presencia del barco
							btn.setStyle("-fx-background-color: red;");
							System.out.println("Barco visualmente actualizado en " + i + ", " + j);
						}
					}
				}
			}
			// Resto de tu lógica para updateBoardView...
		} catch (Exception e) {
			System.out.println("Intento fallido de colocar barco en ");
			e.printStackTrace(); // Captura cualquier otra excepción inesperada.
		}

		// Similarmente, podrías querer actualizar el tablero de la IA para reflejar los
		// disparos hechos por el jugador
		// Esto dependerá de cómo quieras mostrar la información en la interfaz de
		// usuario
		// for (int i = 0; i < game.getAIBoard().getSize(); i++) {
		// 	for (int j = 0; j < game.getAIBoard().getSize(); j++) {
		// 		String buttonId = "btn" + i + j;
		// 		Button btn = (Button) aiGrid.lookup("#" + buttonId);
		// 		if (btn != null) {
		// 			// Suponiendo que quieras marcar los disparos hechos al tablero de la IA
		// 			// Aquí necesitarás una lógica para decidir el color basado en si fue un
		// 			// acierto, un fallo, etc.
		// 			// Por ejemplo, si el disparo fue un acierto:
		// 			// if (game.getAIBoard().isHitAt(i, j)) {
		// 			// btn.setStyle("-fx-background-color: red;");
		// 			// } else if (game.getAIBoard().wasShotAt(i, j)) {
		// 			// btn.setStyle("-fx-background-color: grey;");
		// 			// }
		// 		}
		// 	}
		// }
	}

	// public void placeShip(Board board, Ship ship, Point start, boolean isHorizontal) {
	// 	for (int i = 0; i < ship.getCoordinates().size(); i++) {
	// 		int x = start.getX() + (isHorizontal ? i : 0);
	// 		int y = start.getY() + (isHorizontal ? 0 : i);

	// 		// Asegúrate de que las coordenadas están dentro del tablero
	// 		if (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize()) {
	// 			// Coloca una parte del barco en el tablero
	// 			board.setShipAt(x, y, ship);
	// 			// Añade la coordenada al barco
	// 			ship.addCoordinate(new Point(x, y));
	// 		} else {
	// 			// Manejar el error, por ejemplo, lanzando una excepción o devolviendo false
	// 		}
	// 	}
	// }

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
