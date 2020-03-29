package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;

public class LibLogin implements ActionListener,KeyListener {

	JFrame f;
	JButton b1;
	JLabel l1, l2, l3;
	JTextField f1;
	JPasswordField p1;
	Connection con;PreparedStatement pst;ResultSet rs;

	public LibLogin() {

		f = new JFrame("Login");
		f.setLocation(100, 100);
		f.setSize(400, 400);
		l1 = new JLabel("Librarian login form");
		l1.setBounds(120, 30, 200, 30);
		f.add(l1);
		b1 = new JButton("Login");
		b1.setBounds(140, 250, 90, 30);
		f.add(b1);
		b1.addActionListener(this);
		
		l2 = new JLabel("username");
		l2.setBounds(40, 105, 100, 30);
		f.add(l2);
		f1 = new JTextField();
		f1.setBounds(165, 105, 120, 30);
		f.add(f1);
		l3 = new JLabel("password");
		l3.setBounds(40, 145, 100, 30);
		f.add(l3);
		p1 = new JPasswordField();
		p1.setBounds(165, 145, 120, 30);
		f.add(p1);p1.addKeyListener(this);

		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		
		connect();

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


	public static void main(String[] args) {
		new LibLogin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		String name=f1.getText();
		String pass=p1.getText();
		
		
		try {
			pst=con.prepareStatement("select Name,Password from addlib2 where Name=?");
			pst.setString(1, name);
			rs=pst.executeQuery();
			
			while(rs.next()) {
			if(pass.equals(rs.getString("Password"))) {
				
				JOptionPane.showMessageDialog(null, "Logged in");
				
				new LibSection();
			}else {
				JOptionPane.showMessageDialog(null, "failed");
			}
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		String name=f1.getText();
		String pass=p1.getText();
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			
			try {
				pst=con.prepareStatement("select Name,Password from addlib2 where Name=?");
				pst.setString(1, name);
				rs=pst.executeQuery();
				
				while(rs.next()) {
				if(pass.equals(rs.getString("Password"))) {
					
					JOptionPane.showMessageDialog(null, "Logged in");
					
					new LibSection();
				}else {
					JOptionPane.showMessageDialog(null, "failed");
				}
					
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
