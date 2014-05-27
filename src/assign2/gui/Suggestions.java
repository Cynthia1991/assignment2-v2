/**  
 * @title Suggestions.java  
 * @package assign2.gui  
 * @author kervin  
 * @version V1.0  
 * created 2014年5月17日  
 */
package assign2.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import assign2.examples.jfreechart.BarChart;
import assign2.examples.swing.SimpleFrame;

/**
 * @version 1.0
 * @author QianFu&ChuanLi
 * @created 2014.5.17.
 */

public class Suggestions {
	
	public static void main(String[] args) {
		//SwingUtilities.invokeLater(new ResultPanel("Welcome to Cynthia's Search suggestion:)"));
	    SwingUtilities.invokeLater(new NGramGUI("Welcome to Cynthia&Lambert's Search suggestion:)"));
	    
	}
}
