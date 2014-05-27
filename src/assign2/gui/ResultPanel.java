/**  
 * @title ResultPanel.java  
 * @package assign2.gui  
 * @author kervin  
 * @version V1.0  
 * created 2014年5月17日  
 */
package assign2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.sun.org.apache.xpath.internal.operations.Bool;

import assign2.ngram.NGramContainer;
import assign2.ngram.NGramNode;
import assign2.ngram.NGramStore;

import java.awt.peer.SystemTrayPeer;
import java.util.ArrayList;
import java.util.List;

/**
 * ResultPanel
 * 
 * a class extends JPanel, to create and control the results display area
 * 
 * @version 1.0
 * @author QianFu n9223002
 * @author ChuanLi n8818452
 * @created 2014.5.17.
 */
public class ResultPanel extends JPanel {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	private static int SCROLL_WIDTH = 600;
	private static int SCROLL_HEIGHT = 700;

	private JTextArea textDisplay;
	private JScrollPane resultScroll;
	private static java.awt.Font Font = new Font("Serif", 0, 20);

	public ResultPanel() {
		// Set the layout, and title border
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1, true),
				"Results~", TitledBorder.CENTER, TitledBorder.TOP));

		// Create the text area
		textDisplay = new JTextArea("");
		textDisplay.setEditable(false);
		textDisplay.setFont(Font);

		// Create the scroll
		resultScroll = new JScrollPane(textDisplay,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resultScroll
				.setPreferredSize(new Dimension(SCROLL_WIDTH, SCROLL_HEIGHT));
		this.add(resultScroll, BorderLayout.CENTER);
	}

	// @SuppressWarnings("unused")
	public void SetResultText(String result) {
		StringBuffer resultText = new StringBuffer();

		// Display the results on the results text area
		resultText.append(result);
		resultText.append("\n");
		textDisplay.setText(resultText.toString());

		this.textDisplay.setText(result);

	}

}
