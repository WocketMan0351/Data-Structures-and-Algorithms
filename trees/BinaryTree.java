package trees;

public interface BinaryTree<E> extends Tree<E> {
	/**
	 * Returns the Position of p's left child (null if no child exists).
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	Position<E> left(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns the Position of p's right child (null if no child exists).
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	Position<E> right(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns the Position of p's sibling (null if no sibling exists).
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}