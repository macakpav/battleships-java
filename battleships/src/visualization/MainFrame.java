/**
 * 
 */
package visualization;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import application.BoardSetup;
import application.HighScoreManager;
import implementation.EqualScoringSystem;
import implementation.Game;
import implementation.IScoringSystem;
import implementation.Player;

/**
 * The main menu window.
 * 
 * @author Pavel Macak
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String title = "The Battleship Game - Menu Screen",
			placementSettings = "Choose Ship Placement and Board Size",
			rules = Texts.RULES.str();

	// GUI objects
	private final JLabel lblWelcome, lblComment, lblP1, lblP2;

	private final JButton btnPlacement, btnScoring, btnStart, btnRules, btnHighScores,
			btnExit;

	private final JTextField txtP1, txtP2;

	private final JTextArea txtRules;

	private final JPanel p1, p2, pNames, pNamesSub1, pNamesSub2, p3, p4;

	// pop-up dialogs
	private final ScoringSystemDialog scoringSystemDialog;
	private final PlacementDialog placementDialog;

	// game objects
	private IScoringSystem iScoringSystem;
	private final HighScoreManager highScoreManager;
	private BoardSetup boardSetup;
	private Player playerOne, playerTwo;
	private SwingWorker<Boolean, Integer> gameBackgroundTask;

	public MainFrame() {
		super(title);
		iScoringSystem = new EqualScoringSystem();
		boardSetup = new BoardSetup();
		highScoreManager = new HighScoreManager();
		playerOne = new Player("Frodo");
		playerTwo = new Player("Gandalf");
		scoringSystemDialog = new ScoringSystemDialog(this, this.playerOne,
				this.playerTwo);
		placementDialog = new PlacementDialog(this, this.boardSetup);

		this.setPreferredSize(new Dimension(900, 300));

		lblWelcome = new JLabel("Welcome to The Battleship Game");
		MyUtils.setFontSize(lblWelcome, 24);
		lblComment = new JLabel("Please select your options and get started.");
		MyUtils.setFontSize(lblComment, 16);

		p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		p1.add(lblWelcome);
		lblComment.setAlignmentX(Component.CENTER_ALIGNMENT);
		p1.add(lblComment);
		p1.setBorder(BorderFactory.createEmptyBorder(10, 50, 5, 50));

		btnPlacement = new JButton(placementSettings);
		MyUtils.setFontSize(btnPlacement, 18);
		btnPlacement.setPreferredSize(btnPlacement.getPreferredSize());
		btnPlacement.addActionListener(new OpenPlacementWindow());
		btnScoring = new JButton("Choose Scoring System");
		btnScoring.setPreferredSize(btnPlacement.getPreferredSize());
		MyUtils.setFontSize(btnScoring, 18);
		btnScoring.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				iScoringSystem = MainFrame.this.scoringSystemDialog.showDialog();
			}
		});

		p2 = new JPanel(new FlowLayout());
		p2.add(btnPlacement);
		p2.add(btnScoring);
		p2.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));

		lblP1 = new JLabel("Starting player's name:");
		lblP2 = new JLabel("Second player's name:");
		txtP1 = new JTextField(playerOne.getName(), 12);
		txtP2 = new JTextField(playerTwo.getName(), 12);
		MyUtils.setFontSize(lblP1, 16);
		MyUtils.setFontSize(lblP2, 16);
		MyUtils.setFontSize(txtP1, 16);
		MyUtils.setFontSize(txtP2, 16);

		pNamesSub1 = new JPanel(new GridLayout(1, 2));
		pNamesSub1.add(lblP1);
		pNamesSub1.add(txtP1);
		pNamesSub2 = new JPanel(new GridLayout(1, 2));
		pNamesSub2.add(lblP2);
		pNamesSub2.add(txtP2);

		pNames = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
		pNames.add(pNamesSub1);
		pNames.add(pNamesSub2);

		btnStart = new JButton("Start new game");
		MyUtils.setFontSize(btnStart, 24);
		btnStart.addActionListener(new OpenNewGameWindow());

		p3 = new JPanel(new FlowLayout());
		p3.add(btnStart);
		p3.setBorder(BorderFactory.createEmptyBorder(0, 50, 5, 50));

		txtRules = new JTextArea(rules);
		MyUtils.setFontSize(txtRules, 24);
		btnRules = new JButton("Rules");
		btnRules.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, txtRules,
						"The Battleship Game - Rules", JOptionPane.PLAIN_MESSAGE);
			}
		});

		btnHighScores = new JButton("High scores");
		btnHighScores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HighScoresDialog.showDialog(MainFrame.this, highScoreManager);
			}
		});
		btnExit = new JButton("Exit game");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
			}
		});

		Dimension btnsOnP4size = btnHighScores.getPreferredSize();

		btnRules.setPreferredSize(btnsOnP4size);
		btnHighScores.setPreferredSize(btnsOnP4size);
		btnExit.setPreferredSize(btnsOnP4size);

		p4 = new JPanel(new FlowLayout());
		p4.add(btnRules);
		p4.add(btnHighScores);
		p4.add(btnExit);
		p4.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));

		this.getContentPane()
				.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		p1.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getContentPane().add(p1);
		p2.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getContentPane().add(p2);
		pNames.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getContentPane().add(pNames);
		p3.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getContentPane().add(p3);
		p4.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.getContentPane().add(p4);

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
//	this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	private class OpenPlacementWindow implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			btnPlacement.setText("Please wait...");
			placementDialog.openDialog();
			btnPlacement.setText(placementSettings);
		}
	}

	private class OpenNewGameWindow implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (gameBackgroundTask == null) {
				gameBackgroundTask = new SwingWorker<Boolean, Integer>() {

					@Override
					protected Boolean doInBackground() throws Exception {
						btnStart.setText("Starting...");

						playerOne.setName(txtP1.getText().trim());
						playerTwo.setName(txtP2.getText().trim());

						new GameFrame(MainFrame.this, highScoreManager, new Game(
								boardSetup, iScoringSystem, playerOne, playerTwo));
						return true;
					}

					@Override
					public void done() {
						btnStart.setText("Start new game");
						gameBackgroundTask = null;
					}

				};
				gameBackgroundTask.execute();
			} else {
				gameBackgroundTask.cancel(true);
			}
		}
	}

	@Override
	public void dispose() {
		int decision = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to exit the game?", "Exit program",
				JOptionPane.YES_NO_OPTION);
		if (decision == JOptionPane.YES_OPTION) {
			super.dispose();
		}
	}
}
