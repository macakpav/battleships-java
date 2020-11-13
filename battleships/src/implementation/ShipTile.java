/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
public class ShipTile extends Tile {

    private Ship ship;

    /**
     * @param id   ID of the tile
     * @param ship Ship that occupies this tile.
     */
    protected ShipTile(int id, Ship ship) {
	super(id, ship.getColor());
	this.ship = ship;
    }

    @Override
    protected void hit() throws Exception {
	super.flip();
	this.ship.hit();
    }

    @Override
    protected double pointsForReveleaning() {
	return this.ship.getPointsPerHit();
    }

    /**
     * super.toString() + this.ship.nameAbbreviation()
     */
    @Override
    public String toString() {
	return super.toString() + this.ship.charRepresentation();
    }

}
