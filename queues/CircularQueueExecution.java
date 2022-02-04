package queues;

public class CircularQueueExecution {

	public static void main(String[] args) {
		String[] a1 = { "Alice", "Bob", "Cindy", "Doug", "Ed", "Fred" };
		String[] a2 = { "Gene", "Hope", "Irene", "Jack", "Kim", "Lance" };
		String[] a3 = { "Mike", "Roberto" };
		System.out.println("First survivor is " + Josephus(buildQueue(a1), 3));
		System.out.println("Second survivor is " + Josephus(buildQueue(a2), 10));
		System.out.println("Third survivor is " + Josephus(buildQueue(a3), 7));
	}

	public static <E> E Josephus(CircularQueue<E> queue, int k) {
		if (queue.isEmpty()) {
			return null;
		}
		while (queue.size() > 1) {
			for (int i = 0; i < k - 1; i++) {
				queue.rotate();
			}
			E e = queue.dequeue();
			System.out.println("     " + e + " is executed");
		}
		return queue.dequeue();
	}

	public static <E> CircularQueue buildQueue(E a[]) {
		CircularQueue<E> queue = new LinkedCircularQueue<>();
		for (int i = 0; i < a.length; i++) {
			queue.enqueue(a[i]);
		}
		return queue;
	}

}
