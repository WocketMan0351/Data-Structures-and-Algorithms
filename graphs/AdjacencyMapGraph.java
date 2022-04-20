package graphs;

import java.util.HashSet;
import java.util.Set;

import hash_tables.ProbeHashMap;
import heaps.HeapAdaptablePriorityQueue;
import lists.LinkedPositionalList;
import lists.Position;
import lists.PositionalList;
import maps.Map;
import priority_queues.AdaptablePriorityQueue;
import priority_queues.Entry;

/**
 * An implementation for a graph structure using an adjacency map for each
 * vertex.
 */
public class AdjacencyMapGraph<V, E> implements Graph<V, E> {

	private boolean isDirected;
	private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
	private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();

	/**
	 * Constructs and empty graph. The parameter determines whether this is an
	 * undirected or directed graph.
	 */
	public AdjacencyMapGraph(boolean directed) {
		isDirected = directed;
	}

	public int numVertices() {
		return vertices.size();
	}

	public Iterable<Vertex<V>> vertices() {
		return (Iterable<Vertex<V>>) vertices;
	}

	public int numEdges() {
		return edges.size();
	}

	public Iterable<Edge<E>> edges() {
		return (Iterable<Edge<E>>) edges;
	}

	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOutgoing().size();
	}

	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOutgoing().values(); // edges are the values in the adjacency map
	}

	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getIncoming().size();
	}

	public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		return vert.getOutgoing().values(); // edges are the values in the adjacency map
	}

	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> origin = validate(u);
		return origin.getOutgoing().get(v); // will be null if no edge from u to v
	}

	public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		return edge.getEndpoints();
	}

	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if (endpoints[0] == v) {
			return endpoints[1];
		} else if (endpoints[1] == v) {
			return endpoints[0];
		} else {
			throw new IllegalArgumentException("v is not incident to this edge");
		}
	}

	public Vertex<V> insertVertex(V element) {
		InnerVertex<V> v = new InnerVertex<>(element, isDirected);
		v.setPosition(vertices.addLast(v));
		return v;
	}

	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
		if (getEdge(u, v) == null) {
			InnerEdge<E> e = new InnerEdge<>(u, v, element);
			e.setPosition(edges.addLast(e));
			InnerVertex<V> origin = validate(u);
			InnerVertex<V> dest = validate(v);
			origin.getOutgoing().put(v, e);
			dest.getIncoming().put(u, e);
			return e;
		} else {
			throw new IllegalArgumentException("Edge from u to v already exists");
		}
	}

	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);
		// remove all incident edges from the graph
		for (Edge<E> e : vert.getOutgoing().values()) {
			removeEdge(e);
		}
		for (Edge<E> e : vert.getIncoming().values()) {
			removeEdge(e);
		}
		// remove this vertex from the list of vertices
		vertices.remove(vert.getPosition());
		vert.setPosition(null); // invalidates the vertex
	}

	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		// remove this edge from vertices' adjacencies
		InnerVertex<V>[] verts = (InnerVertex<V>[]) edge.getEndpoints();
		verts[0].getOutgoing().remove(verts[1]);
		verts[1].getIncoming().remove(verts[0]);
		// remove this edge from the list of edges
		edges.remove(edge.getPosition());
		edge.setPosition(null); // invalidates the edge
	}

	private InnerVertex<V> validate(Vertex<V> v) {
		if (!(v instanceof InnerVertex)) {
			throw new IllegalArgumentException("Invalid vertex");
		}
		InnerVertex<V> vert = (InnerVertex<V>) v; // safe cast
		if (!vert.validate(this)) {
			throw new IllegalArgumentException("Invalid vertex");
		}
		return vert;
	}

	private InnerEdge<E> validate(Edge<E> e) {
		if (!(e instanceof InnerEdge)) {
			throw new IllegalArgumentException("Invalid edge");
		}
		InnerEdge<E> edge = (InnerEdge<E>) e; // safe cast
		if (!edge.validate(this)) {
			throw new IllegalArgumentException("Invalid edge");
		}
		return edge;
	}

	/**
	 * Performs depth-first search of Graph g starting at Vertex u. Plunges
	 * depth-first into g without regard for which edge it takes until it cannot go
	 * any further, at which point it backtracks and continues.
	 * 
	 * Runs in O(n + m) time for n vertices reachable from u and m incident edges to
	 * those vertices.
	 */
	public static <V, E> void depthFirstSearch(Graph<V, E> g, Vertex<V> u, Set<Vertex<V>> known,
			Map<Vertex<V>, Edge<E>> forest) {
		known.add(u); // u has been discovered
		for (Edge<E> e : g.outgoingEdges(u)) { // for every outgoing edge from u
			Vertex<V> v = g.opposite(u, e);
			if (!known.contains(v)) {
				forest.put(v, e); // e is the tree edge that discovered v
				depthFirstSearch(g, v, known, forest); // recursively explore from v
			}
		}
	}

	/**
	 * Returns an unordered list of edges comprising the directed path from Vertex u
	 * to Vertex v. If v is unreachable from u, or if u equals v, an empty path is
	 * returned. Runs in O(n) time.
	 * 
	 * @param forest must be a map resulting from a previous call to
	 *               depthFirstSearch started at u.
	 * 
	 */
	public static <V, E> PositionalList<Edge<E>> constructPath(Graph<V, E> g, Vertex<V> u, Vertex<V> v,
			Map<Vertex<V>, Edge<E>> forest) {
		PositionalList<Edge<E>> path = new LinkedPositionalList<>();
		if (forest.get(v) != null) { // v was discovered during the search
			Vertex<V> walk = v; // we construct the path from back to front
			while (walk != u) {
				Edge<E> edge = forest.get(walk);
				path.addFirst(edge); // add edge to the front of the path
				walk = g.opposite(walk, edge); // repeat with opposite endpoint
			}
		}
		return path;
	}

	/**
	 * Performs a depthFirstSearch for the entire graph and returns the
	 * depthFirstSearch forest as a map.
	 */
	public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearchComplete(Graph<V, E> g) {
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Edge<E>> forest = new ProbeHashMap<>();
		for (Vertex<V> u : g.vertices()) {
			if (!known.contains(u)) {
				depthFirstSearch(g, u, known, forest); // (re)start the depthSearchFirst process
			}
		}
		return forest;
	}

	/**
	 * Performs a bread-first search of a Graph g starting at Vertex s.
	 */
	public static <V, E> void breadthFirstSearch(Graph<V, E> g, Vertex<V> s, Set<Vertex<V>> known,
			Map<Vertex<V>, Edge<E>> forest) {
		PositionalList<Vertex<V>> level = new LinkedPositionalList<>();
		known.add(s);
		level.addLast(s); // first level includes only u
		while (!level.isEmpty()) {
			PositionalList<Vertex<V>> nextLevel = new LinkedPositionalList<>();
			for (Vertex<V> u : level) {
				for (Edge<E> e : g.outgoingEdges(u)) {
					Vertex<V> v = g.opposite(u, e);
					if (!known.contains(v)) {
						known.add(v);
						forest.put(v, e); // e is the tree edge that discovered u
						nextLevel.addLast(v); // v will be further considered in next pass
					}
				}
			}
			level = nextLevel; // relabel 'next' level to become the current
		}
	}

	/**
	 * Computes shortest path distances from src vertex to all reachable vertices of
	 * g. Assumes g is connected, g's edges are undirected, and g's edge weights are
	 * non-negative.
	 */
	public static <V> Map<Vertex<V>, Integer> shortestPathLengths(Graph<V, Integer> g, Vertex<V> src) {
		// d.get(v) is upper bound on distance from src to v
		Map<Vertex<V>, Integer> d = new ProbeHashMap<>();
		// map reachable v to its d value
		Map<Vertex<V>, Integer> cloud = new ProbeHashMap<>();
		// priority queue pq will have vertices as elements, with d.get(v) as key
		AdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();
		// maps from vertex to its pq locator
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens = new ProbeHashMap<>();

		// for each vertex v of the graph, add an entry to the priority queue, with the
		// source having distance 0 and all others having infinite distance
		for (Vertex<V> v : g.vertices()) {
			if (v == src) {
				d.put(v, 0);
			} else {
				d.put(v, Integer.MAX_VALUE);
			}
			pqTokens.put(v, pq.insert(d.get(v), v)); // save entry for future updates
		}

		// now begin adding reachable vertices to the cloud
		while (!pq.isEmpty()) {
			Entry<Integer, Vertex<V>> entry = pq.removeMin();
			int key = entry.getKey();
			Vertex<V> u = entry.getValue();
			cloud.put(u, key); // this is actual distance to u
			pqTokens.remove(u); // u is no longer in pq
			for (Edge<Integer> e : g.outgoingEdges(u)) {
				Vertex<V> v = g.opposite(u, e);
				if (cloud.get(v) == null) {
					// perform relaxation step on edge (u, v)
					int wgt = e.getElement();
					if (d.get(u) + wgt < d.get(v)) { // is there a shorter path to v?
						d.put(v, d.get(u) + wgt); // update the distance
						pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
					}
				}
			}
		}
		return cloud; // this includes all reachable vertices
	}

	/**
	 * Reconstructs a shortest path tree rooted at vertex s, given distance map d.
	 * The tree is represented as a map from each reachable verex v (other than s)
	 * to the edge e = (u, v) that is used to raech v from its parent u in the tree.
	 */
	public static <V> Map<Vertex<V>, Edge<Integer>> shortestPathTree(Graph<V, Integer> g, Vertex<V> s,
			Map<Vertex<V>, Integer> d) {
		Map<Vertex<V>, Edge<Integer>> tree = new ProbeHashMap<>();
		for (Vertex<V> v : d.keySet()) {
			if (v != s) {
				for (Edge<Integer> e : g.incomingEdges(v)) { // consider INCOMING edges
					Vertex<V> u = g.opposite(v, e);
					int wgt = e.getElement();
					if (d.get(v) == d.get(u) + wgt) {
						tree.put(v, e); // edge is used to reach v
					}
				}
			}
		}
		return tree;
	}

	/**
	 * A vertex of an adjacency map graph representation.
	 */
	private class InnerVertex<V> implements Vertex<V> {

		private V element;
		private Position<Vertex<V>> pos;
		private Map<Vertex<V>, Edge<E>> outgoing;
		private Map<Vertex<V>, Edge<E>> incoming;

		/**
		 * Constructs a new InnerVertex instance, storing the given element.
		 */
		public InnerVertex(V element, boolean graphIsDirected) {
			this.element = element;
			outgoing = new ProbeHashMap<>();
			if (graphIsDirected) {
				incoming = new ProbeHashMap<>();
			} else {
				incoming = outgoing;
			}
		}

		public V getElement() {
			return element;
		}

		/**
		 * Stores the position of this vertex within the graph's vertex list.
		 */
		public void setPosition(Position<Vertex<V>> p) {
			pos = p;
		}

		/**
		 * Returns the position of this vertex within the graph's vertex list.
		 */
		public Position<Vertex<V>> getPosition() {
			return pos;
		}

		/**
		 * Returns reference to the underlying map of outgoing edges.
		 */
		public Map<Vertex<V>, Edge<E>> getOutgoing() {
			return outgoing;
		}

		/**
		 * Returns reference to the underlying map of incoming edges.
		 */
		public Map<Vertex<V>, Edge<E>> getIncoming() {
			return incoming;
		}

		/**
		 * Validates that this vertex instance belongs to the given graph.
		 */
		public boolean validate(Graph<V, E> graph) {
			return (AdjacencyMapGraph.this == graph && pos != null);
		}

	}

	/**
	 * An edge between two vertices.
	 */
	private class InnerEdge<E> implements Edge<E> {

		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;

		/**
		 * Constructs a new InnerEdge instance from u to v, storing the given element.
		 * 
		 * @param u
		 * @param v
		 * @param element
		 */
		public InnerEdge(Vertex<V> u, Vertex<V> v, E element) {
			this.element = element;
			endpoints = (Vertex<V>[]) new Vertex[] { u, v };
		}

		public E getElement() {
			return element;
		}

		/**
		 * Returns reference to the endpoint array.
		 */
		public Vertex<V>[] getEndpoints() {
			return endpoints;
		}

		/**
		 * Stores the position of this edge within the graph's vertex list.
		 */
		public void setPosition(Position<Edge<E>> p) {
			pos = p;
		}

		/**
		 * Returns the position of this edge within the graph's vertex list.
		 */
		public Position<Edge<E>> getPosition() {
			return pos;
		}

		/**
		 * Validates that this edge instance belongs to the given graph.
		 */
		public boolean validate(Graph<V, E> graph) {
			return (AdjacencyMapGraph.this == graph && pos != null);
		}

	}
}
