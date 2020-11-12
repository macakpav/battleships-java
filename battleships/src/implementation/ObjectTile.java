/**
 * 
 */
package implementation;

/**
 * @author Pavel Mačák
 *
 */
public class ObjectTile extends Tile {

    private Placeable tileObject;

    /**
     * @param id   ID of the tile
     * @param ship Ship that occupies this tile.
     */
    public ObjectTile(int id, Placeable tileObject) {
	super(id, tileObject.getColor());
	this.tileObject = tileObject;
    }

    /**
     * Flip the tile. (TODO)Signal that a ship has been hit.
     */
    @Override
    public void flip() throws Exception {
	super.flip();
	// shipHit(this.ship);
    }

    /**
     * super.toString() + this.ship.nameAbbreviation()
     */
    @Override
    public String toString() {
	return super.toString() + this.tileObject.charRepresentation();
    }

}
