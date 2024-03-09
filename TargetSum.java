package csClubWeekly;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Mahnoor Ahsen

This program prompts the user to enter an array of integers and a target sum
It returns true or false depending on whether or not it has found a subset array 
that adds up to the target sum. 

*/

public class TargetSum 
{
    public static void main(String args [])
    {
        Scanner input = new Scanner(System.in);

        //Prompt user for input. 
        String userIntegers = "";
        
        while (!userIntegers.matches("(\\d+\\s*)+") || userIntegers.isEmpty())
        {
            System.out.println("Enter an array of integers (separate them by spaces): ");
            userIntegers = input.nextLine().trim();

            // Validate that the input contains only integers
            if (!userIntegers.matches("(\\d+\\s*)+") || userIntegers.isEmpty()) {
                System.out.println("Invalid input. Please enter only integers.");
            }
        }
        
        int targetSum = 0;
        
        while(targetSum == 0) 
        {
        	 try {
                 // Prompt user for target sum input.
                 System.out.println("Enter the target sum: ");
                 targetSum = input.nextInt();
                 
                 break;  
                 
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input. Enter a valid integer for the target sum.");
                 input.nextLine(); // clear the invalid input from the scanner
             }
        }

	     //Put user string of integers to an array by splitting at every space. 
	     String numbers [] = userIntegers.split(" ");
	        
	     //Create subset array of integers.
	     int [] subsetArray = new int[numbers.length];
	        
	     //Add those numbers to an array.
	     for(int i = 0; i < numbers.length; i++)
	     {
	    	 subsetArray[i] = Integer.parseInt(numbers[i]);
	     }
	        
	     //Display user subset.
	     System.out.println("User array: " + Arrays.toString(subsetArray));
	
	    //Create a temporary array of boolean values.
	    //Number of rows is one more than user given array to represent empty subset.
	    //Number of columns is all the possible sums adding up to user target, including 0.
	    boolean [][] tempArray = new boolean [subsetArray.length+1][targetSum +1];
	
	    //Check if subset exists.
	    isSubset(subsetArray, targetSum, tempArray);
	        
	    input.close();
    }

    //Method to see if subset exists.
    public static void isSubset(int [] subsetArray, int targetSum, boolean [][] tempArray)
    {
        //Traverse temporary array.
        for(int i = 0; i < tempArray.length; i++)
        {
            for(int j = 0; j < tempArray[0].length; j++)
            {
                //If the row value is an empty subset and first possible sum (column) is 0.
                if(i == 0 && j == 0)
                {
                    tempArray[i][j] = true;
                }
                //If only the row value is an empty subset.
                //An empty subset cannot equal to a sum greater than 0.
                else if(i == 0)
                {
                    tempArray[i][j] = false;
                }
                //If the possible sum is 0, then every row can has an empty subset that can equal to it. 
                else if(j == 0)
                {
                    tempArray[i][j] = true;
                }
                //If i is not an empty subset and the possible sum (j) is not 0.
                else
                {
                    //If the previous subset (excluding current 'i' in the user subset array) 
                    //can add up to the current possible sum (j), then the current value is part of the subset. 
                    if(tempArray[i-1][j]== true)
                    {
                        tempArray[i][j] = true;
                    }
                    else
                    {
                      //Get value of current subset element.
                      int val = subsetArray[i-1]; 

                      //If the current sum j is >= to current subset value
                      if(j >= val)
                      {
                        //If the previous subset can add up to the remaining sum, then current value is a subset. 
                        if(tempArray[i-1][j-val] == true)
                        {
                            tempArray[i][j] = true;
                        }
                      }

                    }
                }
            }
        }
        //Display if subset exists or not. 
        System.out.println(tempArray[subsetArray.length][targetSum]);

    }
    
}

/*
Output: 
Enter an array of integers (separate them by spaces): 
4 2 7 1 3 
Enter the target sum: 
10
User array: [4, 2, 7, 1, 3]
true
*/
