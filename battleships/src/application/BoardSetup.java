/**
 * 
 */
package application;

import java.io.File;
import java.io.IOException;
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
    private final ShipList shipList;
    private int sizeX, sizeY;

    public BoardSetup() {
	this.sizeX = 6;
	this.sizeY = 8;

	this.shipList = new ShipList();
	defualtInitialization();
    }

    public BoardSetup(String filePath) throws IOException {
	this.sizeX = 6;
	this.sizeY = 8;

	this.shipList = new ShipList();
	this.initilazeFromFile(filePath);
    }

    private void defualtInitialization() {
	this.shipList.addShip(ShipType.CARRIER, 1, 1, 1, 5);
	this.shipList.addShip(ShipType.BATTLESHIP, 2, 1, 2, 4);
	this.shipList.addShip(ShipType.SUBMARINE, 3, 1, 3, 3);
	this.shipList.addShip(ShipType.DESTROYER, 4, 1, 4, 2);
    }

    public boolean initilazeFromFile() throws IOException {
	return this.initilazeFromFile(getFile());
    }

    public boolean initilazeFromFile(String pathName) throws IOException {
	return this.initilazeFromFile(getFile(pathName));
    }

    public boolean initilazeFromFile(File inputFile) {
	try (Scanner scan = new Scanner(inputFile)) {
	    if (!scan.hasNextInt())
		throw new IOException("Expected an integer on first place!");

	    // get the Board sizes
	    this.sizeX = scan.nextInt();
	    if (scan.hasNextInt())
		this.sizeY = scan.nextInt();
	    else
		this.sizeY = this.sizeX;

	    scan.skip("\\s?\\n");
	    InputFileLine processedLine = new InputFileLine();
	    String line = "";

	    while (scan.hasNextLine()) {

		line = scan.nextLine();
		if (line.trim().equals(""))
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
	    System.out.println("Reading file " + inputFile + " has failed.");
	    System.out.println(ex);
	    ex.printStackTrace();
	}
	return false;
    }

    private static File getFile() throws IOException {
	return getFile("src/application/input.txt");
    }

    private static File getFile(String pathName) throws IOException {
	File file = new File(pathName);
	if (file.exists() && !file.isDirectory())
	    return file;
	throw new IOException(
		"Could not find a file on specified path:\n" + pathName);
    }

    public void randomInit() {

    }

    /**
     * @return the shipList
     */
    public ShipList getShipList() {
	return this.shipList;
    }

    /**
     * @return the sizeX
     */
    public int getSizeX() {
	return this.sizeX;
    }

    /**
     * @return the sizeY
     */
    public int getSizeY() {
	return this.sizeY;
    }

}
