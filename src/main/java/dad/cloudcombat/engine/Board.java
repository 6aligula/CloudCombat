package dad.cloudcombat.engine;

public class Board {
    private Ship[][] ships;
    private boolean[][] hits;
    private int size;

    public Board(int size) {
        this.size = size;
        ships = new Ship[size][size];
        hits = new boolean[size][size];
    }

    // Devuelve el tamaño del tablero
    public int getSize() {
        return size;
    }

    // Coloca un barco en la posición (x, y) del tablero
    public void setShipAt(int x, int y, Ship ship) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }

        // Aquí puedes incluir más lógica para asegurarte de que el barco no se solape
        // con otros,
        // o para validar que la posición es correcta según las reglas de tu juego.

        ships[x][y] = ship;
    }
    public boolean hasShipAt(int x, int y) {
        return ships[x][y] != null;
    }
    // Métodos para colocar barcos, realizar disparos, etc.
}
