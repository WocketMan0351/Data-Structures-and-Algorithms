package queues;

import stacks.LinkedStack;

public class QueueExercises {

	public static void main(String[] args) {
		LinkedCircularQueue<Integer> queue = new LinkedCircularQueue<>();
		LinkedStack<Integer> stack = new LinkedStack<>();

		// load the queue with 1-10 ascending
		for (int i = 1; i <= 10; i++) {
			queue.enqueue(i);
		}

		// reverse the queue
		reverseQueue(queue);

		// the queue is now in reverse order
		for (int i = 1; i <= 10; i++) {
			System.out.print(queue.dequeue() + " ");
		}

	}

	/**
	 * Reverses a queue in place. Mutates the original queue.
	 * 
	 * @param <E> LinkedCircularQueue
	 * @return void
	 */
	public static <E> void reverseQueue(LinkedCircularQueue<E> queue) {
		LinkedStack<E> stack = new LinkedStack<>();
		int queueSize = queue.size();

		while (!queue.isEmpty()) {
			stack.push(queue.dequeue());
		}

		int stackSize = stack.size();

		while (!stack.isEmpty()) {
			queue.enqueue(stack.pop());
		}

	}

}
