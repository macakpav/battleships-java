/**
 * 
 */
package implementation;

/**
 * Types of ships.
 * 
 * @Exposes NAME(), POINTS(), LEN(), COLOR(), ABB()
 * 
 * @author Pavel Mačák
 *
 */
public enum ShipType {
    CARRIER('A', "Carrier", 5, 600, TileColor.ORANGE),
    BATTLESHIP('B', "Battleship", 4, 500, TileColor.GREEN),
    SUBMARINE('S', "Submarine", 3, 300, TileColor.RED),
//    BATTLECRUISER('C', "Battlecruiser", 6, 6, TileColor.WHITE),
//    SPEEDBOAT('S', "Speedboat", 1, 1, TileColor.YELLOW),
//    AIRPLANE('A', "Airplane", 1, 1, TileColor.YELLOW),
    DESTROYER('D', "Destroyer", 2, 100, TileColor.PINK);

    /**
     * One character abbreviation of ships name.
     */
    private char abbreviation;
    /**
     * String representing the ship (f.e. in printed output, inplacement file etc.).
     */
    private String name;
    /**
     * How many tiles the ship takes.
     */
    private int length;
    /**
     * Points for a successful hit of the ship.
     */
    private int pointsPerHit;
    /**
     * The color of tile assigned to the ship.
     */
    private TileColor color;

    /**
     * Enum constructor.
     * 
     * @param abbreviation
     * @param name
     * @param length
     * @param pointsPerHit
     * @param color
     */
    private ShipType(char abbreviation, String name, int length, int pointsPerHit,
	    TileColor color) {
	assert (length > 0);
	this.abbreviation = abbreviation;
	this.name = name;
	this.length = length;
	this.pointsPerHit = pointsPerHit;
	this.color = color;
    }

    /**
     * @return Name of ship type.
     */
    public String NAME() {
	return this.name;
    }

    /**
     * @return Number of tiles this ship type occupies.
     */
    public int LEN() {
	return this.length;
    }

    /**
     * @return Points for single hit of this ship type.
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
     * @return Abbreviation of ship name. One character.
     */
    public char ABB() {
	return this.abbreviation;
    }

    public static boolean isValidShipType(String shipTypeName) {
	return (getShipType(shipTypeName) != null);
    }

    public static ShipType getShipType(String shipTypeName) {
	for (ShipType shipType : ShipType.values())
	    if (shipType.NAME().toUpperCase()
		    .equals(shipTypeName.trim().toUpperCase()))
		return shipType;
	return null;
    }

    /**
     * @return length of longest shipType known
     */
    public static int maxLen() {
	int max_ = 0;
	for (ShipType shipType : ShipType.values()) {
	    if (max_ < shipType.LEN()) {
		max_ = shipType.LEN();
	    }
	}
	return max_;
    }

    public static int noShipTypes() {
	return ShipType.values().length;
    }

    public static String shipTypeSummary() {
	String str = "";
	for (ShipType type : ShipType.values()) {
	    str += type.NAME() + "(" + type.COLOR().toString() + ") " + type.LEN()
		    + (type.LEN() == 1 ? " tile " : " tiles ") + "long"
		    + ", hit scores " + type.POINTS() + " points"
		    + System.lineSeparator();
	}
	return str;
    }

}
