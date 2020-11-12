/**
 * 
 */
package implementation;

/**
 * @author macakpav
 *
 */
public class WaterTile extends Tile {
    public WaterTile(int id) {
	super(id, TileColor.WATER);
    }

    @Override
    public String toString() {
	return super.id + "W";
    }
}
