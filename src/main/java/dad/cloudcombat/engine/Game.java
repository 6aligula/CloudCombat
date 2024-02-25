package dad.cloudcombat.engine;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board playerBoard;
    private Board aiBoard;
    private Player player;
    private AI ai;
    private boolean isPlayerTurn;
    private List<Ship> playerShips;
    private List<Ship> aiShips;
    private GameState gameState;

    public Game() {
        // Inicialización del juego
        playerBoard = new Board(10); // Suponiendo que el tablero es de 10x10
        aiBoard = new Board(10);
        player = new Player();
        ai = new AI();
        isPlayerTurn = true; // o determinado por alguna lógica inicial
        gameState = GameState.SETUP; // Enum para representar el estado del juego, e.g., SETUP, IN_PROGRESS,
                                     // GAME_OVER

        // Aquí podrías inicializar los barcos, etc.
        initializeShips();
    }

    public List<Ship> getPlayerShips() {
        return new ArrayList<>(playerShips); // Devuelve una copia para evitar modificaciones externas
    }

    public List<Ship> getAIShips() {
        return new ArrayList<>(aiShips); // Devuelve una copia para evitar modificaciones externas
    }

    private void initializeShips() {
        // Definir los tamaños de los barcos, por ejemplo:
        int[] shipSizes = { 5, 4, 3, 3, 2 };

        playerShips = new ArrayList<>();
        aiShips = new ArrayList<>();

        for (int size : shipSizes) {
            // Crea barcos para el jugador y la IA
            playerShips.add(new Ship(size));
            aiShips.add(new Ship(size));
        }
    }

    // Método para colocar un barco en el tablero
    public void placeShip(Board board, Ship ship, Point start, boolean isHorizontal) {
        // Asume que shipSize es la longitud del barco
        int shipSize = ship.getSize(); // Asegúrate de tener un método getSize() en Ship que devuelva su tamaño

        for (int i = 0; i < shipSize; i++) {
            int x = start.getX() + (isHorizontal ? i : 0);
            int y = start.getY() + (isHorizontal ? 0 : i);

            // Verifica si la posición está dentro del tablero
            if (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize()) {
                // Coloca una parte del barco en el tablero
                board.setShipAt(x, y, ship);
                // Añade la coordenada al barco
                ship.addCoordinate(new Point(x, y));
            } else {
                throw new IllegalArgumentException("Ship placement is out of bounds.");
            }
        }
    }

    // Verifica el estado del juego después de cada acción
    private void checkGameState() {
        // Lógica para verificar si el juego ha terminado
    }

    // Getters y setters según sea necesario
    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getAIBoard() {
        return aiBoard;
    }

    public enum GameState {
        SETUP, // Cuando estás preparando el juego, por ejemplo, colocando barcos
        IN_PROGRESS, // El juego está en curso
        PLAYER_WIN, // El jugador ha ganado
        AI_WIN, // La IA ha ganado
        DRAW, // El juego ha terminado en empate (si es posible en tu juego)
        GAME_OVER // El juego ha terminado, sin importar el resultado
    }

    // Luego, puedes cambiar el estado del juego así:
    public void startGame() {
        gameState = GameState.IN_PROGRESS;
        // Otras inicializaciones para comenzar el juego...
    }

    public void endGame(boolean playerWon) {
        gameState = playerWon ? GameState.PLAYER_WIN : GameState.AI_WIN;
        // Otras limpiezas o acciones al final del juego...
    }

    // Y para verificar el estado del juego:
    public boolean isGameOver() {
        return gameState == GameState.GAME_OVER;
    }

    // Getters y setters para GameState si son necesarios
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    // Métodos para manejar los turnos de los jugadores
    // public void playerTurn(Point shot) {
    // // Suponiendo que shot es la posición a la que el jugador quiere disparar en
    // el
    // // tablero de la IA
    // if (!aiBoard.isShotAt(shot)) { // Necesitas implementar este método en Board
    // aiBoard.recordShot(shot); // Registra el disparo en el tablero de la IA
    // if (aiBoard.hasShipAt(shot.getX(), shot.getY())) {
    // // Acertó a un barco, manejar lógicamente el golpe
    // } else {
    // // Falló, manejar lógicamente el fallo
    // }
    // // Cambiar el turno al de la IA, si es necesario
    // isPlayerTurn = false;
    // // Verificar el estado del juego después del turno
    // checkGameState();
    // }
    // }

    // public void aiTurn() {
    // // La IA decide a dónde disparar en el tablero del jugador
    // // Puedes implementar una lógica simple aleatoria o una más compleja basada
    // en
    // // estrategia
    // Point aiShot = ai.decideShot(); // Necesitarías implementar decideShot() en
    // AI
    // if (!playerBoard.isShotAt(aiShot)) {
    // playerBoard.recordShot(aiShot);
    // if (playerBoard.hasShipAt(aiShot.getX(), aiShot.getY())) {
    // // La IA acertó, manejar lógicamente el golpe
    // } else {
    // // La IA falló, manejar lógicamente el fallo
    // }
    // // Cambiar el turno al jugador
    // isPlayerTurn = true;
    // // Verificar el estado del juego después del turno
    // checkGameState();
    // }
    // }

}
