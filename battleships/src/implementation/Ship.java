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
final class Ship {

    private final int id;
    /**
     * ShipType Enumerator.
     */
    private final ShipType shipType;
    private final Placement placement;
    /**
     * Number of hidden tiles of this ship.
     */
    private int noHiddenTiles;

    protected Ship(int id, ShipType shipType, Placement placement) {
	this.id = id;
	assert (shipType.LEN() == placement.len());
	this.placement = placement;
	this.shipType = shipType;
	this.noHiddenTiles = shipType.LEN();
    }

    /**
     * Deep copy constructor
     * 
     * @param that
     */
    public Ship(Ship that) {
	this(that.id, that.shipType, that.placement);
    }

    /**
     * @return ID of this ship.
     */
    protected int id() {
	return this.id;
    }

    /**
     * @return Placement of this object.
     */
    public Placement getPlacement() {
	return this.placement;
    }

    /**
     * Tell the ship that it received a hit.
     */
    public void hit() {
	this.noHiddenTiles -= 1;
	assert (this.noHiddenTiles > -1);
    }

    /**
     * @return True if the ship has sunk (no more hidden tiles).
     */
    public boolean isDestroyed() {
	return this.noHiddenTiles == 0;
    }

    /**
     * @return the name of ship type.
     */
    protected String getName() {
	return this.shipType.NAME();
    }

    /**
     * @return Number of tiles the ship occupies.
     */
    protected int getlength() {
	return this.shipType.LEN();
    }

    /**
     * @return Points for single hit of the ship.
     */
    public double getPointsPerHit() {
	return this.shipType.POINTS();
    }

    /**
     * @return The color of tile assigned to the ship.
     */
    public TileColor getColor() {
	return this.shipType.COLOR();
    }

    /**
     * @return Abbreviation of ship name.
     */
    public char charRepresentation() {
	return this.shipType.ABB();
    }

    /**
     * "Ship [" + ", shipType=" + this.shipType + super.toString() + "]"
     */
    @Override
    public String toString() {
	return "Ship [" + "id=" + this.id + ", shipType=" + this.shipType
		+ ", placement=" + this.placement + "]";
    }

}
