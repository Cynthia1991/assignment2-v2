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
import java.util.List;

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

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import assign2.ngram.NGramContainer;
import assign2.ngram.NGramMap;

/**  
 * @version 1.0  
 * @author QianFu&ChuanLi 
 * @created 2014.5.17.  
 */
/*a panel to hold the jfreechart bar chart object for display*/

//@SuppressWarnings("serial")
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
	
	public ChartPanel(){
		setLayout(new BorderLayout());
		chartTitle = "Bar Results";
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1, true),chartTitle, TitledBorder.CENTER, TitledBorder.TOP));
		dataset = createDataset();
		chart = createChart(dataset, chartTitle);
		SetChartFormat(chart);
		
		chartPanel = new org.jfree.chart.ChartPanel(chart);
		chartPanel.setBackground(Color.PINK);
		add(chartPanel,BorderLayout.CENTER);
		
	}

	public void ShowResultChart(NGramContainer node) {
		remove(chartPanel);
		dataset = createDataset(node);
		chart = createChart(dataset, chartTitle );
		SetChartFormat(chart);
		chartPanel = new org.jfree.chart.ChartPanel(chart);
		add(chartPanel, BorderLayout.CENTER);
	}
	private void SetChartFormat(JFreeChart chart){
		chart.setTitle(new TextTitle(chartTitle ,Font));
		//chart.setBackgroundPaint(paint());
		chart.setBackgroundPaint(ChartColor.PINK);
		
		CategoryPlot p = chart.getCategoryPlot();
		// 设置图的背景颜色
		p.setBackgroundPaint(ChartColor.WHITE);
		// 设置表格线颜色
		p.setRangeGridlinePaint(ChartColor.red);
		
	}
	private DefaultCategoryDataset createDataset(NGramContainer node) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < node.getPredictions().length; i++) {
			dataset.setValue(node.getProbabilities()[i], node.getContext(),
					node.getPredictions()[i]);
		}
		return dataset;
	}

	public void ShowResultBar(String[] contexts, NGramMap store){
		remove(chartPanel);
		dataset = createDataset(contexts, store);
        chart = createChart(dataset, chartTitle );
        SetChartFormat(chart);
        chartPanel = new org.jfree.chart.ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
	}
	private  DefaultCategoryDataset createDataset() {
	       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       return dataset;
	}
	 
	 private  DefaultCategoryDataset createDataset(String[] contexts,NGramMap ngramMap) {
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
			return dataset;
	 }


	   /**
	    * Creates a chart
	    */
	   private JFreeChart createChart(CategoryDataset dataset, String title) {
	   	
	   	JFreeChart chart = ChartFactory.createBarChart3D(
	   		title, 
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
		/**
	   * Clear all the result on the chart. Creating an empty dataset, a chart and
	   * stacking it inside the chart panel
	   */
	   public void clearResultChart() {
	   // Creating an empty dataset
	   remove(chartPanel);
	   dataset = createDataset();
	   chart = createChart(dataset, chartTitle );
	   SetChartFormat(chart);
	   chartPanel = new org.jfree.chart.ChartPanel(chart);
	   add(chartPanel, BorderLayout.CENTER);
	   // Updating the chart panel with the above components
	   updateUI();
	   }
}
