package Demo1;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HelloFrame extends JFrame
	implements ActionListener{

	public static void main(String[] args){
		new HelloFrame();
		
		
	}
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton exit;
	
	public HelloFrame(){
		this.setSize(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("World Languages");
		
		JPanel panel1 = new JPanel();
		button1 = new JButton("French");
		button2 = new JButton("German");
		button3 = new JButton("Italian");
		button4 = new JButton("English");
		button5 = new JButton("Spanish");
		exit = new JButton("Exit");
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		exit.addActionListener(this);
		
		panel1.add(button1);
		panel1.add(button2);
		panel1.add(button3);
		panel1.add(button4);
		panel1.add(button5);
		panel1.add(exit);
		
		this.add(panel1);
		
		this.setVisible(true);
		setBackground(Color.black);
	}

	private int clickCount1 = 0;
	private int clickCount2 = 0;
	private int clickCount3 = 0;
	private int clickCount4 = 0;
	private int clickCount5 = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button1){
			clickCount1++;
			if(clickCount1 == 1){
				button1.setText("You clicked French!");
			}
			else{
				button1.setText("You clicked French"
						+ clickCount1 + " times!");
			}
		}
		if(e.getSource() == button2){
			clickCount2++;
			if(clickCount2 == 1){
				button2.setText("You clicked German!");
			}
			else{
				button2.setText("You clicked German"
						+ clickCount2 + " times!");
			}
		}
		if(e.getSource() == button3){
			clickCount3++;
			if(clickCount3 == 1){
				button3.setText("You clicked Italian!");
			}
			else{
				button3.setText("You clicked Italian"
						+ clickCount3 + " times!");
			}
		}
		if(e.getSource() == button4){
			clickCount4++;
			if(clickCount4 == 1){
				button4.setText("You clicked English!");
			}
			else{
				button4.setText("You clicked English"
						+ clickCount4 + " times!");
			}
		}
		if(e.getSource() == button5){
			clickCount5++;
			if(clickCount5 == 1){
				button5.setText("You clicked Spanish!");
			}
			else{
				button5.setText("You clicked Spanish"
						+ clickCount5 + " times!");
			}
		}
		if(e.getSource() == exit){
			if(clickCount1 > 0)
				System.exit(0);
			else if(clickCount2 > 0)
				System.exit(0);
			else if(clickCount3 > 0)
				System.exit(0);
			else if(clickCount4 > 0)
				System.exit(0);
			else if(clickCount5 > 0)
				System.exit(0);
			else{
				JOptionPane.showMessageDialog(HelloFrame.this,
						"You must click at least once!",
						"Not so fast, buddy",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
