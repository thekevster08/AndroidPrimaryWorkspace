package com.example.poopsdatabasetesttwo;

public class Poop {
	private int _id;
	private int duration;
	private int amountEarned;
	private int rating;

	public Poop(int duration, int amountEarned, int rating) {
		this.duration = duration;
		this.amountEarned = amountEarned;
		this.rating = rating;
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

	//
	// // Will be used by the ArrayAdapter in the ListView
	// @Override
	public String toString() {
		String returnString = new String(Integer.toString(amountEarned) + " "
				+ Integer.toString(duration) + " " + Integer.toString(rating));
		return returnString;
	}
}