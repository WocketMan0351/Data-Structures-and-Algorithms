package linked_lists;

/**
 * A singly linked list. Keeps an explicit reference to the tail.
 * 
 * @author codyworthen
 * @param <E>
 */
public class SinglyLinkedList<E> implements Cloneable {

	public static class Node<E> {
		private E element; // reference to the element stored at this node
		private Node<E> next; // reference to next node in the list

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

	public Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public SinglyLinkedList() {
	}

	// access methods
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		return head.getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	// update methods
	public void addFirst(E element) {
		head = new Node<>(element, head);
		if (isEmpty()) {
			tail = head;
		}
		size++;
	}

	public void addLast(E element) {
		Node<E> newest = new Node<>(element, null);
		if (isEmpty()) {
			head = newest;
		} else {
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if (size == 0) {
			tail = null;
		}
		return answer;
	}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}

		SinglyLinkedList other = (SinglyLinkedList) object;

		if (size != other.size) {
			return false;
		}

		Node walkA = head;
		Node walkB = other.head;

		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement())) {
				return false;
			}
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}

		return true;
	}

	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();

		if (size > 0) {
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext();
			Node<E> otherTail = other.head;

			while (walk != null) {
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest);
				otherTail = newest;
				walk = walk.getNext();
			}
		}

		return other;
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		for (int i = 0; i < 100; i++) {
			list.addLast((int) Math.round(i * 100));
		}

		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		for (int i = 0; i < 100; i++) {
			list2.addLast((int) Math.round(i * 100));
		}

		System.out.println(list.equals(list2));

		SinglyLinkedList<Integer> list2Clone = new SinglyLinkedList<>();

		try {
			list2Clone = list2.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		list2Clone.removeFirst();

		System.out.println(list2.equals(list2Clone));
	}

}
