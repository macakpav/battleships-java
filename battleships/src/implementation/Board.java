/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.InputMismatchException;

import application.BoardSetup;

/**
 * @author Pavel Mačák
 *
 */
public class Board {
    /**
     * array holding different Tiles
     */
    private final ArrayList<Tile> tiles;
    /**
     * size of board in horizontal direction
     */
    private final int sizeX;
    /**
     * size of board in vertical direction
     */
    private final int sizeY;
    /**
     * number of tiles (sizeX*sizeY)
     */
    private final int noTiles;

    /**
     * Constructor using the BoardSetup class from application module.
     * 
     * @param setup that holds necessary setup for the Board.
     */
    public Board(BoardSetup setup) {
	super();
	this.sizeX = setup.getSizeX();
	this.sizeY = setup.getSizeY();
	this.noTiles = this.sizeX * this.sizeY;

	this.tiles = new ArrayList<Tile>(this.noTiles);// initialize with capacity
						       // noTiles
	this.initTiles(setup.getShipList());
    }

    /**
     * Initializes Tiles of the Board. Firstly all to empty (water) tiles and then
     * starts adding board objects from the list.
     * 
     * @param boardObjectList
     */
    private void initTiles(ShipList shipList) {
	System.out.println(shipList);
	for (int i = 0; i < this.noTiles; i++) {
	    this.tiles.add(new WaterTile(i));
	}

	for (Ship ship : shipList) {
	    placeShip(ship);
	}
    }

    /**
     * Place a ship object on the board.
     * 
     * @param ship to be placed on the board
     */
    private void placeShip(Ship ship) {
	int tileID;
	for (Coords coord : ship.getPlacement()) {
	    tileID = tile(coord).id();
	    if (tile(tileID) instanceof ShipTile)
		throw new InputMismatchException(
			"Cannot place object on board, there is already another thing on tile "
				+ tileID + "!");
	    this.tiles.set(tileID, new ShipTile(tileID, ship));
	}
    }

    /**
     * Get tile by index.
     * 
     * @param index integer
     * @return Tile object
     */
    public Tile tile(int index) {
	assert (index < this.noTiles);
	return this.tiles.get(index);
    }

    /**
     * Get tile by coordinates.
     * 
     * @param x integer
     * @param y integer
     * @return Tile object
     */
    public Tile tile(int x, int y) {
	assert (x <= this.sizeX);
	assert (y <= this.sizeY);
	return tile((x - 1) * this.sizeY + (y - 1));
    }

    /**
     * Get tile using Coords object.
     * 
     * @param coords
     * @return Tile object
     */
    public Tile tile(Coords coords) {
	return tile(coords.getX(), coords.getY());
    }

    /**
     * String representation of tiles order in square/rectangle format.
     */
    @Override
    public String toString() {
	String out = "";
	for (int i = 1; i <= this.sizeX; i++) {
	    for (int j = 1; j <= this.sizeY; j++) {
		out += this.tile(i, j).toString() + "\t";
	    }
	    out += "\n";
	}
	return out;
    }

    /**
     * @return the sizeX
     */
    public int getSizeX() {
	return this.sizeX;
    }

    /**
     * @return the sizeY
     */
    public int getSizeY() {
	return this.sizeY;
    }

    /**
     * @return the noTiles
     */
    public int getNoTiles() {
	return this.noTiles;
    }

}
