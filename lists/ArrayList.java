package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {

	// this can only be accesses by an instance method of ArrayList<E>
	private class ArrayIterator implements Iterator<E> {
		private int j = 0;
		private boolean removable = false;

		public boolean hasNext() {
			return j < size;
		}

		public E next() throws NoSuchElementException {
			if (j == size) {
				throw new NoSuchElementException("No next element");
			}
			removable = true;
			return data[j++];
		}

		public void remove() throws IllegalStateException {
			if (!removable)
				throw new IllegalStateException("Nothing to remove");
			ArrayList.this.remove(j - 1);
			j--;
			removable = false;
		}
	}

	// creates a new ArrayIterator
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}

	public static final int CAPACITY = 16;
	private E[] data;
	private int size = 0;

	public ArrayList() {
		this(CAPACITY);
	}

	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}

	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if (size == data.length) {
			resize(2 * data.length);
		}
		for (int k = size - 1; k >= i; k--) {
			data[k + 1] = data[k];
		}
		data[i] = e;
		size++;
	}

	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		for (int k = i; k < size - 1; k++) {
			data[k] = data[k + 1];
		}
		data[size - 1] = null;
		size--;
		return temp;
	}

	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}

	protected void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<E> walk = this.iterator();
		while (walk.hasNext()) {
			sb.append(walk.next() + " ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(0, 11);
		numbers.add(1, 12);
		numbers.add(2, 13);

		System.out.println(numbers.toString());
	}

}
