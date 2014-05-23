/**  
 * @title NGramGUI.java  
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import assign2.examples.swing.SimpleFrame;
import assign2.ngram.NGramContainer;
import assign2.ngram.NGramException;
import assign2.ngram.NGramNode;
import assign2.ngram.NGramStore;

import java.awt.peer.SystemTrayPeer;
import java.util.ArrayList;
import java.util.List;

import assign2.ngram.*;

import java.util.regex.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * @version 1.0
 * @author QianFu&ChuanLi
 * @created 2014.5.17.
 */
/* a frame class */
public class NGramGUI extends JFrame implements ActionListener, Runnable {
	/**
	 * @fields serialVersionUID
	 */

	/**
	 * @param args
	 */
	private static final long serialVersionUID = -9160731609575231933L;
	// private static final long serialVersionUID = -7031008862559936404L;
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 800;
	private static final int TEXT_WIDTH = 600;
	private static final int TEXT_HEIGHT = 100;
	private static final int RESULT_WIDTH = 600;
	private static final int RESULT_HEIGHT = 600;
	private static final int BAR_WIDTH = 1000;
	private static final int BAR_HEIGHT = 800;
	// private static final Double[] nodeProbabilities = null;

	// creat GUI parts
	private JPanel btmPanel;
	private JPanel textPanel;
	private JTextArea textDisplay;
	private JPanel barPanel;
	private JPanel resultTextPanel;
	private ResultPanel resultBar;
	private ResultPanel resultText;

	// creat ngram parts
	private NGramNode ngram;
	private NGramStore ngramsMap;
	private int MAXRESULTS = 5;
	// private String[] textResults = new String();//store all the results
	private List<String> textResults = new ArrayList<String>();
	// private List<String> inputContexts = new ArrayList<String>();
	private int indexOfResults = 0;// the index of all the results

	// private String[] inputContexts;
	private List<String> inputContexts = new ArrayList<String>();
	private List<String> inputNoResultContexts = new ArrayList<String>();
	private String[] nodePredictions;// node prediction
	private Double[] nodeProbabilities;// node probabilities

	// private NGramGUI barGramGUI;
	public NGramGUI(String arg0) throws HeadlessException {
		super(arg0);
	}

	/*
	 * public static void main(String[] args) { SwingUtilities.invokeLater(new
	 * SimpleFrame("Swing GUI Demo")); }
	 */

	/**
	 * @param arg0
	 *            - the Frame Title
	 */
	/*
	 * public SimpleFrame(String arg0) throws HeadlessException { super(arg0); }
	 */

	// helper method to construct the GUI
	private void createGUI() {
		setSize(WIDTH, HEIGHT);// creat window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Creat text area
		textDisplay = new JTextArea("Please input your message~");
		textDisplay.setPreferredSize(new Dimension(TEXT_WIDTH,TEXT_HEIGHT));
		//textDisplay.pr
		textDisplay.setEditable(true);
		textDisplay.setFont(new Font("Serif",0,20));

		// Creat input panel
		textPanel = new JPanel();
		textPanel.setBackground(null);
		textPanel.setSize(TEXT_WIDTH, TEXT_HEIGHT);
		textPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textDisplay, BorderLayout.CENTER);
		//textPanel.setFont(new Font("Serif",0,40));

		this.getContentPane().add(textPanel, BorderLayout.NORTH);

		resultTextPanel = new JPanel();
		resultTextPanel.setBackground(null);
		resultTextPanel.setSize(RESULT_WIDTH, RESULT_HEIGHT);
		resultTextPanel.setLayout(new BorderLayout());
		resultTextPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Results"));
		// resultTextPanel.add(resultText, BorderLayout.WEST);

		resultText = new ResultPanel("This is the results~~~");
		// resultText.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultText.run();
		resultText.pack();
		resultText.setPreferredSize(new Dimension(RESULT_WIDTH,RESULT_HEIGHT));
		resultText.setFont(new Font("Serif",0,20));
		
		// resultText.setVisible(true);
		resultTextPanel.add(resultText.getContentPane());

		this.getContentPane().add(resultTextPanel, BorderLayout.WEST);
		
		barPanel = new JPanel();
		barPanel.setBackground(null);
		resultTextPanel.setSize(BAR_WIDTH, BAR_HEIGHT);
		barPanel.setLayout(new BorderLayout());
		barPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Graph"));
		/*
		 barPanel = new JPanel(); barPanel.setBackground(Color.blue);
		 barPanel.setLayout(new BorderLayout()); //
		 barPanel.add(resultBar,BorderLayout.NORTH); 
		 resultBar = new ResultPanel(" Chart ", "5-grams");
		 resultBar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 resultBar.pack(); // resultBar.setVisible(true);
		 barPanel.add(resultBar.getContentPane());
		 this.getContentPane().add(barPanel, BorderLayout.CENTER);
		 */
		 resultBar = new ResultPanel(" SUGGESTION~ ", "5-grams",
					inputContexts, ngramsMap);
			resultBar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			resultBar.pack();
			// resultBar.setVisible(true);
			barPanel.add(resultBar.getContentPane());
			
