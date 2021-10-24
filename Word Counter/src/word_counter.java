/*
 * Word Counter Code With Organized Order
 * 
 * Author: Abdulrahman Aljuhani
 * KAU ID: 1847693
 * Version: 1.00v
 * Last Update: 01/02/2021
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class word_counter {
	
	public static void main(String[] args) throws IOException{
		try {
			// objects
			File fileIn = new File(args[0]); // created a file object called fileIn for the input file
			Scanner scan = new Scanner(fileIn); // created a scanner object called scan
			File fileOut = new File(args[1]); // created a file object called fileOut for the output file
			FileWriter fw = new FileWriter(fileOut); // created a file writer object called fw
			PrintWriter pw = new PrintWriter(fw); // created a print writer object called pw to write in the output file
			ArrayList<String> stringList = new ArrayList<String>(); //
		    HashMap<String, Integer> words = new HashMap<String, Integer>();

		    // adding the text file to arraylist
			while (scan.hasNextLine()) { // this will add each line into an array list 
				stringList.add(scan.nextLine());
			}
	
			//add the words to the hashmap
		    for (String line : stringList) { // a for each loop will for each element of the arraylist
		        String[] sentenceSpli = line.split("\\W|\\_"); // this will store the splitted words inside string array
		        for (String word : sentenceSpli) { // this this will take the array of strings and store it as a string
		        	if(word.trim().equals("")) //this to remove unwanted spaces
	  			  	{
		        		continue;
	  			  	} 
		        	if (words.containsKey(word)) { //if there is duplicate of the words
	  			  		words.put(word, words.get(word) + 1); //then add +1

	  			  	} else {
	  			  		words.put(word, 1); // if it's not available as a key add it
		            }
		        }
		    }
		    
	        // create a list from elements of hashmap 
	        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(words.entrySet()); 
	  
	        // sort the list in ascending order by using the class collection, storing it in a list and then use the class compare to sort it
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() { 
	            public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2){ 
	                return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }
	        );

	        // make sure the default order is descending otherwise 
	        if (args.length == 2 || args[2].equals("des")) {
	        	Collections.reverse(list);
	        } else if (args.length == 3){
	        	if (args[2].equals("asc")) {
	        	} else {
			        pw.close();
		        	throw new Exception("Error, make sure you type either \"asc\" or \"des\" ");
	        	}
	        }
	          
	        // put data from sorted list into a hashmap again
	        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
	        for (Map.Entry<String, Integer> tempForHashList : list) { 
	            temp.put(tempForHashList.getKey(), tempForHashList.getValue());
	        }

	        // print the data into a file
	        for (Map.Entry<String, Integer> printerOutput : temp.entrySet()) { 
	            pw.println("The word [ " + printerOutput.getKey() +" ] Has been repeated a total of [" + printerOutput.getValue()+"] time/'s");
	        }
			System.out.println("The Program has been done! Check "+ args[1] );

	        pw.close();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("An Error Have been occurred, Please Make Sure Your Input Is As Follows:");
			System.out.println("java word_counter [InputFileName.txt] [OutputFileName.txt] [asc|dec]");
		} catch (FileNotFoundException e) {
			System.out.println("An Error Have been occurred, Cannot Find The Input File.");
			System.out.println("Make Sure You Have Entered The Correct Location Of The File");
		} catch (Exception e) {
			System.out.println("An Error Have been occurred, Please Only Enter [asc] or [dsc]");
			System.out.println("In the Last Argument. Defualt dsc");

		}
	}


}
