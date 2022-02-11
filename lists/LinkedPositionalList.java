package lists;

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

	public Position<E> before(Position<E> position) throws IllegalArgumentException {
		Node<E> node = validate(position);
		return position(node.getPrev());
	}

	public Position<E> after(Position<E> position) throws IllegalArgumentException {
		Node<E> node = validate(position);
		return position(node.getNext());
	}

	public Position<E> addBetween(E element, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(element, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
		return newest;
	}

	public Position<E> addFirst(E element) {
		return addBetween(element, header, header.getNext());
	}

	public Position<E> addLast(E element) {
		return addBetween(element, trailer.getPrev(), trailer);
	}

	public Position<E> addBefore(Position<E> position, E element) throws IllegalArgumentException {
		Node<E> node = validate(position);
		return addBetween(element, node.getPrev(), node.getNext());
	}

	public Position<E> addAfter(Position<E> position, E element) throws IllegalArgumentException {
		Node<E> node = validate(position);
		return addBetween(element, node, node.getNext());
	}

	public E set(Position<E> position, E element) throws IllegalArgumentException {
		Node<E> node = validate(position);
		E elementToReplace = node.getElement();
		node.setElement(element);
		return elementToReplace;
	}

	public E remove(Position<E> position) throws IllegalArgumentException {
		Node<E> node = validate(position);
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
	private Node<E> validate(Position<E> position) throws IllegalArgumentException {
		if (!(position instanceof Node)) {
			throw new IllegalArgumentException("Invalid p");
		}
		Node<E> node = (Node<E>) position;
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
