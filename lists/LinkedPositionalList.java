package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implemented with a doubly linked list. All operations run in O(1) time (worse
 * case)
 * 
 * @author Cody Worthen
 * @param <E>
 */
public class LinkedPositionalList<E> implements PositionalList<E> {

	private static class Node<E> implements Position<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;

		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}

		public E getElement() throws IllegalStateException {
			if (next == null) {
				throw new IllegalStateException("Position no longer valid");
			}
			return element;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first();
		private Position<E> recent = null;

		public boolean hasNext() {
			return cursor != null;
		}

		public Position<E> next() throws NoSuchElementException {
			if (cursor == null) {
				throw new NoSuchElementException("No next element");
			}
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}

		public void remove() throws IllegalStateException {
			if (recent == null) {
				throw new IllegalAccessError("Nothing to remove");
			}
			LinkedPositionalList.this.remove(recent);
			recent = null;
		}
	}

	private class PositionIterable implements Iterable<Position<E>> {
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}

	/**
	 * Returns an iterable representation of the list's positions.
	 */
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * Adapts the iterations produced by positions() to return elements.
	 */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = new PositionIterator();

		public boolean hasNext() {
			return posIterator.hasNext();
		}

		public E next() {
			return posIterator.next().getElement();
		}

		public void remove() {
			posIterator.remove();
		}
	}

	/**
	 * Returns an iterator of the elements stored in the list.
	 */
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	public LinkedPositionalList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> first() {
		return position(header.getNext());
	}

	public Position<E> last() {
		return position(trailer.getPrev());
	}

	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	public Position<E> addBetween(E element, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(element, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
		return newest;
	}

	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}

	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}

	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node.getNext());
	}

	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E elementToReplace = node.getElement();
		node.setElement(e);
		return elementToReplace;
	}

	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E elementToRemove = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return elementToRemove;
	}

	// validates the position and returns it as a node
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Invalid p");
		}
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null) {
			throw new IllegalArgumentException("p is no longer in the list");
		}
		return node;
	}

	// returns the given node as a Position (null if it's a sentinel)
	private Position<E> position(Node<E> node) {
		if (node == header || node == trailer) {
			return null;
		}
		return node;
	}

}
