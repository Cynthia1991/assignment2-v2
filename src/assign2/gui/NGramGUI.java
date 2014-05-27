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
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;

import com.sun.org.glassfish.external.statistics.annotations.Reset;

import assign2.ngram.NGramException;
import assign2.ngram.NGramNode;
import assign2.ngram.NGramStore;

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
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 800;
	private static final int TEXTPANEL_WIDTH = 1000;
	private static final int TEXTPANEL_HEIGHT = 100;
	private static final int PREDICTNUM_PANEL_WIDTH = 600;
	private static final int PREDICTNUM_PANEL_HEIGHT = 100;
	private static final int TEXT_WIDTH = 800;
	private static final int TEXT_HEIGHT = 60;

	private static final int PNUM_WIDTH = 60;
	private static final int PNUM_HEIGHT = 60;
	private static final int RESULT_WIDTH = 500;
	private static final int RESULT_HEIGHT = 600;
	private static final int BAR_WIDTH = 1100;
	private static final int BAR_HEIGHT = 600;
	// private static final Double[] nodeProbabilities = null;

	// creat GUI parts

	private JPanel textAreaPanel;// input text area
	private JPanel predictionNumPanel;// input text area
	private JTextField textDisplay;// input text
	private JTextField predictionsNum;// input the number of predictions

	private JPanel resultsPanel;// results display area panel
	private JPanel resultsBarPanel;// results bar display panel
	private JTextField resultText;// results display text

	private JPanel buttomPanel;// buttom panel

	private NGramNode ngram;
	private NGramStore ngramsMap;

	private int predictionsNumber = 0;

	private String[] inputContexts;

	private static java.awt.Font Font = new Font("Serif", 0, 20);
	private Thread myThread;
	private JButton predictionButton;
	private JButton exitButton;
	private JButton chartButton;
	private JButton hideChartButton;
	
	private boolean START_INPUT = false;
	// helper method to construct the GUI

	/**
	 * @param arg0
	 *            - the Frame Title
	 */
	public NGramGUI(String arg0) throws HeadlessException {
		super(arg0);
	}

	
	/**  
	 * Create input text area   
	 *   
	 */
	private void createInputAreaGUI() {
		// create textPanel
		textAreaPanel = new JPanel();
		textAreaPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1,
				true), "Input Area~", TitledBorder.CENTER, TitledBorder.TOP));
		textAreaPanel.setBackground(Color.PINK);
		textAreaPanel.setPreferredSize(new Dimension(TEXTPANEL_WIDTH,
				TEXTPANEL_HEIGHT));
		
		textAreaPanel.setLayout(new FlowLayout());
		//textAreaPanel.setLayout(new BorderLayout());
		textAreaPanel
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// create input label, then add into textAreaPanel
		JLabel inputLabel = new JLabel("Input: ");
		inputLabel.setFont(Font);
		textAreaPanel.add(inputLabel);

		// create text display JTextField(), then add into textAreaPanel
		textDisplay = new JTextField();
		textDisplay.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		
		textDisplay.setText("    Click here input your context ~");
		textDisplay.setFont(Font);
		textDisplay.setBackground(Color.WHITE);
		textDisplay.setEditable(false);
		textDisplay.setForeground(Color.GRAY);
	
		textAreaPanel.add(textDisplay);
		//If it is the first input,clear JTextField when mouse clicks the JTextField
		
		
		textDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent e) {
	            if(START_INPUT == false){
	            	textDisplay.setText("");
	            textDisplay.setForeground(Color.BLACK);
	            textDisplay.setEditable(true);
	            
	            START_INPUT = true;
	            }
	        } 
	    });
		

		// create textPanel
		predictionNumPanel = new JPanel();
		predictionNumPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1,
						true), "Prediction Num", TitledBorder.CENTER, TitledBorder.TOP));
		predictionNumPanel.setBackground(Color.PINK);
		predictionNumPanel.setPreferredSize(new Dimension(PREDICTNUM_PANEL_WIDTH,
						PREDICTNUM_PANEL_HEIGHT));
		//predictionNumPanel.setLayout(new FlowLayout());
		//predictionNumPanel.setLayout(new BorderLayout());
		//predictionNumPanel
		//				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	  
		// create prediction number label, then add into textAreaPanel
		JLabel predictionsNumberLabel = new JLabel("Num: ");
		predictionsNumberLabel.setFont(Font);
		textAreaPanel.add(predictionsNumberLabel);

		// create prediction number text field, then add into textAreaPanel
		predictionsNum = new JTextField(10);
		predictionsNum.setPreferredSize(new Dimension(PNUM_WIDTH, PNUM_HEIGHT));
		predictionsNum.setFont(Font);
		predictionsNum.setText("5");
		textAreaPanel.add(predictionsNum);
		//predictionNumPanel.add(predictionsNum);
		//setLayout(new GridLayout(2, 3));
		// add the textAreaPanel into window
		//this.getContentPane().add(textAreaPanel, GridLayout);
		this.getContentPane().add(textAreaPanel, BorderLayout.PAGE_START);
		//this.getContentPane().add(predictionNumPanel,  BorderLayout.PAGE_END);
	}

	/**  
	 * Create button area    
	 *   
	 */
	private void createButtonGUI() {
		buttomPanel = new JPanel();
		buttomPanel.setLayout(new FlowLayout());
		buttomPanel.setBackground(Color.PINK);
		// Create the predict button
		predictionButton = new JButton("PREDICT");
		predictionButton.addActionListener(this);
		predictionButton.setBackground(Color.PINK);
		buttomPanel.add(predictionButton);
		this.getContentPane().add(buttomPanel, BorderLayout.PAGE_END);
		
		// Create the Showchart button
		chartButton = new JButton("DISPLAY_CHART");
		chartButton.addActionListener(this);
		chartButton.setBackground(Color.PINK);
		buttomPanel.add(chartButton);
		this.getContentPane().add(buttomPanel, BorderLayout.PAGE_END);
		
		// Create the Showchart button
		hideChartButton = new JButton("HIDE_CHART");
		hideChartButton.addActionListener(this);
		hideChartButton.setBackground(Color.PINK);
		buttomPanel.add(hideChartButton);
		this.getContentPane().add(buttomPanel, BorderLayout.PAGE_END);
		
		// Create the Exit button
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(this);
		exitButton.setBackground(Color.PINK);
		buttomPanel.add(exitButton);
		this.getContentPane().add(buttomPanel, BorderLayout.PAGE_END);
		
		this.getContentPane().add(buttomPanel,  BorderLayout.SOUTH);
	}

	// Create the display text area
	private void createResultDispalyGUI() {
		// creat the display area for showing the results
		resultsPanel = new ResultPanel();
		//resultsPanel
		//		.setPreferredSize(new Dimension(RESULT_WIDTH, RESULT_HEIGHT));
		this.getContentPane().add(resultsPanel, BorderLayout.CENTER);
	}

	// Create the bar display text area
	private void createResultDispalyChartGUI() {
		// Create the display area for showing the bar results
		resultsBarPanel = new assign2.gui.ChartPanel();
		resultsBarPanel.setFont(Font);
		resultsPanel.setPreferredSize(new Dimension(RESULT_WIDTH, RESULT_HEIGHT));
		resultsBarPanel.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		this.getContentPane().add(resultsBarPanel, BorderLayout.EAST);
	}
	//
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		//
		createInputAreaGUI();
		createResultDispalyGUI();
		
		createButtonGUI();
		chartButton.setEnabled(false);
		hideChartButton.setEnabled(false);
	}
	
	private String[] parseInput(String text) throws NGramException {
		boolean VALID = false;
		String[] arrayContexts = null;
		String str = "^[0-9a-zA-Z ,']+[0-9a-zA-Z ']$";

		Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		// Judge whether the input is valid or invalid if valid
		VALID = matcher.matches();

		System.out.println(matcher.matches());

		if (VALID == true) {
			// If valid : cut every phrase
			// from the input,by "," return the string[]
			arrayContexts = text.split(",");

		} else {
			// if not valid : throw
			// exception
			throw new NGramException("invalid input!");
		}
		return arrayContexts;

	}

	// according to the input, display the results
	private String displayResults(String[] searchTextArr, NGramStore ngramMap) {
		StringBuffer ngramBuffer = new StringBuffer();
		for (String s : searchTextArr) {
			ngramBuffer.append("NGram Results for Query: ");
			ngramBuffer.append(s);
			NGramNode node = (NGramNode) ngramMap.getNGram(s);
			
			if (node == null) {
				ngramBuffer.append("\n");
				ngramBuffer.append("No results were returned for this phrase.")
						.append("\n\n");
			} else {
				
				ngramBuffer.append("\n");
				ngramBuffer.append(node.toString());
				ngramBuffer.append("\n");
			}
		}
		return ngramBuffer.toString();
	}

	

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI();
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setVisible(true);
	}

	/*
	 * Helper method to search and show the results on GUI
	 * 
	 * @param seachNumber - Search for number of results
	 * 
	 * @param searchTextArr - Search for number of suggestions
	 */
	//@SuppressWarnings("null")
	private void predictionShowResults(int predictionNumber, String[] inputText) {
		// Use LinkedHashSet to ensure there are no duplicated texts to search
		Set<String> input = new LinkedHashSet<String>(Arrays.asList(inputText));
		Set<String> inputHaveResults = new LinkedHashSet<String>(
				Arrays.asList(inputText));
		String[] inputHaveResultsArray = null;
		boolean HAVERESULTS = false;
		((ResultPanel) resultsPanel).SetResultText(" ");
		//((assign2.gui.ChartPanel) resultsBarPanel).clearResultsChart();

		for (String s : input) {
			try {
				// Get the predictions from the service
				HAVERESULTS = ngramsMap.getNGramsFromService(s,
						predictionNumber);
				// If the phrase have no results, then delete that from the list
				if (HAVERESULTS == false) {
					inputHaveResults.remove(s);
				} else if (HAVERESULTS == true) {

				}

			} catch (NGramException e1) {
				// If a correct result is not retrieved from the NGramService,
				// Then show the warning
				JOptionPane.showMessageDialog(this,
						"Please input valid contexts!");
				return;
			}

		}
		// Change the list to string[]
		inputHaveResultsArray = new String[inputHaveResults.size()];
		int index = 0;
		for (String s : inputHaveResults) {
			inputHaveResultsArray[index] = s;
			index++;
		}

		String resultsString = displayResults(
				input.toArray(new String[input.size()]), ngramsMap);
		// Display the results on the text field
		((ResultPanel) resultsPanel).SetResultText(resultsString);
		// Display the results by using the bar
		//((assign2.gui.ChartPanel) resultsBarPanel).ShowResultChart(
			//	inputHaveResultsArray, ngramsMap);
	}
	
	/**  
	 * @param predictionNumber
	 * @param inputText   
	 *  
	 */
	private void predictionShowResultsChart(int predictionNumber, String[] inputText) {
		// Use LinkedHashSet to ensure there are no duplicated texts to search
		Set<String> input = new LinkedHashSet<String>(Arrays.asList(inputText));
		Set<String> inputHaveResults = new LinkedHashSet<String>(
				Arrays.asList(inputText));
		String[] inputHaveResultsArray = null;
		boolean HAVERESULTS = false;
		((ResultPanel) resultsPanel).SetResultText(" ");
		((assign2.gui.ChartPanel) resultsBarPanel).clearResultsChart();

		for (String s : input) {
			try {
				// Get the predictions from the service
				HAVERESULTS = ngramsMap.getNGramsFromService(s,
						predictionNumber);
				// If the phrase have no results, then delete that from the list
				if (HAVERESULTS == false) {
					inputHaveResults.remove(s);
				} else if (HAVERESULTS == true) {

				}

			} catch (NGramException e1) {
				// If a correct result is not retrieved from the NGramService,
				// Then show the warning
				JOptionPane.showMessageDialog(this,
						"Please input valid contexts!");
				return;
			}

		}
		// Change the list to string[]
		inputHaveResultsArray = new String[inputHaveResults.size()];
		int index = 0;
		for (String s : inputHaveResults) {
			inputHaveResultsArray[index] = s;
			index++;
		}

		String resultsString = displayResults(
				input.toArray(new String[input.size()]), ngramsMap);
		// Display the results on the text field
		((ResultPanel) resultsPanel).SetResultText(resultsString);
		// Display the results by using the bar chart
		((assign2.gui.ChartPanel) resultsBarPanel).ShowResultChart(
				inputHaveResultsArray, ngramsMap);
	}

	/*
	 * Disabling the text areas and button components on the GUI, after
	 * completion of search
	 */
	private void disableComponent() {
		predictionButton.setText("Searching...");
		predictionButton.setEnabled(false);
		textDisplay.setEnabled(false);
		predictionsNum.setEnabled(false);
	}

	/*
	 * Enabling the text areas and button components on the GUI, after
	 * completion of search
	 */
	private void EnableComponent() {
		predictionButton.setText("Search");
		predictionButton.setEnabled(true);
		textDisplay.setEnabled(true);
		predictionsNum.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonString = e.getActionCommand();

		String textString = textDisplay.getText();// get the input
		String predictionsNumString = predictionsNum.getText();

		this.ngramsMap = new NGramStore();
		//chartButton.setEnabled(false);
		if (buttonString.equals("PREDICT")) {
			if(resultsBarPanel!= null){
				resultsBarPanel.setVisible(false);
				}
			hideChartButton.setEnabled(false);
			// Judge whether or not the input prediction number is valid:
			// positive integer
			try {
				predictionsNumber = Integer.valueOf(predictionsNumString);
				if (predictionsNumber <= 0) {
					JOptionPane.showMessageDialog(this,
							"Please input positive integer!");
					return;
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this,
						"Please input positive integer!");
				return;
			}

			// Judge whether or not the input contexts are valid
			try {
				if(textDisplay.getText() =="    Click here input your context ~" ){
					JOptionPane.showMessageDialog(this,
							"Please input valid contexts!");
					return;
				}
				inputContexts = parseInput(textString);
			} catch (NGramException e3) {

				JOptionPane.showMessageDialog(this,
						"Please input valid contexts!");
				return;
			}

			// Opening a new thread to handle the suggestion searching
			// processing
			 myThread = new Thread() {
			 @Override
			 public void run() {
			// During search, disabling the GUI components i.e., the
			// text areas and button
			// disableComponent();
			//chartButton.setEnabled(false);
			//createResultDispalyGUI();
			//createResultDispalyChartGUI();
			
			predictionShowResults(predictionsNumber, inputContexts);
			chartButton.setEnabled(true);
			
			// After searching, enabling all the GUI components
			// EnableComponent();
			}
			};
			 myThread.start();

			// System.out.println(predictionsNumber);

		}
		if (buttonString.equals("DISPLAY_CHART")) {
			// Ask whether the user want to show chart
			createResultDispalyGUI();
			createResultDispalyChartGUI();
			//resultsPanel.setPreferredSize(new Dimension(RESULT_WIDTH, RESULT_HEIGHT));
			predictionShowResultsChart(predictionsNumber, inputContexts);
			resultsBarPanel.setVisible(true);
			hideChartButton.setEnabled(true);
			chartButton.setEnabled(false);
		}
		if (buttonString.equals("HIDE_CHART")) {
			// Ask whether the user want to hide the chart
			if(resultsBarPanel!= null){
			resultsBarPanel.setVisible(false);
			}
			hideChartButton.setEnabled(false);
			chartButton.setEnabled(true);
		}
		if (buttonString.equals("EXIT")) {
			// Ask whether the user want to exit
			if (JOptionPane.showConfirmDialog(this, "Do you want to EXIT?",
					"Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			return;
		}

	}

}
