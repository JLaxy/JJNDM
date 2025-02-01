package finalProjectAOOP;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserialize {

	public static void main(String[] args) {
		ArrayList<Movie> data = new ArrayList<>();
		//passing the bytestream to a new arraylist
		try {
			FileInputStream fileIn = new FileInputStream("movie.ser");//get the file
			ObjectInputStream in = new ObjectInputStream(fileIn);// create the object that will read the content of the file
	        data = (ArrayList) in.readObject();//pass the file into the arraylist
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		//for checking
		for(Movie movie: data) {
			System.out.println(movie);
			System.out.println("");
		}
         
	}

}
