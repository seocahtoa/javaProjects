package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is the main program where the csv files are parsed and lists are made accordingly. Besides the main method,
 * there exists static inputCheck, static location, and static splitCSVLine method. In the main method, when the file is being read, 
 * for each line of the file gets added to an arraylist which is then translated into a Meteorite object to be added to MeteoriteList.
 * After all meteor's in the csv file are translated into MeteoriteList, then several methods can be called depending on the types of searches 
 * that needs doing.
 * 
 * 
 * @author Minsu Seo
 * 
 */

public class FallenStars {
    public static void main(String args[]) throws FileNotFoundException{
        /** 
        * Lines 8-21 was adapted from Professor Klukowska's ColorConverter.java file.
        */
        //If there are no command line arguments present, the program prints the error message accordingly.
        if (args.length == 0 ) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		//verify that command line argument contains a name of an existing file 
		File meteoFile = new File(args[0]); 
		if (!meteoFile.exists()){
			System.err.println("Error: the file "+meteoFile.getName()+" does not exist.\n");
			System.exit(1);
        }
        //if the file is not readable, the program prints the error message accordingly also. 
		if (!meteoFile.canRead()){
			System.err.println("Error: the file "+meteoFile.getName()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		//The list to add Meteorite object to
		MeteoriteList list = new MeteoriteList();
		Scanner input = new Scanner(meteoFile);
		input.nextLine();
		//Going thorugh the csv file line by line
		while(input.hasNextLine()){
			//Default Meteorite data fields to fill up later
			String name = "";
			int id = 0;
			int mass = 0;
			int year = 0;
			double latitude = 0;
			double longitude = 0;
			//For each line of the file, use splitCSVLine to seperate the line into an arraylist with different elements
			ArrayList<String> line = splitCSVLine(input.nextLine());
			Meteorite m = null;
			Location l = null;
			for (int i = 0; i < line.size();i++){
				//checks if there are any empty Meteorite data fields
				if (!line.get(i).equals("")){
					if(i == 0){
						name = line.get(i);
					}
					else if (i == 1){
						//Uses the name that was dug previously along with the id to create a Meteorite object
						id = Integer.parseInt(line.get(i));
						m = new Meteorite(name, id);
					}
					else if (i == 4){
						//Sets the mass of the meteorite with the method setMass
						mass = (int)Double.parseDouble(line.get(i));
						m.setMass(mass);
					}
					else if (i == 6){
						//Sets the year of the meteorite with the method setYear
						year = (Integer.parseInt(line.get(i).substring(6,10)));
						m.setYear(year);
					}
					else if (i == 7){
						latitude = Double.parseDouble(line.get(i));
					}
					else if (i == 8){
						//using the latitude that was obtained previously, uses the setLocation method to assign the longitude and latitude of the meteorite
						longitude = Double.parseDouble(line.get(i));
						l = new Location(latitude,longitude);
						m.setLocation(l);
					}
				}
			}
			//After all the attributes were added, add the finished meteorite to the list
			list.add(m);
		}
		input.close();

		Scanner sc = new Scanner(System.in);
		System.out.println("Search the databse by using one of the follong queries" + "\n  " 
		+ "To search for meteorite nearest to a given geo-location, enter" + "\n     "
		+ "location LATITUDE LONGITUDE" + "\n  " 
		+ "To search for meteorites that fell in a given year, enter" + "\n     "
		+ "year YEAR" + "\n  "
		+ "To search for meteorites with weights MASS +/- 10 grams, enter" + "\n     "
		+ "mass MASS" + "\n  "
		+ "To finish the program, enter" + "\n     "
		+ "quit" + "\n");

		System.out.println("Enter your search query:" + "\n");



		String query = sc.nextLine();
		String[] sen = query.split(" ");
		//While the user types the word quit
		while(!sen[0].equals("quit")){
			//Calls the inputcheck method to check if the user input was valid
			if (inputCheck(sen) == false){
				//If not tell the user for a differnt user input
				System.out.println("\n" + "This is not a valid query. Try again.");
				System.out.println("\n" + "Enter your search query:" + "\n");
				query = sc.nextLine();
				sen = query.split(" ");
				continue;
			}
			else if (sen[0].equals("location")){
				//If the first word of the user input was lcoation
				try{
					//Calls on the location method to return a meteorite that is closest to the location provided
					Meteorite m = location(list, sen[1],sen[2]);
					if (m == null){
						//if there are no meteorite that is closest asks for different search
						System.out.println("\n" + "No mathces found. Try again.");
						System.out.println("\n" + "Enter your search query:" + "\n");
						query = sc.nextLine();
						sen = query.split(" ");
						continue;
					}
					else{
						//If there was a meteorite that is closest, prints it and ends the program
						System.out.println(m.toString()+ "\n");
						System.exit(1);
					}
				}catch(Exception e){
					//If there are any invalid inputs being passed, program asks the user for input again
					System.out.println("\n" + "This is not a valid geolocation. Try again" + "\n");
					query = sc.nextLine();
					sen = query.split(" ");
					continue;
				}
			}
			else if(sen[0].equals("year")){
				try {
					//If the first word of the user input was year, it calls the list's getByYear method to return the MeteoriteLinkedList that contains all the year that is being queried
					MeteoriteLinkedList m = list.getByYear(Integer.parseInt(sen[1]));
					if (m == null){
						//If there are no matches, asks the user for input again
						System.out.println("\n" + "No mathces found. Try again.");
						System.out.println("\n" + "Enter your search query:" + "\n");
						query = sc.nextLine();
						sen = query.split(" ");
						continue;
					}
					else{
						//prints the result, if found
						System.out.println(m.toString()+ "\n");
						System.exit(1);
					}
				} catch (Exception e) {
					//Catches any invalid inputs
					System.out.println("\n" + "This is not a valid year. Try again" + "\n");
					query = sc.nextLine();
					sen = query.split(" ");
					continue;
				}
			}
			else{
				try {
					//The first word of the user input has to be mass, it calls on the getByMass method on the list to return a MeteoriteLinkedList that contains all the meteorites with the given interval
					MeteoriteLinkedList m = list.getByMass(Integer.parseInt(sen[1]),10);
					//if there are no results, asks the user for input again
						if (m == null){
						System.out.println("\n" + "No mathces found. Try again.");
						System.out.println("\n" + "Enter your search query:" + "\n");
						query = sc.nextLine();
						sen = query.split(" ");
						continue;
					}
					else{
						//return results if any
						System.out.println(m.toString() + "\n");
						System.exit(1);
					}
				} catch (Exception e) {
					//Catches any input invalid errors
					System.out.println("\n"+"This is not a valid mass. Try again" + "\n");
					query = sc.nextLine();
 					sen = query.split(" ");
					continue;
				}
			}

		}
	}

	/**
	 * Checks the user inputs on different guidelines
	 * @param sen
	 * @return Boolean
	 */
	public static Boolean inputCheck(String[] sen){
		//if the input is more than 3 words, fails the check
		if (sen.length > 3)
			return false;
		//if the first word of the input does not contain the words: location, year, or mass, it fails the test
		if (sen[0].equals("location") || sen[0].equals("year") || sen[0].equals("mass")){
			//If the first word contain the word location but does not have 3 words in total, it fails the test
			if (sen[0].equals("location")){
				if (sen.length!=3)
					return false;
			}
			//If the first word contain the word year or mass and not have 2 words in total, it fails the test
			else if (sen[0].equals("year") || sen[0].equals("mass")){
				if (sen.length != 2)
					return false;
			}
			//Otherwise it passes the test
			return true;
		}
		else{
			return false;
		}
	}




	/**
	 * Returns the Meteorite object that is closest to the location.
	 * @param m
	 * @param la
	 * @param lo
	 * @return Meteorite
	 * @throws IllegalArgumentException
	 */
	public static Meteorite location (MeteoriteList m, String la, String lo) throws IllegalArgumentException{
		//Converts the string value of latitude and longitude to doubles
			double lat = Double.parseDouble(la);
			double lon = Double.parseDouble(lo);
			//Makes a new location class with those two latitude and longitiude values
			Location loc = new Location(lat,lon);
			//calls on the getByLocation method
			return m.getByLocation(loc);
		}
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine  a line of text from a CSV file to be parsed
	 * @return an ArrayList object containing all individual entries found on that line;
	 *  any missing entries are indicated as empty strings; null is returned if the textline
	 *  passed to this function is null itself.
	 */
	public static ArrayList<String> splitCSVLine(String textLine){

		if (textLine == null ) return null;

		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;

		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {

				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				}
				else {
					insideQuotes = true;
					insideEntry = true;
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else { // skip all spaces between entries
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar);
				}
				else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}

		}
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
	
    
}
