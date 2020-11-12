/**
 * 
 */
package application;

import implementation.Board;

/**
 * @author macakpav
 *
 */
public class Control {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Board myBoard = new Board(new BoardSetup());
	System.out.print(myBoard);
    }

}
