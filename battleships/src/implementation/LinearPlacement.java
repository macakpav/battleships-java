/**
 * 
 */
package implementation;

import java.util.ArrayList;

/**
 * Class holding placement of objects on the Board. Allow only for linear
 * placement, meaning it will throw exceptions if constructed to be anything
 * else apart from one cell or a column or row of cells. Implements the Iterable
 * interface to iterate through list of coordinates.
 * 
 * @author Pavel Mačák
 *
 */
class LinearPlacement extends Placement {

    /**
     * Constructs linear placement going from point [beginX,beginY] to [endX,endY].
     * 
     * @param beginX X-coordinate of starting point.
     * @param beginY Y-coordinate of starting point.
     * @param endX   X-coordinate of ending point.
     * @param endY   Y-coordinate of ending point.
     * @throws IllegalArgumentException
     */
    protected LinearPlacement(int beginX, int beginY, int endX, int endY)
	    throws IllegalArgumentException {
	this(new Coords(beginX, beginY), new Coords(endX, endY));
    }

    /**
     * Constructs linear placement going from Coordinate begin to end.
     * 
     * @param begin Starting Coordinate object.
     * @param end   Ending Coordinate object.
     * @throws IllegalArgumentException
     */
    protected LinearPlacement(Coords begin, Coords end)
	    throws IllegalArgumentException {
	assert (begin != null);
	assert (end != null);
	if (begin.equals(end))
	    throw new IllegalArgumentException("Coordiantes are identical!");
	if (!begin.inLine(end))
	    throw new IllegalArgumentException(
		    "Coordinates " + begin + " and " + end + " are not in line!");
	this.addCoord(begin);
	if (begin.distance(end) > 1)
	    for (Coords coord : begin.inBetween(end))
		this.addCoord(coord);
	this.addCoord(end);
    }

    /**
     * Constructs linear placement going from integer array of tuples i.e. the array
     * should be [x1,y1; x2,y2; ...etc. ].
     * 
     * @param coordinates Integer array of coordinate tuples.
     * @throws IllegalArgumentException
     */
    protected LinearPlacement(int[] coordinates) throws IllegalArgumentException {
	assert (coordinates != null);

	if (coordinates.length % 2 != 0) {
	    throw new IllegalArgumentException(
		    "Length of coordinates list has to be dividable by two!");
	}

	for (int i = 0; i < coordinates.length; i += 2)
	    this.addCoord(new Coords(coordinates[i], coordinates[i + 1]));
    }

    /**
     * Construct linear placement using a ArrayList of Coordinates. The coordinates
     * are first checked for correctness before the object is constructed. This
     * constructor makes use of the provided ArrayList instead of creating a new one
     * for efficiency.
     * 
     * @param coordinateList
     * @throws IllegalArgumentException
     */
    protected LinearPlacement(ArrayList<Coords> coordinateList)
	    throws IllegalArgumentException {
	// only perform checks if the list is valid
	for (int i = 1; i < coordinateList.size(); i++) {
	    checkNewCoordCorrectness(coordinateList.get(0), coordinateList.get(i - 1),
		    coordinateList.get(i), coordinateList);

	}
    }

    /**
     * Construct linear placement using an Iterable collection of Coordinates.
     * 
     * @param coordinateList
     * @throws IllegalArgumentException
     */
    protected LinearPlacement(Iterable<Coords> coordinates)
	    throws IllegalArgumentException {
	this.addCoord(coordinates);
    }

    /**
     * Adds a Coordinates object at the end of Coordinate ArrayList, but checks if
     * linearity will not be broken.
     * 
     * @param newCoord Coordinate object to
     * @throws IllegalArgumentException
     */
    @Override
    protected void addCoord(Coords newCoord) throws IllegalArgumentException {
	assert (newCoord != null);
	if (super.coordinateList == null) {
	    super.coordinateList = new ArrayList<Coords>();
	} else {
	    checkNewCoordCorrectness(super.coordinateList.get(0),
		    super.coordinateList.get(super.coordinateList.size() - 1),
		    newCoord, super.coordinateList);
	}
	super.coordinateList.add(newCoord);
    }

    /**
     * Adds multiple Coordinates objects at the end of Coordinate ArrayList, but
     * checks if linearity will not be broken.
     * 
     * @param newCoords array of Coordinates objects
     * @throws IllegalArgumentException
     */
    protected void addCoord(Iterable<Coords> newCoords)
	    throws IllegalArgumentException {
	assert (newCoords != null);
	for (Coords coord : newCoords) {
	    this.addCoord(coord);
	}
    }

    /**
     * Checks if the new_ coordinate will not break linearity of the placement. This
     * means that new_ has to be in line with first, has to be a neighbor of
     * previous and be a member of list.
     * 
     * @param first coordinate in list
     * @param prev  coordinate preceding new_
     * @param new_  coordinate to check
     * @param list  of coordinates
     */
    private static void checkNewCoordCorrectness(Coords first, Coords prev,
	    Coords new_, ArrayList<Coords> list) {
	if (!prev.isNeighbor(new_))
	    throw new IllegalArgumentException("Coordinates are not consecutive!");
	if (!first.inLine(new_))
	    throw new IllegalArgumentException("Coordinates are not in line!");
	if (list.contains(new_))
	    throw new IllegalArgumentException("Two coordiantes are identical!");
    }

    /**
     * @return "LinearPlacement [" + super.coordinateList + "]"
     */
    @Override
    public String toString() {
	return "LinearPlacement [" + super.coordinateList + "]";
    }

}
