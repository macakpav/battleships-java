/**
 * 
 */
package implementation;

/**
 * Abstract class. Represents a tile on the Board.
 * 
 * @author Pavel Mačák
 *
 */
public abstract class Tile {
    /**
     * ID of tile, also index of this tile in tiles array of Board class.
     */
    private int id;
    /**
     * Color of the tile when revealed.
     */
    private TileColor color;
    /**
     * Indicates if the tile has been revealed.
     */
    private boolean visible;

    /**
     * Basic constructor. Variable visible is initialized as false.
     * 
     * @param id    ID of tile
     * @param color Color of the tile when revealed
     */
    protected Tile(int id, TileColor color) {
	this.id = id;
	this.color = color;
	this.visible = false;
    }

    /**
     * Flips the tile. Throws an exception if it was already uncovered (i.e.
     * visible).
     */
    /**
     * @throws Exception
     */
    protected void flip() throws Exception {
	if (this.visible)
	    throw new Exception("Tile " + this.id + " is already visible!");
	this.visible = true;
    }

    /**
     * @return Points awarded for revealing this tile.
     */
    protected abstract double pointsForReveleaning();

    /**
     * Tell the tile, that it has been hit.
     * 
     * @throws Exception if the tile was already visible.
     */
    protected abstract HitType hit() throws Exception;

    /**
     * @return Color that this Tile should be displayed with. Depends whether it was
     *         already uncovered or not.
     */
    public TileColor getColor() {
	if (this.visible)
	    return this.color;
	return TileColor.FOW;
    }

    /**
     * @return ID of this Tile.
     */
    public int id() {
	return this.id;
    }

    /**
     * @return True if tile is visible
     */
    protected boolean isVisible() {
	return this.visible;
    }

    /**
     * (this.visible ? "v" : "h") + this.id
     */
    @Override
    public String toString() {
	return (this.visible ? "v" : "h") + this.id;
    }

}
