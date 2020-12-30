/**
 * 
 */
package implementation;

/**
 * Class representing a tile with a ship on it.
 * 
 * @author Pavel Macak
 *
 */
class ShipTile extends Tile {

	/**
	 * Ship object residing on this tile.
	 */
	private final Ship ship;

	/**
	 * @param id   ID of the tile
	 * @param ship Ship that occupies this tile.
	 */
	protected ShipTile(int id, Ship ship) {
		super(id, ship.getColor());
		this.ship = ship;
	}

	@Override
	protected HitType hit() throws Exception {
		super.flip();
		this.ship.hit();
		if (this.ship.isDestroyed()) {
			return HitType.SINK;
		}
		return HitType.HIT;

	}

	@Override
	protected double pointsForReveleaning() {
		int c = 1;
		if (this.ship.isDestroyed())
			c = 2;

		return c * this.ship.getPointsPerHit();
	}

	/**
	 * super.toString() + this.ship.nameAbbreviation()
	 */
	@Override
	public String toString() {
		return super.toString() + this.ship.charRepresentation();
	}

}
