package maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import priority_queues.Entry;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {

	private class EntryIterator implements Iterator<Entry<K, V>> {
		private int i = 0;

		public boolean hasNext() {
			return i < table.size();
		}

		public Entry<K, V> next() {
			if (i == table.size()) {
				throw new NoSuchElementException();
			}
			return table.get(i++);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class EntryIterable implements Iterable<Entry<K, V>> {
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	}

	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}

	private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

	public UnsortedTableMap() {
	}

	/**
	 * Returns the index of an entry with an equal key, or -1 if none is found.
	 * 
	 * @param key
	 * @return
	 */
	private int findIndex(K key) {
		int n = table.size();
		for (int i = 0; i < n; i++) {
			if (table.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	public int size() {
		return table.size();
	}

	public V get(K key) {
		int i = findIndex(key);
		if (i == -1) {
			return null;
		}
		return table.get(i).getValue();
	}

	public V put(K key, V value) {
		int i = findIndex(key);
		if (i == -1) {
			table.add(new MapEntry<>(key, value));
			return null;
		} else {
			return table.get(i).setValue(value);
		}
	}

	public V remove(K key) {
		int i = findIndex(key);
		int n = size();
		if (i == -1) { // key not found
			return null;
		}
		V toBeRemoved = table.get(i).getValue();
		if (i != n - 1) {
			table.set(i, table.get(n - 1)); // relocate last entry to the hole created by removal
		}
		table.remove(n - 1); // remove last entry of table
		return toBeRemoved;
	}

	public static void main(String[] args) {
		UnsortedTableMap<String, Integer> map = new UnsortedTableMap<>();
		map.put("Bella", 2);
		map.put("Chloe", 3);
		map.put("Tank", 11);

		System.out.println(map.containsKey("Chloe"));
	}

}
