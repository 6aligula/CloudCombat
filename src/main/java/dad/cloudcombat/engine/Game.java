package dad.cloudcombat.engine;

class Game {
    private Board playerBoard;
    private Board aiBoard;
    private AI ai;
    private boolean playerTurn;

    public Game() {
        playerBoard = new Board();
        aiBoard = new Board();
        ai = new AI(aiBoard);
        playerTurn = true;
        // Inicializar el juego, colocar barcos, etc.
    }

    public void playerMove(int x, int y) {
        // Lógica para el movimiento del jugador...
        playerTurn = false;
    }

    public void aiMove() {
        Point move = ai.makeMove();
        // Lógica para el movimiento de la IA...
        playerTurn = true;
    }

    // ... Más métodos para la lógica del juego
}
