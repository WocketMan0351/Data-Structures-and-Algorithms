package priority_queues;

import java.util.Comparator;

import lists.LinkedPositionalList;
import lists.Position;
import lists.PositionalList;

/**
 * An implementation of a priority queue with a sorted list. Inserting is O(n),
 * finding/removal is O(1), sorting is O(n^2), for n elements.
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

	public SortedPriorityQueue() {
		super();
	}

	public SortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}

	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K, V> newest = new PQEntry<>(key, value);
		Position<Entry<K, V>> walk = list.last();
		while (walk != null && compare(newest, walk.getElement()) < 0) {
			walk = list.before(walk);
		}
		if (walk == null) {
			list.addFirst(newest);
		} else {
			list.addAfter(walk, newest);
		}
		return newest;
	}

	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.first().getElement();
	}

	public Entry<K, V> removeMin() {
		if (list.isEmpty()) {
			return null;
		}
		return list.remove(list.first());
	}

	public int size() {
		return list.size();
	}

}
