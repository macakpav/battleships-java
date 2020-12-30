/**
 * 
 */
package visualization;

import implementation.ShipType;

/**
 * Enum with some texts. There is probably a smarter way to save long foramted
 * texts in Java, but I haven't looked for it at the moment.
 * 
 * @author Pavel Mačák
 * 
 * 
 */
public enum Texts {

    RULES("Welcome to the Battleships Game!" + System.lineSeparator()
	    + System.lineSeparator()
	    + "In this game you will take the role of two gunners during a naval battle. Players take"
	    + System.lineSeparator()
	    + "turns to shoot on the enemy armada by clicking on hidden (gray) tiles. When a player "
	    + System.lineSeparator()
	    + "mises or completely sinks a ship, the turn goes to the other player. There are multiple "
	    + System.lineSeparator()
	    + "types of ship that the enemy has deployed. Hitting a ship gives you points acording to "
	    + System.lineSeparator()
	    + "its type - the aircraft carriers are the most valuable! Sinking a ship awards you with "
	    + System.lineSeparator()
	    + "double the points normaly awarded for a single hit. The game ends once all enemy ships"
	    + System.lineSeparator() + "are destoyed. " + System.lineSeparator()
	    + System.lineSeparator() + "The Ship Types on board are:"
	    + System.lineSeparator() + System.lineSeparator()
	    + ShipType.shipTypeSummary() + System.lineSeparator()
	    + "There are several options how you can customize your game experience. Firstly you can "
	    + System.lineSeparator()
	    + "choose the size of the battle, randomize ship placement and number or read it from a file."
	    + System.lineSeparator()
	    + "Secondly, there are mutliple scoring systems with different advantages/handicaps. "
	    + System.lineSeparator() + System.lineSeparator()
	    + "Have fun and enjoy the game! " + System.lineSeparator()
	    + System.lineSeparator() + "Pavel Mačák, 2020 ");

    Texts(String str) {
	this.str = str;
    }

    String str;

    public String str() {
	return str;
    }

}
