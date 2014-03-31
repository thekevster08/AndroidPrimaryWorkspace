package edu.rose_hulman.poopatwork;

public class Poop {
	private String date;
	private int duration; //in seconds
	private float amountEarned; // in cents

	//constructor
	
	public Poop(){
		
	}
	public Poop(String date, int duration, float amountEarned) {
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
	public float getDuration(){
		return this.duration;
	}
	//AmountEarned
	public void setAmountEarned(float amountEarned) {
		this.amountEarned = amountEarned;
	}
	
	public float getAmountEarned() {
		return amountEarned;
	}	
	/*To String*/
	@Override
	public String toString() {
		return "Poop [duration=" + duration + ", amountEarned=" + amountEarned
				+ "]";
	}
}
