/**
 * 
 */
package visualization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import implementation.EqualScoringSystem;
import implementation.HandicapScoringSystem;
import implementation.Player;
import implementation.ScoringSystem;
import implementation.SubstractScoringSystem;

/**
 * @author Pavel Mačák
 *
 */
class ScoringSystemDialog {

    private static String[] names = new String[] { "Equal", "Multiplier handicap",
	    "Substarction handicap" };
    private static String[] tooltips = new String[] {
	    "Both players receive equal ammount of points.",
	    "Starting player receives 90% of the normal points.",
	    "Starting player receives 1 pont less for each hit." };

    JFrame parent;
    ScoringSystem scoringSystem;
    Player pOne;
    Player pTwo;
    JPanel dialogPanel;
    ScoringSystem[] ssOptions;

    ScoringSystemDialog(JFrame parent_, ScoringSystem scoringSystem_, Player pOne_,
	    Player pTwo_) {
	super();
	this.parent = parent_;
	this.scoringSystem = scoringSystem_;
	this.pOne = pOne_;
	this.pTwo = pTwo_;
	dialogPanel = new JPanel();
	dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS));
	ssOptions = new ScoringSystem[] { new EqualScoringSystem(),
		new HandicapScoringSystem(0.9, pOne),
		new SubstractScoringSystem(1, pOne) };
	ButtonGroup group = new ButtonGroup();
	JRadioButton[] radioButtons = new JRadioButton[names.length];
	for (int i = 0; i < radioButtons.length; i++) {
	    radioButtons[i] = new JRadioButton(names[i]);
	    radioButtons[i]
		    .addActionListener(new ChangeSystemActionListener(ssOptions[i]));
	    radioButtons[i].setToolTipText(tooltips[i]);
	    MyUtils.setFontSize(radioButtons[i], 16);

	    dialogPanel.add(radioButtons[i]);
	    group.add(radioButtons[i]);
	}
	radioButtons[0].setSelected(true);

    }

    void showDialog() {
	JOptionPane.showMessageDialog(this.parent, this.dialogPanel, "Scoring system",
		JOptionPane.PLAIN_MESSAGE);
    }

    private class ChangeSystemActionListener implements ActionListener {

	ScoringSystem selectedSystem;

	private ChangeSystemActionListener(ScoringSystem selectedSystem) {
	    super();
	    this.selectedSystem = selectedSystem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    ScoringSystemDialog.this.scoringSystem = this.selectedSystem;
	}

    }

}
