class Node {
	int data;
	Node next;

	Node(int d) { // Constructor
		data = d;
		next = null;
	}
}

public class ACOLinkedList {// a Singly Linked List
	Node head; // head of list

	public static ACOLinkedList insert(ACOLinkedList list, int data) {
		Node new_node = new Node(data); // Create a new node with given data
		new_node.next = null;
		if (list.head == null) { // If the Linked List is empty, then make the new node as head
			list.head = new_node;
		} else { // Else traverse till the last node and insert the new_node there
			Node last = list.head;
			while (last.next != null) {
				last = last.next;
			}
			last.next = new_node; // Insert the new_node at last node
		}
		return list;
	}

	public static void printList(ACOLinkedList list) {
		Node currNode = list.head;
		System.out.print("LinkedList: ");
		while (currNode != null) { // Traverse through the LinkedList
			System.out.print(currNode.data + " "); // Print the data at current node
			currNode = currNode.next; // Go to next node
		}
	}

	public static void main(String[] args) {
		ACOLinkedList list = new ACOLinkedList(); // Start with the empty list.
		// Insert the values
		list = insert(list, 1);
		list = insert(list, 2);
		list = insert(list, 3);
		list = insert(list, 4);
		list = insert(list, 1);
		list = insert(list, 2);
		list = insert(list, 3);
		list = insert(list, 4);
		printList(list); // Print the LinkedList
	}
}
