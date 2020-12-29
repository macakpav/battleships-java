/**
 * 
 */
package implementation;

import java.awt.Color;

/**
 * Colors that a tile can have.
 * 
 * @author Pavel Mačák
 *
 */
public enum TileColor {
    WATER(Color.BLUE),
    FOW(Color.GRAY),
    WHITE(Color.WHITE),
    GREEN(Color.GREEN),
    RED(Color.RED),
    PINK(Color.PINK),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    NONE(null);

    public Color color;

    TileColor(Color color) {
	this.color = color;
    }

    public Color COLOR() {
	return this.color;
    }

    @Override
    public String toString() {
	return this.name().toLowerCase();
    }
}
