/**
 * 
 */
package implementation;

/**
 * @author macakpav
 *
 */
public class ShipTile extends Tile {
    Ship ship;

    public ShipTile(int id, Ship ship) {
	super(id, ship.color());
	this.ship = ship;
    }

    /**
     * Flip the tile. (TODO)Signal that a ship has been hit.
     */
    @Override
    public void flip() throws Exception {
	super.flip();
	// shipHit(this.shipType);
    }

    @Override
    public String toString() {
	return super.toString() + this.ship.nameAbbreviation();
    }
}
