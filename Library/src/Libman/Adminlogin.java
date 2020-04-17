package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Adminlogin implements ActionListener,KeyListener {

	JFrame f;
	JButton b1;
	JLabel l1, l2, l3;
	JTextField f1;
	JPasswordField p1;

	public Adminlogin() {

		f = new JFrame("Login");
		f.setLocation(100, 100);
		f.setSize(400, 400);
		l1 = new JLabel("Admin login form");
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
		

	}

	public static void main(String[] args) {
		new Adminlogin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String c1 = f1.getText();
		String c2 = p1.getText();

		if (c1.equals("admin") && c2.equals("1234")) {

			new Adminsection();
			f1.setText("");
			p1.setText("");
			f1.requestFocus();
		} else {

			JOptionPane.showMessageDialog(null, "Login failed");
			f1.setText("");
			p1.setText("");
			f1.requestFocus();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String c1 = f1.getText();
		String c2 = p1.getText();
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			
			if(c1.equals("admin") && c2.equals("1234")) {
				
				new Adminsection();
			}else {
				
				JOptionPane.showMessageDialog(null, "Login failed");
				f1.setText("");
				p1.setText("");
				f1.requestFocus();
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}