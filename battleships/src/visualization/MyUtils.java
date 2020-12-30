/**
 * 
 */
package visualization;

import java.awt.Font;

import javax.swing.JComponent;

/**
 * Some funtions to save time writing the same code.
 * 
 * @author Pavel Macak
 *
 */
public class MyUtils {

	static void setFontSize(JComponent label, int size) {
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, size));
	}
}
