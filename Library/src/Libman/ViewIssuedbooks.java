package Libman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewIssuedbooks {
	
	JFrame f;
	JTable j;
	JScrollPane sp;

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ViewIssuedbooks() {
		init();
		connect();
		issuebookLoad();
	}
	
	public void init() {

		f = new JFrame("View Issued Books");
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "ID", "Callno", "Studentid", "Studentname", "Contact","Date"});

		j = new JTable(model);

		JScrollPane sp = new JScrollPane(j);

		f.add(sp);
		f.setSize(600, 500);
		f.setLocation(200, 50);

		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

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
	
	
	public void issuebookLoad() {

		try {

			
			ps = con.prepareStatement("select * from issuebook");
	    //ps=con.prepareStatement("select i.ID,b.Callno,i.Studentid,i.Studentname,i.Contact,i.Date from issuebook i JOIN book b ON i.ID=b.ID");
			rs = ps.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c = rsd.getColumnCount();
			DefaultTableModel d2 = (DefaultTableModel) j.getModel();

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i < c; i++) {

					v2.add(rs.getString("ID"));
					v2.add(rs.getString("Callno"));
					v2.add(rs.getString("Studentid"));
					v2.add(rs.getString("Studentname"));
					v2.add(rs.getString("Contact"));
					v2.add(rs.getString("Date"));
					
				}

				d2.addRow(v2);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(f, e);

		}

	}
	
	public static void main(String[] args) {
		new ViewIssuedbooks();
	}
}
