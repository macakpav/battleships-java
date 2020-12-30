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
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import application.BoardSetup;
import application.ShipCounter;
import implementation.ShipType;

/**
 * Dialog window for setting ship placement and ship numbers.
 * 
 * @author Pavel Mačák
 * 
 *
 */
class PlacementDialog {

    boolean changed, warned, applyFailed;

    private static final String title = "The Battleship Game - Ship Placement Settings";

    private final BoardSetup setup;

    private final JDialog dialog;
    private final JLabel[] lblShipTypes;
    private final ShipCountSpinner[] spinShipCounts;
    private final JButton btnFilePath, btnApply, btnApplyClose, btnClose,
	    btnChoseShipNumbers;
    private final JLabel lblTitle, lblBoardSize, lblRows, lblCols;
    private final JRadioButton rbRandomInit, rbFileInit;
    private final JSpinner spinRows, spinCols;
    private final JTextField txtFilePath;
    private final JFileChooser fileChooser;
    private final JPanel p1, p11, p12, pBoardSize, p2, p21, p3, pShipNumbers;
    private SwingWorker<Boolean, Integer> randomInitBackgroundTask;

    private ShipCounter shipCounter;

    PlacementDialog(JFrame parentFrame_, BoardSetup setup_) {
	shipCounter = new ShipCounter();
	dialog = new JDialog(parentFrame_, title);
	this.setup = setup_;
	lblTitle = new JLabel("Ship placement Settings");
	MyUtils.setFontSize(lblTitle, 24);

	rbRandomInit = new JRadioButton("Random placement of ships");
	MyUtils.setFontSize(rbRandomInit, 18);

	lblBoardSize = new JLabel("Choose board size");
	MyUtils.setFontSize(lblBoardSize, 18);

	lblRows = new JLabel("Rows:");
	MyUtils.setFontSize(lblRows, 16);
	spinRows = new JSpinner(new SpinnerNumberModel(setup.getRows(), 6, 64, 1));
	spinRows.addChangeListener(new StatusChangedListener());
	MyUtils.setFontSize(spinRows, 16);
	p11 = new JPanel(new GridLayout(1, 2));
	p11.add(lblRows);
	p11.add(spinRows);

	lblCols = new JLabel("Columns:");
	MyUtils.setFontSize(lblCols, 16);
	spinCols = new JSpinner(new SpinnerNumberModel(setup.getCols(), 6, 64, 1));
	spinCols.addChangeListener(new StatusChangedListener());
	MyUtils.setFontSize(spinCols, 16);
	p12 = new JPanel(new GridLayout(1, 2));
	p12.add(lblCols);
	p12.add(spinCols);

	lblShipTypes = new JLabel[ShipType.noShipTypes()];
	spinShipCounts = new ShipCountSpinner[lblShipTypes.length];
	pShipNumbers = new JPanel(new GridLayout(lblShipTypes.length, 2));

	int i = 0;
	for (ShipType type : ShipType.values()) {
	    lblShipTypes[i] = new JLabel(type.NAME());
	    spinShipCounts[i] = new ShipCountSpinner(
		    new SpinnerNumberModel(shipCounter.getShipCount(type), 0, 24, 1));
	    spinShipCounts[i].setShipType(type);
	    spinShipCounts[i].addChangeListener(new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
		    if (!warned)
			CheckShipNumber();
		}
	    });
	    pShipNumbers.add(lblShipTypes[i]);
	    pShipNumbers.add(spinShipCounts[i]);
	    i++;
	}
	for (JLabel jLabel : lblShipTypes)
	    MyUtils.setFontSize(jLabel, 16);
	for (JSpinner jSpinner : spinShipCounts)
	    MyUtils.setFontSize(jSpinner, 16);

	btnChoseShipNumbers = new JButton("Choose number of ships");
	btnChoseShipNumbers.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		ShipCounter temp = new ShipCounter(shipCounter);

		int result = JOptionPane.showConfirmDialog(dialog, pShipNumbers,
			"Cusomize ship numbers", JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION)
		    PlacementDialog.this.changed = true;
		else {
		    shipCounter = temp;
		    for (ShipCountSpinner spin : spinShipCounts) {
			spin.setValue(shipCounter.getShipCount(spin.getShipType()));
		    }
		}
	    }
	});

	pBoardSize = new JPanel();
	pBoardSize.setLayout(new BoxLayout(pBoardSize, BoxLayout.PAGE_AXIS));
	pBoardSize.setBorder(BorderFactory.createEmptyBorder(5, 200, 5, 200));

	for (JComponent comp : new JComponent[] { lblBoardSize, p11, p12,
		btnChoseShipNumbers }) {
	    pBoardSize.add(new JLabel(""));
	    pBoardSize.add(comp);
	    comp.setAlignmentX(Component.CENTER_ALIGNMENT);
	    pBoardSize.add(new JLabel(""));
	}

	p1 = new JPanel();
	p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
	p1.add(rbRandomInit);
	rbRandomInit.setAlignmentX(Component.LEFT_ALIGNMENT);
	rbRandomInit.addActionListener(new StatusChangedListener());
	p1.add(pBoardSize);
	pBoardSize.setAlignmentX(Component.LEFT_ALIGNMENT);
	p1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

	rbFileInit = new JRadioButton("Choose ship placement from file");
	MyUtils.setFontSize(rbFileInit, 18);
	txtFilePath = new JTextField("");
	txtFilePath.setPreferredSize(new Dimension(500, 24));
	txtFilePath.addActionListener(new StatusChangedListener());
	ImageIcon ic = new ImageIcon("src/figs/folder.png");
	btnFilePath = new JButton(ic);
	btnFilePath.setPreferredSize(new Dimension(24, 24));
	btnFilePath.addActionListener(new FileDialogListener());
	p21 = new JPanel(new FlowLayout());
	p21.add(txtFilePath);
	p21.add(btnFilePath);
	fileChooser = new JFileChooser("./");

	p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
	p2.add(rbFileInit);
	rbFileInit.setAlignmentX(Component.LEFT_ALIGNMENT);
	rbFileInit.addActionListener(new FileDialogListener());
	p2.add(p21);
	p21.setAlignmentX(Component.LEFT_ALIGNMENT);
	p2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

	ButtonGroup group = new ButtonGroup();
	group.add(rbRandomInit);
	group.add(rbFileInit);
	rbRandomInit.setSelected(true);

	btnApply = new JButton("Apply");
	btnApply.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		changed = true;
		updateSetup();
	    }
	});
	btnApplyClose = new JButton("Apply & Close");
	btnApplyClose.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		updateSetup();
		closeDialog();
	    }
	});
	btnClose = new JButton("Close");
	btnClose.addActionListener(new CloseListener());

	p3 = new JPanel(new FlowLayout());
	p3.add(btnApply);
	p3.add(btnApplyClose);
	p3.add(btnClose);
	p3.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));

	dialog.getContentPane().setLayout(
		new BoxLayout(dialog.getContentPane(), BoxLayout.PAGE_AXIS));
	dialog.getContentPane().add(lblTitle);
	lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	dialog.getContentPane().add(p1);
	p1.setAlignmentX(Component.CENTER_ALIGNMENT);
	dialog.getContentPane().add(p2);
	p2.setAlignmentX(Component.CENTER_ALIGNMENT);
	dialog.getContentPane().add(p3);
	p3.setAlignmentX(Component.CENTER_ALIGNMENT);

	dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	dialog.pack();
	dialog.setResizable(false);
	dialog.setLocationRelativeTo(null);

    }

    private class FileDialogListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (JFileChooser.APPROVE_OPTION == PlacementDialog.this.fileChooser
		    .showOpenDialog(PlacementDialog.this.dialog)) {
		PlacementDialog.this.txtFilePath.setText(
			PlacementDialog.this.fileChooser.getSelectedFile().getPath());
		PlacementDialog.this.rbFileInit.setSelected(true);
		changed = true;
	    }

	}

    }

    private class StatusChangedListener implements ActionListener, ChangeListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    changed = true;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	    changed = true;
	}

    }

    private class CloseListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (changed) {
		int choice = JOptionPane.showConfirmDialog(
			PlacementDialog.this.dialog,
			"Apply changes before returning to main menu?", "Cancel",
			JOptionPane.YES_NO_CANCEL_OPTION);
		if (choice == JOptionPane.CANCEL_OPTION)
		    return;
		if (choice == JOptionPane.YES_OPTION)
		    updateSetup();
	    }
	    closeDialog();
	}

    }

    void openDialog() {
	dialog.setModal(true);
	dialog.setVisible(true);
    }

    private void closeDialog() {
	dialog.setVisible(false);
	dialog.setModal(false);
    }

    private void updateSetup() {
	if (!changed)
	    return;
	applyFailed = true;
	BoardSetup temp = new BoardSetup(setup);
	if (rbRandomInit.isSelected()) {
	    try {
		spinRows.commitEdit();
		spinCols.commitEdit();
		updateShipCounts();
	    } catch (java.text.ParseException ex) {
		JOptionPane.showMessageDialog(PlacementDialog.this.dialog,
			"Invalid value for number of columns or rows", "Board Size",
			JOptionPane.ERROR_MESSAGE);
	    }
	    int rows = (Integer) spinRows.getValue();
	    int cols = (Integer) spinCols.getValue();
	    backgroundRandomInit(rows, cols);

	} else if (rbFileInit.isSelected()) {
	    String filePath = txtFilePath.getText();
	    if (!setup.initilazeFromFile(filePath)) {
		JOptionPane.showMessageDialog(PlacementDialog.this.dialog,
			"Cannot read ship placement from file:"
				+ System.lineSeparator() + filePath,
			"File Error", JOptionPane.ERROR_MESSAGE);
	    } else {
		JOptionPane.showMessageDialog(PlacementDialog.this.dialog,
			"Ship placement loaded successfully.",
			"Loaded ship placement", JOptionPane.INFORMATION_MESSAGE);
		changed = false;
		applyFailed = false;
	    }

	} else {
	    throw new Error("No radio button selected!");
	}

	if (applyFailed) {
	    setup.copy(temp);
	}
    }

    private void updateShipCounts() {
	for (ShipCountSpinner spin : spinShipCounts) {
	    try {
		spin.commitEdit();
		int count = (Integer) spin.getValue();
		shipCounter.setShipCount(spin.getShipType(), count);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	}
    }

    public void backgroundRandomInit(int rows, int cols) {
	if (randomInitBackgroundTask == null) {
	    randomInitBackgroundTask = new SwingWorker<Boolean, Integer>() {

		@Override
		protected Boolean doInBackground() throws Exception {
		    if (!setup.randomInit(rows, cols, shipCounter)) {
			JOptionPane.showMessageDialog(PlacementDialog.this.dialog,
				"Could not generate random ship placement (maybe board is too small?)",
				"Random ship placement", JOptionPane.ERROR_MESSAGE);
		    } else {
			JOptionPane.showMessageDialog(PlacementDialog.this.dialog,
				"New random ship placement successfully generated.",
				"Random ship placement",
				JOptionPane.INFORMATION_MESSAGE);
			changed = false;
			applyFailed = false;
		    }
		    return true;
		}

		@Override
		public void done() {
		    randomInitBackgroundTask = null;
		}

	    };
	    randomInitBackgroundTask.execute();
	} else {
	    randomInitBackgroundTask.cancel(true);
	}
    }

    private void CheckShipNumber() {
	updateShipCounts();
	if (shipCounter.tooManyShips(setup.getSize())) {
	    JOptionPane.showMessageDialog(dialog,
		    "Be careful when putting too many ships on the board."
			    + System.lineSeparator() + "They might not fit in!",
		    "Warning", JOptionPane.WARNING_MESSAGE);
	    warned = true;
	}
    }

    private class ShipCountSpinner extends JSpinner {
	private static final long serialVersionUID = 1L;
	ShipType shipType;

	/**
	 * @param spinnerNumberModel
	 */
	public ShipCountSpinner(SpinnerNumberModel spinnerNumberModel) {
	    super(spinnerNumberModel);
	}

	/**
	 * @return the shipType
	 */
	private ShipType getShipType() {
	    return this.shipType;
	}

	/**
	 * @param shipType the shipType to set
	 */
	private void setShipType(ShipType shipType) {
	    this.shipType = shipType;
	}

    }
}
