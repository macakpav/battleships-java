/**
 * 
 */
package visualization;

import javax.swing.SwingUtilities;

/**
 * Main class controlling the flow of the program.
 * 
 * @author Pavel Mačák
 *
 */
public class Control {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		new MainFrame();
	    }
	});
    }

}
