package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class IssueBook implements ActionListener{
	
	JFrame f;JLabel l1,l2,l3,l4,l5,l6;JComboBox c;JButton b1,b2;JTextField f2,f3,f4,f5;JDateChooser rd;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public IssueBook() {
		init();
		connect();
		comboload2();
		
	}
	
	public void init() {
		
		f=new JFrame();
		c=new JComboBox();
		l1=new JLabel("Call no");
		l1.setBounds(50, 50, 100, 30);
		f.add(l1);c.setBounds(250, 50, 130, 30);f.add(c);
		
		l2=new JLabel("Student ID");
		l2.setBounds(50, 100, 100, 30);
		f.add(l2);
		f2=new JTextField();f2.setBounds(250, 100, 130, 30);f.add(f2);
		
		l4=new JLabel("Student Name");
		l4.setBounds(50, 150, 100, 30);
		f.add(l4);
		f4=new JTextField();f4.setBounds(250, 150, 130, 30);f.add(f4);
		
		l5=new JLabel("Contact");
		l5.setBounds(50, 200, 100, 30);
		f.add(l5);
		f5=new JTextField();f5.setBounds(250, 200, 130, 30);f.add(f5);
		

		l6=new JLabel("Date");
		l6.setBounds(50, 250, 100, 30);f.add(l6);
		rd=new JDateChooser();rd.setBounds(250, 250,130, 30);f.add(rd);
		
		b1=new JButton("Issue");b1.setBounds(250, 350, 120, 40);f.add(b1);b1.addActionListener(this);
		b2=new JButton("Back");b2.setBounds(275, 420, 70, 25);f.add(b2);b2.addActionListener(this);
		
		
		f.setSize(600, 500);
		f.setLocation(200, 50);
        f.setLayout(null);
		f.setVisible(true);
		
		
	}
	
	
	public class comboload{
		int ID;String callno;
		
		public comboload(int ID,String callno) {
			
			this.ID=ID;
			this.callno=callno;
			
		}
	public String toString() {
			return callno;
		}
		
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
	
	
	public void comboload2() {
		
		try {
			ps=con.prepareStatement("select * from book");
			rs=ps.executeQuery();
			while(rs.next()) {
			c.addItem(new comboload(rs.getInt("ID"),rs.getString("Callno")));
			//comboload l=new comboload(rs.getInt("ID"), rs.getString("Callno"));
			//c.addItem(l.toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		}
		
		public void ADD2() {
			
			
			comboload citem=(comboload)c.getSelectedItem();
			//String citem=c.getSelectedItem().toString();
			String id = f2.getText();
			String name = f4.getText();
			String cont = f5.getText();
			SimpleDateFormat d1=new SimpleDateFormat("yyyy-MM-dd");
			String date=d1.format(rd.getDate());
			

			try {
				ps = con.prepareStatement("insert into issuebook(Callno,Studentid,Studentname,Contact,Date) values(?,?,?,?,?)");
				ps.setInt(1, citem.ID);
				ps.setString(2, id);
				ps.setString(3, name);
				ps.setString(4, cont);
				ps.setString(5, date);
				
				
				int k = ps.executeUpdate();

				if (k == 1) {

					JOptionPane.showMessageDialog(null, "Issued");

				} else {

					JOptionPane.showMessageDialog(null, "Error");

				}

				c.setSelectedIndex(-1);
				f2.setText("");
				
				f4.setText("");
				f5.setText("");
				c.requestFocus();

			} catch (Exception e) {
				System.out.println("error" + e);
			}

		}
		
		
		
	
	
	
	public static void main(String[] args) {
		
		IssueBook i=new IssueBook();
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			ADD2();
		}else if(e.getSource()==b2) {
			
			f.setVisible(false);
			
		}
		
	}

}
