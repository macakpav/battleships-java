/**
 * 
 */
package application;

import java.util.InputMismatchException;

/**
 * @author Pavel Mačák
 *
 */
final class InputFileLine {
    private String shipTypeName;
    private int[] coordinates;

    protected InputFileLine() {
	this.shipTypeName = "UNDEFINED";
	this.coordinates = null;
    }

    /**
     * @param line shall be a trimmed string
     * @throws NumberFormatException
     * @throws InputMismatchException
     */
    protected void readLine(String line)
	    throws NumberFormatException, InputMismatchException {

//	System.out.println(line);

	String[] buffer = line.split(";|\\*");

	if ((buffer.length - 1) % 2 != 0)
	    throw new InputMismatchException(
		    "Wrong number of inputs on line! (Perhaps worng format/separators?)");

	String coord;

//	System.out.println(buffer.length);

	this.shipTypeName = buffer[0].trim();
//	System.out.println(buffer[0]);

	this.coordinates = new int[buffer.length - 1];
	for (int i = 1; i < buffer.length; i++) {
//	    System.out.println(buffer[i]);
	    coord = buffer[i].trim();
	    this.coordinates[i - 1] = Integer.parseInt(coord);
	    if (this.coordinates[i - 1] < 1)
		throw new InputMismatchException(
			"Coordinates should be positive integers!");
	}
    }

    /**
     * @return the shipTypeName
     */
    protected String getShipTypeName() {
	return this.shipTypeName;
    }

    /**
     * @return the coordinates
     */
    protected int[] getCoordinates() {
	return this.coordinates;
    }
}
