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
public class BoardObjectList implements Iterable<BoardObject> {

    /**
     * ArrayList of ships.
     */
    private final ArrayList<BoardObject> boardObjects;

    /**
     * Basic constructor. Initializes ArrayList.
     */
    public BoardObjectList() {
	this.boardObjects = new ArrayList<BoardObject>();
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
    public void addShip(ShipType shipType, LinearPlacement linearPlacement)
	    throws IllegalArgumentException {
	if (shipType.LEN() != linearPlacement.len())
	    throw new IllegalArgumentException(
		    "Length of ship is different from placement!");

	this.addShip(new Ship(this.boardObjects.size(), shipType, linearPlacement));
    }

    /**
     * @param ship Ship to put at the end of ship list.
     * @throws IllegalArgumentException
     */
    public void addShip(Ship ship) throws IllegalArgumentException {
	if (this.checkConflict(ship.getPlacement()))
	    throw new IllegalArgumentException("Error trying to place ship "
		    + ship.getName() + ". Overlaping with other ships.");

	this.boardObjects.add(ship);

    }

    /**
     * Check if the placement would collide with any ship already in list of ships.
     * 
     * @param linearPlacement
     * @return True if any placement collides with Placement.
     */
    private boolean checkConflict(Placement placement) {
	for (Placeable placeableObject : this.boardObjects) {
	    if (placeableObject.getPlacement().collidesWith(placement))
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
//    private boolean checkConflict(Ship ship) {
//	return this.checkConflict(ship.getPlacement());
//    }

    /**
     * Returns the iterator of boardObjects.
     */
    @Override
    public Iterator<BoardObject> iterator() {
	return this.boardObjects.iterator();
    }

    /**
     * String representation of each boardObject on new lines.
     */
    @Override
    public String toString() {
	String str = new String("BoardObjectList:\n");
	for (BoardObject object : this.boardObjects) {
	    str += object.toString();
	    str += "\n";
	}
	return str;
    }

}
