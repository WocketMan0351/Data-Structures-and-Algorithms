package trees;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {
	/**
	 * Returns the Position of the root of the tree (null if empty).
	 * 
	 * @return
	 */
	Position<E> root();

	/**
	 * Returns the Position of the parent of Position p (null if p is the root).
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	Position<E> parent(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns an Iterable collection containing the children of Position p (null if
	 * none).
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns the number of children of Position p.
	 * 
	 * @param p Position<E>
	 * @return Position<E>
	 * @throws IllegalArgumentException
	 */
	int numChildren(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns true if Position p has at least one child.
	 * 
	 * @param p Position<E>
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	boolean isInternal(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns true if Position p does not have any children.
	 * 
	 * @param p Position<E>
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	boolean isExternal(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns true if Position p is the root of the tree.
	 * 
	 * @param p Position<E>
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	boolean isRoot(Position<E> p) throws IllegalArgumentException;

	/**
	 * Returns the number of position (and hence elements) that are contained in the
	 * tree.
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Returns true if the tree does not contain any positions (and thus no
	 * elements).
	 * 
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * Returns an Iterator for all elements in the tree (so that the tree itself is
	 * Iterable).
	 * 
	 * @return Iterator<E>
	 */
	Iterator<E> iterator();

	/**
	 * Returns an Iterable collection of all positions of the tree.
	 * 
	 * @return Iterable<Position<E>>
	 */
	Iterable<Position<E>> positions();
}
