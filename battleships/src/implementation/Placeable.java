/**
 * 
 */
package implementation;

/**
 * Objects that can have a place on the board like i.e. ships.
 * 
 * @author Pavel Mačák
 *
 */
public abstract interface Placeable {
    /**
     * @return Placement of the object.
     */
    Placement getPlacement();

    /**
     * @return Color of object.
     */
    TileColor getColor();

    /**
     * @return Representation of object with one character.
     */
    char charRepresentation();
}
