/**
 * 
 */
package visualization;

import java.awt.Font;

import javax.swing.JComponent;

/**
 * @author Pavel Mačák
 *
 */
public class MyUtils {

    static void setFontSize(JComponent label, int size) {
	label.setFont(new Font(label.getFont().getName(), Font.PLAIN, size));
    }
}
