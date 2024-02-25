package dad.cloudcombat.engine;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int size;
    private List<Point> coordinates;
    //private boolean isSunk;

    public Ship(int size) {
        this.size = size;
        this.coordinates = new ArrayList<>();
        //this.isSunk = false;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public List<Point> getCoordinates() {
        return coordinates;
    }

    public void addCoordinate(Point point) {
        coordinates.add(point);
    }

    public boolean isAtPosition(int x, int y) {
        return coordinates.contains(new Point(x, y));
    }

    // Métodos para agregar puntos, verificar si está hundido, etc.
}
