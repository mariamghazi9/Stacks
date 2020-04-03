package eg.edu.alexu.csd.datastructure.stack.cs;

public class Stack implements IStack {
	/**
	 * 
	 * @author LENOVO
	 *node class that assigns element ,next and head for each node
	 */
	class Node {
		Object element;
		Node next;
		Node head;
	}

	/**
	 * 
	 * @author LENOVO
	 *Singly linked list class all i needed is a head node
	 */
	class SinglyLinkedList {
		Node head;
	}

	SinglyLinkedList stack = new SinglyLinkedList();
	int size;
	/**
	 * Removes the element at the top of stack and returns that element.
	 *
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object pop() {
		if (size == 0)
			throw new RuntimeException("Empty stack");
		else {
			Object x = stack.head.element;;
			stack.head = stack.head.next;
			size--;
			return x;
		}
	}

	/**
	 * Get the element at the top of stack without removing it from stack.
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object peek() {
		if (size == 0)
			throw new RuntimeException("Empty stack");
		else {
			Object x = stack.head.element;
			return x;
		}
	}
	/**
	 * pushs an item to the top of the stack
	 * @param element
	 * the item to be pushed
	 */
	@Override
	public void push(Object element) {
		Node x = new Node();
		x.element = element;
		x.next = stack.head;
		stack.head = x;
		size++;
	}
	/**
	 * @return true if the stack is empty an false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}
	/**
	 * @return the size of a stack
	 */
	@Override
	public int size() {
		return size;
	}
}