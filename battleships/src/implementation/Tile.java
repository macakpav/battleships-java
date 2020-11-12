/**
 * 
 */
package implementation;

/**
 * @author macakpav
 *
 */
public abstract class Tile {
    private int id; // ID of tile, also position in tiles array in Board
    private TileColor color; // color of the tile
    protected boolean flipped; // if the tile has been revealed

    public Tile(int id, TileColor color) {
	this.id = id;
	this.color = color;
	this.flipped = false;
    }

    /**
     * Flips the tile. Throws an exception if it was already uncovered (i.e.
     * flipped).
     */
    public void flip() throws Exception {
	if (this.flipped)
	    throw new Exception("Tile " + this.id + " is already flipped!");
	this.flipped = true;
    }

    /**
     * @return Color that this Tile should have displayed. Depends whether it was
     *         already uncovered or not.
     */
    public TileColor getColor() {
	if (this.flipped)
	    return this.color;
	return TileColor.FOW;
    }

    /**
     * @return ID of this Tile.
     */
    public int id() {
	return this.id;
    }

    @Override
    public String toString() {
	return (this.flipped ? "v" : "h") + this.id;
    }

}
