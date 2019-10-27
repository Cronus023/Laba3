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

	
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub
		return null;
	}

}
