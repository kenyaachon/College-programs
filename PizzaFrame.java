package pizza;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author jacob
 * This is a program for creating a pizza shop gui
 *
 */
public class PizzaFrame extends JFrame implements ActionListener {
	private PizzaStore pstore;
	
	private JPanel panel1;
	private GridBagConstraints c;
	
	private Sqlattempt database;
	
	/**
	 * 
	 */
	public PizzaFrame(){
		database = new Sqlattempt();

		
		this.create();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PizzaFrame();
			

	}
	
	/**
	 * Pass the info to the Pizza classes
	 */
	public void create(){
		this.setLayout(new GridBagLayout());
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Johhny's Pizza Store");
		
		//this Panel is going to us the GridBagLayout manager
		//this is going to be for everything that is permanent on the page
		panel1 = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		

		//adds the clock to the window
		WindowClock clock = new WindowClock();
		
		c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .5;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 0;
		panel1.add(clock, c);
		
		LoadImageApp pizzaimg = new LoadImageApp();
		c.weightx = .2;
		c.gridwidth = 2;
		c.gridx  = 3;
		c.gridy = 1;
		panel1.add(pizzaimg, c);
		
		getInfo();
		

		c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
		this.add(panel1, c);

		this.setVisible(true);
		
	}
	
	
	
	/**
	 * Creates the clock Inner class, a clock that gets updated
	 */
	class WindowClock extends JLabel implements ActionListener {

		String type;
		SimpleDateFormat sdf;
		public WindowClock(){
			setForeground(Color.black);
			create(); //creates the format for the clock
		}
		
		/*
		 * Creates the frrmat for the clock
		 */
		public void create(){
			sdf = new SimpleDateFormat("MMMM dd yyyy      hh:mm:ss a");
			setFont(new Font("sans-serif", Font.PLAIN, 12));
			setHorizontalAlignment(SwingConstants.CENTER);
			
			Timer t = new Timer(1000, this);
			t.start();
		}

