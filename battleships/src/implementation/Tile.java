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
    protected boolean visible;

    /**
     * Basic constructor. Variable visible is initialized as false.
     * 
     * @param id    ID of tile
     * @param color Color of the tile when revealed
     */
    public Tile(int id, TileColor color) {
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
    public void flip() throws Exception {
	if (this.visible)
	    throw new Exception("Tile " + this.id + " is already visible!");
	this.visible = true;
    }

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
     * (this.visible ? "v" : "h") + this.id
     */
    @Override
    public String toString() {
	return (this.visible ? "v" : "h") + this.id;
    }

}
