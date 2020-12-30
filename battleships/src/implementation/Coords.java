/**
 * 
 */
package implementation;

/**
 * Class holding POSITIVE x-coordinate and y-coordinate as integers. Can also be
 * seen as class for indexing. Class name is abbreviation for Coordinates.
 * 
 * @apiNote Coords is abbreviation for the word coordinates.
 * 
 * @author Pavel Mačák
 *
 */
public class Coords extends Object {
    private final int xCoord; // horizontal coordinate
    private final int yCoord; // vertical coordinate

    /**
     * Simple constructor from tow coordinates.
     * 
     * @param x
     * @param y
     */
    public Coords(int x, int y) {
	assert (x > 0);
	assert (y > 0);
	this.xCoord = x;
	this.yCoord = y;
    }

    /**
     * Two Coordinates objects are equal if their coordinates are equal. Calls the
     * equals(Coordinates that) comparison.
     */
    @Override
    public boolean equals(Object that) {
	assert (that != null);
	if (!(that instanceof Coords))
	    return super.equals(that);
	return this.equals((Coords) that);
    }

    /**
     * Two Coordinates objects are equal if their coordinates are equal.
     * 
     * @param that
     * @return boolean
     */
    private boolean equals(Coords that) {
	assert (that != null);
	if (this.xCoord == that.xCoord && this.yCoord == that.yCoord)
	    return true;

	return false;
    }

    /**
     * @return This x coordinate.
     */
    public int getX() {
	return this.xCoord;
    }

    /**
     * @return This y coordinate.
     */
    public int getY() {
	return this.yCoord;
    }

    /**
     * Measure distance between two Coordinates.
     * 
     * @param that Coordinates object to measure distance to.
     * @return Non-negative integer.
     */
    public int distance(Coords that) {
	assert (that != null);
	if (this.equals(that))
	    return 0;
	int dist = 0;
	if (this.xCoord > that.xCoord)
	    dist += this.xCoord - that.xCoord;
	else
	    dist += that.xCoord - this.xCoord;

	if (this.yCoord > that.yCoord)
	    dist += this.yCoord - that.yCoord;
	else
	    dist += that.yCoord - this.yCoord;

	return dist;
    }

    /**
     * Check if coordinates are on same row, or column.
     * 
     * @param that Coordinates object to compare this with.
     * @return True if either inLineHorizontally() or inLineVertically()
     */
    public boolean inLine(Coords that) {
	assert (that != null);
	return (this.inLineHorizontally(that) || this.inLineVertically(that));
    }

    /**
     * Check if coordinates are aligned horizontally.
     * 
     * @param that Coordinates object to compare this with.
     * @return True if and only if x-coordinates are equal AND y-coordinates are
     *         different.
     */
    private boolean inLineHorizontally(Coords that) {
	assert (that != null);
	return this.xCoord == that.xCoord && this.yCoord != that.yCoord;
    }

    /**
     * Check if coordinates are aligned vertically.
     * 
     * @param that Coordinates object to compare this with.
     * @return True if and only if y-coordinates are equal AND x-coordinates are
     *         different.
     */
    private boolean inLineVertically(Coords that) {
	assert (that != null);
	return this.yCoord == that.yCoord && this.xCoord != that.xCoord;
    }

    /**
     * Used to get Coordinates laying between this and that. The two Coordinates
     * have to be inLine().
     * 
     * @param that Coordinates object
     * @return Coordinates[] array going from this to that coordinate or null if
     *         this and that are neighbors or equal().
     */
    public Coords[] inBetween(Coords that) {
	assert (that != null);
	assert (this.inLine(that));

	if (this.distance(that) == 1)
	    return new Coords[0];
	Coords[] coordsInBetween = new Coords[this.distance(that) - 1];

	int i = 0;
	if (this.inLineHorizontally(that)) {
	    int x = this.getX();
	    if (this.getY() < that.getY()) {
		for (int y = this.getY() + 1; y < this.getY()
			+ this.distance(that); y++, i++) {
		    coordsInBetween[i] = new Coords(x, y);
		}
	    } else {
		for (int y = this.getY() - 1; y > this.getY()
			- this.distance(that); y--, i++) {
		    coordsInBetween[i] = new Coords(x, y);
		}
	    }

	} else if (this.inLineVertically(that)) {
	    int y = this.getY();
	    if (this.getX() < that.getX()) {
		for (int x = this.getX() + 1; x < this.getX()
			+ this.distance(that); x++, i++) {
		    coordsInBetween[i] = new Coords(x, y);
		}
	    } else {
		for (int x = this.getX() - 1; x > this.getX()
			- this.distance(that); x--, i++) {
		    coordsInBetween[i] = new Coords(x, y);
		}
	    }
	}
	return coordsInBetween;
    }

    /**
     * @return "[" + this.xCoord + ", " + this.yCoord + "]"
     */
    @Override
    public String toString() {
	return "[" + this.xCoord + ", " + this.yCoord + "]";
    }

    /**
     * Check if that is neighbor of this.
     * 
     * @param that Coordinates object to check.
     * @return True if and only if distance == 1
     */
    public boolean isNeighbor(Coords that) {
	assert (that != null);
	return this.distance(that) == 1;
    }

}
