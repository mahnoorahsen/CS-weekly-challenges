package csClubWeekly;
import java.util.ArrayList;
import java.util.Scanner; 

/*
Mahnoor Ahsen

This program prompts the user to enter their credit card number 
and then, using Hans Peter Luhn's algorithm, returns whether it is
a valid American Express, MasterCard, or Visa card number.

*/

public class ValidateCreditCard 
{

    public static void main(String[] args) 
    {
	Scanner input = new Scanner(System.in);
				   	   
	//ArrayList to store each digit of the credit card number.
	ArrayList<Integer> creditCardDigitList = new ArrayList<Integer>();
					
   	//Prompt user to enter credit card number. 
	System.out.println("Enter your credit card number (Enter digits only). ");
	System.out.print("Number: ");
	String userCreditCardNum = input.nextLine();
		   
	//Handle input errors.
	//Ensure text only contains numbers. 
	while(!userCreditCardNum.matches("\\d+") || userCreditCardNum.isEmpty())	
	{
	   System.out.println("Invalid input. Enter your credit card number with only digits to determine validity: ");
	   System.out.print("Number: ");
	   userCreditCardNum = input.nextLine().trim();
	}	
	    
	//Use for loop to access characters from user string (starting from the last character).
	for(int i =  userCreditCardNum.length()-1; i >= 0; i--) 
	{
	    //Convert each character in the string to a number.
	    int credDigit = Character.getNumericValue(userCreditCardNum.charAt(i));
			   
	    //Add the number to the beginning of the array list.
	    creditCardDigitList.add(0,credDigit); 
	}
		   
	 //Use isValidCreditCard method to display result.
	 isValidCreditCard(creditCardDigitList,userCreditCardNum);
		  		
	 input.close();
    }
		
    public static void isValidCreditCard(ArrayList<Integer> creditCardDigitList, String userCreditCardNum) 
    {
	 //Variable to hold cumulative sum of digits.
	 int sum = 0; 
			
	 //Variable that holds the digits that will be multiplied by 2.
	 int multipliedDigit = 0; 
	 		
	 //Access every other digit of the credit card number starting from second-to-last digit. 
	  for(int i = creditCardDigitList.size()-2; i >= 0; i = i-2) 
	  {
	      //Multiply the digit by 2.
	      multipliedDigit = (creditCardDigitList.get(i)*2);
		  				  		
	      //Check if multipliedDigit is a 2 digit number, if so then the digits need to be separated.
	      if(multipliedDigit >= 10) 
	      {
	         //Get the first digit of the value and add it to the sum. 
	         sum += multipliedDigit/10; 
			  		
	         //Get the second digit of the value and add it to the sum. 
		 sum+= multipliedDigit % 10; 
	      }
	      //If the digit multiplied by 2 remains single digit, add it to the sum without modifying. 
	      else 
	      {
	      sum+= multipliedDigit;
	      }
          }
	//Variable holding unaltered digits.
	int unalteredDigits = 0;
	  		
	//Access the digits of the credit card number (starting from end) that were not multiplied by 2 and add them to the sum.  
	for(int i = creditCardDigitList.size()-1; i >= 0; i=i-2)
	{
	   unalteredDigits = creditCardDigitList.get(i);		  		
	   sum+= unalteredDigits;
	}
	 //If the last digit of the sum = 0, it is valid.
	 if(sum % 10 == 0)
	 {
	     //Check what type of card it is based on the first/first two digits and display result.
	      if(userCreditCardNum.startsWith("4")) 
	      {
	  	System.out.println("VISA");
	      }
	      else if(userCreditCardNum.startsWith("34") || userCreditCardNum.startsWith("37"))
	      {
	        System.out.println("AMERICAN EXPRESS");
	      }
	      else if(userCreditCardNum.startsWith("51") || userCreditCardNum.startsWith("52")  || userCreditCardNum.startsWith("53") 
	  	   || userCreditCardNum.startsWith("54") || (userCreditCardNum.startsWith("55"))) 
	      { 
	        System.out.println("MASTERCARD");
	      }
	 }	
	      //Inform user of invalid card number.
	 else 
	 {
	   System.out.println("INVALID CREDIT CARD NUMBER"); 			
	 }	
    }
}

/*
 Output: 
Enter your credit card number (Enter digits only). 
Number: 6442323234545343
INVALID CREDIT CARD NUMBER

Enter your credit card number (Enter digits only). 
Number: 4003600000000014
VISA

Enter your credit card number (Enter digits only). 
Number: 378734493671000
AMERICAN EXPRESS

Enter your credit card number (Enter digits only). 
Number: 5105105105105100
MASTERCARD

*/
