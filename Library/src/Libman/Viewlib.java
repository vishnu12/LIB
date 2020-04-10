package Libman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Viewlib {

	JFrame f;
	JTable j;
	JScrollPane sp;

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public Viewlib() {

		init();
		//connect();
		catLoad();
	}

	public void init() {

		f = new JFrame("View Librarian");
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "ID", "Name", "Password", "Address", "City", "Contact" });

		j = new JTable(model);

		JScrollPane sp = new JScrollPane(j);

		f.add(sp);
		f.setSize(600, 500);
		f.setLocation(200, 50);

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

	public void catLoad() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testv?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC");
			ps = con.prepareStatement("select * from addlib2");
			rs = ps.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c = rsd.getColumnCount();
			DefaultTableModel d1 = (DefaultTableModel) j.getModel();

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i < c; i++) {

					v2.add(rs.getString("ID"));
					v2.add(rs.getString("Name"));
					v2.add(rs.getString("Password"));
					v2.add(rs.getString("Address"));
					v2.add(rs.getString("City"));
					v2.add(rs.getString("Contact"));
				}

				d1.addRow(v2);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(f, e);

		}

	}

	public static void main(String[] args) {
		new Viewlib();
	}
}