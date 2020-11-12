/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Pavel Mačák
 *
 */
public abstract class Placement implements Iterable<Coords> {
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

    protected abstract void addCoord(Coords coord);

    /**
     * @return Count of coordinates in this placement object.
     */
    public int len() {
	return this.coordinateList.size();
    }

    /**
     * Check if placements overlap.
     * 
     * @param linearPlacement LinearPlacement object to check collision with.
     * @return True if at least one coordinate is common for both placements.
     */
    public boolean collidesWith(Placement placement) {
	assert (placement != null);
	for (Coords coords : this.coordinateList) {
	    if (placement.coordinateList.contains(coords))
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
