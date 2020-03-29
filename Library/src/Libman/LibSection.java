package Libman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LibSection implements ActionListener{
	
	JFrame f;JButton b1,b2,b3,b4,b5,b6;
	
	public LibSection() {
		

		f = new JFrame("Librarian section");
		f.setLocation(100, 100);
		f.setSize(500, 500);

		b1 = new JButton("Add Books");
		b1.setBounds(160, 50, 140, 50);
		f.add(b1);
		b1.addActionListener(this);

		b2 = new JButton("View Books");
		b2.setBounds(160, 120, 140, 50);
		f.add(b2);
		b2.addActionListener(this);

		b3 = new JButton("Issue Book");
		b3.setBounds(160, 190, 140, 50);
		f.add(b3);
		b3.addActionListener(this);
		
		b4 = new JButton("ViewvIssued Book");
		b4.setBounds(160, 260, 140, 50);
		f.add(b4);
		b4.addActionListener(this);
		
		b5 = new JButton("Return Book");
		b5.setBounds(160, 330, 140, 50);
		f.add(b5);
		b5.addActionListener(this);

		b6 = new JButton("Logout");
		b6.setBounds(160, 400, 140, 50);
		f.add(b6);
		b6.addActionListener(this);

		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			
			new Addbooks();
		}else if(e.getSource()==b2) {
			
			new ViewBooks();
		}else if(e.getSource()==b3) {
			new IssueBook();
		}else if(e.getSource()==b4) {
			
			new ViewIssuedbooks();
		}else if(e.getSource()==b5) {
			
			new returnBook();
		}else if(e.getSource()==b6) {
			new Login();
		}
		
	}
public static void main(String[] args) {
	
	new LibSection();
}
}
