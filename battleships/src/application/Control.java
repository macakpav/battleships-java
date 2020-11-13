/**
 * 
 */
package application;

import java.io.IOException;

import implementation.Board;
import implementation.EqualScoringSystem;
import implementation.Game;

/**
 * @author Pavel Mačák
 *
 */
public class Control {

    /**
     * @param args
     */
    public static void main(String[] args) {
	try {
	    BoardSetup myBoardSetup = new BoardSetup("src/application/input.txt");
	    Board myBoard = new Board(myBoardSetup);
	    Game myGame = new Game(myBoard, new EqualScoringSystem());
	    myGame.consolePrint();
	    myGame.clickOnTile(myBoard.tile(1, 2));
	    myGame.consolePrint();
	} catch (IOException io) {
	    System.out.println(io);
	    System.out.println("Could not read board setup from file!");
	    io.printStackTrace();
	}

    }

}
