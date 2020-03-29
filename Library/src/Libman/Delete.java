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
import javax.swing.JTextField;


public class Delete implements ActionListener{
	JFrame f;JLabel l;JButton b1,b2;JTextField t;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Delete() {
		f = new JFrame("Delete Librarian");
		l=new JLabel("Enter ID");l.setBounds(50, 110, 130, 30);f.add(l);
		t=new JTextField();t.setBounds(200, 110, 130, 30);f.add(t);
		b1=new JButton("Delete");b1.setBounds(190, 200, 100, 40);f.add(b1);b1.addActionListener(this);
		b2=new JButton("Back");b2.setBounds(205, 270, 70, 30);f.add(b2);b2.addActionListener(this);
		
		
		
		f.setSize(400, 400);f.setLocation(200, 200);
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
	
	public void delete() {
		
		String id=t.getText();
		
		try {
			ps=con.prepareStatement("delete from addlib2 where ID=?");
			
			ps.setString(1, id);
			int k=ps.executeUpdate();
			
			if(k==1) {
				
				JOptionPane.showMessageDialog(null, "Deleted");
				
			}else {
				JOptionPane.showMessageDialog(null, "Error");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.setText("");t.requestFocus();
	}

	public static void main(String[] args) {
		new Delete();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			
			delete();
		}else if(e.getSource()==b2) {
			
			new Adminsection();
		}
		
	}
	
}
