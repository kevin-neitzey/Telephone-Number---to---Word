/**
 * Kevin Neitzey
 **/
package project;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Project2
{

    private static final char[][] possibles = new char[][]{ {'0'},{'1'},{'A', 'B', 'C'},{'D', 'E', 'F'},{'G', 'H', 'I'},{'J', 'K', 'L'},{'M', 'N', 'O'},{'P', 'Q', 'R', 'S'},{'T', 'U', 'V'},{'W', 'X', 'Y', 'Z'}};//storing each number key's letter combination
    static String[] dictionary = new String[54782]; //dictionary to hold english words
    static int matchCounter = 0;


    public static void test()
    {
    	System.out.println("Please enter a 7 digit number:");
        Scanner input = new Scanner(System.in);
        String sevenDigitNumber = input.next();
      	String file = ("..//EnglishWordList.txt");
      	getWords(file);
      	ArrayList<String> combo = new ArrayList<String>();
        combinations(sevenDigitNumber, 0, combo);  
    }
    
    /**
     * Stores all character combinations of phone number
     * @param phoneNum
     * @param index
     * @param results
     */
    private static void combinations(String phoneNum, int index, List<String> results)
    {
        if (index == phoneNum.length())
        {//if index reaches the end of the phone number,
            findMatches(results); //go find some matches
            return;
        }
        int num = Integer.parseInt(phoneNum.charAt(index) + "");//parse each character of phone number

        if (index==0)
        { //otherwise, if index is 0, 
            for (int j = 0; j<possibles[num].length; j++)//go through character array and add combo to list
            { 
                results.add(possibles[num][j]+"");
            }
            combinations(phoneNum, index+1, results); //increment the index for the recursive call and use what it has so far 
        }
        else
        {
            List<String> combos = new ArrayList<String>(); //otherwise, make a new combo list 
            for (int j = 0; j<possibles[num].length; j++)  //going through character array   
            {
                 for (String result : results)//going through result list
                 {			
                     combos.add(result+possibles[num][j]); //adding prefix to combo from character array
                 }
            }
            combinations(phoneNum, index+1, combos);//recursive call advancing digit and using current combos
        }
        
    }

 /**
  * Compares the list of combinations to the list of words in the dictionary file
  * @param results
  */
    
    private static void findMatches(List<String> results)
    {
    	int matchCount = 1; //start match count off at 1
    	String[] combos = results.toArray(new String[results.size()]); //convert list to array
    	  for(int i =0; i< combos.length; i++)//go through combinations
          { 
    		  for(int j = 0; j<dictionary.length; j++)//go through dictionary 
                  { 
    				if(combos[i].equals(dictionary[j]))//if theres a match
                                {
    				  System.out.println("Match #"+ matchCount + " at " + combos[i]);//print the match
    				  matchCount++;//keep track of matches
    			        }
    		  }
                  
          }
             if(matchCount == 1)
              {       
        	System.out.println("Sorry, no matches were found. Try using a phone number like 2226366 to get the word ABANDON");
              }       
    	}

    /**
     * Gets list of english words to store in Dictionary array
     * @param file
     */

    public static void getWords(String file)
    {
    	Scanner input = new Scanner(System.in);
    	int counter1 = 0;
        String tem = "";
        
    	try
        {
        	  FileInputStream fstream = new FileInputStream(file);
        	  DataInputStream in = new DataInputStream(fstream);
        	  BufferedReader br = new BufferedReader(new InputStreamReader(in));
        	  String strLine;
        	  //Read File Line By Line
        	  while ((strLine = br.readLine()) != null)   //as long as there isn't a blank line
                  { 
        		  
		          tem = br.readLine();
		          dictionary[counter1] = strLine.toUpperCase(); //put input into dictionary array
		          counter1++; //dictionary size
        	  }
        	  in.close(); //close input stream
         }
                      catch (Exception e)//Catch any exceptions
                        {
        	           System.err.println("Error: " + e.getMessage());
        	        }
       
    }
    
}
