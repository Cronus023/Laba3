package myPAKET;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    
	//array of coefficients of the polynomial
	private Double[] coefficients;
	//start and end points of the segment tabulations
	private Double from;
	private Double to;
	//step of tabulation
	private Double step;
	
	public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
		this.from = from;
		this.to = to;
		this.step = step;
		this.coefficients = coefficients;
	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
    
	//get information about column names
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return super.getColumnClass(arg0);
	}

	//get information about the data type in the columns
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return super.getColumnName(arg0);
	}
	
	
}
