package dad.cloudcombat.engine;

public class AI {
    private Board board;
    
    public AI() {
        // Inicialización de la IA
    }   
    public AI(Board board) {
        this.board = board;
        // Colocar barcos aleatoriamente en el tablero...
    }

    public Point makeMove() {
        // Generar y retornar un movimiento aleatorio...
        return new Point(0, 0); // Por ahora, siempre ataca en la esquina superior izquierda    
    }
    // ... Más lógica de la IA
}
