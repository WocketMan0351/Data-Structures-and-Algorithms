package assignments.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An implementation for an undirected graph structure using an adjacency map
 * for each vertex. For use with Integer vertices only.
 */
public class Graph {

	private LinkedList<Integer>[] arrayOfLinkedLists;
	private int vertexCount;

	public Graph(int vertexCount) {
		this.vertexCount = vertexCount;
		arrayOfLinkedLists = new LinkedList[vertexCount];

		for (int i = 0; i < vertexCount; i++) {
			arrayOfLinkedLists[i] = new LinkedList<>();
		}
	}

	/**
	 * Adds an undirected edge to the graph.
	 * 
	 * @param u int
	 * @param v int
	 */
	public void addEdge(int u, int v) {
		arrayOfLinkedLists[u].add(v); // undirected so one-way connections are fine
	}

	/**
	 * Traverses the graph breadth first. Prints each vertex when it is first
	 * visited.
	 */
	public void breadthFirstSearch() {
		breadthFirstSearch(0); // start the first list of incident edges unless specified otherwise
	}

	/**
	 * Traverses the graph breadth first. Prints each vertex when it is first
	 * visited. Begins the traversal at start.
	 * 
	 * @param start int
	 */
	public void breadthFirstSearch(int start) {
		boolean[] visited = new boolean[vertexCount]; // 1 slot for each vertex

		Queue<Integer> q = new LinkedList<>(); // we will add whatever incident edges we want to
												// check next here
		visited[start] = true; // we are currently here
		q.offer(start); // begin with queue that has 1 vertex

		while (!q.isEmpty()) {
			int visiting = q.remove(); // remove 1 vertex from queue
			System.out.print(visiting + " "); // we are currently here
			LinkedList<Integer> list = arrayOfLinkedLists[visiting]; // list corresponds to vertex
																		// we're currently at
			list.forEach((vertex) -> { // traverse the list of edges incident to the vertex
				if (!visited[vertex]) { // if we haven't checked this edge yet
					visited[vertex] = true; // now we have
					q.offer(vertex); // add all unvisited neighbors of this vertex to the queue
				}
			});
		}
	}

	/**
	 * Prints the adjacency list data structure of the graph.
	 */
	public void print() {
		for (int i = 0; i < vertexCount; i++) {
			System.out.printf("Adjacency list of vertex: %2d  Head", i);
			arrayOfLinkedLists[i].forEach(element -> System.out.print(" -> " + element));
			System.out.println();
		}
	}

}
