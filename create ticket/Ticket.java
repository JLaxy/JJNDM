package finalProjectAOOP;

import java.io.Serializable;

public class Ticket implements Serializable {
	private int tickUID, seatNo;
	private float movPrice;
	private String timeSlot;
	private int movUID;
	Movie mov;
	
	Ticket(String timeSlot, int tickUID, int movUID, int seatNo, float movPrice, Movie mov){
		this.timeSlot = timeSlot;
		this.tickUID = tickUID;
		this.movUID = movUID;
		this.seatNo = seatNo;
		this.movPrice = movPrice;
		this.mov = mov;
	}
	
	//SETTERS
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	public void setTickUID(int tickUID) {
		this.tickUID = tickUID;
	}
	
	public void setMovUID(int movUID) {
		this.movUID = movUID;
	}
	
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	public void setMovPrice(float movPrice) {
		this.movPrice = movPrice;
	}
	
	public void setMov(Movie mov) {
		this.mov = mov;
	}
	// GETTERS
	public String getTimeSlot() {
		return timeSlot;
	}
	
	public int getTickUID() {
		return tickUID;
	}
	public int getMovUID() {
		return movUID;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public float getMovPrice() {
		return movPrice;
	}
	public Movie getMov() {
		return mov;
	}
	
}
