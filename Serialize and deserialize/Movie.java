package finalProjectAOOP;

import java.io.Serializable;

public class Movie implements Serializable {
	private String movName;
	private String timeSlot;
	private int availSeat;
	private int movieUID;
	private float movPrice;
	private int cinemaNo;
	
	public Movie(int movieUID, String movName, String timeSlot, int availSeat, float movPrice, int cinemaNo) {
		this.movName = movName;
		this.timeSlot = timeSlot;
		this.availSeat = availSeat;
		this.movieUID = movieUID;
		this.movPrice = movPrice;
		this.cinemaNo = cinemaNo;
	}
	//-------------getters for movie data----------------
	public String getMovieName() {
		return movName;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public int getAvailSeat() {
		return availSeat;
	}
	public float getPrice() {
		return movPrice;
	}
	public int getCinemaNum() {
		return cinemaNo;
	}
	//default method when trying to print this object
	public String toString() {
		return "Movie ID: "+ movieUID+" \r\nMovie Name: "+movName+" \r\nTime Slot: "+timeSlot+" \r\nSeats Available: "+availSeat+" \r\nPrice:"+movPrice+" \r\nCinama Number: "+cinemaNo;
	}
}
