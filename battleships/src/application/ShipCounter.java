
package application;

import java.util.HashMap;

import implementation.ShipType;

/**
 * Class keeping track of how many ships should be put on board when generating
 * random placements.
 * 
 * @author Pavel Macak
 *
 */
public class ShipCounter {

	private final HashMap<ShipType, Integer> shipNumbers;

	public ShipCounter() {
		this.shipNumbers = new HashMap<ShipType, Integer>();
		for (ShipType shipType : ShipType.values())
			this.shipNumbers.put(shipType, 1);
	}

	/**
	 * Deep copy constructor
	 * 
	 * @param shipCounter
	 */
	public ShipCounter(ShipCounter that) {
		this.shipNumbers = new HashMap<ShipType, Integer>();
		for (ShipType shipType : ShipType.values())
			this.shipNumbers.put(shipType, that.getShipCount(shipType));
	}

	public int getShipCount(ShipType type) {
		assert (type != null);
		return this.shipNumbers.get(type);
	}

	public void setShipCount(ShipType type, int count) {
		assert (type != null);
		this.shipNumbers.put(type, count);
	}

	public boolean tooManyShips(int noTiles) {
		return tooManyShips(noTiles, 0.5);
	}

	public boolean tooManyShips(int noTiles, double tolerance) {
		double tilesTaken = 0;
		for (ShipType shipType : ShipType.values()) {
			tilesTaken += shipType.LEN() * shipNumbers.get(shipType);
		}
		return (tilesTaken / noTiles >= tolerance);
	}
}
