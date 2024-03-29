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
	private JMenuItem AboutProgrammeMenuItem;
	
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
		JMenu tableInformation = new JMenu("Справка");
		menuBar.add(tableInformation );
		
		//create a new "action" - save to a text file
		Action saveToTextAction = new AbstractAction("Сохранить в текстовый файл") {
		public void actionPerformed(ActionEvent event) {
		    if (fileChooser==null) {
		        fileChooser = new JFileChooser();
		        fileChooser.setCurrentDirectory(new File("."));
		    }
		    // show the dialog window
		    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
		        saveToTextFile(fileChooser.getSelectedFile());
		}
		};
		//add to our menu a new item
		saveToTextMenuItem = fileMenu.add(saveToTextAction);
		// by default, the menu item is not available (no data yet)
		saveToTextMenuItem.setEnabled(false);
		
		//create a new "action" - save to a text file for graph
		Action saveToGraphicsAction = new AbstractAction("Сохранить данные для построения графика") {
		public void actionPerformed(ActionEvent event) {
		    if (fileChooser==null) {
		        fileChooser = new JFileChooser();
		        fileChooser.setCurrentDirectory(new File("."));
		    }
		    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION);
		        saveToGraphicsFile(fileChooser.getSelectedFile());
		}
		};
		saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
		saveToGraphicsMenuItem.setEnabled(false);
		
		//create a new "action" - find a value of palinomial
		Action searchValueAction = new AbstractAction("Найти значение многочлена") {
			public void actionPerformed(ActionEvent event) {
			    // prompt the user to enter the search string
			    String value = JOptionPane.showInputDialog(MainFrame.this,"Введите значение для поиска", "Поиск значения",JOptionPane.QUESTION_MESSAGE);
			    // set the entered value as a needle in the Visualizer
			    renderer.setNeedle(value);
			    // refresh the table
			    getContentPane().repaint();
			}
			};
		searchValueMenuItem = tableMenu.add(searchValueAction);
		searchValueMenuItem.setEnabled(false);
			
		//create a new "action" - AboutProgramme
		Action AboutProgramme = new AbstractAction("О программе") {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(MainFrame.this,"Клочко Андрей 8 группа", "Автор программы",JOptionPane.INFORMATION_MESSAGE);
		}
		};
		AboutProgrammeMenuItem = tableInformation.add(AboutProgramme);
		
		// create the button "Вычислить"
		JButton buttonCalc = new JButton("Вычислить");
		buttonCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
			try {
			    // read the values of the beginning and end of the segment, step from the input fields
			    Double from = Double.parseDouble(textFieldFrom.getText());
			    Double to = Double.parseDouble(textFieldTo.getText());
			    Double step = Double.parseDouble(textFieldStep.getText());
			
			    // create the table
			    data = new GornerTableModel(from, to, step,MainFrame.this.coefficients);
			    JTable table = new JTable(data);
			    table.setDefaultRenderer(Double.class, renderer);
			    // set the size of row 
			    table.setRowHeight(30);
			
			    //remove all nested items from the hboxresult container
			    hBoxResult.removeAll();
			    // add to hBoxResult the table "wrapped" in the panel by scroll bars
			    hBoxResult.add(new JScrollPane(table));
			
			    // refresh the main window
			    getContentPane().validate();
			   
			    //some actions now is available
			    saveToTextMenuItem.setEnabled(true);
			    saveToGraphicsMenuItem.setEnabled(true);
			    searchValueMenuItem.setEnabled(true);
			} 
			catch (NumberFormatException ex) {
			    JOptionPane.showMessageDialog(MainFrame.this,"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",JOptionPane.WARNING_MESSAGE);
			}
			}
			});
		
		//create the button "Очистить поля"
		JButton buttonReset = new JButton("Очистить поля");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				//set the value of fields by default
				textFieldFrom.setText("0.0");
				textFieldTo.setText("1.0");
				textFieldStep.setText("0.1");
				
				//remove all nested items from the hboxresult container 
				hBoxResult.removeAll();
				// add in hBoxResult container empty panel
				hBoxResult.add(new JPanel());
				
				//some actions now is not available
				saveToTextMenuItem.setEnabled(false);
				saveToGraphicsMenuItem.setEnabled(false);
				searchValueMenuItem.setEnabled(false);
				
				// refresh the main window
				getContentPane().validate();	   
			}
	    });
		
		// signature to enter the left border of the segment
		JLabel labelForFrom = new JLabel("X изменяется на интервале от:");
		textFieldFrom = new JTextField("0.0", 10);
		textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
		
		// signature to enter the right border of the segment
		JLabel labelForTo = new JLabel("до:");
		textFieldTo = new JTextField("1.0", 10);
		textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());
		
		//signature to enter the step of tabulation
		JLabel labelForStep = new JLabel("с шагом:");
		textFieldStep = new JTextField("0.1", 10);
		textFieldStep.setMaximumSize(textFieldStep.getPreferredSize());
		
		
		Box hboxRange = Box.createHorizontalBox();
		hboxRange.setBorder(BorderFactory.createBevelBorder(1));
		hboxRange.add(Box.createHorizontalGlue());
		
		hboxRange.add(labelForFrom);
		hboxRange.add(Box.createHorizontalStrut(10));
		hboxRange.add(textFieldFrom);
		hboxRange.add(Box.createHorizontalStrut(20));
		
		hboxRange.add(labelForTo);
		hboxRange.add(Box.createHorizontalStrut(10));
		hboxRange.add(textFieldTo);
		hboxRange.add(Box.createHorizontalStrut(20));
		
		hboxRange.add(labelForStep);
		hboxRange.add(Box.createHorizontalStrut(10));
		hboxRange.add(textFieldStep);
		hboxRange.add(Box.createHorizontalGlue());
		hboxRange.setPreferredSize(new Dimension(new Double(hboxRange.getMaximumSize().getWidth()).intValue(),new Double(hboxRange.getMinimumSize().getHeight()).intValue()*2));getContentPane().add(hboxRange, BorderLayout.NORTH);
	
		// put buttons "Вычислить" and "Очистить поля" in container(СENTER)
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
		hboxButtons.add(Box.createHorizontalGlue());
		
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalGlue());
		
		hboxButtons.setPreferredSize(new Dimension(new Double(hboxButtons.getMaximumSize().getWidth()).intValue(), new Double(hboxButtons.getMinimumSize().getHeight()).intValue()*2));
		getContentPane().add(hboxButtons, BorderLayout.SOUTH);
		hBoxResult = Box.createHorizontalBox();
		hBoxResult.add(new JPanel());
		getContentPane().add(hBoxResult, BorderLayout.CENTER);
	}
	
	protected void saveToTextFile(File selectedFile) {
		 try {
			PrintStream out = new PrintStream(selectedFile);
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
			for (int i = 0; i<data.getRowCount(); i++) {
			    out.println("Значение в точке " + data.getValueAt(i,0) + " равно " + data.getValueAt(i,1));
			}
			out.close();
			} catch (FileNotFoundException e) {
				
		    }
	}
    
	
	protected void saveToGraphicsFile(File selectedFile) {
		try {
		DataOutputStream out = new DataOutputStream(new
		FileOutputStream(selectedFile));
		for (int i = 0; i<data.getRowCount(); i++) {
		    out.writeDouble((Double)data.getValueAt(i,0));
		    out.writeDouble((Double)data.getValueAt(i,0));
		}
		out.close();
		} 
		catch (Exception e) {
		
		}
	}

	public static void main(String[] args) {
		Double[] coefficients = new Double[2];
		coefficients[0] = 1.0;
		coefficients[1] = 2.0;
		MainFrame frame = new MainFrame(coefficients);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
