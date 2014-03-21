package edu.rose_hulman.poopatwork;

public class Poop {
	private String date;
	private int duration; //in seconds
	private int amountEarned; // in cents

	//constructor
	public Poop(String date, int duration, int amountEarned) {
		this.date = date;
		this.duration = duration;
		this.amountEarned = amountEarned;
	}

	/*Getters and setters*/
	//Date
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return this.date;
	}
	//Duration
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration(){
		return this.duration;
	}
	//AmountEarned
	public void setAmountEarned(int amountEarned) {
		this.amountEarned = amountEarned;
	}
	
	public int getAmountEarned() {
		return amountEarned;
	}	
	/*To String*/
	@Override
	public String toString() {
		return "Poop [duration=" + duration + ", amountEarned=" + amountEarned
				+ "]";
	}
}
