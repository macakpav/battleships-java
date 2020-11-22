/**
 * 
 */
package application;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import implementation.ShipList;
import implementation.ShipType;

/**
 * Class holding necessary variables to initialize an instance of Board.
 * 
 * @apiNote (TODO) Initialization from file. (TODO) Initialization from GUI
 *          elements.
 * 
 * @author Pavel Mačák
 *
 */
public class BoardSetup {
    private ShipList shipList;
    private int noCols, noRows;

    /**
     * Maximum number of attempts when generating a random ship distribution.
     */
    static private final int maxAttempts = 20;

    public BoardSetup() {
	this.noCols = 0;
	this.noRows = 0;
	this.shipList = new ShipList();
    }

    public BoardSetup(int x, int y) {
	this.noCols = x;
	this.noRows = y;
	this.shipList = new ShipList();
    }

    public void defualtInitialization() {
	this.noCols = 8;
	this.noRows = 8;
	this.shipList.addShip(ShipType.CARRIER, 1, 1, 1, 5);
	this.shipList.addShip(ShipType.BATTLESHIP, 2, 1, 2, 4);
	this.shipList.addShip(ShipType.SUBMARINE, 3, 1, 3, 3);
	this.shipList.addShip(ShipType.DESTROYER, 4, 1, 4, 2);
    }

    public boolean initilazeFromFile() {
	return this.initilazeFromFile("src/application/input.txt");
    }

    public boolean initilazeFromFile(String pathName) {
	String line = "";

	try (Scanner scan = new Scanner(new File(pathName))) {
	    scan.skip("\n|\\s*");
	    if (!scan.hasNextInt()) {
		System.out.println(scan.nextLine());
		throw new IOException("Expected an integer at the beginning!");
	    }

	    // get the Board sizes
	    this.noRows = scan.nextInt();
	    if (scan.hasNextInt())
		this.noCols = scan.nextInt();
	    else
		this.noCols = this.noRows;

	    scan.skip("\\s?\\n?");
	    InputFileLine processedLine = new InputFileLine();

	    while (scan.hasNextLine()) {

		line = scan.nextLine().trim();
		if (line.startsWith("//")) // comment sign
		    continue;
		if (line.equals(""))
		    continue;
		processedLine.readLine(line);

		ShipType shipType = ShipType
			.getShipType(processedLine.getShipTypeName());
//		System.out.println(processedLine.getShipTypeName());
		if (shipType == null)
		    throw new IOException("Invalid name of ship type:"
			    + processedLine.getShipTypeName());

//		System.out.println(shipType);
//		for (int i = 0; i < processedLine.getCoordinates().length; i++) {
//		    System.out.println(processedLine.getCoordinates()[i]);
//		}
		this.shipList.addShip(shipType, processedLine.getCoordinates());
	    }
	    return true;
	} catch (Exception ex) {
	    System.out.println(
		    "Reading file " + pathName + " has failed on line:\n" + line);
	    System.out.println(ex);
	    ex.printStackTrace();
	}
	return false;
    }

    /**
     * Puts randomly one of each ship type.
     * 
     * @TODO Check if ship can fit horizontally or vertically (in case of small
     *       board)
     */
    public boolean randomInit(int rows, int cols) {
	assert (rows >= ShipType.maxLen() || cols >= ShipType.maxLen());
	this.noRows = rows;
	this.noCols = cols;
	Random rand = new Random(); // 4687
	boolean success = false;
	ShipList randList;
	for (int outerLoop = 0; outerLoop < BoardSetup.maxAttempts; outerLoop++) {
	    randList = new ShipList();
	    for (ShipType shipType : ShipType.values()) {
		success = false;
		for (int i = 0; i < BoardSetup.maxAttempts; i++) {
		    if (rand.nextBoolean() && this.noRows >= shipType.LEN()) {
			// put ship vertically
			int row = rand.nextInt(this.noRows - shipType.LEN() + 1) + 1;
//			System.out.println(x);
			// minimum for x is one
			int col = rand.nextInt(this.noCols - 1) + 1;
			try {
			    randList.addShip(shipType, col, row, col,
				    row + shipType.LEN() - 1);
			} catch (IllegalArgumentException ia) {
			    continue;
			}
//			System.out.println("Placed " + shipType.NAME() + " to "
//				+ randList.toString());
			success = true;
			break;

		    }
		    // put ship horizontally
		    int row = rand.nextInt(this.noRows - 1) + 1;
		    // minimum for x is one
		    int col = rand.nextInt(this.noCols - shipType.LEN() + 1) + 1;
//		    System.out.println(y);
		    try {
			randList.addShip(shipType, col, row, col + shipType.LEN() - 1,
				row);
		    } catch (IllegalArgumentException ia) {
			continue;
		    }
//		    System.out.println("Placed " + shipType.NAME() + " to "
//			    + randList.toString());
		    success = true;
		    break;
		}
		if (!success) {
		    System.out.println(
			    "Failed to randomly fit ship " + shipType.NAME());
		    break;
		}
	    }
	    if (success) {
		System.out.println("Successful random initialization of the board.");
		this.shipList = randList;
		break;
	    }
	}
	return success;
    }

    /**
     * @return the shipList
     */
    public ShipList getShipList() {
	return this.shipList;
    }

    /**
     * @return the number of tiles in x direction
     */
    public int getSizeX() {
	return this.noCols;
    }

    /**
     * @return the number of tiles in y direction
     */
    public int getSizeY() {
	return this.noRows;
    }

}
