package stacks;

import java.util.EmptyStackException;

import linked_lists.SinglyLinkedList;

/**
 * A SinglyLinkedList implementation of a stack. Memory usage is O(n) and all
 * operations have a time complexity of O(1). The size can grow or shrink
 * depending how large the stack is. Uses more space than a properly sized
 * array-based stack.
 * 
 * @author Cody Worthen
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>();

	public LinkedStack() {
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E element) {
		list.addFirst(element);
	}

	public E top() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.removeFirst();
	}

}
