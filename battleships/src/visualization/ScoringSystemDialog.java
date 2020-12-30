
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
import implementation.IScoringSystem;
import implementation.MultiplierScoringSystem;
import implementation.Player;
import implementation.SubstractScoringSystem;

/**
 * Dialog for choosing the scoring system.
 * 
 * @author Pavel Macak
 *
 */
class ScoringSystemDialog {

	private static String[] names = new String[] { "Equal", "Multiplier handicap",
			"Substarction handicap" };
	private static String[] tooltips = new String[] {
			"Both players receive equal ammount of points.",
			"Starting player receives 90% of the normal points.",
			"Starting player receives 50 points less for each hit." };

	private JFrame parent;
	private final Player pOne;
	private final Player pTwo;
	private JPanel dialogPanel;
	private IScoringSystem[] ssOptions;
	private IScoringSystem selectedSystem;

	ScoringSystemDialog(JFrame parent_, Player pOne_, Player pTwo_) {
		super();
		this.parent = parent_;
		this.pOne = pOne_;
		this.pTwo = pTwo_;
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.PAGE_AXIS));
		ssOptions = new IScoringSystem[] { new EqualScoringSystem(),
				new MultiplierScoringSystem(0.9, pOne),
				new SubstractScoringSystem(50, pOne) };
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

	IScoringSystem showDialog() {
		JOptionPane.showMessageDialog(this.parent, this.dialogPanel, "Scoring system",
				JOptionPane.PLAIN_MESSAGE);
		return selectedSystem;
	}

	private class ChangeSystemActionListener implements ActionListener {

		private IScoringSystem btnSystem;

		private ChangeSystemActionListener(IScoringSystem selectedSystem) {
			super();
			this.btnSystem = selectedSystem;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ScoringSystemDialog.this.selectedSystem = this.btnSystem;
		}

	}

}
