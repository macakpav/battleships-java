/**
 * 
 */
package application;

import implementation.ShipList;
import implementation.ShipType;

/**
 * Class holding necessary variables to initialize an instance of Board.
 * 
 * @apiNote (TODO) Initialization from file. (TODO) Initialization from GUI
 *          elements.
 * 
 * @author macakpav
 *
 */
public class BoardSetup {
    private final ShipList shipList;
    private int sizeX, sizeY;

    public BoardSetup() {
	this.sizeX = 8;
	this.sizeY = 8;

	this.shipList = new ShipList();
	hardCodeInit();
    }

    private void hardCodeInit() {
	this.shipList.put(ShipType.CARRIER, new int[] { 0, 0, 0, 1, 0, 2, 0, 3, 0, 4 });
	this.shipList.put(ShipType.BATTLESHIP, new int[] { 1, 0, 1, 1, 1, 2, 1, 3 });
	this.shipList.put(ShipType.SUBMARINE, new int[] { 2, 0, 2, 1, 2, 2 });
	this.shipList.put(ShipType.DESTROYER, new int[] { 3, 0, 3, 1 });
	this.shipList.put(ShipType.CARRIER, new int[] { 4, 0, 4, 1, 4, 2, 4, 3, 4, 4 });
    }

    /**
     * @return the shipList
     */
    public ShipList getShipList() {
	return this.shipList;
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
