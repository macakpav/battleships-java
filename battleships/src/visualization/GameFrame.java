/**
 * 
 */
package visualization;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import application.HighScoreManager;
import implementation.Game;
import implementation.ShipType;

/**
 * JFrame extension a window for actually playing the game.
 * 
 * @author Pavel Mačák
 *
 */
class GameFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String title = "The Battleship Game";

    private static final int prefTilesSize = 115;

    private final JFrame parentFrame;
    private final Game game;
    private final HighScoreManager hsManager;

    private final JButton btnHighScores, btnQuitGame, btnShipTypes;
    private final JLabel lblPlayerOne, lblPlayerTwo, lblTurn, lblPlayerOneScore,
	    lblPlayerTwoScore, lblPlayerOnTurn, lblStatusBar;
    private final JButton[] tiles;
    private final JPanel p1, p1btnH, p1btnQ, p1btnR, p2, pWrapper, pStatusBar;

    GameFrame(JFrame parent_, HighScoreManager hsManager_, Game game_) {
	super(title);
	this.parentFrame = parent_;
	this.hsManager = hsManager_;
	this.game = game_;
	int noCols = this.game.getCols();
	int noRows = this.game.getRows();
	int prefWidth = noCols * prefTilesSize, prefHeight = noRows * prefTilesSize;
	setPreferredSize(new Dimension(prefWidth, prefHeight + 100));

	btnHighScores = new JButton("High Scores");
	MyUtils.setFontSize(btnHighScores, 16);
	btnHighScores.setPreferredSize(new Dimension(140, 35));
	btnHighScores.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		HighScoresDialog.showDialog(GameFrame.this, GameFrame.this.hsManager);
	    }
	});

	btnShipTypes = new JButton("Ship types");
	MyUtils.setFontSize(btnShipTypes, 16);
	btnShipTypes.setPreferredSize(btnHighScores.getPreferredSize());
	btnShipTypes.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(GameFrame.this,
			ShipType.shipTypeSummary(), "Ship types",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	});

	btnQuitGame = new JButton("Quit Game");
	MyUtils.setFontSize(btnQuitGame, 16);
	btnQuitGame.setPreferredSize(btnHighScores.getPreferredSize());
	btnQuitGame.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		GameFrame.this.dispose();

	    }
	});

	lblPlayerOne = new JLabel("Score of " + game.playerOneName(),
		SwingConstants.CENTER);
	lblPlayerTwo = new JLabel("Score of " + game.playerTwoName(),
		SwingConstants.CENTER);
	lblTurn = new JLabel("It is turn of:", SwingConstants.CENTER);
	lblPlayerOneScore = new JLabel(game.playerOneScore(), SwingConstants.CENTER);
	lblPlayerTwoScore = new JLabel(game.playerTwoScore(), SwingConstants.CENTER);
	lblPlayerOnTurn = new JLabel(game.playerOnTurnName(), SwingConstants.CENTER);
	MyUtils.setFontSize(lblPlayerOneScore, 22);
	lblPlayerOnTurn.setFont(
		new Font(lblPlayerOnTurn.getFont().getName(), Font.BOLD, 24));
	MyUtils.setFontSize(lblPlayerTwoScore, 22);

	for (JLabel lbl : new JLabel[] { lblPlayerOne, lblTurn, lblPlayerTwo })
	    MyUtils.setFontSize(lbl, 18);

	p1 = new JPanel(new GridLayout(2, 5));
	p1btnH = new JPanel(new FlowLayout(FlowLayout.LEFT));
	p1btnH.add(btnHighScores);
	p1.add(p1btnH);
	p1.add(lblPlayerOne);
	p1.add(lblTurn);
	p1.add(lblPlayerTwo);
	p1btnQ = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	p1btnQ.add(btnQuitGame);
	p1.add(p1btnQ);
	p1btnR = new JPanel(new FlowLayout(FlowLayout.LEFT));
	p1btnR.add(btnShipTypes);
	p1.add(p1btnR);
	p1.add(lblPlayerOneScore);
	p1.add(lblPlayerOnTurn);
	p1.add(lblPlayerTwoScore);
	p1.add(new JLabel(""));
	p1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	GridLayout p2Layout = new GridLayout(noRows, noCols);
	p2Layout.setHgap(2);
	p2Layout.setVgap(2);
	p2 = new JPanel(p2Layout);
	tiles = new JButton[noCols * noRows];
	int j = 0;
	for (JButton btn : tiles) {
	    btn = new JButton();
	    btn.setBackground(GameFrame.this.game.getTileColor(j));
	    btn.addActionListener(new TileActionListener(j));
//	    btn.setText(String.valueOf(j));
	    p2.add(btn);
//	    btn.setPreferredSize(new Dimension(1000 / noCols, 1000 / noRows));
	    j++;
	}

	p2.setPreferredSize(new Dimension(noCols * 100, noRows * 100));
	p2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	pWrapper = new JPanel();
	pWrapper.setLayout(new BoxLayout(pWrapper, BoxLayout.PAGE_AXIS));
	pWrapper.add(p1);
	pWrapper.add(p2);

	lblStatusBar = new JLabel("Game ready.");
	MyUtils.setFontSize(lblStatusBar, 12);
	pStatusBar = new JPanel();
	pStatusBar.setPreferredSize(new Dimension(getWidth(), 18));
	pStatusBar.setLayout(new BoxLayout(pStatusBar, BoxLayout.X_AXIS));
	pStatusBar.add(lblStatusBar);
	lblStatusBar.setHorizontalAlignment(SwingConstants.LEFT);
	pStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));

	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(pWrapper, BorderLayout.CENTER);
	getContentPane().add(pStatusBar, BorderLayout.SOUTH);

	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	pack();
