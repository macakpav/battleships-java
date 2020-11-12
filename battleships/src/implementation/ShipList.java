/**
 * 
 */
package implementation;

import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * @author macakpav
 *
 */
public class ShipList {
//    private int noCarrier; // number of CARRIER type ships
//    private int noBattleship; // number of BATTLESHIP type ships
//    private int noSubmarine; // number of SUBMARINE type ships
//    private int noDestroyer; // number of DESTROYER type ships

    private final HashMap<Ship, Placement> shipPlacements;

    public ShipList() {
	this.shipPlacements = new HashMap<Ship, Placement>();
    }

    /**
     * @param ship
     * @param intArray
     */
    public void put(Ship ship, int[] intArray) {
	this.put(ship, new Placement(intArray));
    }

    public void put(Ship ship, Placement placement) throws InputMismatchException {
	if (this.checkConflict(placement))
	    throw new InputMismatchException("Error trying to place ship " + ship.NAME() + ". Overlaping ships.");
	if (ship.LEN() != placement.len())
	    throw new InputMismatchException("Length of ship is different from placement!");

	this.shipPlacements.put(ship, placement);
    }

    private boolean checkConflict(Placement placement) {
	for (Ship ship : Ship.values()) {
	    if (!this.shipPlacements.containsKey(ship))
		continue;
	    if (this.shipPlacements.get(ship).collidesWith(placement))
		return true;
	}
	return false;
    }

    Placement getShipPlacement(Ship ship) {
	if (!this.shipPlacements.containsKey(ship))
	    return null;
	return this.shipPlacements.get(ship);
    }

}
