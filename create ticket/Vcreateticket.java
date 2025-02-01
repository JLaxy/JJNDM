package finalProjectAOOP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Vcreateticket extends JFrame{
	
	public final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public final String DB_URL = "jdbc:mysql://localhost/jjdnm";
	public final String DB_USER = "root";
	public final String DB_PASS = "password";
	
	Database database;
	JComboBox <String> selector;
	JTextField seatNo;
	JLabel seats, movie, moviePrice, price;
	JButton[] buttons;
	Connection conn;
	JPanel movPanel, pricePanel, seatPanel, unknownPanel, buttonPanel;
	JFrame frame; 
	Statement st;
	ResultSet rs;
	int selectedIndex;
	public Vcreateticket(ResultSet rs) {
		
		seatNo = new JTextField();
		
		movie = new JLabel("Movie     ");
		moviePrice = new JLabel("Price:                                                        ");
		seats = new JLabel("Seat Number:               ");
		
		
		
		movPanel = new JPanel();
		movPanel.setBounds(10, 10, 280, 100);
		
		unknownPanel = new JPanel();

		pricePanel = new JPanel();
		pricePanel.setBounds(10, 110, 280, 50);
		
		seatPanel = new JPanel();
		seatPanel.setBounds(10, 160, 280, 50);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 210, 280, 50);
		buttons = new JButton[2];
		for (int i = 0; i < buttons.length; i++) {
			String[] text = { "Add", "Cancel" };
			buttons[i] = new JButton(text[i]);
			buttons[i].setFocusable(false);
			this.buttonPanel.add(buttons[i]);
		}

		selector = new JComboBox<String>();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql="SELECT * FROM movies";
            st= conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
        
            while(rs.next()){
                selector.addItem(rs.getString("movName"));
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
	
		
		
		price = new JLabel();
		
		this.movPanel.add(movie);
		this.movPanel.add(selector);
		this.pricePanel.add(moviePrice);
		this.seatPanel.add(seats);
		this.seatPanel.add(seatNo);
		this.seatNo.setPreferredSize(new Dimension(100,20));
		this.pricePanel.add(price);
		this.add(seatPanel);
		this.add(movPanel);
		this.add(pricePanel);
		this.add(buttonPanel);
		this.add(unknownPanel);
		this.setSize(300, 300);
		this.setVisible(true);
		
		
	}
	
	public void priceJLabel(String price) {
		this.price.setText(price);
	}
	 	
	public JTextField getSeatNo() {
		return seatNo;
	}
	
	public void setAddActionListener(ActionListener listener) {
		buttons[0].addActionListener(listener);
	}

	// Binding cancel button listener
	public void setCancelListener(ActionListener listener) {
		buttons[1].addActionListener(listener);
	}
	
	public void setSelectorListener(ActionListener listener) {
		selector.addActionListener(listener);
	}


}
