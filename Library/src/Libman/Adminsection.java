package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Adminsection implements ActionListener {

	JFrame f;
	JButton b1, b2, b3, b4;

	public Adminsection() {

		f = new JFrame("Admin section");
		f.setLocation(100, 100);
		f.setSize(500, 500);

		b1 = new JButton("Add Librarian");
		b1.setBounds(160, 50, 140, 50);
		f.add(b1);
		b1.addActionListener(this);

		b2 = new JButton("View Librarian");
		b2.setBounds(160, 140, 140, 50);
		f.add(b2);
		b2.addActionListener(this);

		b3 = new JButton("Delete Librarian");
		b3.setBounds(160, 230, 140, 50);
		f.add(b3);
		b3.addActionListener(this);

		b4 = new JButton("Logout");
		b4.setBounds(160, 320, 140, 50);
		f.add(b4);
		b4.addActionListener(this);

		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Adminsection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {

			new Addlibrarian();
		} else if (e.getSource() == b2) {

			new Viewlib();
		}else if(e.getSource()==b3) {
			new Delete();
		}else if(e.getSource()==b4) {
			
			new Login();
		}

	}
}