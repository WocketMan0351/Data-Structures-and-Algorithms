package assignments.stack;

import java.util.EmptyStackException;

/**
 * An array-based implementation of a stack. Memory use is O(n) and time
 * complexity of all operations is O(1). Once instantiated, the size cannot be
 * changed. Default size is 100 elements.
 * 
 * @author Cody Worthen
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

	public static final int CAPACITY = 100;
	private E[] data;
	private int t = -1;

	public ArrayStack() {
		this(CAPACITY);
	}

	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public int size() {
		return t + 1;
	}

	public boolean isEmpty() {
		return t == -1;
	}

	public void push(E element) {
		if (size() == data.length) {
			throw new IllegalStateException("Stack is full");
		}
		data[++t] = element; // increment t before data[t] is evaluated
	}

	public E top() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return data[t];
	}

	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E element = data[t];
		data[t] = null;
		t--;
		return element;
	}

}
