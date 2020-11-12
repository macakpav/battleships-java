/**
 * 
 */
package application;

import implementation.BoardObjectList;
import implementation.ShipType;

/**
 * Class holding necessary variables to initialize an instance of Board.
 * 
 * @apiNote (TODO) Initialization from file. (TODO) Initialization from GUI
 *          elements.
 * 
 * @author Pavel Mačák
 *
 */
public class BoardSetup {
    private final BoardObjectList boardObjectList;
    private int sizeX, sizeY;

    public BoardSetup() {
	this.sizeX = 8;
	this.sizeY = 8;

	this.boardObjectList = new BoardObjectList();
	hardCodeInit();
    }

    private void hardCodeInit() {
	this.boardObjectList.addShip(ShipType.CARRIER, 0, 0, 0, 4);
	this.boardObjectList.addShip(ShipType.BATTLESHIP, 1, 0, 1, 3);
	this.boardObjectList.addShip(ShipType.SUBMARINE, 2, 0, 2, 2);
	this.boardObjectList.addShip(ShipType.DESTROYER, 3, 0, 3, 1);
	this.boardObjectList.addShip(ShipType.CARRIER, 4, 0, 4, 4);
    }

    /**
     * @return the shipList
     */
    public BoardObjectList getShipList() {
	return this.boardObjectList;
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

}
