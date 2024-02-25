package dad.cloudcombat.engine;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int size;
    private List<Point> coordinates;
    private List<Point> hits; // Para rastrear las partes del barco que han sido golpeadas

    public Ship(int size) {
        this.size = size;
        this.coordinates = new ArrayList<>();
        this.hits = new ArrayList<>();
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
        if (!coordinates.contains(point)) {
            coordinates.add(point);
        }
    }

    public boolean isAtPosition(int x, int y) {
        for (Point point : coordinates) {
            if (point.getX() == x && point.getY() == y) {
                return true;
            }
        }
        return false;
    }

    // Registra un golpe al barco
    public void registerHit(Point point) {
        if (isAtPosition(point.getX(), point.getY()) && !hits.contains(point)) {
            hits.add(point);
        }
    }

    // Verifica si el barco ha sido completamente hundido
    public boolean isSunk() {
        return hits.size() == coordinates.size();
    }

    // Verifica si una parte específica del barco ha sido golpeada
    public boolean isPartHit(Point point) {
        return hits.contains(point);
    }
}
