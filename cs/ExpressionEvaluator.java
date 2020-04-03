package eg.edu.alexu.csd.datastructure.stack.cs;

/**
 * @author LENOVO
 *class ExpressionEvaluator converts infix to postfix then evaluate the postfix
 */

public class ExpressionEvaluator implements IExpressionEvaluator {

	@Override
	/**
	 * takes the infix expression and converts it to postfix
	 * adds a dummy zero for handling negative numbers
	 * there're no assumptions on spaces so user can enter with spaces or without
	 * no assumption on length of term
	 * 
	 * @param expression
	 * @return postfix expression
	 */

	public String infixToPostfix(String expression) {
		Stack stack = new Stack();
		String result = "";
		int i = 0;

		expression = expression.replaceAll(" ", "");

		StringBuffer newExpression = new StringBuffer(expression);
		int count = 0;
		for (int k = 0; k<newExpression.length(); k++) {
			char c = (char) newExpression.charAt(k);
			count = k;
			if ((k == 0 && c == '-') || (c == '-' && isOperator((char) newExpression.charAt(k - 1)))) {
				newExpression.insert(k, "(" + "0");
				while (Character.isLetterOrDigit((char) newExpression.charAt(count + 1))) {
					count++;
				}
				newExpression.insert(count + 3, ")");
				continue;
			}
		}

		while (i<newExpression.length()) {
			char c = (char) newExpression.charAt(i);

			if (!Character.isLetterOrDigit(c) && !isOperator(c) && c != '(' && c != ')')
				return "Invalid expression";
			
			if(i+1!=newExpression.length() && (Character.isDigit(c) && Character.isLetter((char) newExpression.charAt(i+1))))
				return "Invalid expression";
			
			else if(i+1!=newExpression.length() && (Character.isDigit((char) newExpression.charAt(i+1)) && Character.isLetter(c)))
				return "Invalid expression";
			
			else if(i+1!=newExpression.length() && (Character.isLetter((char) newExpression.charAt(i+1)) && Character.isLetter(c)))
				return "Invalid expression";

			if (Character.isLetterOrDigit(c)) {

				while (i<newExpression.length()) {
					c = (char) newExpression.charAt(i);
					if (Character.isLetterOrDigit(c)) {
						result += c;
						i++;
					} else {
						result += ' ';
						break;
					}
				}
				if (i == newExpression.length())
					result += ' ';

			} else if (c == '(') {
				stack.push(c);
				i++;

			} else if (c == ')') {
				while (!stack.isEmpty() && (char) stack.peek() != '(') {
					result += stack.peek();
					result += ' ';
					stack.pop();
				}
				if (stack.isEmpty()) {
					return "Invalid expression";
				} else {
					stack.pop();
					i++;
				}
			} else if (isOperator(newExpression.charAt(newExpression.length() - 1)) || expression.length() == 1 || (isOperator(newExpression.charAt(i)) && isOperator(newExpression.charAt(i + 1))))
				return "Invalid expression";

			else {
				while (!stack.isEmpty() && precedence(c)<= precedence((char) stack.peek())) {
					if ((char) stack.peek() == '(')
						return "Invalid expression";
					result += stack.peek();
					result += ' ';
					stack.pop();
				}
				stack.push(c);
				i++;
			}
		}
		if (stack.isEmpty())
			return "Invalid expression";

		while (!stack.isEmpty()) {
			if ((char) stack.peek() == '(')
				return "Invalid expression";

			result += (char) stack.peek();
			if (stack.size() != 1)
				result += ' ';

			stack.pop();
		}

		return result;
	}

	@Override

	/**
	 * evaluate a numeric postfix expression with single space separator
	 * @param expression
	 * the postfix expression
	 * @return the evaluated value
	 * 
	 */
	public int evaluate(String expression) {
		Stack stack = new Stack();
		for (int i = 0; i<expression.length(); i++) {
			char c = (char) expression.charAt(i);
			if (c == ' ')
				continue;
			else if (Character.isDigit(c)) {
				int number = 0;
				while (Character.isDigit(c)) {
					number = number * 10 + (c - '0');
					i++;
					c = expression.charAt(i);
				}
				i--;
				float value = (Integer) number;
				stack.push(value);
			} else if (!Character.isDigit(c) && c != ' ' && !isOperator(c))
				throw new IllegalArgumentException();

			else {

				float value1 = (Float) stack.pop();
				float value2 = (Float) stack.pop();

				switch (c) {
					case '+':
						stack.push((value2 + value1));
						break;
					case '-':
						stack.push((value2 - value1));
						break;
					case '*':
						stack.push((value2 * value1));
						break;
					case '/':
						if (value1 == 0)
							throw new RuntimeException("Division by zero isn't valid");
						else
							stack.push((value2 / value1));
						break;
				}

			}
		}
		float res = (Float) stack.pop();

		int result = (int) res;

		return result;
	}

	/**
	 * checks which operator has highest priority
	 * @param operator
	 * the operator found in the stack
	 * @return
	 * its precedence
	 */
	public int precedence(char operator) {
		switch (operator) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
		}
		return -1;
	}
	/**
	 * checks that the input is an operator
	 * @param input
	 * the character found
	 * @return true if operator and false otherwise
	 */
	public boolean isOperator(char input) {
		if (input == '+' || input == '-' || input == '*' || input == '/')
			return true;
		else return false;
	}

}