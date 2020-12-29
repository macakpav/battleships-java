/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class holding a list of ships.
 * 
 * @author Pavel Mačák
 *
 */
public class ShipList implements Iterable<Ship> {

    /**
     * ArrayList of ships.
     */
    private final ArrayList<Ship> ships;

    /**
     * Basic constructor. Initializes ArrayList.
     */
    public ShipList() {
	this.ships = new ArrayList<Ship>();
    }

    /**
     * Adds a ship to the array of ships.
     * 
     * @param shipType Type of ship to add.
     * @param intArray to be passed to Placement constructor.
     * @throws IllegalArgumentException
     */
    public void addShip(ShipType shipType, int[] intArray)
	    throws IllegalArgumentException {
	if (intArray.length == 4)
	    this.addShip(shipType, intArray[0], intArray[1], intArray[2],
		    intArray[3]);
	else
	    this.addShip(shipType, new LinearPlacement(intArray));
    }

    /**
     * Adds a ship to the array of ships.
     * 
     * @param shipType Type of ship to add.
     * @param beginX   to be passed to Placement constructor.
     * @param beginY   to be passed to Placement constructor.
     * @param endX     to be passed to Placement constructor.
     * @param endY     to be passed to Placement constructor.
     * @throws IllegalArgumentException
     */
    public void addShip(ShipType shipType, int beginX, int beginY, int endX, int endY)
	    throws IllegalArgumentException {
	this.addShip(shipType, new LinearPlacement(beginX, beginY, endX, endY));
    }

    /**
     * Adds a ship to the array of ships.
     * 
     * @param shipType Type of ship to add.
     * @param begin    to be passed to Placement constructor.
     * @param end      to be passed to Placement constructor.
     * @throws IllegalArgumentException
     */
    public void addShip(ShipType shipType, Coords begin, Coords end)
	    throws IllegalArgumentException {
	this.addShip(shipType, new LinearPlacement(begin, end));
    }

    /**
     * @param shipType        Type of ship to add.
     * @param linearPlacement holding coordinates of the ship
     * @throws IllegalArgumentException
     */
    public void addShip(ShipType shipType, Placement placement)
	    throws IllegalArgumentException {
//	System.out.println(linearPlacement);
	if (shipType.LEN() != placement.len())
	    throw new IllegalArgumentException(
		    "Length of ship is different from length placement!");

	this.add(new Ship(this.ships.size(), shipType, placement));
    }

    /**
     * @param object Object to put at the end of object list.
     * @throws IllegalArgumentException
     */
    protected void add(Ship object) throws IllegalArgumentException {
	if (this.checkOverlaps(object))
	    throw new IllegalArgumentException(
		    "Error trying to place object (Overlaping with other objects):\n "
			    + object.toString());

	this.ships.add(object);

    }

    /**
     * Check if the placement would collide with any object already in list of
     * objects.
     * 
     * @param Placement
     * @return True if any placement collides with Placement.
     */
    private boolean checkOverlaps(Placement placement) {
	for (Ship boardObject : this.ships) {
	    if (boardObject.getPlacement().overlapsWith(placement))
		return true;
	}
	return false;
    }

    /**
     * Check if this ship would collide with any other ship already in list of
     * ships.
     * 
     * @param ship Ship object to check.
     * @return True if any ship collides with this ship.
     */
    private boolean checkOverlaps(Ship ship) {
	return this.checkOverlaps(ship.getPlacement());
    }

    /**
     * Returns the iterator of ships.
     */
    @Override
    public Iterator<Ship> iterator() {
	return this.ships.iterator();
    }

    /**
     * String representation of each ship separated by new line.
     */
    @Override
    public String toString() {
	String str = new String("ShipList:\n");
	for (Ship ship : this.ships) {
	    str += ship.toString();
	    str += "\n";
	}
	return str;
    }

    /**
     * Checks if all ships in the list have been sunk.
     * 
     * @return True if all ships are destroyed.
     */
    public boolean areAllSunk() {
	for (Ship ship : this.ships) {
	    if (!ship.isDestroyed())
		return false;
	}
	return true;
    }

    /**
     * Clear ships in the list.
     */
//    public void clearList() {
//	this.ships.clear();
//    }

}
