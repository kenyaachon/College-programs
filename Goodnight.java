package Demo1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Goodnight implements ActionListener{

	private String name;
	private JPanel panel1;
	
	private JButton french;
	private JButton german;
	private JButton italian;
	private JButton sweden;
	private JButton english;
	private JButton spanish;
	private Name given_name;
	public Goodnight(JPanel panel1, String name, Name given_name){
		this.name = name;
		this.panel1 = panel1;
		this.given_name = given_name;
		
		createButtons();
	}
	
	private void createButtons() {
		french = new JButton("French");
		french.addActionListener(this);
		panel1.add(french);
		
		german = new JButton("German");
		german.addActionListener(this);
		panel1.add(german);
		
		italian = new JButton("Italian");
		italian.addActionListener(this);
		panel1.add(italian);
		
		sweden = new JButton("Sweden");
		sweden.addActionListener(this);
		panel1.add(sweden);
		
		english = new JButton("English");
		english.addActionListener(this);
		panel1.add(english);
		 
		spanish = new JButton("Spanish");
		spanish.addActionListener(this);
		panel1.add(spanish);
	}

	/**
	 * Result of pressing the button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == french){
				JOptionPane.showMessageDialog(
						given_name,
						"Bonne nuit " + name);

		}
		
		if(e.getSource() == german){
			JOptionPane.showMessageDialog(
					given_name,
					"Gute Nacht " + name);

		}
		if(e.getSource() == spanish){
			JOptionPane.showMessageDialog(
					given_name,
					"Buenas noches" + name);

		}
		if(e.getSource() == english){
			JOptionPane.showMessageDialog(
					given_name,
					"Good night" + name);

		}
		if(e.getSource() == sweden){
			JOptionPane.showMessageDialog(
					given_name,
					"Godnatt" + name);

		}
		if(e.getSource() == italian){
			JOptionPane.showMessageDialog(
					given_name,
					"Buona notte " + name);

		}
	}
	
	
}
