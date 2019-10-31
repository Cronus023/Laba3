package myPAKET;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	// the size of the main window
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	
	//array of coefficients of the polynomial
	private Double[] coefficients;
	
	// the object dialog box to select files
	private JFileChooser fileChooser = null;
	
	//menu item
	private JMenuItem saveToTextMenuItem;
	private JMenuItem saveToGraphicsMenuItem;
	private JMenuItem searchValueMenuItem;
	
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JTextField textFieldStep;
	private Box hBoxResult;
	
	private GornerTableCellRenderer renderer = new GornerTableCellRenderer();
	private GornerTableModel data;
    
	public MainFrame(Double[] coefficients) {
		super("Табулирование многочлена на отрезке по схеме Горнера");
		this.coefficients = coefficients;
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);
		
		// create menu
		JMenuBar menuBar = new JMenuBar();
		// set menu as main menu in our window
		setJMenuBar(menuBar);
	
		//add to our menu some items
		JMenu fileMenu = new JMenu("Файл");
		menuBar.add(fileMenu);
		JMenu tableMenu = new JMenu("Таблица");
		menuBar.add(tableMenu);			
	}
	
	protected void saveToTextFile(File selectedFile) {
		 try {
			// Создать новый символьный поток вывода, направленный в указанный файл
			PrintStream out = new PrintStream(selectedFile);
			// Записать в поток вывода заголовочные сведения
			out.println("Результаты табулирования многочлена по схеме Горнера");
			out.print("Многочлен: ");
			for (int i=0; i<coefficients.length; i++) {
			    out.print(coefficients[i] + "*X^" + (coefficients.length-i-1));
			    if (i!=coefficients.length-1)
			        out.print(" + ");
			}
			out.println("");
			out.println("Интервал от " + data.getFrom() + " до " + data.getTo() + " с шагом " + data.getStep());
			out.println("================================================");
			// Записать в поток вывода значения в точках
			for (int i = 0; i<data.getRowCount(); i++) {
			    out.println("Значение в точке " + data.getValueAt(i,0) + " равно " + data.getValueAt(i,1));
			}
			// Закрыть поток
			out.close();
			} catch (FileNotFoundException e) {
				
		    }
	}

	public static void main(String[] args) {
		
		
		Double[] coefficients = new Double[3];
        
		MainFrame frame = new MainFrame(coefficients);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
