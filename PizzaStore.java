package pizza;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class PizzaStore 
	implements ActionListener
{
	private CaliforniaPizza cpizza;
	private ChicagoPizza chpizza;
	private GreekPizza gpizza;
	private HawaiianPizza hpizza;
	private MeatSupremePizza mspizza;
	private NeapolitanPizza npizza;
	private NewYorkStylePizza nyspizza;
	private SicilianPizza spizza;
	private VeggiePizza vpizza;
	
	
	
	//This is the mysql database object
	private Sqlattempt database;
	
	
	
	private String type;
	
	private String customerName;
		
	private PizzaFrame pizzaFrame;
	String pizzaSize;
	
	
	/**
	 * Call other methods
	 */
	public PizzaStore(String type, String customerName, PizzaFrame pizzaframe, String pizzaSize)
	{
		this.type = type;
		this.customerName = customerName;
		this.pizzaFrame = pizzaframe;
		this.pizzaSize = pizzaSize;
		
		
		this.decide();
	}
	/**
	 * Use this method for deciding which 
	 * Pizza class i should use
	 */
	public void decide(){
		
		//Tests to make sure the method was called
		//System.out.println("decide");
		
		switch(type) {
			case "California":
				cpizza = new CaliforniaPizza(pizzaSize);
				data(cpizza.Price(), cpizza.ingredientsList());
				receipt(cpizza);
				break;
			case "Chicago":
				chpizza = new ChicagoPizza(pizzaSize);
				data(chpizza.Price(), chpizza.ingredientsList());
				receipt(chpizza);
				break;
			case "Greek":
				gpizza = new GreekPizza(pizzaSize);
				data(gpizza.Price(), gpizza.ingredientsList());
				receipt(gpizza);
				break;
			case "Hawaiian":
				hpizza = new HawaiianPizza(pizzaSize);
				data(hpizza.Price(), hpizza.ingredientsList());
				receipt(hpizza);
				break;
			case "Meat Supreme":
				mspizza = new MeatSupremePizza(pizzaSize);
				data(mspizza.Price(), mspizza.ingredientsList());
				receipt(mspizza);
				break;
				
			case "NewYorkStyle":
				nyspizza = new NewYorkStylePizza(pizzaSize);
				data(nyspizza.Price(), nyspizza.ingredientsList());
				receipt(nyspizza);
				break;
			case "Neapolitan":
				npizza = new NeapolitanPizza(pizzaSize);
				data(npizza.Price(), npizza.ingredientsList());
				receipt(npizza);

				break;
			case "Sicilian":
				spizza = new SicilianPizza(pizzaSize);
				data(spizza.Price(), spizza.ingredientsList());
				receipt(spizza);
				break;
			case "Veggie":
				vpizza = new VeggiePizza(pizzaSize);
				data(vpizza.Price(), vpizza.ingredientsList());
				receipt(vpizza);
				break;
			default:
				break;
		}
	}
	
	/**
	 * This method gets all the data from pizza object and then puts it into the database
	 */
	public void data(double price, String[] toppings){
		//tests to make sure the method was called
		//System.out.println("data");
		String toppingslist = "";
		
		
		System.out.println(price);
		
		//changing the data type of the toppings so that it fits into the database
		for(int i = 0; i < toppings.length; i++){
			toppingslist += toppings[i];
			toppingslist += ", ";
		}
		
		
		
		database = new Sqlattempt();
		
		//This adds the desired data to the database
		database.add(customerName, type, toppingslist, pizzaSize, price);
		
		
		
	}
	
	
	private JTextArea textArea;
	private JButton acceptOrder;
	/**
	 * This creates the receipt for the pizza the customer chose
	 * @param chosePizza, the pizza type that was chose
	 */
	public void receipt(Pizza chosePizza){
		
		//System.out.println("data");
		
		//Create the text space for the recpeit 
		textArea = new JTextArea(5, 5);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		//This makes sure the scroll panel looks right
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		
		//where I add the content of the recpeit 
		String space = "	   ";
		String newline = "\n";
		
		
		textArea.append("Type" + space + type + newline);
		textArea.append("Size" + space + pizzaSize + "in" + newline);
		
		String[] toppings = chosePizza.ingredientsList();
		for(int i = 0; i < toppings.length; i++){
			textArea.append(toppings[i] + newline);
		}
		
		textArea.append("Total" + space + "$" + chosePizza.Price() + newline);
		textArea.append("Thank you " + customerName + " For Coming to Johnny's Pizza");
		
		
		//This is where I add the text space to the main GUI window
		pizzaFrame.add2(scrollPane, 1, 1, 3, 6);
		
		acceptOrder = new JButton("Accept");
		//acceptOrder.setMaximumSize(new Dimension(10, 5));
		//acceptOrder.setMinimumSize(new Dimension(10, 5));
		pizzaFrame.add2(acceptOrder, 1, 1, 3, 7);
		
		
		
		
	}

	private int step;
	private String[] process;
	private Timer timer;
	/**
	 * Displays the steps for making a pizza with
	 */
	public void pizzaMakingProcess(){
		//This contains the steps for making the pizza 
		process = new String[4];
		process[0] = "The pizza dough is being tossed and made...";
		process[1] = "The yummy ingredients are being put into the pizza....";
		process[2] = "The pizza is being put into the oven...";
		process[3] = "Your pizza is read for pickup!!!";
		
		
		System.out.println("Everything is working properly");
		System.out.println(process.length);
		step = 0;
		
		//Used for slowing down the process of iterating through the list of steps
		timer = new Timer(5000, (ActionListener) this);
		timer.setInitialDelay(2000);
		timer.start();
		
		
		
	}
	
	/**
	 * This accompagnies the Timer in pizzaMakingProcess
	 * This times the process for showing the process for making the pizza
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(step < 4){
			textArea.append("\n" + process[step]);
			step++;
		}
		
		if(step == 4){
			timer.removeActionListener(this);
		}
	}
	
}
