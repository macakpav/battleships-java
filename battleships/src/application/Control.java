/**
 * 
 */
package application;

import implementation.Board;
import implementation.EqualScoringSystem;
import implementation.Game;

/**
 * Main class controlling the flow of the program.
 * 
 * @author Pavel Mačák
 *
 */
public class Control {

    /**
     * @param args
     */
    public static void main(String[] args) {

	BoardSetup myBoardSetup = new BoardSetup();
//	if (myBoardSetup.initilazeFromFile("src/application/input.txt")) {
//	    Board myBoard = new Board(myBoardSetup);
//	    Game myGame = new Game(myBoard, new EqualScoringSystem());
//	    myGame.consolePrint();
//	    myGame.clickOnTile(myBoard.tile(1, 2));
//	    myGame.consolePrint();
//	}

	if (myBoardSetup.randomInit(6, 6)) {
	    Board myBoard = new Board(myBoardSetup);
	    Game myGame = new Game(myBoard, new EqualScoringSystem());
	    myGame.consolePrint();
	    myGame.clickOnTile(myBoard.tile(1, 2));
	    myGame.consolePrint();
	}

    }

}
