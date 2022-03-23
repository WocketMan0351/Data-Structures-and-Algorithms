package priority_queues;

import java.util.Comparator;

import lists.LinkedPositionalList;
import lists.Position;
import lists.PositionalList;

/**
 * An implementation of a priority queue with an unsorted list. Insertion is
 * O(1), finding/removal is O(n), sorting is O(n^2), for n elements.
 */
public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

	public UnsortedPriorityQueue() {
		super();
	}

	public UnsortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	/**
	 * Returns the Position of an entry having minimal key.
	 */
	private Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> small = list.first();
		for (Position<Entry<K, V>> walk : ((LinkedPositionalList<Entry<K, V>>) list).positions()) {
			if (compare(walk.getElement(), small.getElement()) < 0) {
				small = walk;
			}
		}
		return small;
	}

	public Entry<K, V> insert(K k, V v) throws IllegalArgumentException {
		checkKey(k);
		Entry<K, V> newest = new PQEntry(k, v);
		list.addLast(newest);
		return newest;
	}

	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return findMin().getElement();
	}

	public Entry<K, V> removeMin() {
		if (list.isEmpty()) {
			return null;
		}
		return list.remove(findMin());
	}

	public int size() {
		return list.size();
	}

}
