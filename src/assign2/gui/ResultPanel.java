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
public class ResultPanel extends JFrame implements ActionListener, Runnable {
	
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
	
	
	public ResultPanel(String applicationTitle, String chartTitle,List<String> contexts,NGramStore ngramMap) {
		super(applicationTitle);
		// This will create the dataset
		this.chartTitle = chartTitle;
		//CategoryDataset dataset = createDataset(contexts,ngramMap);
		dataset = initDataset();
		// based on the dataset we create the chart
		chart = createChart(dataset, chartTitle);
		// we put the chart into a panel
		chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(200, 500));
		// add it to our application
		setContentPane(chartPanel);
	}
	//init the data of bar
	  private  DefaultCategoryDataset initDataset() {
	       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	       
			/*dataset.setValue(0.13605904897333534, "be or not to", "be");
	       dataset.setValue(0.06656263408951493, "be or not to", "mention");
	       dataset.setValue(0.032759119479403784, "be or not to", "exceed");
	       dataset.setValue(0.028824381687144844, "be or not to", "say");
	       dataset.setValue(0.02452364382944196, "be or not to", "the");
	       
	       
	       dataset.setValue(0.13605904897333534, "singin in the", "rain");
	       dataset.setValue(0.06656263408951493, "singin in the", "shower");
	       
	       */
	       return dataset;
	   }
	  public boolean updateChart(List<String> contexts,NGramStore ngramsMap){
		     
		    //update the dataset
		    dataset = updateDataset(contexts, ngramsMap);
		    
			// based on the dataset we create the chart
			chart = createChart(dataset, chartTitle);
			
			chartPanel = new ChartPanel(chart);
			chartPanel.updateUI();
			setContentPane(chartPanel);
			return true;
	  }
	   
