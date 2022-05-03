package assignments.linkedlist;
/**
 * A singly linked list. Contains a static nested class Node.
 * 
 * @author Cody Worthen
 */
public class ACOLinkedList {

	public static class Node {
		private int data;
		private Node next;

		/**
		 * Constructs a new node consisting of its data and its reference to the next
		 * node
		 * 
		 * @param data int
		 * @param next Node
		 */
		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Gets the data
		 * 
		 * @return int
		 */
		public int getData() {
			return this.data;
		}

		/**
		 * Gets the next node
		 * 
		 * @return Node
		 */
		public Node getNext() {
			return this.next;
		}

		/**
		 * Sets the next node
		 * 
		 * @param next Node
		 * @return void
		 */
		public void setNext(Node next) {
			this.next = next;
		}
	}

	private Node head = null;
	private Node tail = null;
	private int size = 0;

	public ACOLinkedList() {
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true if the list is empty
	 * 
	 * @return boolean
	 */
	private boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Inserts a new node at the end of the list
	 * 
	 * @param data int
	 * @return void
	 */
	public void insert(int data) {
		Node newest = new Node(data, null);
		if (isEmpty()) {
			head = newest;
			head.setNext(tail);
		} else {
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}

	/**
	 * Returns a new ACOLinkedList with the first n elements being removed. The
	 * original ACOLinkedList remains unchanged.
	 * 
	 * @param list ACOLinkedList
	 * @param n    int
	 * @return ACOLinkedList
	 */
	public static ACOLinkedList removeFirstNElements(ACOLinkedList list, int n) {
		ACOLinkedList listWithRemovedElements = new ACOLinkedList();

		if (list.size <= n) {
			return listWithRemovedElements;
		} else {
			Node newHead = list.head; // grab the current head of the list we're removing from
			for (int i = 0; i < n; i++) { // traverse to the nth node
				newHead = newHead.getNext();
			}

			while (newHead != null) {
				listWithRemovedElements.insert(newHead.getData());
				newHead = newHead.getNext();
			}

			return listWithRemovedElements;
		}

	}

	/**
	 * Cleaner interface for reverseList(Node head)
	 * 
	 * @param list ACOLinkedList
	 * @return ACOLinkedList
	 */
	public static ACOLinkedList reverseList(ACOLinkedList list) {
		if (list.isEmpty()) {
			return list;
		}
		ACOLinkedList reversed = new ACOLinkedList();
		reversed.head = reverseList(list.head);

		Node current = reversed.head;
		while (current != null) {
			current = current.getNext();
			reversed.size++; // recount the size of the new reversed list
		}
		return reversed;
	}

	/**
	 * Recursively reverses a singly linked list by starting with the head.
	 * 
	 * @param head Node
	 * @return Node
	 */
	private static Node reverseList(Node head) {
		if (head.getNext() == null) {
			return head;
		}

		Node newHead = reverseList(head.getNext());

		head.next.next = head;
		head.setNext(null);
		return newHead;
	}

	/**
	 * Returns a string containing the nodes of the list separated by a " "
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		while (current != null) {
			sb.append(current.getData() + " ");
			current = current.getNext();
		}
		sb.append("\n");
		return sb.toString();
	}

	public static void main(String[] args) {
		ACOLinkedList list = new ACOLinkedList();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(5);

		// test removeFirstNElements()
		System.out.println("** REMOVING FIRST N ELEMENTS **");
		System.out.println(list.toString());
		ACOLinkedList listWithRemovedElements = removeFirstNElements(list, 4);
		System.out.println(listWithRemovedElements.toString());

		// test reverseList()
		System.out.println("\n** REVERSING **");
		ACOLinkedList reversed = reverseList(listWithRemovedElements);
		System.out.println(reversed.toString());
	}

}
