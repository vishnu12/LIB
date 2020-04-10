package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class returnBook implements ActionListener {
	
	JFrame f;JLabel l1,l2,l3;JButton b1,b2;JComboBox c;JTextField t;JDateChooser rd;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public returnBook() {
		init();
		connect();
		comboload1();
	}

	
	public void init() {
		
		f = new JFrame("Return book");
		f.setLocation(100, 100);
		f.setSize(500, 500);
		
		l1 = new JLabel("Callno");
		l1.setBounds(40, 80, 100, 30);
		f.add(l1);
		c=new JComboBox();c.setBounds(200, 80, 140, 30);f.add(c);
		
		l2 = new JLabel("Studentid");
		l2.setBounds(40, 145, 100, 30);
		f.add(l2);
		t=new JTextField();t.setBounds(200, 145, 140, 30);f.add(t);
		
		l3 = new JLabel("Return date");
		l3.setBounds(40, 210, 100, 30);
		f.add(l3);
		rd=new JDateChooser();rd.setBounds(200, 210, 140, 30);f.add(rd);
		
		b1 = new JButton("Return");
		b1.setBounds(140, 285, 110, 40);
		f.add(b1);b1.addActionListener(this);
		
		b2 = new JButton("Back");
		b2.setBounds(300, 370, 80, 30);
		f.add(b2);b2.addActionListener(this);
		
		
		
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
	
	
	public class combo{
		int id;String callno;
		
		public combo(int id,String callno) {
			
			this.id=id;
			this.callno=callno;
			
		}
		
		public String toString() {
			return callno;
		}
		
	}
	
	
	public void comboload1() {
		
		try {
			ps=con.prepareStatement("select * from book");
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				c.addItem(new combo(rs.getInt("ID"),rs.getString("Callno")));
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void delete() {
		
		String c=t.getText();
		
		try {
			ps=con.prepareStatement("delete from issuebook where Studentid=?");
			ps.setString(1, c);
			int k=ps.executeUpdate();
			
			if(k==1) {
				
				JOptionPane.showMessageDialog(null, "Returned succesfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new returnBook();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource()==b1) {
			
			delete();
		}else if(e.getSource()==b2) {
			
			new LibSection();
		}
		
	}
}
