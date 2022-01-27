/**
 * A singly linked list
 */
public class ACOLinkedList {

	public static class Node {
		private int data;
		private Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}

		public int getData() {
			return this.data;
		}

		public Node getNext() {
			return this.next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	private Node head = null;
	private Node tail = null;
	private int size = 0;

	public ACOLinkedList() {
	}

	private int size() {
		return size;
	}

	private boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Inserts a new node at the end of a list
	 * 
	 * @param data int
	 * @return void
	 */
	public void insert(int data) {
		Node newest = new Node(data);
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
	 * Prints the linked list
	 */
	public void print() {
		try {
			Node current = head;
			System.out.print("LinkedList: ");
			while (current != null) {
				System.out.print(current.getData() + " ");
				current = current.getNext();
			}
			System.out.println();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes the first n elements from a list
	 * 
	 * @param list ACOLinkedList
	 * @param n    int
	 * @return ACOLinkedList
	 */
	public static ACOLinkedList removeFirstNElements(ACOLinkedList list, int n) {
		ACOLinkedList listWithRemovedElements = new ACOLinkedList();
		if (n >= list.size) {
			return listWithRemovedElements;
		} else {
			Node newHead = list.head; // grab the current head
			for (int i = 0; i < n; i++) { // traverse to the nth node
				newHead = newHead.getNext();
			}
			listWithRemovedElements.head = newHead; // track the new head
			listWithRemovedElements.size = n;
			return listWithRemovedElements;
		}
	}

	/**
	 * Cleaner interface for reverseList()
	 * 
	 * @return ACOLinkedList
	 */
	public static ACOLinkedList reverseList(ACOLinkedList list) {
		ACOLinkedList reversed = new ACOLinkedList();
		reversed.head = reverseList(list.head);
		return reversed;
	}

	/**
	 * Recursively reverses a singly linked list by starting with the head.
	 * 
	 * @param head Node
	 * @return Node
	 */
	private static Node reverseList(Node head) {
		Node newHead;

		if (head.getNext() == null) {
			return head;
		}

		newHead = reverseList(head.getNext());

		head.next.next = head;
		head.setNext(null);
		return newHead;
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
		list.insert(7);

		// test removeFirstNElements()
		System.out.println("** REMOVING FIRST N ELEMENTS **");

		list.print();
		ACOLinkedList listWithRemovedElements = removeFirstNElements(list, 4);
		listWithRemovedElements.print();

		// test reverseList()
		System.out.println("\n** REVERSING **");

		listWithRemovedElements.print();
		ACOLinkedList reversed = reverseList(listWithRemovedElements);
		reversed.print();
	}
}
