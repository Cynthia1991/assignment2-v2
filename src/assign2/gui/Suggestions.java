/**  
 * @title Suggestions.java  
 * @package assign2.gui  
 * @author kervin  
 * @version V1.0  
 * created 2014年5月17日  
 */
package assign2.gui;

import javax.swing.SwingUtilities;

/**
 * 
 * @version 1.0
 * @author QianFu n9223002
 * @author ChuanLi n8818452
 * @created 2014.5.17.
 */

public class Suggestions {
	public static void main(String[] args) {
		// SwingUtilities.invokeLater(new
		// ResultPanel("Welcome to Cynthia's Search suggestion:)"));
		SwingUtilities.invokeLater(new NGramGUI(
				"Welcome to Cynthia&Lambert's Search suggestion:)"));

	}
}
