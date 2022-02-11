package lists;

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

	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	public LinkedPositionalList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, null, null);
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
