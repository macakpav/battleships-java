/**
 * 
 */
package implementation;

/**
 * @author macakpav
 *
 */
public enum Ship {
    CARRIER('C', "Carrier", 5, 6, TileColor.ORANGE), BATTLESHIP('B', "Battleship", 4, 5, TileColor.RED),
    SUBMARINE('S', "Submarine", 3, 3, TileColor.PURPLE), DESTROYER('D', "Destroyer", 2, 1, TileColor.GREEN);

    private char abbreviation;
    private String name; // string representing the ship (f.e. in printed output, in placement file etc.)
    private int length; // how many tiles the ship takes
    private int pointsPerHit; // points for a successful hit of the ship
    private TileColor color;

    private Ship(char abbreviation, String name, int length, int pointsPerHit, TileColor color) {
	this.abbreviation = abbreviation;
	this.name = name;
	this.length = length;
	this.pointsPerHit = pointsPerHit;
	this.color = color;
    }

    /**
     * @return Name of ship.
     */
    public String NAME() {
	return this.name;
    }

    /**
     * @return Number of tiles the ship occupies.
     */
    public int LEN() {
	return this.length;
    }

    /**
     * @return Points for single hit of the ship.
     */
    public int POINTS() {
	return this.pointsPerHit;
    }

    /**
     * @return The color of tile assigned to the ship.
     */
    public TileColor COLOR() {
	return this.color;
    }

    /**
     * @return Abbreviation of ship name.
     */
    public char ABB() {
	return this.abbreviation;
    }

}
