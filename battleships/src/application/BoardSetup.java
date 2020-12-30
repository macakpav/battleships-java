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
 * @author Pavel Macak
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
		this.noCols = 8;
		this.noRows = 8;
		randomInit(this.noRows, this.noCols);
	}

	/**
	 * Deep copy constructor.
	 * 
	 * @param setup
	 */
	public BoardSetup(BoardSetup that) {
		this.noCols = that.noCols;
		this.noRows = that.noRows;
		this.shipList = new ShipList(that.shipList);
	}

	public boolean initilazeFromFile() {
		return this.initilazeFromFile("src/application/input.txt");
	}

	public boolean initilazeFromFile(String pathName) {
		this.shipList = new ShipList();
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
				if (shipType == null)
					throw new IOException("Invalid name of ship type:"
							+ processedLine.getShipTypeName());

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
	 */
	public boolean randomInit(int rows, int cols) {
		return randomInit(rows, cols, new ShipCounter());
	}

	/**
	 * Puts randomly each ship type in quantity specified by shipCounter.
	 * 
	 */
	public boolean randomInit(int rows, int cols, ShipCounter shipCounter) {
		assert (rows >= ShipType.maxLen() || cols >= ShipType.maxLen());
		this.noRows = rows;
		this.noCols = cols;
		Random rand = new Random(); // 4687
		boolean success = false;
		ShipList randList;
		for (int outerLoop = 0; outerLoop < BoardSetup.maxAttempts; outerLoop++) {
			randList = new ShipList();
			for (ShipType shipType : ShipType.values()) {
				for (int count = 0; count < shipCounter
						.getShipCount(shipType); count++) {

					success = false;
					for (int i = 0; i < BoardSetup.maxAttempts; i++) {
						if (rand.nextBoolean() && this.noRows >= shipType.LEN()) {
							// put ship vertically
							int row = rand.nextInt(this.noRows - shipType.LEN() + 1)
									+ 1;
							// minimum for x is one
							int col = rand.nextInt(this.noCols - 1) + 1;
							try {
								randList.addShip(shipType, col, row, col,
										row + shipType.LEN() - 1);
							} catch (IllegalArgumentException ia) {
								continue;
							}
							success = true;
							break;

						}
						// put ship horizontally
						int row = rand.nextInt(this.noRows - 1) + 1;
						// minimum for y is one
						int col = rand.nextInt(this.noCols - shipType.LEN() + 1) + 1;
						try {
							randList.addShip(shipType, col, row,
									col + shipType.LEN() - 1, row);
						} catch (IllegalArgumentException ia) {
							continue;
						}
						success = true;
						break;
					}
					if (!success) {
						System.out.println(
								"Failed to randomly fit ship " + shipType.NAME());
						return false;
					}
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
	public int getCols() {
		return this.noCols;
	}

	/**
	 * @return the number of tiles in y direction
	 */
	public int getRows() {
		return this.noRows;
	}

	/**
	 * @return the overall number of tiles
	 */
	public int getSize() {
		return this.noCols * this.noRows;
	}

	/**
	 * Copy contents without changing address.
	 * 
	 * @param temp
	 */
	public void copy(BoardSetup that) {
		this.noCols = that.noCols;
		this.noRows = that.noRows;
		this.shipList = new ShipList(that.shipList);
	}

}
