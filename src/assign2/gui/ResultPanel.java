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
 * @version 1.0
 * @author QianFu&ChuanLi
 * @created 2014.5.17.
 */
/* a simple panel to hold a text area to allow text based reporting of results */
@SuppressWarnings("serial")
public class ResultPanel extends JPanel {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT =500;

	private JPanel btmPanel;
	private JPanel textPanel;
	private JTextArea textDisplay;
	private JPanel barPanel;
	private JPanel resultTextPanel;
	private ChartPanel chartPanel;
	private JFreeChart chart;
	private CategoryDataset dataset;
	private String chartTitle;
	//private NGramStore ngramMap;
	//private JScrollPane textScroll; //add 
	private JScrollPane resultScroll; //add
	private JMenuBar resultMenuBar;
	private static java.awt.Font Font = new Font("Serif", 0, 20);

	public ResultPanel() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1, true),"Results~", TitledBorder.CENTER, TitledBorder.TOP));
		textDisplay = new JTextArea("");

		textDisplay.setEditable(false);
		textDisplay.setFont(Font);
		//resultText = new StringBuffer();
		resultScroll = new JScrollPane(textDisplay,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resultScroll.setPreferredSize(new Dimension(300,HEIGHT));
		//scrollPane.setBounds(10,60,780,500);
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(resultScroll, BorderLayout.CENTER);
	}
	
	@SuppressWarnings("unused")
	public void SetResultText(String result) {
		StringBuffer resultText = new StringBuffer();
		resultText.append(result);
		resultText.append("\n");
		textDisplay.setText(resultText.toString());
	
		this.textDisplay.setText(result);

	}

	

}
