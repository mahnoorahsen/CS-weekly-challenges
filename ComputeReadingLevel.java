package csClubWeekly;
import java.util.Scanner; 

/*
 Mahnoor Ahsen
 
 This program prompts the user to enter some text and determines the approximate
 grade level needed to comprehend the text using the Coleman-Liau index.
 
 */

public class ComputeReadingLevel 
{
	public static void main(String[] args) 
	{
	   Scanner input = new Scanner(System.in);
			
	   String userText = "";
			
	   //Prompt user to enter text.
	   System.out.println("Enter some text to determine the approximate grade level needed for comprehension (use proper end punctuation).");
	   System.out.print("Text: ");
           userText = input.nextLine().trim();
	
	   //Handle input errors
	   //Ensure text contains at least one sentence, and allow user to enter text again.
	   while(userText.matches("\\s+|\\s|[.?!]") || userText.isEmpty() || userText.matches("\\d+") || (!userText.matches(".*[.?!]")))	
	   {
	      System.out.println("Invalid input. Enter at least one complete sentence to determine the approximate grade level needed for comprehension: ");
	      userText = input.nextLine().trim();
	   }
	   //Count letters including alphabets only.
	   int userLetters = userText.replaceAll("[^a-zA-Z]","").length();
			
	   //Count words by splitting at spaces, exclude punctuation.
	   int userWords = userText.replaceAll("[^a-zA-z\\s+]", "").trim().split("\\s").length;  
			
	   //Count sentences by splitting text after any end punctuation. 
	   int userSentences = userText.split("[!.?]").length;      
	
	   //Use getReadingLevel method to display result.
	   getReadingLevel(userLetters, userSentences, userWords);
			
	   input.close();
	}
	
	//Method to compute Coleman-Liau index and display grade level needed for comprehension of text.	
	public static void getReadingLevel(int userLetters, int userSentences, int userWords)
	{
	   //Calculate average letters per 100 words.
	   double L = ((double) userLetters/userWords)*100;  
			
	   //Calculate average sentences per 100 words.
	   double S = ((double) userSentences/userWords)*100;   
			
	  //Coleman-Liau formula to calculate index.
	   int gradeLevel = (int) (0.0588 * L -0.296 * S - 15.8);  
			
	  //Set index value to be in the proper grade range. (Grades 1-12)
	  gradeLevel = Math.max(gradeLevel, 1); 
	  gradeLevel = Math.min(gradeLevel, 12);
			
	  //Display calculated grade level.
          System.out.print("\nReadability Result: Grade " + gradeLevel);
	}
	

}
