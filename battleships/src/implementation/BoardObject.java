/**
 * 
 */
package implementation;

/**
 * An object that can be placed on board and represent something - a ship, a
 * rock, a plane, a fort etc.
 * 
 * @author Pavel Mačák
 *
 */
public abstract class BoardObject implements Placeable {
    private int id;
    private Placement placement;

    public BoardObject(int id, Placement placement) {
	this.id = id;
	this.placement = placement;
    }

    /**
     * @return ID of this ship.
     */
    int id() {
	return this.id;
    }

    /**
     * @return Placement of this ship.
     */
    @Override
    public Placement getPlacement() {
	return this.placement;
    }

    /**
     * "id=" + this.id + ", placement=" + this.placement
     */
    @Override
    public String toString() {
	return "id=" + this.id + ", placement=" + this.placement;
    }

}
