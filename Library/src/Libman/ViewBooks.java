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

public class ViewBooks {
	
	JFrame f;
	JTable j;
	JScrollPane sp;

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ViewBooks() {
		init();
		connect();
		bookLoad();
		
	}
	
	public void init() {

		f = new JFrame("View Books");
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "ID", "Callno", "Book", "Author", "Publisher", "Quantity" });

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
	
	public void bookLoad() {

		try {

			
			ps = con.prepareStatement("select * from book");
			rs = ps.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c = rsd.getColumnCount();
			DefaultTableModel d2 = (DefaultTableModel) j.getModel();

			while (rs.next()) {

				Vector v2 = new Vector();
				for (int i = 1; i < c; i++) {

					v2.add(rs.getString("ID"));
					v2.add(rs.getString("Callno"));
					v2.add(rs.getString("BookName"));
					v2.add(rs.getString("Author"));
					v2.add(rs.getString("Publisher"));
					v2.add(rs.getString("Quantity"));
				}

				d2.addRow(v2);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(f, e);

		}

	}
	
	public static void main(String[] args) {
		new ViewBooks();
	}

}
