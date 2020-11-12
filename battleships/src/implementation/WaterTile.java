/**
 * 
 */
package implementation;

/**
 * Extends Tile abstract class. Represents a water tile.
 * 
 * @author Pavel Mačák
 *
 */
public class WaterTile extends Tile {
    /**
     * Color is set to WATER.
     * 
     * @param id ID of the tile.
     */
    public WaterTile(int id) {
	super(id, TileColor.WATER);
    }

    /**
     * super.toString() + "W"
     */
    @Override
    public String toString() {
	return super.toString() + "W";
    }
}
