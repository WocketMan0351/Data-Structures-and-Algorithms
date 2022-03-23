package queues;

import linked_lists.SinglyLinkedList;

/**
 * A SinglyLinkedList implementation of a queue. Memory usage is O(n) and all
 * operations have a time complexity of O(1). The size can grow or shrink
 * depending how large the queue is. Uses more space than a properly sized
 * array-based queue.
 * 
 * 
 * @author Cody Worthen
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>();

	public LinkedQueue() {
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void enqueue(E e) {
		list.addLast(e);
	}

	public E first() {
		return list.first();
	}

	public E dequeue() {
		return list.removeFirst();
	}

}
