/**
 * 
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Class managing high scores, loading and saving them to a file.
 * 
 * @author Pavel Mačák
 *
 */
public class HighScoreManager {

    private static final String savePath = "./highScores.txt",
	    separator = HighScore.separator;
    private static final int maxHScount = 10;

    private final ArrayList<HighScore> highScores;

    public HighScoreManager() {
	this.highScores = new ArrayList<HighScore>();
	readHighScores();
    }

    public void addHighScore(String playerName, double score) {
	this.highScores.add(new HighScore(playerName, score));
	sort();
	this.highScores.subList(maxHScount, this.highScores.size()).clear();
	saveHighScores();
    }

    private boolean saveHighScores() {
	File file = new File(savePath);
	try {
	    if (file.isDirectory()) {
		System.out.println(
			"Couldn’t create high scores file. Directory in place.");
		return false;
	    }

	    if (!file.exists() && !file.createNewFile()) {
		System.out.println("Couldn’t create high scores file");
		return false;
	    }
	} catch (IOException e) {
	    System.out.println("Couldn’t create high scores file. IO Exception.");
	    e.printStackTrace();
	    return false;
	}
	try (Writer out = new FileWriter(file, false)) {
	    out.write(this.toString());
	    return true;
	} catch (IOException io) {
	    System.out.println("Error creating high scores file.");
	    return false;
	}

    }

    private void readHighScores() {
	String line;
	File file = new File(savePath);
	if (!file.exists()) {
	    for (int i = 0; i < 10; i++) {
		this.highScores.add(new HighScore("Nobody", 0));
	    }
	    saveHighScores();
	    return;
	}

	try (Scanner scan = new Scanner(file)) {

	    while (scan.hasNextLine()) {
		line = scan.nextLine().trim();
		if (line.equals(""))
		    continue;
		String[] buffer = line.split(separator);
		if (buffer.length < 2)
		    continue;
		try {
		    this.highScores.add(new HighScore(buffer[0].trim(),
			    Double.parseDouble(buffer[1].trim())));
		} catch (NumberFormatException nf) {
		    nf.printStackTrace();
		    continue;
		}
	    }
	    if (this.highScores.size() < 1) {
		for (int i = 0; i < 10; i++) {
		    this.highScores.add(new HighScore("Nobody", 0));
		}
	    }
	    sort();

	} catch (FileNotFoundException fnf) {
	    fnf.printStackTrace();
	}
    }

    /**
     * 
     */
    private void sort() {
	this.highScores.sort(new Comparator<HighScore>() {
	    @Override
	    public int compare(HighScore o1, HighScore o2) {
		if (o1.getScore() < o2.getScore())
		    return 1;
		if (o1.getScore() == o2.getScore())
		    return 0;
		return -1;
	    }
	});
    }

    @Override
    public String toString() {
	String str = "";
	for (HighScore highScore : this.highScores) {
	    str += highScore.toString() + System.lineSeparator();
	}
	return str;
    }

    public int scoresCount() {
	return this.highScores.size();
    }

    public String getName(int i) {
	assert (i < this.highScores.size());
	return this.highScores.get(i).getPlayerName();
    }

    public String getScoreString(int i) {
	assert (i < this.highScores.size());
	return String.valueOf(this.highScores.get(i).getScore());
    }

}
