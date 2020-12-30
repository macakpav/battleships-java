/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract class. Represents placement on tile grid. Concrete examples could be
 * Linear, Square, Cross placements.
 * 
 * @author Pavel Mačák
 *
 */
abstract class Placement implements Iterable<Coords> {
    /**
     * Array list containing placement coordinates.
     */
    protected ArrayList<Coords> coordinateList;

    /**
     * Returns the iterator of coordinateList.
     */
    @Override
    public Iterator<Coords> iterator() {
	return this.coordinateList.iterator();
    }

    /**
     * Adds a coordinate to this Placement.
     * 
     * @param coord Coordinate to add
     */
    protected void addCoord(Coords coord) {
	this.coordinateList.add(coord);
    }

    /**
     * @return Count of coordinates in this placement object.
     */
    protected int len() {
	return this.coordinateList.size();
    }

    /**
     * Check if placements overlap.
     * 
     * @param linearPlacement LinearPlacement object to check collision with.
     * @return True if at least one coordinate is common for both placements.
     */
    protected boolean overlapsWith(Placement that) {
	assert (that != null);
	for (Coords coords : this.coordinateList) {
	    if (that.coordinateList.contains(coords))
		return true;
	}
	return false;
    }

    /**
     * @return "Placement [" + super.coordinateList + "]"
     */
    @Override
    public String toString() {
	return "Placement [" + this.coordinateList + "]";
    }
}
