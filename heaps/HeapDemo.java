package heaps;

import priority_queues.PriorityQueue;

public class HeapDemo {

	public static void main(String[] args) {
		PriorityQueue<Integer, String> q = new HeapPriorityQueue<>();

		// runs in O(n log n) time because we are repeatedly calling the insert
		// operation on an initially empty priority queue.
		try {
			q.insert(2, "Sue");
			q.insert(9, "Jeff");
			q.insert(6, "Mark");
			q.insert(5, "Pat");
			q.insert(7, "Anna");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		while (!q.isEmpty()) {
			System.out.println(q.removeMin().getValue());
		}

		System.out.println();

		try {

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
