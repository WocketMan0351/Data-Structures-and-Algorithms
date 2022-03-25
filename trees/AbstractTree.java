package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import queues.LinkedQueue;

public abstract class AbstractTree<E> implements Tree<E> {

	/**
	 * This class adapts the iterations produced by positions() to return elements.
	 */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = positions().iterator();

		public boolean hasNext() {
			return posIterator.hasNext();
		}

		public E next() {
			return posIterator.next().getElement();
		}

		public void remove() {
			posIterator.remove();
		}
	}

	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	public Iterable<Position<E>> positions() {
		return preorder();
	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in
	 * preorder.
	 */
	public Iterable<Position<E>> preorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()) {
			preorderSubtree(root(), snapshot);
		}
		return snapshot;
	}

	/**
	 * Adds positions of the subtree rooted at Position p to the given snapshot.
	 */
	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		snapshot.add(p);
		for (Position<E> c : children(p)) {
			preorderSubtree(c, snapshot);
		}
	}

	/**
	 * Returns an iterable collection of positions of the tree, reported in
	 * postorder.
	 */
	public Iterable<Position<E>> postorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()) {
			postorderSubtree(root(), snapshot);
		}
		return snapshot;
	}

	/**
	 * Adds positions of the subtree rooted at Position p to the given snapshot.
	 */
	private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		for (Position<E> c : children(p)) {
			postorderSubtree(c, snapshot);
		}
		snapshot.add(p);
	}

	/**
	 * Returns an iterable collection of positions of the tree in breadth-first
	 * order.
	 */
	public Iterable<Position<E>> breadthfirst() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty()) {
			LinkedQueue<Position<E>> fringe = new LinkedQueue<>();
			fringe.enqueue(root());
			while (!fringe.isEmpty()) {
				Position<E> p = fringe.dequeue();
				snapshot.add(p);
				for (Position<E> c : children(p)) {
					fringe.enqueue(c);
				}
			}
		}
		return snapshot;
	}

	public boolean isInternal(Position<E> p) {
		return numChildren(p) > 0;
	}

	public boolean isExternal(Position<E> p) {
		return numChildren(p) == 0;
	}

	public boolean isRoot(Position<E> p) {
		return p == root();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int depth(Position<E> p) {
		if (isRoot(p)) {
			return 0;
		} else {
			return 1 + depth(parent(p));
		}
	}

	/**
	 * Returns the height of the tree. Runs in O(n^2) time.
	 */
	private int heightBad() {
		int height = 0;
		for (Position<E> p : positions()) {
			if (isExternal(p)) {
				height = Math.max(height, depth(p));
			}
		}
		return height;
	}

	/**
	 * Returns the height of the subtree rooted at Position p. Runs in O(n) time.
	 */
	public int height(Position<E> p) { // pass the root as p
		int height = 0; // if p is external
		for (Position<E> child : children(p)) {
			height = Math.max(height, 1 + height(child));
		}
		return height;
	}

}