			this.getContentPane().add(barPanel, BorderLayout.CENTER);
		/*
		 * JButton redButton = new JButton("Red");
		 * redButton.setBackground(Color.WHITE);
		 * redButton.addActionListener(this); barPanel.add(redButton); //creat
		 * bar panel
		 */

		btmPanel = new JPanel();
		btmPanel.setBackground(Color.LIGHT_GRAY);
		btmPanel.setLayout(new FlowLayout());

		JButton blueButton = new JButton("Predict");
		blueButton.setBackground(Color.WHITE);
		blueButton.addActionListener(this);
		btmPanel.add(blueButton);

		JButton blackButton = new JButton("Black");
		blackButton.setBackground(Color.WHITE);
		blackButton.addActionListener(this);
		btmPanel.add(blackButton);

		this.getContentPane().add(btmPanel, BorderLayout.SOUTH);
	}

	/*
	 * judge whether the input is valid or invalid if valid : cut every phrase
	 * from the input,by "," return the string[] input if invalid : throw
	 * exception,clear the resultText area
	 */
	private String[] parseInput(String text) throws NGramException {
		boolean VALID = false;
		String[] arrayContexts = null;
		String str3 = "^[0-9a-zA-Z ,']+[0-9a-zA-Z ']$";
		// System.out.println(match(,));
		Pattern pattern = Pattern.compile(str3, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		VALID = matcher.matches();

		System.out.println(matcher.matches());

		if (VALID == true) {
			arrayContexts = text.split(",");
		} else {
			throw new NGramException("");
		}
		return arrayContexts;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonString = e.getActionCommand();

		String textString = textDisplay.getText();// get the input

		String[] phrase = null;// store valid input

		String aResult = null;// store the result

		boolean HAVERESULT = false;// store whether phrase have predictions

		this.ngramsMap = new NGramStore();

		/*
		 * judge whether the input is valid or invalid if valid : cut every
		 * phrase from the input,by "," return the string[] input if invalid :
		 * throw exception,clear the resultText area
		 */
		try {
			phrase = parseInput(textString);
			// inputContexts = Arrays.asList(arrayContexts);
			inputContexts = new ArrayList<String>(Arrays.asList(phrase));

			// search each result of every phrase.
			for (String ngramsContext : inputContexts) {
				// try {

				HAVERESULT = ngramsMap.getNGramsFromService(ngramsContext,
						MAXRESULTS);

				/*
				 * search the predictions of input, if no result, return false.
				 * if yes, store the result.
				 */
				if (HAVERESULT == false) {// no results
					if(aResult == null){
						aResult = "NGram Results for Query: " + ngramsContext
								+ "\n" + "No results were retured for this phrase"
								+ "\n" + "\n";
					}
					else{
					aResult += "NGram Results for Query: " + ngramsContext
							+ "\n" + "No results were retured for this phrase"
							+ "\n" + "\n";
					}
					// inputContexts.remove(ngramsContext);
					textResults.add(aResult);
					// add the no results display texts into this list
					inputNoResultContexts.add(ngramsContext);

				} else if (HAVERESULT == true) {// have result
					if(aResult ==null){
						aResult = ngramsMap.toString();
					}
					else{
					aResult += ngramsMap.toString();
					}
					textResults.add(aResult);
				}

			}
			// delete phrase that have no results
			for (String s : inputNoResultContexts) {
				inputContexts.remove(s);
			}
			// users press predict button
			if (buttonString.equals("Predict")) {
				//display the results of input
				for (String output : textResults) {
					this.resultText.SetResultText(output);
				}
				resultBar.updateChart(inputContexts, ngramsMap);
				barPanel.add(resultBar.getContentPane());
				
				this.getContentPane().add(barPanel, BorderLayout.CENTER);
				/*
				barPanel = new JPanel();
				barPanel.setBackground(Color.blue);
				barPanel.setLayout(new BorderLayout());
*/
				//creat the bar to display the result
				/*
				resultBar = new ResultPanel(" SUGGESTION~ ", "5-grams",
						inputContexts, ngramsMap);
				resultBar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				resultBar.pack();
				// resultBar.setVisible(true);
				barPanel.add(resultBar.getContentPane());
				
				this.getContentPane().add(barPanel, BorderLayout.CENTER);
*/
				//System.out.println("Success!");

				// this.textDisplay.setBackground(Color.BLUE);
				// this.textDisplay.setForeground(Color.WHITE);

			} else if (buttonString.equals("Black")) {
				// this.textDisplay.setBackground(Color.BLACK);
				// this.textDisplay.setForeground(Color.GREEN);
				JOptionPane.showMessageDialog(this, "Invalid input!", "Error~",
						JOptionPane.ERROR_MESSAGE);
			}
			
			//barPanel.updateUI();

		} catch (NGramException exception) {
			JOptionPane.showMessageDialog(this, "Invalid input!", "Error~",
					JOptionPane.ERROR_MESSAGE);
			this.resultText.SetResultText("  ");
		}
	}

	@Override
	public void run() {
		createGUI();
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setVisible(true);
	}

}
