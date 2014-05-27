/**  
 * @title NGramGUI.java  
 * @package assign2.gui  
 * @author QianFu&ChuanLi  
 * @version V2.0  
 * created 2014.5.27.  
 */
package assign2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedHashSet;
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

import assign2.ngram.NGramException;
import assign2.ngram.NGramNode;
import assign2.ngram.NGramStore;

/**
 * @version 1.0
 * @author QianFu n9223002
 * @author ChuanLi n8818452
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
	private static final int TEXT_WIDTH = 800;
	private static final int TEXT_HEIGHT = 60;

	private static final int PNUM_WIDTH = 60;
	private static final int PNUM_HEIGHT = 60;
	private static final int RESULT_WIDTH = 500;
	private static final int RESULT_HEIGHT = 600;
	private static final int BAR_WIDTH = 1100;
	private static final int BAR_HEIGHT = 600;

	// Creating the GUI

	// Creating the panel for input text area
	private JPanel textAreaPanel;
	private JTextField textDisplay;// input text field
	private JTextField predictionsNum;// input the number of predictions

	// results display area panel
	private JPanel resultsPanel;

	// bar display panel
	private JPanel resultsBarPanel;

	// button panel
	private JPanel buttonPanel;
	private JButton predictionButton;
	private JButton exitButton;
	private JButton chartButton;
	private JButton hideChartButton;

	private NGramStore ngramsMap;

	private int predictionsNumber = 0;
	// This string array stores the input contexts
	private String[] inputContexts;
	// This string array stores the contexts of the resulting array
	private String[] inputHaveResultsArray = null;

	private Thread myThread1;
	// Symbol to mark if this is the first input for controlling the textDisplay
	private boolean START_INPUT = false;

	private static java.awt.Font Font = new Font("Serif", 0, 20);

	// Helper method for constructing the GUI
	/**
	 * @param arg0
	 *            - the Frame Title
	 */
	public NGramGUI(String arg0) throws HeadlessException {
		super(arg0);
	}
	/**
	 * Create input text area GUI
	 * 
	 */
	private void createInputAreaGUI() {
		// Creating the textPanel
		textAreaPanel = new JPanel();
		textAreaPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1,
				true), "Input Area~", TitledBorder.CENTER, TitledBorder.TOP));
		textAreaPanel.setBackground(Color.PINK);
		textAreaPanel.setPreferredSize(new Dimension(TEXTPANEL_WIDTH,
				TEXTPANEL_HEIGHT));
		textAreaPanel.setLayout(new FlowLayout());
		textAreaPanel
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// Creating the input label and then adding it to the textAreaPanel
		JLabel inputLabel = new JLabel("Input: ");
		inputLabel.setFont(Font);
		textAreaPanel.add(inputLabel);

		// Creating the text display JTextField() and then adding it to the
		// textAreaPanel
		textDisplay = new JTextField();
		textDisplay.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		textDisplay.setText("    Click here input your context ~");
		textDisplay.setFont(Font);
		textDisplay.setBackground(Color.WHITE);
		textDisplay.setEditable(false);
		textDisplay.setForeground(Color.GRAY);
		textAreaPanel.add(textDisplay);

		// If it is the first input, clearing the JTextField on mouse click over
		// it
		textDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (START_INPUT == false) {
					textDisplay.setText("");
					textDisplay.setForeground(Color.BLACK);
					textDisplay.setEditable(true);

					START_INPUT = true;
				}
			}
		});

		// Creating the prediction number text field and then adding it to the
		// textAreaPanel
		predictionsNum = new JTextField(10);
		predictionsNum.setPreferredSize(new Dimension(PNUM_WIDTH, PNUM_HEIGHT));
		predictionsNum.setFont(Font);
		predictionsNum.setText("5");
		textAreaPanel.add(predictionsNum);
		this.getContentPane().add(textAreaPanel, BorderLayout.PAGE_START);
	}

	/**
	 * Create button area
	 * 
	 */
	private void createButtonGUI() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.PINK);
		// Creating the predict button
		predictionButton = new JButton("PREDICT");
		predictionButton.addActionListener(this);
		predictionButton.setBackground(Color.PINK);
		buttonPanel.add(predictionButton);
		this.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

		// Creating the button for displaying the chart panel
		chartButton = new JButton("DISPLAY_CHART");
		chartButton.addActionListener(this);
		chartButton.setBackground(Color.PINK);
		buttonPanel.add(chartButton);
		this.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

		// Creating the button for hiding the chart panel
		hideChartButton = new JButton("HIDE_CHART");
		hideChartButton.addActionListener(this);
		hideChartButton.setBackground(Color.PINK);
		buttonPanel.add(hideChartButton);
		this.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

		// Creating the Exit button
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(this);
		exitButton.setBackground(Color.PINK);
		buttonPanel.add(exitButton);
		this.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
	}

	// Creating the display text area
	private void createResultDispalyGUI() {
		// Create the display area for showing the results
		resultsPanel = new ResultPanel();
		this.getContentPane().add(resultsPanel, BorderLayout.CENTER);
	}

	private void createResultDispalyChartGUI() {
		// Creating the display area for showing the bar results
		resultsBarPanel = new assign2.gui.ChartPanel();
		resultsBarPanel.setFont(Font);
		resultsPanel
				.setPreferredSize(new Dimension(RESULT_WIDTH, RESULT_HEIGHT));
		resultsBarPanel.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		this.getContentPane().add(resultsBarPanel, BorderLayout.EAST);
	}

	// Creating the entire GUI form
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		// Creating the input area on the GUI
		createInputAreaGUI();
		// Creating the display area for result on the GUI
		createResultDispalyGUI();
		// Creating the area for button on the GUI
		createButtonGUI();
		// Firstly, the chart and the hide chart buttons are enabled
		chartButton.setEnabled(false);
		hideChartButton.setEnabled(false);
	}

	/**
	 * This method for test if the input is valid or not, if no, then throw the
	 * NGramException
	 * 
	 * @param text
	 * @return split the text by ",",then store them into the string[]
	 * @throws NGramException
	 * 
	 */
	private String[] parseInput(String text) throws NGramException {
		boolean VALID = false;
		String[] arrayContexts = null;
		String str = "^[0-9a-zA-Z ,']+$";

		Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		// Checking whether the input is valid or invalid
		VALID = matcher.matches();

		System.out.println(matcher.matches());

		if (VALID == true) {
			// If valid : separating all the phrases
			// of the input by a comma (",") and then returning the string array
			arrayContexts = text.split(",");

		} else {
			// if not valid : throw
			// exception
			throw new NGramException("invalid input!");
		}
		return arrayContexts;

	}

	/**
	 * This method for displaying the results,according to the input.
	 * 
	 * @param searchTextArr
	 * @param ngramMap
	 * @return
	 * 
	 */
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

	/**
	 * This method for predicting the results
	 * 
	 * @param predictionNumber
	 * @param inputText
	 * 
	 */
	private void predictionShowResults(int predictionNumber, String[] inputText) {
		// storing the input text
		Set<String> input = new LinkedHashSet<String>(Arrays.asList(inputText));
		// storing the contexts supplied as part of input that have suggestion
		// results
		Set<String> inputHaveResults = new LinkedHashSet<String>(
				Arrays.asList(inputText));
		// storing the contexts supplied as part of input into the array
		this.inputHaveResultsArray = null;
		boolean HAVERESULTS = false;
		((ResultPanel) resultsPanel).SetResultText(" ");

		for (String s : input) {
			try {
				// Getting the predictions from the service
				HAVERESULTS = ngramsMap.getNGramsFromService(s,
						predictionNumber);
				// If the phrase has no results, then delete it from the list
				if (HAVERESULTS == false) {
					inputHaveResults.remove(s);
				}
			} catch (NGramException e1) {
				// If the correct result is not retrieved from the NGramService,
				// then showing a warning message
				if (resultsPanel != null) {
					((ResultPanel) resultsPanel).SetResultText(" ");
				}
				JOptionPane.showMessageDialog(this,
						"Please input valid contexts!");
				return;
			}

		}
		// Changing the list to a string array
		inputHaveResultsArray = new String[inputHaveResults.size()];
		int index = 0;
		for (String s : inputHaveResults) {
			inputHaveResultsArray[index] = s;
			index++;
		}

		String resultsString = displayResults(
				input.toArray(new String[input.size()]), ngramsMap);
		// Displaying the results on the text field
		((ResultPanel) resultsPanel).SetResultText(resultsString);
	}

	/**
	 * This method for displaying the results bar
	 * 
	 * @param predictionNumber
	 * @param inputResultsText
	 * 
	 */
	private void predictionShowResultsBar(String[] inputResultsText) {
		// this.inputHaveResultsArray = null;
		((assign2.gui.ChartPanel) resultsBarPanel).clearResultsChart();
		// Displaying the results on the bar chart
		((assign2.gui.ChartPanel) resultsBarPanel).DisplayResultChart(
				inputResultsText, ngramsMap);
	}

	/**
	 * 
	 * This methods for disabling the prediction button when predicting.
	 */
	private void predicting() {
		// resultText.setText("Please waiting...");

		textDisplay.setEnabled(false);
		predictionsNum.setEnabled(false);
		predictionButton.setEnabled(false);
		chartButton.setEnabled(false);

	}

	/**
	 * This methods for enabling the prediction button when predicting
	 * 
	 */
	private void afterPredict() {

		textDisplay.setEnabled(true);
		predictionsNum.setEnabled(true);
		predictionButton.setEnabled(true);
		chartButton.setEnabled(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonString = e.getActionCommand();

		// chartButton.setEnabled(false);
		if (buttonString.equals("PREDICT")) {
			// Getting the input text
			String textString = textDisplay.getText();
			// Getting the number of predictions on the text field
			String predictionsNumString = predictionsNum.getText();
			// Creating the display area for results on the GUI
			createResultDispalyChartGUI();
			this.ngramsMap = new NGramStore();
			// Disabling the resultsBarPanel and hideChartButton
			if (resultsBarPanel != null) {
				resultsBarPanel.setVisible(false);
			}
			hideChartButton.setEnabled(false);
			// Checking whether the number of suggestions provided as part of
			// input is valid or not
			// i.e., it is to be strictly a positive integer greater than zero
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

			// Checking whether the input contexts are valid or not
			try {
				if (textDisplay.getText() == "    Click here input your context ~") {
					JOptionPane.showMessageDialog(this,
							"Please input valid contexts!");
					if (resultsPanel != null) {
						((ResultPanel) resultsPanel).SetResultText(" ");
					}

					return;
				}
				inputContexts = parseInput(textString);
			} catch (NGramException e3) {

				JOptionPane.showMessageDialog(this,
						"Please input valid contexts!");
				if (resultsPanel != null) {
					((ResultPanel) resultsPanel).SetResultText(" ");
				}
				return;
			}
			// Adding a thread to operate the prediction process
			myThread1 = new Thread() {
				@Override
				public void run() {
					// Enabling the button if the service is working
					predicting();
					predictionShowResults(predictionsNumber, inputContexts);
					// chartButton.setEnabled(true);
					afterPredict();
				}
			};
			myThread1.start();
		}
		if (buttonString.equals("DISPLAY_CHART")) {
			// Asking confirmation whether the user would like to get the
			// results displayed on bar chart

			createResultDispalyChartGUI();
			predictionShowResultsBar(inputHaveResultsArray);
			// After the bar chart is displayed, the hide button is enabled
			hideChartButton.setEnabled(true);
			// After the bar chart is displayed, the chart button is enabled
			chartButton.setEnabled(false);
		}
		if (buttonString.equals("HIDE_CHART")) {
			// Asking confirmation whether the user does not want to view the
			// bar chart
			// Hiding the bar chart
			if (resultsBarPanel != null) {
				resultsBarPanel.setVisible(false);
			}
			hideChartButton.setEnabled(false);
			chartButton.setEnabled(true);
		}
		if (buttonString.equals("EXIT")) {
			// Asking whether the user would like to exit from the application
			if (JOptionPane.showConfirmDialog(this, "Do you want to EXIT?",
					"Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			return;
		}

	}

}
