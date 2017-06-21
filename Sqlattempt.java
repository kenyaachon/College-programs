package pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Sqlattempt {
	
	//These are the components needed to alterig the database
	private static Connection con = null;
	
	private static Statement st = null;
	
	private static PreparedStatement pst = null;
	
	private static ResultSet rs = null;
	
	
	public Sqlattempt(){
	}
	
	
	public static void main(String[] args){
		//This is for testing
		//add("Moses", "Veggie", "Bell Pepper", "10", 12.35);
	}
	
	
	/**
	 * Returns false if an item was not able to be added into the database
	 * @param custName
	 * @param pizzaType
	 * @param toppings
	 * @param size
	 * @param price
	 * @return
	 */
	//public static boolean add(){
		
	public boolean add(String custName, String pizzaType, String toppings, String size, Double price) {	
		
		//tells whether the add to the database was successful
		boolean successful = true;
		
		String url = "jdbc:mysql://localhost:3306/hello";
        String user = "root";
        String password = "Jesusjesu#1";

        
        //prints out the contents of the database 
        try {
            
        	
        	//getting the connection to the database
            con = DriverManager.getConnection(url, user, password);
            
            
            //Formatting the sql instructions
            String firstpart = "INSERT INTO hello.pizza (CustomerName, PizzaType, Toppings, Size, Price) ";
            
            String data = "VALUES ('" + custName + "', '" + pizzaType + "', '" + toppings + "', '" + size + "', " + 
            		price.toString() + ");";
            
            
            
            
            //Creates the sql statement for putting into the database
            pst = con.prepareStatement(firstpart + data);
            
            pst.executeUpdate();
            
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM hello.pizza");

            while(rs.next()) {
                
                System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", "+  rs.getString(3) +
                		", " + rs.getString(4) + ", " + rs.getString(5)  + ", " + rs.getString(6));
                
            }

        } catch (SQLException ex) {
        
            Logger lgr = Logger.getLogger(Sqlattempt.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            
            successful = false;

        } finally {
            
            try {
                
            	//Closing all of the resources to save memory
                if (rs != null) {
                    rs.close();
                }
                
                if (st != null) {
                    st.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(Sqlattempt.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
		return successful;
	}
	
	
	/**
	 * Creates the table showing the data on the database in the main window
	 * @throws SQLException 
	 */
	public void creatTable(){		
		String url = "jdbc:mysql://localhost:3306/hello";
        String user = "root";
        String password = "Jesusjesu#1";
               
		try {
			
		//getting the connection to the database
        con = DriverManager.getConnection(url, user, password);	
        
        st = con.createStatement();
        
        //Get the last row, and the id of that number
        rs = st.executeQuery("SELECT * FROM hello.pizza ORDER BY idPizza DESC LIMIT 1");
       
        //used for knowing how many arrays to creates
        int numcustomers = 0;
        if (rs.next()){        
        	numcustomers = Integer.parseInt(rs.getString(1));
        }
       
		
       //Get the data from the database
		st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM hello.pizza");
        
        //Where to store all the data received from the database
        Object[][] dataset = new Object[numcustomers][6];

        
        for(int i = 0; i < numcustomers; i++){
        	if(rs.next()){
        	for(int j = 0; j < 6; j++){
        			dataset[i][j] = rs.getString(j + 1);
        	}
        	}
        }
        
        
        //Create the extra window
       SimpleTable data = new SimpleTable(dataset);
		
       //Now work on making it visible
        JFrame sub = new JFrame();
        //sub.add(new SimpleTable());
		sub.add(data);
        sub.setSize(500, 250);
        sub.setVisible(true);
        }
		catch (SQLException ex) {
	        
            Logger lgr = Logger.getLogger(Sqlattempt.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            
        } finally {
            
            try {
                
            	//Closing all of the resources to save memory
                if (rs != null) {
                    rs.close();
                }
                
                if (st != null) {
                    st.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(Sqlattempt.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        
	}
	

}
