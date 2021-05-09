//--------------------------------------------
//Part: 2 (TVShow) assignment 4
//Written by: Yason Bedoshvili ID No: 40058829
//

import java.util.Scanner;

public class TVShow implements Watchable {

	public static Scanner in = new Scanner(System.in);
	private String showID;
	private String showName;
	private double startTime;
	private double endTime;

	/**
	 * 
	 * @param showID    show id which is unique
	 * @param showName  name if the show
	 * @param startTime when show starts
	 * @param endTime   when show ends
	 */
	public TVShow(String showID, String showName, double startTime, double endTime) {
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * 
	 * @param show   Object of the show
	 * @param showID show id which is unique
	 */
	public TVShow(TVShow show, String showID) {
		this.showName = show.showName;
		this.startTime = show.startTime;
		this.endTime = show.endTime;
		this.showID = showID;
	}

	/**
	 * clone method
	 */
	public TVShow clone() {
		System.out.println("Please enter a new showID: ");
		String id = in.next();
		return new TVShow(this, id);
	}

	/**
	 * 
	 * @return return show id
	 */
	public String getShowID() {
		return showID;
	}

	/**
	 * 
	 * @param showID sets show id
	 */
	public void setShowID(String showID) {
		this.showID = showID;
	}

	/**
	 * 
	 * @return returns show's name
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * 
	 * @param showName sets show name
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * 
	 * @return returns start time
	 */
	public double getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime sets start time
	 */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return returns end time
	 */
	public double getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @param endTime sets end time
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	/**
	 * method toString()
	 */
	public String toString() {
		return "Show ID is: " + showID + ", Show name is: " + showName + ", Start time is: " + startTime
				+ ", End time is: " + endTime + ".";
	}

	/**
	 * 
	 * @param other object of the TVShow
	 * @return returns true if it's equal
	 */
	public boolean equals(TVShow other) {
		if (other == null)
			return false;
		else if (getClass() != other.getClass())
			return false;
		else
			return (showName.equals(other.showName) && startTime == other.startTime && endTime == other.endTime);
	}

	/**
	 * method that compares if shows overlap
	 */
	public String isOnSameTime(TVShow S) {
		if ((startTime == S.startTime) && (endTime == S.endTime))
			return "Same time";
		else if (((S.startTime >= startTime) && (S.startTime <= endTime))
				|| ((S.startTime <= startTime) && (S.endTime >= startTime)))
			return "Some Overlap";
		else
			return "Different time";
	}
}
