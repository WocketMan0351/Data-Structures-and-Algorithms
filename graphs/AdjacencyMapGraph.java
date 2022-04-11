package graphs;

import hash_tables.ProbeHashMap;
import lists.LinkedPositionalList;
import lists.Position;
import lists.PositionalList;
import maps.Map;

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
