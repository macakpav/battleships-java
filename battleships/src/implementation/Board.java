/**
 * 
 */
package implementation;

import java.util.ArrayList;

import application.BoardSetup;

/**
 * @author macakpav
 *
 */
public class Board {
    private final ArrayList<Tile> tiles; // array holding different Tiles
    private int sizeX; // size of board in horizontal direction
    private int sizeY; // size of board in vertical direction
    private int noTiles; // number of tiles (sizeX*sizeY)

    public Board(BoardSetup setup) {
	super();
	this.sizeX = setup.getSizeX();
	this.sizeY = setup.getSizeY();
	this.noTiles = this.sizeX * this.sizeY;

	this.tiles = new ArrayList<Tile>(this.noTiles);// initialize with capacity noTiles
	this.initTiles(setup.getShipList());
    }

    private void initTiles(ShipList shipList) {
	for (int i = 0; i < this.noTiles; i++) {
	    this.tiles.add(new WaterTile(i));
	}
	for (Ship ship : Ship.values()) {
	    placeShip(ship, shipList.getShipPlacement(ship));
	}
    }

    private void placeShip(Ship ship, Placement placement) {
	int id;
	for (Coordinates coord : placement) {
	    id = tile(coord).id();
	    this.tiles.set(id, new ShipTile(id, ship));
	}
    }

    public Tile tile(int index) {
	assert (index < this.noTiles);
	return this.tiles.get(index);
    }

    public Tile tile(int x, int y) {
	assert (x < this.sizeX);
	assert (y < this.sizeY);
	return tile(x * this.sizeX + y);
    }

    public Tile tile(Coordinates tileCoordinates) {
	return tile(tileCoordinates.getX(), tileCoordinates.getY());
    }

    @Override
    public String toString() {
	String out = "";
	for (int i = 0; i < this.sizeX; i++) {
	    for (int j = 0; j < this.sizeY; j++) {
		out += this.tile(i, j).toString() + "\t";
	    }
	    out += "\n";
	}
	return out;
    }

    /**
     * @return the sizeX
     */
    public int getsizeX() {
	return this.sizeX;
    }

    /**
     * @return the sizeY
     */
    public int getsizeY() {
	return this.sizeY;
    }

    /**
     * @return the noTiles
     */
    public int getNoTiles() {
	return this.noTiles;
    }

}
