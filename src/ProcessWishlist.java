//--------------------------------------------
//Part: 2 (ProcessWishlist) assignment 4
//Written by: Yason Bedoshvili ID No: 40058829
//

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProcessWishlist {

	/**
	 * 
	 * @param in Scanner
	 * @return returns object of TVShow
	 */
	public static TVShow parseRecord(Scanner in) {
		String showID = in.next();
		String showName = in.next();
		in.next();
		double startTime = in.nextDouble();
		in.next();
		double endTime = in.nextDouble();
		return new TVShow(showID, showName, startTime, endTime);
	}

	/**
	 * 
	 * @param fileName String that is a file name
	 * @return returns object of Scanner
	 */
	public static Scanner openScanner(String fileName) {
		Scanner inputStream1 = null;
		try {
			inputStream1 = new Scanner(new FileInputStream(fileName));

		} catch (FileNotFoundException e) {
			System.out.println("Could not open the file " + fileName + " for reading.");
			System.out.println("Please check if file exists! Program will terminate after closing all open files.");

		}
		return inputStream1;
	}

	/**
	 * 
	 * @param sc    object of Scanner
	 * @param watch ArrayList of watching
	 * @param wish  ArrayList of wishing
	 */
	public static void proccessInterests(Scanner sc, ArrayList<String> watch, ArrayList<String> wish) {
		sc.nextLine();
		String showID = sc.nextLine();
		do {
			watch.add(showID);
			showID = sc.nextLine();

		} while (!(showID.equals("Wishlist")));

		while (sc.hasNext()) {
			showID = sc.nextLine();
			wish.add(showID);
		}
	}

	public static void main(String[] args) {

		ShowList tvGuide2 = null;
		ShowList tvD = new ShowList();
		ShowList tvD1 = new ShowList();
		ShowList tvGuide = new ShowList(tvD);
		ShowList tvGuide1 = new ShowList(tvD1);
		ArrayList<String> watchList = new ArrayList<>();
		ArrayList<String> wishList = new ArrayList<>();

		Scanner inputStream1 = null;
		final String inputFileName1 = "TVGuide.txt";
		inputStream1 = openScanner(inputFileName1);
		if (inputStream1 == null)
			System.exit(0);

		while (inputStream1.hasNext()) {
			TVShow newTv = parseRecord(inputStream1);
			if (!(tvGuide.contains(newTv)))
				tvGuide.addToStart(newTv);
		}
		final String inputFileName2 = "Interest.txt";
		inputStream1 = openScanner(inputFileName2);
		if (inputStream1 == null)
			System.exit(0);

		proccessInterests(inputStream1, watchList, wishList);

		System.out.println(tvGuide);

		tvGuide2 = new ShowList(tvGuide); 
		System.out.println("tvGuide2 is a duplicate of tvGuide: ");
		System.out.println(tvGuide2);
		
		for (int i = 0; i < watchList.size(); i++) {
			for (int j = 0; j < wishList.size(); j++) {
				String watchID = watchList.get(i);
				String wishID = wishList.get(j);
				if (tvGuide.contains(watchID) && tvGuide.contains(wishID)) {
					String result = tvGuide.find(watchID).getTVShow().isOnSameTime(tvGuide.find(wishID).getTVShow());
					if (result.equals("Same time"))
						System.out.println("User can't watch show " + wishID
								+ " as he/she will begin another show at the same time.");
					else if (result.equals("Some Overlap"))
						System.out.println("User can't watch show " + wishID
								+ " as he/she is not finished a show he/she is watching.");
					else if (result.equals("Different time"))
						System.out.println("User can watch show " + wishID
								+ " as he/she is not watching anything else during that time.");
				}

			}
		}

		System.out.println();

		// Part d: verifying the showIDs
		System.out.println("Verifying show ids. Please enter the showIDs: ");
		String a = TVShow.in.next();
		String b = TVShow.in.next();
		String c = TVShow.in.next();
		String d = TVShow.in.next();
		System.out.println(tvGuide.contains(a) + " The number iterations performed are: " + ShowList.getCounter());
		System.out.println(tvGuide.contains(b) + " The number iterations performed are: " + ShowList.getCounter());
		System.out.println(tvGuide.contains(c) + " The number iterations performed are: " + ShowList.getCounter());
		System.out.println(tvGuide.contains(d) + " The number iterations performed are: " + ShowList.getCounter());

		// Part e: verifying all the methods from classes
		TVShow tv = new TVShow("CBS20", "Big_Brother", 20.00, 21.00);
		TVShow tv1 = new TVShow("PBS21", "Wonders_Of_Mexico", 21.00, 22.00);
		TVShow tv2 = new TVShow("FOX21", "Gordon_Ramsay's_24_Hours_To_Hell_And_Back", 21.00, 22.00);
		TVShow tv3 = new TVShow("ABC20", "Wonders_Of_Mexico", 21.00, 22.00);
		TVShow tv4 = new TVShow("CBS21", "Terminator_2", 21.00, 22.30);

		// A copy constructor of TVShow
		TVShow tvCopy = new TVShow(tv, "CBS20");
		System.out.println();
		System.out.println(tvCopy + "\n");

		// A clone method in TVShow class
		System.out.println(tvCopy.clone());
		System.out.println();

		// Method equals
		System.out.println(tv.equals(tv1));
		System.out.println(tv1.equals(tv3));
		System.out.println();

		// Method on isOnSameTime
		System.out.println(tv.isOnSameTime(tv2));
		System.out.println(tv1.isOnSameTime(tv2));
		System.out.println();

		// Method addToStart() from ShowList class
		tvGuide1.addToStart(tv);
		tvGuide1.addToStart(tv1);
		tvGuide1.addToStart(tv2);
		tvGuide1.addToStart(tv3);
		System.out.println(tvGuide1);
		System.out.println();

		// Method insertAtIndex() from ShowList
		tvGuide1.insertAtIndex(tv4, 1);
		System.out.println(tvGuide1);
		System.out.println();

		// Method deleteFromIndex() from ShowList
		tvGuide1.deleteFromIndex(2);
		System.out.println(tvGuide1);
		System.out.println();

		// Method deleteFromStart() from ShowList
		tvGuide1.deleteFromStart();
		System.out.println(tvGuide1);
		System.out.println();

		// Method replaceAtIndex() from ShowList
		tvGuide1.replaceAtIndex(tv4, 1);
		System.out.println(tvGuide1);
		System.out.println("\n");
		System.out.println("The program terminates here.");
	}
}