/*
	public ResultPanel(String applicationTitle, String chartTitle,List<String> contexts,NGramStore ngramMap) {
		super(applicationTitle);
		// This will create the dataset
		CategoryDataset dataset = createDataset(contexts,ngramMap);
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, chartTitle);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(200, 500));
		// add it to our application
		setContentPane(chartPanel);
	}*/
	public ResultPanel(String arg0) throws HeadlessException {
		
		super(arg0);
	}
	
	@SuppressWarnings("unused")
	public void SetResultText(String result) {
		System.out.println("    SUCCESS!!!    ");
	
		this.textDisplay.setText(result);

	}

	 /**
    * Creates a sample dataset 
    */
   private  DefaultCategoryDataset updateDataset(List<String> contexts,NGramStore ngramMap) {
       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       NGramContainer ngram ;
       String prediction;//
   	   double probability;//
   	   int LENGTH = 0;
       //int index;
   	   //
		for (String s : contexts) {
			ngram = ngramMap.getNGram(s);
			LENGTH = ngram.getPredictions().length;
			
			for (int count = 0; count < LENGTH; count++) {
				
				prediction = ngram.getPredictions()[count];
				probability = ngram.getProbabilities()[count];

				dataset.setValue(probability, ngram.getContext(), prediction);
			}
		}
       
		/*dataset.setValue(0.13605904897333534, "be or not to", "be");
       dataset.setValue(0.06656263408951493, "be or not to", "mention");
       dataset.setValue(0.032759119479403784, "be or not to", "exceed");
       dataset.setValue(0.028824381687144844, "be or not to", "say");
       dataset.setValue(0.02452364382944196, "be or not to", "the");
       
       
       dataset.setValue(0.13605904897333534, "singin in the", "rain");
       dataset.setValue(0.06656263408951493, "singin in the", "shower");
       
       */
       return dataset;
   }
   
   /**
    * Creates a chart
    */
   private JFreeChart createChart(CategoryDataset dataset, String title) {
   	
   	JFreeChart chart = ChartFactory.createBarChart3D(
   		title, 
    		"Phrase _____",
   		"Probability",
   		dataset, 
   		PlotOrientation.VERTICAL, 
   		true, 
   		false, 
   		false
   	);
   	
   	chart.setBackgroundPaint(Color.LIGHT_GRAY);
   	chart.setBorderVisible(true);
   	chart.setBorderPaint(Color.BLACK);
   	chart.getTitle().setPaint(Color.BLUE); 
   	CategoryPlot p = chart.getCategoryPlot(); 
		p.setRangeGridlinePaint(Color.red);

		return chart;
	}
   /*
   public JFreeChart updateChart(CategoryDataset dataset, String title) {
	   	
	   	JFreeChart chart = ChartFactory.createBarChart3D(
	   		title, 
	    		"Phrase _____",
	   		"Probability",
	   		dataset, 
	   		PlotOrientation.VERTICAL, 
	   		true, 
	   		false, 
	   		false
	   	);
	   	
	   	chart.setBackgroundPaint(Color.LIGHT_GRAY);
	   	chart.setBorderVisible(true);
	   	chart.setBorderPaint(Color.BLACK);
	   	chart.getTitle().setPaint(Color.BLUE); 
	   	CategoryPlot p = chart.getCategoryPlot(); 
			p.setRangeGridlinePaint(Color.red);

			return chart;
		}*/
   private void createGUI() {
		setSize(WIDTH, HEIGHT);//creat window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//creat text area
		textDisplay = new JTextArea("This is the results display area~~~");
		textDisplay.setBackground(Color.PINK);
		textDisplay.setSize(WIDTH, HEIGHT);
		textDisplay.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		textDisplay.setEditable(false);
		textDisplay.setFont(new Font("Serif",0,20));
		resultScroll = new JScrollPane(textDisplay);
		resultScroll.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		resultScroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		resultMenuBar = new JMenuBar();
		this.setJMenuBar(resultMenuBar);
        this.add(resultScroll);
        //this.setVisible(true);
		//creat panel
	    textPanel = new JPanel(); 
	    textPanel.setBackground(Color.RED);
	    textPanel.setLayout(new BorderLayout());
	    textPanel.add(textDisplay,BorderLayout.CENTER);
	    textPanel.add(resultScroll,BorderLayout.EAST);
	    this.getContentPane().add(textPanel,BorderLayout.CENTER);
	    /*
		 * JButton redButton = new JButton("Red");
		 * redButton.setBackground(Color.WHITE);
		 * redButton.addActionListener(this); barPanel.add(redButton); //creat
		 * bar panel
		 */
	    
	
/*
		btmPanel = new JPanel();
	    btmPanel.setBackground(Color.LIGHT_GRAY);
       btmPanel.setLayout(new FlowLayout());

	    JButton blueButton = new JButton("Blue");
	    blueButton.setBackground(Color.WHITE);
	    blueButton.addActionListener(this);
	    btmPanel.add(blueButton);

	    JButton blackButton = new JButton("Black");
	    blackButton.setBackground(Color.WHITE);
	    blackButton.addActionListener(this);
	    btmPanel.add(blackButton);

	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);	*/
	} 
   
/*  
(non-Javadoc)  
* @see java.lang.Runnable#run()  
*/
@Override
public void run() {
	
	createGUI();
	this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
	this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	
	this.pack();
	//this.setVisible(true);
}

/*  
(non-Javadoc)  
* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)  
*/
@Override
public void actionPerformed(ActionEvent e) {
	String buttonString = e.getActionCommand();

	  if (buttonString.equals("Blue")) {
		  this.textDisplay.setBackground(Color.BLUE);
		  this.textDisplay.setForeground(Color.WHITE);
	  } else if (buttonString.equals("Black")) {
		  this.textDisplay.setBackground(Color.BLACK);
		  this.textDisplay.setForeground(Color.GREEN);
		  JOptionPane.showMessageDialog(this,"An Error Message","Wiring Class: Error",JOptionPane.ERROR_MESSAGE);
	  }
}

}