		//Helps to makesure the clock is updated frequently
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Date d = new Date();
			setText(sdf.format(d));
		}
		
		
		
		
	}
	
	
	private JLabel name;
	private JTextField nameentry;
	private JButton yes1;
	private JButton refresh;//for refreshing the page
	private JButton data;
	private JButton closedata;
	/**
	 * Formats some of the entry fields for retrieving information from the user
	 */
	public void getInfo(){
		
		refresh = new JButton("refresh");
		refresh.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .02;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 0;
        panel1.add(refresh, c);
        
        data = new JButton("data");
		data.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .02;
        c.gridwidth = 1;
        c.gridx = 4;
        c.gridy = 0;
        panel1.add(data, c);
              
        
        
	
		
		name = new JLabel("Full Name");
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .02;
		c.gridwidth = 1;
		c.gridx  = 2;
		c.gridy = 2;
		panel1.add(name, c);
		
		nameentry = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .3;
		c.gridwidth = 1;
		c.gridx  = 3;
		c.gridy = 2;
		panel1.add(nameentry, c);
		
		getInfo2();
	}
	
	/**
	 * Get info from the Customer then
	 * use the other classes
	 * Gets the info for pizzasize, pizza type and 
	 */	
	private JButton yes2;//for the pizza types
	private JLabel pizzatype;
	private JComboBox<String> patternList;
	private JLabel pizzaSize;
	private JComboBox<String> patternList2;//for the pizza size list
	public void getInfo2(){
		
		pizzatype = new JLabel("Pizza types");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .02;
		c.gridwidth = 1;
		c.gridx  = 2;
		c.gridy = 3;
		panel1.add(pizzatype, c);
		
		pizzaSize = new JLabel("Pizza Sizes (.in)");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .02;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 4;
		panel1.add(pizzaSize, c);
		
		
		//For the combo box, lists all the pizza types
		String[] patternExamples = {
		         "California",
		         "Chicago",
		         "Greek",
		         "Hawaiian",
		         "Meat Supreme",
		         "Neapolitan",
		         "Sicilian",
		         "NewYorkStyle",
		         "Veggie"
		};
		patternList = new JComboBox<String>(patternExamples);
		patternList.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .3;
		c.gridwidth = 1;
		c.gridx  = 3;
		c.gridy = 3;
		panel1.add(patternList, c);
		
		
		yes2 = new JButton("Go");
		yes2.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .1;
		c.gridwidth = 1;
		c.gridx  = 4;
		c.gridy = 3;
		panel1.add(yes2, c);
		
		
		
		//for the pizzasizes  comboxbox, all the possible pizza sizes
		String[] patternSizes2 = {
		         "8",
		         "10",
		         "12",
		         "14",
		         "16",
		         "18"
		};
		patternList2 = new JComboBox<String>(patternSizes2);
		patternList2.addActionListener(this);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .3;
		c.gridwidth = 1;
		c.gridx  = 3;
		c.gridy = 4;
		panel1.add(patternList2, c);
		
}
	
	
	
	//Used for loading the pizza image
	class LoadImageApp extends Component {
		BufferedImage img;
		 
	    public void paint(Graphics g) {
	        g.drawImage(img, 2, 2, null);
	    }
	 
	    public LoadImageApp() {
	       try {
	           img = ImageIO.read(new File("pizza1.jpg"));
	       } catch (IOException e) {
	    	    System.err.println(e);
	       }
	    
	    }
	 
	    public Dimension getPreferredSize() {
	        if (img == null) {
	             return new Dimension(400,300);
	        } else {
	           return new Dimension(img.getWidth(null), img.getHeight(null));
	       }
	    }
	}	

	
	/**
	 * For the events in the game
	 * Going to look at what event is happening
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == yes2){
			//creates another selection box with the available toppings for
			//for a specfic type of pizza
			
			
			String[] custinfo = new String[50];
			custinfo[0] = nameentry.getText();
			custinfo[1] = patternList.getSelectedItem().toString();//getting the input from the selection list
			
			
			
			//changes the appearance of the main gui
			panel1.remove(name);
			panel1.remove(nameentry);
			panel1.remove(patternList);
			panel1.remove(patternList2);
			panel1.remove(pizzaSize);
			panel1.remove(pizzatype);
			panel1.remove(yes2);
			
			
			//For testing that input
			//System.out.println(nameentry.getText());
			pstore = new PizzaStore(patternList.getSelectedItem().toString(), nameentry.getText(),
					this, patternList2.getSelectedItem().toString());
		}
			
			//continues checking for action events
			actionPerformed2(e);
			
	}
	
	private void actionPerformed2(ActionEvent e) {
		//When the next button is pressed the next page of the GUI is show
		
		//For refreshing the current page
		if(e.getSource() == refresh){
			new PizzaFrame();
			this.dispose();
		}
		
	
		if(e.getSource() == acceptOrder){
			pstore.pizzaMakingProcess();
		}
		
		
		//for the databutton
		if(e.getSource() == data){
			
			//shows a list of the last orders
			database.creatTable();
			 
		}
		
	}
	
	
	private JButton acceptOrder;
	/**
	 * Works adding the specified widget to the lower panel in the frame
	 * @param bj, the desired component
	 */
	public void add2(JComponent bj, int gridwidth, int gridheight, int gridx, int gridy){
		//used for position the item in the panel
		c.fill = GridBagConstraints.BOTH;
		c.weightx = .1;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		c.gridx  = gridx;
		c.gridy = gridy;
		panel1.add(bj, c);
		//panel1.setVisible(true);
		
		if(bj instanceof JButton){
			acceptOrder = (JButton) bj;
			acceptOrder.addActionListener(this);
		}
		
	}
	
	
	/**
	 * Removes the component from the JPanel when it is not needed
	 * @param bj
	 */
	public void remove(JComponent bj){
		panel1.remove(bj);
		//selections.remove(bj):
	}
	
	
}
