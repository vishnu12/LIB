package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Login implements ActionListener{

	JFrame f;
	JButton b1, b2;;

	public Login() {

		f = new JFrame("Login");
		f.setLocation(100, 100);
		f.setSize(400, 400);

		b1 = new JButton("Admin Login");
		b1.setBounds(140, 100, 120, 50);
		b1.addActionListener(this);
		f.add(b1);
		b2 = new JButton("Librarian Login");
		b2.setBounds(140, 170, 120, 50);
		b2.addActionListener(this);
		f.add(b2);

		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {
			new Adminlogin();
		}else if(e.getSource()==b2) {
			
			new LibLogin();
		}

	}

}
