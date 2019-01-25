/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	
	
	public NameSurferEntry(String line) {
		if (line == null) {	
			System.out.println("The line get to NameSurferEntry was infact null");
		}
		name = line.substring(0, line.indexOf(' '));
		String numbers = line.substring(line.indexOf(' ')+1, line.length());
		String [] numberArr = numbers.split(" ");
		for(int i = 0; i < 11; i++) {
		   rank[i] = Integer.parseInt(numberArr[i]);
		}
		  // You fill this in //
	}

/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		return rank[(decade-START_DECADE)/10];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		return name + " " + Arrays.toString(rank);
	}
	
	/*private instance variables*/
	private String name;
	private int[] rank = new int[11];
	
	
	
}

