package myPAKET;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class GornerTableCellRenderer implements TableCellRenderer {
	
    /*We will look for cells whose string representation is equal to needle 
	for this we use the analogy of finding a needle in hay, in the role of hay-table*/
	
	private String needle = null;
	
	//our "workhorse"
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel();

	//this field for formatting string representation of numbers
	private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();
	
	
	public void setNeedle(String needle) {
        this.needle = needle;
	}
	public GornerTableCellRenderer() {
		// show only 5 decimal places
		formatter.setMaximumFractionDigits(5);
		
		// do not use grouping (do not separate thousand commas or spaces)
		formatter.setGroupingUsed(false);
		
		//set a point as the decimal separator
		DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
		dottedDouble.setDecimalSeparator('.');
		formatter.setDecimalFormatSymbols(dottedDouble);
		
		panel.add(label);
		// the left alignment
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int col) {
		    //convert double to string using formatter
			String formattedDouble = formatter.format(value);
			// set the label text equal to the string representation of a number
			label.setText(formattedDouble);
			if (col==1 && needle!=null && needle.equals(formattedDouble)) {
			    //paint the cell in red, if we find a needle
			    panel.setBackground(Color.RED);
			} 
			else {
			    panel.setBackground(Color.WHITE);
			}
		return panel;
	}
	
}
