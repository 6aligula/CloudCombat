package dad.cloudcombat.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
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
        boolean playerShipsAllHit = areAllShipsSunk(playerBoard, playerShips);
        boolean aiShipsAllHit = areAllShipsSunk(aiBoard, aiShips);
    
        if (playerShipsAllHit && !aiShipsAllHit) {
            gameState = GameState.AI_WIN;
            System.out.println("La IA ha ganado el juego.");
        } else if (!playerShipsAllHit && aiShipsAllHit) {
            gameState = GameState.PLAYER_WIN;
            System.out.println("El jugador ha ganado el juego.");
        } else if (playerShipsAllHit) {
            gameState = GameState.DRAW; // Este caso puede ser poco común y depende de las reglas específicas del juego
            System.out.println("El juego ha terminado en empate.");
        } else {
            // El juego continúa
            return;
        }
    
        // Si se llega a este punto, el juego ha terminado
        //endGame(); // Puedes definir este método para realizar cualquier limpieza final o preparación para un nuevo juego
    }
    // Método para verificar si todos los barcos han sido hundidos
    private boolean areAllShipsSunk(Board board, List<Ship> ships) {
        for (Ship ship : ships) {
            for (Point point : ship.getCoordinates()) {
                if (!board.isShotAt(point.getX(), point.getY())) {
                    return false; // Si cualquier parte del barco no ha sido golpeada, el barco no está hundido
                }
            }
        }
        return true; // Todos los barcos han sido hundidos
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
    public void playerTurn(Point shot) {
        if (!isPlayerTurn || isGameOver()) {
            System.out.println("No es el turno del jugador o el juego ha terminado.");
            return; // O manejar de otra manera
        }

        if (aiBoard.isShotAt(shot.getX(), shot.getY())) {
            System.out.println("Ya has disparado a esta posición.");
            return; // O manejar de otra manera
        }

        aiBoard.recordShot(shot.getX(), shot.getY());
        if (aiBoard.hasShipAt(shot.getX(), shot.getY())) {
            System.out.println("¡Acertaste un barco de la IA!");
            // Aquí puedes agregar lógica para verificar si el barco ha sido hundido
            // completamente.
        } else {
            System.out.println("Fallaste.");
        }

        isPlayerTurn = false; // Cambia el turno a la IA
        checkGameState(); // Verifica el estado del juego para ver si continúa o termina
    }

    public void aiTurn() {
        if (isPlayerTurn || isGameOver()) {
            System.out.println("No es el turno de la IA o el juego ha terminado.");
            return; // O manejar de otra manera
        }

        // Implementación simple de selección aleatoria para el disparo de la IA
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(playerBoard.getSize());
            y = random.nextInt(playerBoard.getSize());
        } while (playerBoard.isShotAt(x, y)); // Repite hasta encontrar una posición no disparada

        playerBoard.recordShot(x, y);
        if (playerBoard.hasShipAt(x, y)) {
            System.out.println("La IA acertó uno de tus barcos!");
            // Aquí puedes agregar lógica para verificar si el barco ha sido hundido
            // completamente.
        } else {
            System.out.println("La IA falló.");
        }

        isPlayerTurn = true; // Cambia el turno al jugador
        checkGameState(); // Verifica el estado del juego para ver si continúa o termina
    }

}
