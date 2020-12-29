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
     * number of tiles (sizeX*sizeY)
     */
    private final int noTiles;

    private final BoardSetup setup;

    /**
     * Constructor using the BoardSetup class from application module.
     * 
     * @param setup that holds necessary setup for the Board.
     */
    public Board(BoardSetup setup) {
	super();
	this.setup = setup;
	this.noTiles = this.setup.getCols() * this.setup.getRows();

	this.tiles = new ArrayList<Tile>(this.noTiles);// initialize with capacity
						       // noTiles
	this.initTiles(this.setup.getShipList());
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
     * @param col integer
     * @param row integer
     * @return Tile object
     */
    public Tile tile(int col, int row) {
	assert (col <= this.getCols());
	assert (row <= this.getRows());
	return tile((col - 1) + (row - 1) * getCols());
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
	for (int i = 1; i <= this.getCols(); i++) {
	    for (int j = 1; j <= this.getRows(); j++) {
		out += this.tile(i, j).toString() + "\t";
	    }
	    out += "\n";
	}
	return out;
    }

    /**
     * @return the sizeX
     */
    public int getRows() {
	return this.setup.getRows();
    }

    /**
     * @return the sizeY
     */
    public int getCols() {
	return this.setup.getCols();
    }

    /**
     * @return the noTiles
     */
    public int getNoTiles() {
	return this.noTiles;
    }

    /**
     * @return True if all ships are destroyed.
     */
    public boolean areAllSunk() {
	return this.setup.getShipList().areAllSunk();
    }

}
