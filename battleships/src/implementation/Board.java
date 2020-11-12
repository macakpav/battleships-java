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
    private final ArrayList<Tile> tiles; // array holding different Tiles
    private int sizeX; // size of board in horizontal direction
    private int sizeY; // size of board in vertical direction
    private int noTiles; // number of tiles (sizeX*sizeY)

    public Board(BoardSetup setup) {
	super();
	this.sizeX = setup.getSizeX();
	this.sizeY = setup.getSizeY();
	this.noTiles = this.sizeX * this.sizeY;

	this.tiles = new ArrayList<Tile>(this.noTiles);// initialize with capacity
						       // noTiles
	this.initTiles(setup.getShipList());
    }

    private void initTiles(BoardObjectList boardObjectList) {
	System.out.println(boardObjectList);
	for (int i = 0; i < this.noTiles; i++) {
	    this.tiles.add(new WaterTile(i));
	}

	for (BoardObject obj : boardObjectList) {
	    placeOnBoard(obj);
	}
    }

    private void placeOnBoard(Placeable placeable) {
	int tileID;
	for (Coords coord : placeable.getPlacement()) {
	    tileID = tile(coord).id();
	    if (tile(tileID) instanceof ObjectTile)
		throw new InputMismatchException(
			"Cannot place object on board, there is already another thing on tile "
				+ tileID + "!");
	    this.tiles.set(tileID, new ObjectTile(tileID, placeable));
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

    public Tile tile(Coords tileCoordinates) {
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
