package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Addlibrarian implements ActionListener {

	JFrame f;
	JLabel l1, l2, l3, l4, l5;
	JPasswordField p;
	JTextArea a;
	JButton b1, b2;
	JTextField f1, f2, f3;

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	public Addlibrarian() {

		init();
		connect();

	}

	public void init() {

		f = new JFrame("Admin section");
		f.setLocation(100, 100);
		f.setSize(500, 500);
		l1 = new JLabel("Name");
		l1.setBounds(50, 50, 100, 30);
		f.add(l1);
		f1 = new JTextField();
		f1.setBounds(200, 50, 130, 30);
		f.add(f1);

		l2 = new JLabel("Password");
		l2.setBounds(50, 100, 100, 30);
		f.add(l2);
		p = new JPasswordField();
		p.setBounds(200, 100, 130, 30);
		f.add(p);

		l3 = new JLabel("Address");
		l3.setBounds(50, 150, 100, 30);
		f.add(l3);
		a = new JTextArea();
		a.setBounds(200, 150, 130, 70);
		f.add(a);

		l4 = new JLabel("City");
		l4.setBounds(50, 250, 100, 30);
		f.add(l4);
		f2 = new JTextField();
		f2.setBounds(200, 250, 130, 30);
		f.add(f2);

		l5 = new JLabel("Contact");
		l5.setBounds(50, 300, 100, 30);
		f.add(l5);
		f3 = new JTextField();
		f3.setBounds(200, 300, 130, 30);
		f.add(f3);

		b1 = new JButton("ADD");
		b1.setBounds(210, 350, 100, 40);
		f.add(b1);
		b1.addActionListener(this);
		b2 = new JButton("Back");
		b2.setBounds(224, 410, 70, 30);
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
		String name = f1.getText();
		String pass = p.getText();
		String add = a.getText();
		String city = f2.getText();
		String cont = f3.getText();

		try {
			pst = con.prepareStatement("insert into addlib2(Name,Password,Address,City,Contact) values(?,?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, pass);
			pst.setString(3, add);
			pst.setString(4, city);
			pst.setString(5, cont);
			int k = pst.executeUpdate();

			if (k == 1) {

				JOptionPane.showMessageDialog(null, "Added");

			} else {

				JOptionPane.showMessageDialog(null, "Error");

			}

			f1.setText("");
			p.setText("");
			a.setText("");
			f2.setText("");
			f3.setText("");
			f1.requestFocus();

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}
	
	
		
		
	

	public static void main(String[] args) {

		new Addlibrarian();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {

			ADD();

		} else if (e.getSource() == b2) {

			new Adminsection();

		}

	}
}
