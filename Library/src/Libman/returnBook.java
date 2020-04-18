package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class returnBook implements ActionListener {
	
	JFrame f;JLabel l1,l2,l3,l4;JButton b1,b2;JComboBox c;JTextField t,t1;;JDateChooser rd;
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
		
		l4=new JLabel("Fine");
		l4.setBounds(40, 195, 100, 30);
		f.add(l4);
		t1=new JTextField();t1.setBounds(200, 195, 140, 30);f.add(t1);
		
		l3 = new JLabel("Return date");
		l3.setBounds(40, 250, 100, 30);
		f.add(l3);
		rd=new JDateChooser();rd.setBounds(200, 250, 140, 30);f.add(rd);
		
		b1 = new JButton("Return");
		b1.setBounds(140, 355, 110, 40);
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
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void returnbook() {
		
		SimpleDateFormat d1=new SimpleDateFormat("yyyy-MM-dd");
		String date=d1.format(rd.getDate());
		String id=t.getText();
		
		try {
			ps=con.prepareStatement("insert into returnbook(studentid,returndate) values(?,?)");
			ps.setString(1, id);
			ps.setString(2, date);
			int k=ps.executeUpdate();
			if(k==1) {
				
				JOptionPane.showMessageDialog(null, "Returned successfully");
			}else {
				
				JOptionPane.showMessageDialog(null, "Failed");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void fineCalc() {
		
		String id=t.getText();
		try {
			ps=con.prepareStatement("select * from issuebook where Studentid=?");
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()==false) {
				
				
				JOptionPane.showMessageDialog(null, "Column not found");
				
			}else {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String d2=rs.getString("Datereturn");
				Date str = Calendar.getInstance().getTime(); 
			    String d1=df.format(str);
			    LocalDate ldA = LocalDate.parse(d2);
			    LocalDate ldB = LocalDate.parse(d1);
			    long daysBetween = ChronoUnit.DAYS.between( ldB , ldA );
			    
			    int m=(int)daysBetween;
			    int fine=Math.abs(m*10);
			    t1.setText(fine+"");
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
			fineCalc();
		    returnbook();
			delete();
			
		}else if(e.getSource()==b2) {
			
			f.setVisible(false);
		}
		
	}
}
