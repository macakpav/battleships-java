/**
 * 
 */
package application;

import java.io.IOException;

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

    protected void readLine(String line) throws IOException {

//	System.out.println(line);
	String[] buffer = line.trim().split(";");

//	System.out.println(buffer.length);

	this.shipTypeName = buffer[0].trim();
//	System.out.println(buffer[0]);

	this.coordinates = new int[(buffer.length - 1) * 2];
	for (int i = 1; i < buffer.length; i++) {
//	    System.out.println(buffer[i]);
	    String[] pointCoords = buffer[i].trim().split("\\*");
	    if (pointCoords.length != 2)
		throw new IOException(
			"Wrongly specified point coordinates! " + buffer[i]);
	    this.coordinates[(i - 1) * 2] = Integer.parseInt(pointCoords[0].trim());
	    this.coordinates[(i - 1) * 2 + 1] = Integer
		    .parseInt(pointCoords[1].trim());
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
