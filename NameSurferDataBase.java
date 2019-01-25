import acm.util.*;
import java.util.*;
import java.io.*;


/*
 * 
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		rd = openFile(filename);
		while (true) { 
			String line = "";
			try {
				line = rd.readLine();
			}
			catch (IOException ex2) {
				System.out.println("Problem reading from file unrelated to reaching the end of the file");
				throw new ErrorException (ex2);
			}if (line == null) {
				break;
			}NameSurferEntry entry = new NameSurferEntry(line);
			String name = entry.getName();
			String nameCaps = name.toUpperCase();
			entryDatabase.put(nameCaps, entry);
			
			
		}try {
			rd.close();
		
		}catch (IOException ex3) {
			System.out.println("Problems closing the file");
			throw new ErrorException (ex3);
		}
	}
	
	private BufferedReader openFile(String filename) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(filename));
			} catch (IOException ex) {
				System.out.println("Could not find file");
				throw new ErrorException (ex);
			}
		}return rd;
	}
	
	
	
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		String nameCaps = name.toUpperCase();
		if (entryDatabase.containsKey(nameCaps)) {
			return entryDatabase.get(nameCaps);
		} else {
			return null;
		}
	}

	/*private instance variables*/
	private BufferedReader rd; 
	private HashMap <String, NameSurferEntry> entryDatabase = new HashMap <String, NameSurferEntry>();
}

