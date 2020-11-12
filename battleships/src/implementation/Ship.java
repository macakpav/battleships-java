/**
 * 
 */
package implementation;

/**
 * @author macakpav
 *
 */
public class Ship {
    private int id;
    private ShipType shipType;
    private Placement placement;

    public Ship(int id, ShipType shipType, Placement placement) {
	this.id = id;
	this.shipType = shipType;
	this.placement = placement;
    }

    /**
     * @return Number of tiles the ship occupies.
     */
    public int length() {
	return this.shipType.LEN();
    }

    /**
     * @return Points for single hit of the ship.
     */
    public int pointsPerHit() {
	return this.shipType.POINTS();
    }

    /**
     * @return The color of tile assigned to the ship.
     */
    public TileColor color() {
	return this.shipType.COLOR();
    }

    /**
     * @return Abbreviation of ship name.
     */
    public char nameAbbreviation() {
	return this.shipType.ABB();
    }

    /**
     * @return ID of this ship.
     */
    public int id() {
	return this.id;
    }

    /**
     * @return Placement of this ship.
     */
    public Placement getPlacement() {
	return this.placement;
    }

    @Override
    public String toString() {
	return "Ship [id=" + this.id + ", shipType=" + this.shipType + ", placement=" + this.placement + "]";
    }

}
