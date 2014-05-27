/**  
 * @title ChartPanel.java  
 * @package assign2.gui  
 * @author kervin  
 * @version V1.0  
 * created 2014年5月17日  
 */
package assign2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import assign2.ngram.NGramContainer;
import assign2.ngram.NGramMap;

/**ChartPanel
 * A class extends the JPanel, to create and control the chart area   
 * @version 1.0  
 * @author QianFu&ChuanLi 
 * @created 2014.5.17.  
 */

public class ChartPanel extends JPanel{
	/**  
	 * @fields serialVersionUID  
	 */  
	private static java.awt.Font Font = new Font("Serif", 0, 20);
	private static final long serialVersionUID = 1L;
	private CategoryDataset dataset;
	private JFreeChart chart;
	private org.jfree.chart.ChartPanel chartPanel;
	private String chartTitle;
	
	/**  
	 * Constructor Method     
	 */
	public ChartPanel(){
		setLayout(new BorderLayout());
		chartTitle = "Bar Results";
		setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1, true),chartTitle, TitledBorder.CENTER, TitledBorder.TOP));
		//Create the data 
		dataset = createDataset();
		chart = createChart(dataset, chartTitle);
		//Set the bar format
		SetChartFormat(chart);
		//Create the chart panel 
		chartPanel = new org.jfree.chart.ChartPanel(chart);
		chartPanel.setBackground(Color.PINK);
		add(chartPanel,BorderLayout.CENTER);
		
	}
	/**createChart(CategoryDataset dataset, String title)
	 * 
	 * @param dataset
	 * @param title
	 * @return chart
	 * 
	 */
	private JFreeChart createChart(CategoryDataset dataset, String ChartTitle) {
	   	
	   	JFreeChart chart = ChartFactory.createBarChart3D(
	   		ChartTitle, 
	    	"Phrase",
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
	/**SetBarFormat(JFreeChart chart)
	 * 
	 * Set the format of Chart  
	 * @param chart   
	 *   
	 */
	private void SetChartFormat(JFreeChart chart){
		//Title and font
		chart.setTitle(new TextTitle(chartTitle ,Font));
		//Background
		chart.setBackgroundPaint(ChartColor.PINK);
		//Set the background and line color of category
		CategoryPlot p = chart.getCategoryPlot();
		p.setBackgroundPaint(ChartColor.WHITE);
		p.setRangeGridlinePaint(ChartColor.red);
		
	}	
	
    //*********************************************ShowResultBar**********************************************
	/**ShowResultBar(NGramContainer ngram)
	 * 
	 * Update the Bar, according to the ngram  
	 * @param node   
	 * 
	 */
	public void ShowResultChart(NGramContainer ngram) {
		remove(chartPanel);
		//Create the data 
		dataset = createDataset(ngram);
		chart = createChart(dataset, chartTitle );
		//Set the bar format
		SetChartFormat(chart);
		//Create the chart panel 
		chartPanel = new org.jfree.chart.ChartPanel(chart);
		chartPanel.setBackground(Color.PINK);
		add(chartPanel, BorderLayout.CENTER);
	}
	/**  
	 * ShowResultChart(String[] contexts, NGramMap store)
	 * 
	 * Update the chart, according to the ngramStore
	 * @param contexts
	 * @param store   
	 * 
	 */
	public void ShowResultChart(String[] contexts, NGramMap ngramStore){
		remove(chartPanel);
		dataset = createDataset(contexts, ngramStore);
        chart = createChart(dataset, chartTitle );
        SetChartFormat(chart);
        chartPanel = new org.jfree.chart.ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
	}
	//*********************************************End of ShowResultBar**********************************************
	
	//*********************************************CreateDataset**********************************************
	
	/**DefaultCategoryDataset createDataset() 
	 * 
	 * Create the new data 
	 * @return dataset 
	 * 
	 */
	private  DefaultCategoryDataset createDataset() {
	       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       return dataset;
	}
	
	/**DefaultCategoryDataset createDataset(NGramContainer ngram)
	 * 
	 * Update the data,according to the ngram 
	 * @param node
	 * @return dataset
	 */
	private DefaultCategoryDataset createDataset(NGramContainer ngram) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//get the components of ngram 
		for (int count = 0; count < ngram.getPredictions().length; count++) {
			//set value of bar chart
			dataset.setValue(ngram.getProbabilities()[count], ngram.getContext(),
					ngram.getPredictions()[count]);
		}
		return dataset;
	}
	/**
	 * DefaultCategoryDataset createDataset(String[] contexts,NGramMap ngramMap)
	 * 
	 * Search the contexts from the ngramMap,and then update the data
	 *  
	 * @param contexts
	 * @param ngramMap
	 * @return dataset
	 * 
	 */
	private  DefaultCategoryDataset createDataset(String[] contexts,NGramMap ngramMap) {
	       
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       NGramContainer ngram ;
	       String prediction;
	   	   double probability;
	   	   int PREDICTION_LENGTH = 0;
	   	   //get the results of context from the ngramMap
			for (String s : contexts) {
				ngram = ngramMap.getNGram(s);
				PREDICTION_LENGTH = ngram.getPredictions().length;
				
				for (int count = 0; count < PREDICTION_LENGTH; count++) {
					
					prediction = ngram.getPredictions()[count];
					probability = ngram.getProbabilities()[count];

					dataset.setValue(probability, ngram.getContext(), prediction);
				}
			}
			return dataset;
	 }   
	//********************************************End of createDataset**********************************************
	/** clearResultsChart 
	 * For clearing the result Chart   
	 *    
	 */
	public void clearResultsChart() {
		
		remove(chartPanel);
		//Create the new data and barChart
		dataset = createDataset();
		chart = createChart(dataset, chartTitle);
		SetChartFormat(chart);
		chartPanel = new org.jfree.chart.ChartPanel(chart);
		add(chartPanel, BorderLayout.CENTER);
		// Update the bar
		updateUI();
	}
}
