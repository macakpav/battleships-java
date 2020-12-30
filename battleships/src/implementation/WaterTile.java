/**
 * 
 */
package implementation;

/**
 * Class representing an empty tile on the board.
 * 
 * @author Pavel Macak
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
	@Override
	public double pointsForReveleaning() {
		return 0.0;
	}

	@Override
	public HitType hit() throws Exception {
		super.flip();
		return HitType.MISS;
	}

	/**
	 * super.toString() + "W"
	 */
	@Override
	public String toString() {
		return super.toString() + "W";
	}
}
