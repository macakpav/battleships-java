/**
 * 
 */
package implementation;

/**
 * @author macakpav
 *
 */
public class Coordinates extends Object {
    private int x; // horizontal coordinate
    private int y; // vertical coordinate

    public Coordinates(int x, int y) {
	this.x = x;
	this.y = y;
    }

    @Override
    public boolean equals(Object that) {
	return this.equals((Coordinates) that);
    }

    public boolean equals(Coordinates that) {
	if (this.x == that.x && this.y == that.y)
	    return true;

	return false;
    }

    /**
     * @return The x coordinate.
     */
    public int getX() {
	return this.x;
    }

    /**
     * Set x coordinate.
     */
    public void setX(int x) {
	this.x = x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY() {
	return this.y;
    }

    /**
     * Set y coordinate.
     */
    public void setY(int y) {
	this.y = y;
    }

    /**
     * @param newCoord
     * @return
     */
    public int distance(Coordinates that) {
	int dist = 0;
	if (this.x > that.x)
	    dist += this.x - that.x;
	else
	    dist += that.x - this.x;

	if (this.y > that.y)
	    dist += this.y - that.y;
	else
	    dist += that.y - this.y;

	return dist;
    }

}
