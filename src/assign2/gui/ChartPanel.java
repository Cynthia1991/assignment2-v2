/**  
 * @title ChartPanel.java  
 * @package assign2.gui  
 * @author kervin  
 * @version V1.0  
 * created 2014年5月17日  
 */
package assign2.gui;

import org.jfree.chart.JFreeChart;

/**  
 * @version 1.0  
 * @author QianFu&ChuanLi 
 * @created 2014.5.17.  
 */
/*a panel to hold the jfreechart bar chart object for display*/
@SuppressWarnings("serial")
public class ChartPanel extends org.jfree.chart.ChartPanel{
	public ChartPanel(JFreeChart chart) {
		super(chart);
		// TODO Auto-generated constructor stub
	}

	public ChartPanel(JFreeChart chart, boolean useBuffer) {
		super(chart, useBuffer);
		// TODO Auto-generated constructor stub
	}

	public ChartPanel(JFreeChart chart, boolean properties, boolean save,
			boolean print, boolean zoom, boolean tooltips) {
		super(chart, properties, save, print, zoom, tooltips);
		// TODO Auto-generated constructor stub
	}

	public ChartPanel(JFreeChart chart, int width, int height,
			int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
			int maximumDrawHeight, boolean useBuffer, boolean properties,
			boolean save, boolean print, boolean zoom, boolean tooltips) {
		super(chart, width, height, minimumDrawWidth, minimumDrawHeight,
				maximumDrawWidth, maximumDrawHeight, useBuffer, properties,
				save, print, zoom, tooltips);
		// TODO Auto-generated constructor stub
	}

	public ChartPanel(JFreeChart chart, int width, int height,
			int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
			int maximumDrawHeight, boolean useBuffer, boolean properties,
			boolean copy, boolean save, boolean print, boolean zoom,
			boolean tooltips) {
		super(chart, width, height, minimumDrawWidth, minimumDrawHeight,
				maximumDrawWidth, maximumDrawHeight, useBuffer, properties,
				copy, save, print, zoom, tooltips);
		// TODO Auto-generated constructor stub
	}
	
}
