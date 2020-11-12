/**
 * 
 */
package implementation;

/**
 * A class representing a ship.
 * 
 * @author Pavel Mačák
 *
 */
public class Ship extends BoardObject {
    /**
     * ShipType Enumerator.
     */
    private ShipType shipType;
    /**
     * Number of hidden tiles of this ship.
     */
    private int noHiddenTiles;

    public Ship(int id, ShipType shipType, LinearPlacement linearPlacement) {
	super(id, linearPlacement);
	assert (shipType.LEN() == linearPlacement.len());
	this.shipType = shipType;
	this.noHiddenTiles = shipType.LEN();
    }

    /**
     * Tell the ship that it received a hit.
     */
    void hit() {
	this.noHiddenTiles -= 1;
	assert (this.noHiddenTiles > -1);
    }

    /**
     * @return True if the ship has sunk (no more hidden tiles).
     */
    boolean sunk() {
	return this.noHiddenTiles == 0;
    }

    /**
     * @return the name of ship type.
     */
    String getName() {
	return this.shipType.NAME();
    }

    /**
     * @return Number of tiles the ship occupies.
     */
    int getlength() {
	return this.shipType.LEN();
    }

    /**
     * @return Points for single hit of the ship.
     */
    int getPointsPerHit() {
	return this.shipType.POINTS();
    }

    /**
     * @return The color of tile assigned to the ship.
     */
    @Override
    public TileColor getColor() {
	return this.shipType.COLOR();
    }

    /**
     * @return Abbreviation of ship name.
     */
    @Override
    public char charRepresentation() {
	return this.shipType.ABB();
    }

    /**
     * "Ship [" + ", shipType=" + this.shipType + super.toString() + "]"
     */
    @Override
    public String toString() {
	return "Ship [ shipType=" + this.shipType + ", " + super.toString() + "]";
    }

}
