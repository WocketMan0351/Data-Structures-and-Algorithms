package linked_lists;

public class CircularlyLinkedList<E> {

	private static class Node<E> {
		private E element; // refers to the element stored at this node
		private Node<E> next; // refers to the next node in the list

		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

	private Node<E> tail = null;
	private int size = 0;

	public CircularlyLinkedList() {
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		return tail.getNext().getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	public void rotate() {
		if (tail != null) {
			tail = tail.getNext();
		}
	}

	public void addFirst(E element) {
		if (isEmpty()) {
			tail = new Node<>(element, null);
			tail.setNext(tail);
		} else {
			Node<E> newest = new Node<>(element, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}

	public void addLast(E element) {
		addFirst(element);
		tail = tail.getNext();
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		Node<E> head = tail.getNext();

		if (head == tail) {
			tail = null;
		} else {
			tail.setNext(head.getNext());
		}

		size--;
		return head.getElement();
	}

}
