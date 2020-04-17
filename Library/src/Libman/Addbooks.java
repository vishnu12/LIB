package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Addbooks implements ActionListener{
	JFrame f;
	JLabel l1, l2, l3, l4, l5;
	
	
	JButton b1, b2;
	JTextField f1, f2, f3,f4,f5;

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public Addbooks() {
		init();
		connect();
		
	}

	
	public void init() {

		f = new JFrame("Admin section");
		f.setLocation(100, 100);
		f.setSize(500, 500);
		l1 = new JLabel("Call Name");
		l1.setBounds(50, 50, 100, 30);
		f.add(l1);
		f1 = new JTextField();
		f1.setBounds(200, 50, 130, 30);
		f.add(f1);

		l2 = new JLabel("BookName");
		l2.setBounds(50, 100, 100, 30);
		f.add(l2);
		f2 = new JTextField();
		f2.setBounds(200, 100, 130, 30);
		f.add(f2);

		l3 = new JLabel("Author");
		l3.setBounds(50, 150, 100, 30);
		f.add(l3);
		f3 = new JTextField();
		f3.setBounds(200, 150, 130, 30);
		f.add(f3);

		l4 = new JLabel("Publisher");
		l4.setBounds(50, 200, 100, 30);
		f.add(l4);
		f4 = new JTextField();
		f4.setBounds(200, 200, 130, 30);
		f.add(f4);

		l5 = new JLabel("Quantity");
		l5.setBounds(50, 250, 100, 30);
		f.add(l5);
		f5 = new JTextField();
		f5.setBounds(200, 250, 130, 30);
		f.add(f5);

		b1 = new JButton("ADD");
		b1.setBounds(210, 320, 100, 40);
		f.add(b1);
		b1.addActionListener(this);
		b2 = new JButton("Back");
		b2.setBounds(224, 380, 70, 30);
		f.add(b2);
		b2.addActionListener(this);

		f.setLayout(null);
		f.setVisible(true);
		

	}
	
	public void connect() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testv?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC");

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}
	
	public void ADD() {
		String call = f1.getText();
		String name = f2.getText();
		String auth = f3.getText();
		String pub = f4.getText();
		String quant = f5.getText();

		try {
			pst = con.prepareStatement("insert into book(Callno,BookName,Author,Publisher,Quantity) values(?,?,?,?,?)");
			pst.setString(1, call);
			pst.setString(2, name);
			pst.setString(3, auth);
			pst.setString(4, pub);
			pst.setString(5, quant);
			int k = pst.executeUpdate();

			if (k == 1) {

				JOptionPane.showMessageDialog(null, "Added");

			} else {

				JOptionPane.showMessageDialog(null, "Error");

			}

			f1.setText("");
			f2.setText("");
			f3.setText("");
			f4.setText("");
			f5.setText("");
			f1.requestFocus();

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}
	
	
	public static void main(String[] args) {
		new Addbooks();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			
			ADD();
		}else if(e.getSource()==b2) {
			
			f.setVisible(false);
		}
		
	}
}
