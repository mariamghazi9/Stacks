package eg.edu.alexu.csd.datastructure.stack.cs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionEvaluatorTest {
	ExpressionEvaluator e=new ExpressionEvaluator();

	@Test
	void testInfixToPostfix() {
		
		assertEquals(e.infixToPostfix("2+3*4"),"2 3 4 * +");
		assertEquals(e.infixToPostfix("a * b + 5"),"a b * 5 +");
		assertEquals(e.infixToPostfix("(1+2)*7"),"1 2 + 7 *");
		assertEquals(e.infixToPostfix("a * b / c"),"a b * c /");
		assertEquals(e.infixToPostfix("(a / (b - c + d)) * (e - a) * c "),"a b c - d + / e a - * c *");
		assertEquals(e.infixToPostfix("a/b-c+d*e-a*c"),"a b / c - d e * + a c * -");
		assertEquals(e.infixToPostfix("a++b"),"Invalid expression");
		assertEquals(e.infixToPostfix(""),"Invalid expression");
		assertEquals(e.infixToPostfix("1+3*"),"Invalid expression");
		assertEquals(e.infixToPostfix("5*-2+3/-1-4"),"5 0 2 - * 3 0 1 - / + 4 -");
		assertEquals(e.infixToPostfix("1 + 2) * 7 "),"Invalid expression");
		assertEquals(e.infixToPostfix("(1 + 2 * 7 "),"Invalid expression");
	    assertEquals(e.infixToPostfix("(1 & 2) @ 7 "),"Invalid expression");
	    assertEquals(e.infixToPostfix("2a+3b"),"Invalid expression");
	    assertEquals(e.infixToPostfix("ab*cd"),"Invalid expression");



	}

	@Test
	void testEvaluate() {
		
		assertEquals(e.evaluate("6 2 / 3 - 4 2 * +"),8);
		assertEquals(e.evaluate("1 2 + 4 * 3 +"),15);
		assertEquals(e.evaluate("10 3 5 * 16 4 - / +"),11);
		assertThrows(RuntimeException.class,()->{e.evaluate("1 0 /");});
		assertThrows(IllegalArgumentException.class,()->{e.evaluate("a b +");});

	}

}
