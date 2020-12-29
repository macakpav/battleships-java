/**
 * 
 */
package visualization;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.HighScoreManager;

/**
 * @author Pavel Mačák
 *
 */
class HighScoresDialog {

    static void showDialog(JFrame parent, HighScoreManager hsManager) {

	JLabel[][] labels;
	if (hsManager.scoresCount() > 10) {
	    labels = new JLabel[10][2];
	} else {
	    labels = new JLabel[hsManager.scoresCount()][2];
	}
	for (int i = 0; i < labels.length; i++) {
	    labels[i][0] = new JLabel((i + 1) + ". " + hsManager.getName(i));
	    MyUtils.setFontSize(labels[i][0], 22);
	    labels[i][1] = new JLabel(hsManager.getScoreString(i));
	    MyUtils.setFontSize(labels[i][1], 22);
	}
	GridLayout layout = new GridLayout(labels.length, 2);
	layout.setHgap(20);
	layout.setVgap(5);
	JPanel panel = new JPanel(layout);
	for (JLabel[] jLabels : labels) {
	    panel.add(jLabels[0]);
	    panel.add(jLabels[1]);
	}
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

	JOptionPane.showMessageDialog(parent, panel, "High Scores",
		JOptionPane.PLAIN_MESSAGE);
    }
}
