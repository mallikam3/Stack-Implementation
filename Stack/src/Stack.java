/* Programmer: Mallika Mainali
 * Class: COSC 2336.001
 * Date: 10/30/2019
 * Java program to implement stacks to convert a given infix expression to a postfix expression
 */

import java.util.Scanner;

public class Stack
{
	/* Method: precedence
	 * Function: Evaluating and returning the precedence of the given operators. 
	 * Precondition: The input is an operator.
	 * Postcondition: The return output is 1 for operators '+' or '-' and the return output is 2 for operators '*' or '/' 
	 */
	
	//precedence() method takes operator as input and return the precendence as an integer. 
	//Larger number indicates higher precedence. 
	public static int precedence (char ch)
	{
		if (ch == '+' || ch == '-')
		{
			return 1;
		}
		else if (ch == '*' || ch == '/') 
		{
			return 2;
		}
		return 0;
	}
	
	/* Method: Main method
	 * Function: Asking for user's input of infix expression and displaying the converted postfix expression.
	 * Precondition: There is no whitespace in the expression given.
	 * Postcondition: The user's input is stored in a variable. 
	 */
	
	public static void main(String[] args)
	{
		//Asking for user's input
		System.out.println("Please enter an infix expression: ");	
		
		//Reading the user's input
		Scanner scan = new Scanner(System.in);
		String input = scan.next();
		scan.close();
		
		//Calling the function convertToPostfix(input) that converts the given infix expression to postfix
		String output = convertToPostfix(input);
		System.out.println("The postfix expression for your given infix expression is: " + output);
	}

	/* Method: convertToPostfix
	 * Function: Converting the entered infix expression "input" to postfix expression named "output" using stack 
	 * Precondition: There is no whitespace in the expression given and given infix expression is syntactically correct and only consists of single-digit operands, the operators +,  -,  *,  /, and parentheses.
	 * Postcondition: The converted postfix expression "output" is returned.
	 */
	
	public static String convertToPostfix(String input)
	{
		//Creating a new stack using the given StackArrayBased class.
		StackArrayBased stack = new StackArrayBased(); 
		String output = "";
		
		//Using for loop to traverse through the characters of given infix expression.
		for(int i = 0; i<input.length(); i++)
		{
			//Checking for the operators +,-,*,/, and paranthesis and pushing them into the stack
			switch(input.charAt(i))
			{
				case '(':
					stack.push(input.charAt(i));
				break;
				
				//For the operator ')', pop the other characters off the stack if top of the stack is not '('.
				case ')':
					while((char)stack.peek()!='(')
					{
						output += stack.pop(); //adding the characters to converted postfix variable
					}
				stack.pop();
				break;
				
				//For operators that are checked for their precedence level and then pushed into the stack.
				case '+':
				case '-':
				case '*':
				case '/':
					while(!stack.isEmpty() && (char)stack.peek() != '(' 
						&& (precedence(input.charAt(i)) <= precedence((char)stack.peek())))
					{
							output = output + stack.pop();
					}
					stack.push(input.charAt(i));
				break;
				
				//For a single-digit operand where the character is added to the output variable.
				default: 
					output = output + input.charAt(i);
			}
		}
		
		//Add the remaining characters from given infix expression to converted post-fix expression.
		while(!stack.isEmpty())
		{
			output = output + stack.pop();
		}
		return output;
	}
	
}
