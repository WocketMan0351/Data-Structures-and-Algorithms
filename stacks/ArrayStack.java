package stacks;

/**
 * An array-based implementation of a stack. Memory is O(n) and time complexity
 * of all operations is O(1);
 * 
 * @author Cody Worthen
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int t = -1;

	/**
	 * Default constructor
	 */
	public ArrayStack() {
		this(CAPACITY);
	}

	/**
	 * @param capacity the maximum allowed size of the stack (immutable)
	 */
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}

	/**
	 * @return int
	 */
	public int size() {
		return (t + 1);
	}

	/**
	 * @return boolean
	 */
	public boolean isEmpty() {
		return t == -1;
	}

	/**
	 * @return void
	 */
	public void push(E element) {
		if (size() == data.length) {
			throw new IllegalStateException("Stack is full");
		}
		data[++t] = element; // increments t before the expression is evaluated
	}

	/**
	 * @return E
	 */
	public E top() {
		if (isEmpty()) {
			return null;
		}
		return data[t];
	}

	/**
	 * @return E
	 */
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E element = data[t];
		data[t] = null; // aids in garbage collection
		t--;
		return element;
	}
}
