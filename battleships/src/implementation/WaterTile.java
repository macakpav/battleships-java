/**
 * 
 */
package implementation;

/**
 * Extends Tile abstract class. Represents an empty tile on the board.
 * 
 * @author Pavel Mačák
 *
 */
final class WaterTile extends Tile {
    /**
     * Color is set to WATER.
     * 
     * @param id ID of the tile.
     */
    protected WaterTile(int id) {
	super(id, TileColor.WATER);
    }

    /**
     * No points for hitting an empty tile.
     */
    public double pointsForReveleaning() {
	return 0.0;
    }

    @Override
    public void hit() throws Exception {
	super.flip();
    }

    /**
     * super.toString() + "W"
     */
    @Override
    public String toString() {
	return super.toString() + "W";
    }
}
