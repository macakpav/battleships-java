/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * @author macakpav
 *
 */
public class Placement implements Iterable<Coordinates> {
    private ArrayList<Coordinates> coordinateList;
    
    @Override
    public Iterator<Coordinates> iterator() {
	return this.coordinateList.iterator();
    }

    public Placement(int[] coordinates) throws InputMismatchException {
	assert (coordinates != null);
	if (coordinates.length % 2 != 0) {
	    throw new InputMismatchException("Length of coordinates list has to be dividable by two!");
	}
	this.coordinateList = new ArrayList<Coordinates>(coordinates.length / 2);
	this.coordinateList.add(new Coordinates(coordinates[0], coordinates[1]));
	for (int i = 2; i < coordinates.length; i += 2) {
	    Coordinates newCoord = new Coordinates(coordinates[i], coordinates[i + 1]);
	    if (this.lastCoord().distance(newCoord) != 1)
		throw new InputMismatchException("Coordinates are not consecutive!");
//	    System.out.println(this.coordinateList);
	    if (this.coordinateList.contains(newCoord))
		throw new InputMismatchException("Two coordiantes are identical!");
	    this.coordinateList.add(newCoord);
	}
    }

    private Coordinates lastCoord() {
	return this.coordinateList.get(this.coordinateList.size() - 1);
    }

    public int len() {
	return this.coordinateList.size();
    }

    /**
     * @return True if at least one coordinate is common for both placements.
     */
    public boolean collidesWith(Placement placement) {
	for (Coordinates coordinates : this.coordinateList) {
	    if (placement.coordinateList.contains(coordinates))
		return true;
	}
	return false;
    }

    @Override
    public String toString() {
	return "Placement [coordinateList=" + this.coordinateList + "]";
    }

}
