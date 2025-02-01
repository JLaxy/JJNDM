package finalProjectAOOP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Serialize {
	ResultSet rs;
	ArrayList<Movie> data = new ArrayList<Movie>();
	Database database;
    ArrayList<Ticket> unpaidTickets;
	public Serialize(ResultSet rs, Database database, ArrayList<Ticket> unpaidTickets) {
		// Syncing
        this.database = database;
        this.unpaidTickets = unpaidTickets;
        //get the movie data from the database
		this.rs = rs;
		
		int length = 0;//used to iterate over the arraylist
		try {
            // Retrieving data from the database
            while (rs.next()) {
                //adding each movie to the arraylist
               data.add(new Movie(rs.getInt("movUID"),rs.getString("movName"), rs.getString("timeSlot"), rs.getInt("availSeat"), rs.getFloat("movPrice"), rs.getInt("cinemaNo")));
               //For debugging and confirming if it was added successfully to the arraylist
               System.out.println("Array list check elements");
               System.out.println(data.get(length)+ " eto sa serialize");
               length++;
            }
            //serialize the arraylist
            FileOutputStream fileOut = new FileOutputStream("movie.ser");//create the file for the bytestream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);// create the object that will write the content of the arraylist to the fill
            out.writeObject(data);//placing the arraylist to the file
            //close both file output and object output stream
            out.close();
            fileOut.close();
            //show feedback to the user
            JOptionPane.showMessageDialog(null, "Movie list saved successfully");
            new Cmainview(this.unpaidTickets, this.database);
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }catch (IOException i) {
        	JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            i.printStackTrace();
        }
	}

}
