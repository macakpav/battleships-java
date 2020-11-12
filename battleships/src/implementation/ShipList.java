/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * @author macakpav
 *
 */
public class ShipList implements Iterable<Ship> {
//    private int noCarrier; // number of CARRIER type ships
//    private int noBattleship; // number of BATTLESHIP type ships
//    private int noSubmarine; // number of SUBMARINE type ships
//    private int noDestroyer; // number of DESTROYER type ships

    private final ArrayList<Ship> ships;

    public ShipList() {
	this.ships = new ArrayList<Ship>();
    }

    /**
     * @param shipType
     * @param intArray
     */
    public void put(ShipType shipType, int[] intArray) {
	this.put(shipType, new Placement(intArray));
    }

    public void put(ShipType shipType, Placement placement) throws InputMismatchException {
	if (this.checkConflict(placement))
	    throw new InputMismatchException("Error trying to place ship " + shipType.NAME() + ". Overlaping ships.");
	if (shipType.LEN() != placement.len())
	    throw new InputMismatchException("Length of ship is different from placement!");

	this.ships.add(new Ship(this.ships.size(), shipType, placement));
    }

    private boolean checkConflict(Placement placement) {
	for (Ship ship : this.ships) {
	    if (this.ships.get(ship.id()).getPlacement().collidesWith(placement))
		return true;
	}
	return false;
    }

    @Override
    public Iterator<Ship> iterator() {
	return this.ships.iterator();
    }

    @Override
    public String toString() {
	String str = new String("Ship list:\n");
	for (Ship ship : this.ships) {
	    str += ship.toString();
	    str += "\n";
	}
	return str;
    }

}