//	setResizable(false);
	setLocationRelativeTo(null);
	parentFrame.setVisible(false);
	setVisible(true);
    }

    private class TileActionListener implements ActionListener {

	private final int id;

	public TileActionListener(int id) {
	    this.id = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    assert (e.getSource() instanceof JButton);
	    GameFrame.this.game.clickOnTile(id);
	    JButton btn = (JButton) e.getSource();
	    btn.setBackground(GameFrame.this.game.getTileColor(id));
	    GameFrame.this.updateCounters();
	    if (game.isOver()) {
		GameFrame.this.gameOver();
	    }
	}

    }

    @Override
    public void dispose() {
	int decision;
	if (!game.isOver())
	    decision = JOptionPane.showConfirmDialog(GameFrame.this,
		    "Are you sure you want to exit the game and return to menu?",
		    "Quit game", JOptionPane.YES_NO_OPTION);
	else
	    decision = JOptionPane.YES_OPTION;
	if (decision == JOptionPane.YES_OPTION) {
	    game.resetPlayers();
	    super.dispose();
	    parentFrame.setVisible(true);
	}
    }

    private void updateCounters() {
	this.lblPlayerOneScore.setText(game.playerOneScore());
	this.lblPlayerTwoScore.setText(game.playerTwoScore());
	this.lblPlayerOnTurn.setText(game.playerOnTurnName());
	this.lblStatusBar.setText(game.getComment());
    }

    private void gameOver() {
	assert (game.isOver());
	String str = "The game is over!" + System.lineSeparator();
	if (game.isTie())
	    str += "It is a tie!" + System.lineSeparator();
	else
	    str += game.getWinnerName() + " is the winner today!";

	str += System.lineSeparator() + "Do you want to save the score of "
		+ game.getWinnerName() + " to high scores?" + System.lineSeparator();

	int decision = JOptionPane.showConfirmDialog(this, str, "Game Over",
		JOptionPane.YES_NO_OPTION);
	if (decision == JOptionPane.YES_NO_OPTION) {
	    hsManager.addHighScore(game.getWinnerName(), game.getWinnerScore());
	}
	JOptionPane.showMessageDialog(this, "The game will now return to main menu.",
		"Return to menu", JOptionPane.INFORMATION_MESSAGE);

	dispose();
    }

}
