package Demo1;
import javax.swing.*;
import java.awt.event.*;


/**
 * Running class the uses High and GoodNight objects
 * @author Moses Mbugua
 *
 */
public class Name extends JFrame{

	
	public static void main(String[] args){
		new Name();
	}
	
	//The buttons that are going to be displayed
	private JButton buttonOK;
	private JTextField textName;
	
	private JButton buttonHigh;
	private JButton buttonGoodNight;
	private JPanel panel1;
	private JLabel text;
	private JButton refresh;
	
	/**
		Constructor for the program
		Creates the guis
	*/
	public Name(){
		this.setSize(325, 100);
		this.setTitle("Bonjour");
		this.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		ButtonListener b1 = new ButtonListener();
		
		panel1 = new JPanel();
		panel1.add(new JLabel("Enter your name: "));
		textName = new JTextField(15);
		panel1.add(textName);
		
		buttonOK = new JButton("OK");
		buttonOK.addActionListener(b1);
		panel1.add(buttonOK);
		
		text = new JLabel("What type of greeting do you want?");
		panel1.add(text);
		
		buttonHigh = new JButton("High");
		buttonGoodNight = new JButton("Good Night");
		refresh = new JButton("Refresh");
		
		buttonHigh.addActionListener(b1);
		buttonGoodNight.addActionListener(b1);
		refresh.addActionListener(b1);
		
		panel1.add(buttonGoodNight);
		panel1.add(buttonHigh);
		panel1.add(refresh);
		
		this.add(panel1);
		this.setVisible(true);
		
	}
	
	/**
		Class that deals with the event of a button being clicked
	
	*/
	private class ButtonListener implements ActionListener
	{
		/**
			When the button High or Good night is pressed a High or Goodnight object is created
			@params, the event that causes a certain action to occur
		*/
		public void actionPerformed(ActionEvent e){
			
		if(actionPerformed2(e) == true){
			if(e.getSource() == buttonHigh){
				 buttonHigh.setVisible(false);
				 buttonGoodNight.setVisible(false);
				 text.setText("These are greetings for saying hello");
				new High(panel1, textName.getText(), Name.this);
			}
			else if(e.getSource() == buttonGoodNight){
				buttonHigh.setVisible(false);
				buttonGoodNight.setVisible(false);
				text.setText("These are greetings for saying goodnight");
				new Goodnight(panel1, textName.getText(), Name.this);
			}
		}
		if(e.getSource() == refresh){
			new Name();
		}
		}
	}
		/**
			Makes sure the user entered their name before proceeding
			@params, the event, a click
			@return, true if the user entered a name, false otherwise
		*/
		public boolean actionPerformed2(ActionEvent e){
			if(e.getSource() == buttonOK){
				String name = textName.getText();
				if(name.length() == 0){
					JOptionPane.showMessageDialog(
							Name.this,
							"You didn't enter anything",
							" Moron",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
				textName.requestFocus();
			}
			return true;
		}
}
