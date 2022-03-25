package trees;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	protected static class Node<E> implements Position<E> {
		private E element;
		private Node<E> parent;
		private Node<E> leftChild;
		private Node<E> rightChild;

		public Node(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
			this.element = element;
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getLeftChild() {
			return leftChild;
		}

		public Node<E> getRightChild() {
			return rightChild;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public void setLeftChild(Node<E> leftChild) {
			this.leftChild = leftChild;
		}

		public void setRightChild(Node<E> rightChild) {
			this.rightChild = rightChild;
		}
	}

	protected Node<E> createNode(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
		return new Node<E>(element, parent, leftChild, rightChild);
	}

	protected Node<E> root = null;
	private int size = 0;

	public LinkedBinaryTree() {
	}

	public int size() {
		return size;
	}

	public Position<E> root() {
		return root;
	}

	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeftChild();
	}

	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRightChild();
	}

	/**
	 * Places element e the root of an empty tree and returns its new position.
	 * 
	 * @param element E
	 * @return Position<E>
	 * @throws IllegalStateException
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty()) {
			throw new IllegalStateException("Tree is not empty");
		}
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	/**
	 * Creates a new left child of Position p and stores element e. Returns its
	 * position.
	 * 
	 * @param p       Position<E>
	 * @param element E
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	public Position<E> addLeftChild(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeftChild() != null) {
			throw new IllegalArgumentException("p already has a left child");
		}
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeftChild(child);
		size++;
		return child;
	}

	/**
	 * Creates a new right child of Position p and stores element e. Returns its
	 * position.
	 * 
	 * @param p       Position<E>
	 * @param element E
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	public Position<E> addRightChild(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRightChild() != null) {
			throw new IllegalArgumentException("p aleady has a right child");
		}
		Node<E> child = createNode(e, parent, null, null);
		parent.setRightChild(child);
		size++;
		return child;
	}

	/**
	 * Replaces the element at Position p with element e. Returns the replaced
	 * element.
	 * 
	 * @param p       Position<E>
	 * @param element E
	 * @return element E
	 * @throws IllegalArgumentException
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	/**
	 * Attaches trees t1 and t2 as left and right substrees of external Position p
	 * 
	 * @param p  Position<E>
	 * @param t1 LinkedBinaryTree<E>
	 * @param t2 LinkedBinaryTree<E>
	 * @throws IllegalArgumentException
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree t2) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (isInternal(p)) {
			throw new IllegalArgumentException("p must be a leaf");
		}
		size += t1.size() + t2.size();
		if (!t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeftChild(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if (!t2.isEmpty()) {
			t2.root.setParent(node);
			node.setRightChild(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}

	/**
	 * Removes the node at Position p and replaces it with its child (if any)
	 * 
	 * @param p Position<E>
	 * @return element E
	 * @throws IllegalArgumentException
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (numChildren(p) == 2) {
			throw new IllegalArgumentException("p has two children");
		}
		Node<E> child = (node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild());
		if (child != null) {
			child.setParent(node.getParent());
		}
		if (node == root) {
			root = child;
		} else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeftChild()) {
				parent.setLeftChild(child);
			} else {
				parent.setRightChild(child);
			}
		}
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setLeftChild(null);
		node.setRightChild(null);
		node.setParent(node);
		return temp;
	}

	/**
	 * Validates position p and returns it as a Node
	 * 
	 * @param p Position<E>
	 * @return Node<E>
	 * @throws IllegalArgumentException
	 */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Not valid position type");
		}
		Node<E> node = (Node<E>) p;
		if (node.getParent() == node) {
			throw new IllegalArgumentException("p is no longer in the tree");
		}
		return node;
	}

}