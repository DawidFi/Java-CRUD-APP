import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class Assignment_B00139625_DawidFiuk extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextField idField;
	private JTextField brandField;
	private JTextField typeField;
	private JTextField quantityField;
	private JTable table;
	DataConnector conn;
	private ArrayList<Coffee> coffeeData = new ArrayList<>();
	private JTextField txtPrice;
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignment_B00139625_DawidFiuk frame = new Assignment_B00139625_DawidFiuk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	
	public Assignment_B00139625_DawidFiuk() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JButton displayAllBtn = new JButton("Display All");
		displayAllBtn.setBounds(60, 40, 110, 23);
		contentPane.add(displayAllBtn);
		displayAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = new DataConnector();
					coffeeData = conn.displayAll();
					display();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		JButton addBtn = new JButton("Add");
		addBtn.setBounds(60, 79, 110, 23);
		contentPane.add(addBtn);
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					conn = new DataConnector();
					float price = Float.parseFloat(txtPrice.getText());
					float quant = Float.parseFloat(quantityField.getText());
					int idInt = Integer.parseInt(idField.getText());
					conn.insertData(idInt, brandField.getText(), typeField.getText(), price, quant);
					coffeeData = conn.displayAll();
					display();
					clearTxt();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		JButton changeBtn = new JButton("Update");
		changeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = new DataConnector();
					float quant = Float.parseFloat(quantityField.getText());
					int idInt = Integer.parseInt(idField.getText());
					conn.updateQuantity(quant, idInt);
					coffeeData = conn.displayAll();
					display();
					clearTxt();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		changeBtn.setBounds(60, 116, 110, 23);
		contentPane.add(changeBtn);
		
		JButton removeBtn = new JButton("Remove");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = new DataConnector();
					int idInt = Integer.parseInt(idField.getText());
					conn.deleteData(idInt);
					coffeeData = conn.displayAll();
					display();
					clearTxt();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		removeBtn.setBounds(60, 155, 110, 23);
		contentPane.add(removeBtn);
		
		txtSearch = new JTextField();
		txtSearch.setText("search...");
		txtSearch.setBounds(217, 11, 147, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(374, 10, 89, 23);
		contentPane.add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = new DataConnector();
					coffeeData = conn.searchByType(txtSearch.getText());
					display();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(217, 44, 46, 14);
		contentPane.add(idLabel);
		
		JLabel brandLabel = new JLabel("Brand");
		brandLabel.setBounds(217, 75, 46, 14);
		contentPane.add(brandLabel);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setBounds(217, 106, 78, 14);
		contentPane.add(typeLabel);
		
		JLabel qunatityLabel = new JLabel("In Stock");
		qunatityLabel.setBounds(217, 137, 78, 14);
		contentPane.add(qunatityLabel);
		
		idField = new JTextField();
		idField.setBounds(320, 41, 130, 20);
		contentPane.add(idField);
		idField.setColumns(10);
		
		brandField = new JTextField();
		brandField.setBounds(320, 72, 130, 20);
		contentPane.add(brandField);
		brandField.setColumns(10);
		
		typeField = new JTextField();
		typeField.setBounds(320, 103, 130, 20);
		contentPane.add(typeField);
		typeField.setColumns(10);
		
		quantityField = new JTextField();
		quantityField.setBounds(320, 134, 130, 20);
		contentPane.add(quantityField);
		quantityField.setColumns(10);
		
		JLabel priceLabel = new JLabel("Price per kg");
		priceLabel.setBounds(217, 168, 78, 14);
		contentPane.add(priceLabel);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 208, 529, 203);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Brand", "type", "Price", "In Stock"
			}
		));
		scrollPane.setViewportView(table);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(320, 165, 130, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
	}
	private void display() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ArrayList<Coffee> coffee = coffeeData;
		Object rowData[] = new Object[5];
		for(int i =0; i < coffee.size(); i++) {
			rowData[0] = coffee.get(i).getId();
			rowData[1] = coffee.get(i).getBrand();
			rowData[2] = coffee.get(i).getType();
			rowData[3] = coffee.get(i).getPrice();
			rowData[4] = coffee.get(i).getQuantity();
			model.addRow(rowData);
		}	
	
	}

	private void clearTxt() {
		txtPrice.setText("");
		idField.setText("");
		brandField.setText("");
		typeField.setText("");
		quantityField.setText("");
	}
}
