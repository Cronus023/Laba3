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
	
	public Double getFrom() {
		return from;
	}
	public Double getTo() {
		return to;
	}
	public Double getStep() {
		return step;
	}
	
	//we have only two columns
	public int getColumnCount() {
		return 2;
	}
    
	//the number of rows in the table depends on the length of the tabulation interval and the step size
	public int getRowCount(){
	    return new Double(Math.ceil((to-from)/step)).intValue()+1;
	}
    
	//the contents of table cells
	public Object getValueAt(int row, int col) {
		// 1)the value of X
		double x = from + step*row;
		if (col==0) {
		    return x;
		} 
		// 2)the value of a polynomial
		else {
		    Double result = 0.0;
		    //the value of the polynomial will be calculated later
		    return result;
		}
	}
    
	//get information about column names
	public Class<?> getColumnClass(int col) {
		return Double.class;
	}

	//get information about column names
	public String getColumnName(int col) {
		switch (col){
		case 0:
		    return "Значение X";
		default:
		    return "Значение многочлена";
		}
	}
	
}
