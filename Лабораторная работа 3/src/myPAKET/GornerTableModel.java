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
		return 3;
	}
	//the number of rows in the table depends on the length of the tabulation interval and the step size
	public int getRowCount(){
	    return new Double(Math.ceil((to-from)/step)).intValue()+1;
	}
    
	//the contents of table cells
	public Object getValueAt(int row, int col) {
		// 1)the value of X
		double x = from + step*row;
		Double result = 0.0;
		for(int i = 0; i < coefficients.length;i++)
		    result = result*x + coefficients[i];
		if (col==0) {
		    return x;
		} 
		// 2)the value of a polynomial
		else if (col == 1){
		    return result;
		}
		else {
		    boolean flag = false, flag1 = false, flag2 = false, flag3 = false;
			Double ClonOfResult = result;
			if(ClonOfResult - ClonOfResult.intValue()==0)
				flag1 = true;
			
			//check the integer part of the number for parity or odd
			while(result.intValue()!=0) {
				if(result.intValue()%2 == 0) {
					flag2=true;
				}
				else {
					flag = true;
				}
				result/=10;
			}
			
			//check the fractional part of the number for parity or odd
			while(ClonOfResult - ClonOfResult.intValue()!=0) {
				if((ClonOfResult*10) % 2 == 0) {
					flag1 = true;
				}
				else {
					flag3 = true;
				}
				ClonOfResult*=10;
			}
			if ((flag == false && flag1 == false) || (flag2 == false  && flag3 == false))
			    return true;
			return false;
		}
	}
    
	//get information about column names
	public Class<?> getColumnClass(int col) {
		if(col == 2)
			return Boolean.class;
		return Double.class;
	}

	//get information about column names
	public String getColumnName(int col) {
		switch (col){
		case 0:
		    return "Значение X";
		case 1:
		    return "Значение многочлена";
		default:
		    return "Разностороннее";
		}
	}
	
}
