import queues.LinkedCircularQueue;
import stacks.LinkedStack;

public class QueueInClassExercise {
	public static void main(String[] args) {
		LinkedStack<Integer> stack1 = new LinkedStack<>();
		LinkedStack<Integer> stack2 = new LinkedStack<>();
		LinkedStack<Integer> stack3 = new LinkedStack<>();

		for (int i = 0; i < 10; i++) {
			stack1.push(i);
		}

		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}

		while (!stack2.isEmpty()) {
			stack3.push(stack2.pop());
		}

		while (!stack3.isEmpty()) {
			System.out.print(stack3.pop() + " ");
		}

		System.out.println();

		LinkedCircularQueue<Integer> queue1 = new LinkedCircularQueue<>();
		LinkedCircularQueue<Integer> queue2 = new LinkedCircularQueue<>();
		LinkedCircularQueue<Integer> queue3 = new LinkedCircularQueue<>();

		for (int i = 0; i < 10; i++) {
			queue1.enqueue(i);
		}

		while (!queue1.isEmpty()) {
			queue2.enqueue(queue1.dequeue());
		}

		while (!queue2.isEmpty()) {
			queue3.enqueue(queue2.dequeue());
		}

		while (!queue3.isEmpty()) {
			System.out.print(queue3.dequeue() + " ");
		}
	}
}
