package pizza;

public class HawaiianPizza implements Pizza {
	
	private double price;
	private boolean deliver;
	//What happens after the pizza is ordered
	private String[] ingredients;
	//the toppings that are selected
	//private String[] toppings;
	
	//the size of the pizza desired
	private String size;
	public HawaiianPizza(String size){
		//this.toppings = toppings;
		this.size = size;
		
		ingredients = new String[6];
		
	}

	/**
	 * Calculates the price of the pizza with tax included,
	 * The price will be different depending on the pizza size
	 * @return, a double with the price of the pizza
	 */
	@Override
	public double Price() {
		// TODO Auto-generated method stub
		System.out.println("price");
		
		double tax = .09;
		
		ingredients[0] = "mozzarella cheese";
		ingredients[1] = "pineapple";
		ingredients[2] = "ham";
		ingredients[3] = "bacon";
		ingredients[4] = "tomato sause";
		
		
		
		/*
		
		iF I want to store all the prices of the individuals ingredients then I will do this
		Map<String, Double> prices = new HashMap<String, Double>();
		
		
		
		
		int i = 0;
		for(String key: prices.keySet()){
			
			if(!key.equals("8") | !key.equals("10") | !key.equals("12") | !key.equals("14") |!key.equals("16") |!key.equals("18")){
				price += prices.get(key);
				ingredients[i] = key;
				i++;
			}
		}
		*/
		
		//This si where i set the price of the pizza depending on the its size
		switch(size){
			case "8":
				price = 9.29;
				break;
			case "10":
				price = 10.29;
				break;
			case "12":
				price = 11.29;
				break;
			case "14":
				price = 12.29;
				break;
			case "16":
				price = 13.29;
				break;
			case "18":
				price = 14.29;
				break;
			default:
				break;
		}
		
		price += (price * tax);
		
		return price;
	}
	
	/**
	 * 
	 * @return, string list of ingredients
	 */
	public String[] ingredientsList (){
		System.out.println("ingredients");
		return ingredients;
	}

}
