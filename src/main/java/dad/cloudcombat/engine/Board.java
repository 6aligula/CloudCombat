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

    public int getSize() {
        return size;
    }
    //Obtiene el barco en la posición dada.
    public void setShipAt(int x, int y, Ship ship) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        if (ships[x][y] != null) {
            throw new IllegalArgumentException("Position already occupied by another ship");
        }
        ships[x][y] = ship;
    }
    //Verifica si hay un barco en la posición dada.
    public boolean hasShipAt(int x, int y) {
        return ships[x][y] != null;
    }
    //Verifica si ya se ha disparado a la posición dada.
    public boolean isShotAt(int x, int y) {
        return hits[x][y];
    }
    //Registra un disparo en la posición dada.
    public void recordShot(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        hits[x][y] = true; // Marca el disparo en el tablero
        
        // Si hay un barco en esta posición, registra el golpe en el barco
        Ship ship = ships[x][y];
        if (ship != null) {
            ship.registerHit(new Point(x, y));
        }
    }
    

    // Método para determinar si el disparo acertó a un barco
    public boolean isHit(int x, int y) {
        return hasShipAt(x, y) && isShotAt(x, y);
    }
}
