package search_trees;

import java.util.ArrayList;
import java.util.Comparator;

import maps.AbstractSortedMap;
import priority_queues.Entry;
import trees.LinkedBinaryTree;
import trees.Position;

/**
 * An implementation of a sorted map using a binary search tree. Worst case
 * running time for all operations is O(n) where n is the height of the current
 * tree. Average running time for all operations is O(log n). Space usage is
 * O(n) where n is the number of entries in the map.
 */
public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

	/**
	 * A specialized version of the LinkedBinaryTree class with additional mutators
	 * to support binary search tree operations, and a specialized node class that
	 * includes an auxiliary instance variable for balancing data.
	 */
	protected class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {
		// -------------- nested BSTNode class --------------
		// this extends the inherited LinkedBinaryTree.Node class
		protected static class BSTNode<E> extends Node<E> {
			int aux = 0;

			BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
				super(e, parent, leftChild, rightChild);
			}

			public int getAux() {
				return aux;
			}

			public void setAux(int value) {
				aux = value;
			}
		} // --------- end of nested BSTNode class ---------

		// positional-based methods related to aux field
		public int getAux(Position<Entry<K, V>> p) {
			return ((BSTNode<Entry<K, V>>) p).getAux();
		}

		public void setAux(Position<Entry<K, V>> p, int value) {
			((BSTNode<Entry<K, V>>) p).setAux(value);
		}

		// Override node factory function to produce a BSTNode (rather than a Node)
		@Override
		protected Node<Entry<K, V>> createNode(Entry<K, V> e, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
				Node<Entry<K, V>> right) {
			return new BSTNode<>(e, parent, left, right);
		}

		/**
		 * Relinks a parent node with its oriented child node.
		 */
		private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
			child.setParent(parent);
			if (makeLeftChild) {
				parent.setLeftChild(child);
			} else {
				parent.setRightChild(child);
			}
		}

		/**
		 * Rotates Position p above its parent. Switches between these configurations,
		 * depending on whether p is a or p is b.
		 * 
		 * <pre>
		 *          b                  a
		 *         / \                / \
		 *        a  t2             t0   b
		 *       / \                    / \
		 *      t0  t1                 t1  t2
		 * </pre>
		 * 
		 * Caller should ensure that p is not the root.
		 */
		public void rotate(Position<Entry<K, V>> p) {
			Node<Entry<K, V>> x = validate(p);
			Node<Entry<K, V>> y = x.getParent(); // we assume this exists
			Node<Entry<K, V>> z = y.getParent(); // grandparent (possibly null)
			if (z == null) {
				root = x; // x becomes root of the tree
				x.setParent(null);
			} else {
				relink(z, x, y == z.getLeftChild()); // x becomes direct child of z
			}
			// now rotate x and y, including transfer of middle subtree
			if (x == y.getLeftChild()) {
				relink(y, x.getRightChild(), true); // x's right child becomes y's left
				relink(x, y, false); // y becomes x's right child
			} else {
				relink(y, x.getLeftChild(), false); // x's left child becomes y's right
				relink(x, y, true); // y becomes left child of x
			}
		}

		/**
		 * Returns the Position that becomes the root of the restructured subtree.
		 * <p>
		 * Assumes the nodes are in one of the following configurations:
		 * 
		 * <pre>
		 *     z=a                 z=c           z=a               z=c
		 *    /  \                /  \          /  \              /  \
		 *   t0  y=b             y=b  t3       t0   y=c          y=a  t3
		 *      /  \            /  \               /  \         /  \
		 *     t1  x=c         x=a  t2            x=b  t3      t0   x=b
		 *        /  \        /  \               /  \              /  \
		 *       t2  t3      t0  t1             t1  t2            t1  t2
		 * </pre>
		 * 
		 * The subtree will be restructured so that the node with key b becomes its
		 * root.
		 * 
		 * <pre>
		 *           b
		 *         /   \
		 *       a       c
		 *      / \     / \
		 *     t0  t1  t2  t3
		 * </pre>
		 * 
		 * Caller should ensure that x has a grandparent.
		 */
		public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
			Position<Entry<K, V>> y = parent(x);
			Position<Entry<K, V>> z = parent(y);
			if ((x == right(y)) == (y == right(z))) { // matching alignments
				rotate(y); // single rotation (of y)
				return y; // y is new subtree root
			} else { // opposite alignments
				rotate(x); // double rotation (of x)
				rotate(x);
				return x; // x is new subtree root
			}
		}

	}

	protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

	/**
	 * Constructs an empty amp using the natural ordering of keys.
	 */
	public TreeMap() {
		super();
		tree.addRoot(null);
	}

	/**
	 * Constructs an empty map using the given comparator to order keys.
	 */
	public TreeMap(Comparator<K> comp) {
		super(comp);
		tree.addRoot(null);
	}

	/**
	 * Returns the number of entries in the map.
	 */
	public int size() {
		return (tree.size() - 1) / 2; // only internal nodes have entries
	}

	/**
	 * Utility used when inserting a new entry at a leaf in the tree.
	 */
	private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
		tree.set(p, entry);
		tree.addLeftChild(p, null);
		tree.addRightChild(p, null);
	}

	// Some notational shorthands for brevity (yet not efficiency)
	protected Position<Entry<K, V>> root() {
		return tree.root();
	}

	protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
		return tree.parent(p);
	}

	protected Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
		return tree.left(p);
	}

	protected Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
		return tree.right(p);
	}

	protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
		return tree.sibling(p);
	}

	protected boolean isRoot(Position<Entry<K, V>> p) {
		return tree.isRoot(p);
	}

	protected boolean isExternal(Position<Entry<K, V>> p) {
		return tree.isExternal(p);
	}

	protected boolean isInternal(Position<Entry<K, V>> p) {
		return tree.isInternal(p);
	}

	protected void set(Position<Entry<K, V>> p, Entry<K, V> e) {
		tree.set(p, e);
	}

	protected Entry<K, V> remove(Position<Entry<K, V>> p) {
		return tree.remove(p);
	}

	protected void rotate(Position<Entry<K, V>> p) {
		tree.rotate(p);
	}

	protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
		return tree.restructure(x);
	}

	/**
	 * Returns the position in p's subtree having key k (or else the terminal leaf).
	 */
	private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K k) {
		if (isExternal(p)) {
			return p; // key not found, return the final leaf.
		}
		int comp = compare(k, p.getElement());
		if (comp == 0) {
			return p; // key found, returns its position
		} else if (comp < 0) {
			return treeSearch(left(p), k); // search left subtree
		} else {
			return treeSearch(right(p), k); // search right subtree
		}
	}

	/**
	 * Returns the value associated with key k (or else null).
	 */
	public V get(K k) {
		checkKey(k);
		Position<Entry<K, V>> p = treeSearch(root(), k);
		rebalanceAccess(p); // HOOK FOR BALANCED TREE SUBCLASSES
		if (isExternal(p)) {
			return null; // unsuccessful search
		}
		return p.getElement().getValue(); // match found
	}

	/**
	 * Associates value v with key k, returning any overridden value.
	 */
	public V put(K k, V v) throws IllegalArgumentException {
		checkKey(k);
		Entry<K, V> newEntry = new MapEntry<>(k, v);
		Position<Entry<K, V>> p = treeSearch(root(), k);
		if (isExternal(p)) { // key is new
			expandExternal(p, newEntry);
			rebalanceInsert(p); // HOOK FOR BALANCED TREE SUBCLASSES
			return null;
		} else { // replacing existing key
			V old = p.getElement().getValue();
			set(p, newEntry);
			rebalanceAccess(p);
			return old;
		}
	}

	/**
	 * Removes the entry having key k (if any) and returns its associated value.
	 */
	public V remove(K k) throws IllegalArgumentException {
		checkKey(k);
		Position<Entry<K, V>> p = treeSearch(root(), k);
		if (isExternal(p)) {
			rebalanceAccess(p); // HOOK FOR BALANCED TREE SUBCLASSES
			return null;
		} else {
			V old = p.getElement().getValue();
			if (isInternal(left(p)) && isInternal(right(p))) { // both children are internal
				Position<Entry<K, V>> replacement = treeMax(left(p));
				set(p, replacement.getElement());
				p = replacement;
			} // now p has at most one child that is an internal node
			Position<Entry<K, V>> leaf = (isExternal(left(p)) ? left(p) : right(p));
			Position<Entry<K, V>> sib = sibling(leaf);
			remove(leaf);
			remove(p); // sib is promoted in p's place
			rebalanceDelete(sib); // HOOK FOR BALANCED TREE SUBCLASSES
			return old;
		}
	}

	/**
	 * Returns the position with the maximum k in subtree rooted at Position p.
	 */
	protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> walk = p;
		while (isInternal(walk)) {
			walk = right(walk);
		}
		return parent(walk); // we want the parent of the leaf
	}

	/**
	 * Returns the position with the minimal key in the subtree rooted at Position
	 * p.
	 */
	protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> walk = p;
		while (isInternal(walk)) {
			walk = left(walk);
		}
		return parent(walk); // we want the parent of the leaf
	}

	/**
	 * Returns the entry having the least key (or null if the map is empty).
	 */
	public Entry<K, V> firstEntry() {
		if (isEmpty()) {
			return null;
		}
		return treeMin(root()).getElement();
	}

	/**
	 * Returns the entry having the greatest key (or null if map is empty).
	 */
	public Entry<K, V> lastEntry() {
		if (isEmpty()) {
			return null;
		}
		return treeMax(root()).getElement();
	}

	/**
	 * Returns the entry with greatest key less than or equal to key k (if any).
	 */
	public Entry<K, V> floorEntry(K k) throws IllegalArgumentException {
		checkKey(k);
		Position<Entry<K, V>> p = treeSearch(root(), k);
		if (isInternal(p)) {
			return p.getElement();
		}
		while (!isRoot(p)) {
			if (p == right(parent(p))) {
				return parent(p).getElement(); // parent has next lesser key
			} else {
				p = parent(p);
			}
		}
		return null; // no such floor exists
	}

	/**
	 * Returns the entry with least key greater than or equal to given key (or null
	 * if no such key exists).
	 */
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
		checkKey(key); // may throw IllegalArgumentException
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if (isInternal(p)) {
			return p.getElement(); // exact match
		}
		while (!isRoot(p)) {
			if (p == left(parent(p))) {
				return parent(p).getElement(); // parent has next greater key
			} else {
				p = parent(p);
			}
		}
		return null; // no such ceiling exists
	}

	/**
	 * Returns the entry with greatest key stricly less than key k.
	 */
	public Entry<K, V> lowerEntry(K k) throws IllegalArgumentException {
		checkKey(k);
		Position<Entry<K, V>> p = treeSearch(root(), k);
		if (isInternal(p)) {
			return treeMax(left(p)).getElement(); // this is the predecessor to p
		}
		// otherwise, search failed or we found a match with no left child
		while (!isRoot(p)) {
			if (p == right(parent(p))) {
				return parent(p).getElement(); // parent has next lesser key
			} else {
				p = parent(p);
			}
		}
		return null; // no such lesser key exists
	}

	/**
	 * Returns the entry with least key strictly greater than given key (or null if
	 * no such key exists).
	 *
	 * @return entry with least key strictly greater than given (or null if no such
	 *         entry)
	 * @throws IllegalArgumentException if the key is not compatible with the map
	 */
	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
		checkKey(key); // may throw IllegalArgumentException
		Position<Entry<K, V>> p = treeSearch(root(), key);
		if (isInternal(p) && isInternal(right(p))) {
			return treeMin(right(p)).getElement(); // this is the successor to p
		}
		// otherwise, we had failed search, or match with no right child
		while (!isRoot(p)) {
			if (p == left(parent(p))) {
				return parent(p).getElement(); // parent has next lesser key
			} else {
				p = parent(p);
			}
		}
		return null; // no such greater key exists
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 */
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for (Position<Entry<K, V>> p : tree.inorder()) {
			if (isInternal(p)) {
				buffer.add(p.getElement());
			}
		}
		return buffer;
	}

	/**
	 * Returns an iterable collection of entrys with key in range [fromKey, toKey].
	 */
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		if (compare(fromKey, toKey) < 0) { // to ensure that fromKey < toKey
			subMapRecurse(fromKey, toKey, root(), buffer);
		}
		return buffer;
	}

	private void subMapRecurse(K fromKey, K toKey, Position<Entry<K, V>> p, ArrayList<Entry<K, V>> buffer) {
		if (isInternal(p)) {
			if (compare(p.getElement(), fromKey) < 0) {
				// p's key is less than fromKey, so any relevant entries are to the right
				subMapRecurse(fromKey, toKey, right(p), buffer);
			} else {
				subMapRecurse(fromKey, toKey, left(p), buffer); // first consider left subtree
				if (compare(p.getElement(), toKey) < 0) { // p is within range
					buffer.add(p.getElement()); // so add it to buffer, and consider
					subMapRecurse(fromKey, toKey, right(p), buffer); // right subtree as well
				}
			}
		}
	}

	/**
	 * HOOK FOR BALANCED TREE SUBCLASSES
	 */
	public void rebalanceAccess(Position<Entry<K, V>> p) {

	}

	/**
	 * HOOK FOR BALANCED TREE SUBCLASSES
	 */
	public void rebalanceInsert(Position<Entry<K, V>> p) {

	}

	/**
	 * HOOK FOR BALANCED TREE SUBCLASSES
	 */
	public void rebalanceDelete(Position<Entry<K, V>> p) {

	}

}
