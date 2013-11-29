package edu.rose_hulman.poopatwork;

public class Poop {
	  private long id;
	  private String date;
	  private String timeStart;
	  private String timeEnd;
	  private int duration; //seconds
	  private int amountEarned; //dollars
	  private int rating; //1-5
	
	  
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getAmountEarned() {
		return amountEarned;
	}
	public void setAmountEarned(int amountEarned) {
		this.amountEarned = amountEarned;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}


	} 