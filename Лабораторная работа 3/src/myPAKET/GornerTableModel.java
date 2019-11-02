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
			int flag = 0, flag1 = 0, flag2 = 0, flag3 = 0;
			Double result1 = result;
			if(result1 - result1.intValue()==0)
				flag1 = 1;
			
			//check the integer part of the number for parity or odd
			while(result.intValue()!=0) {
				if(result.intValue()%2 == 0) {
					flag2=1;
				}
				else {
					flag = 1;
				}
				result/=10;
			}
			
			//check the fractional part of the number for parity or odd
			while(result1 - result1.intValue()!=0) {
				if((result1*10) % 2 == 0) {
					flag1 = 1;
				}
				else {
					flag3 = 1;
				}
				result1*=10;
			}
			
			if ((flag == 0 && flag1 == 0) || (flag2 == 0  && flag3 == 0))
			    return 1;
			return 0;
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
		case 1:
		    return "Значение многочлена";
		default:
		    return "Разностороннее";
		}
	}
	
}
