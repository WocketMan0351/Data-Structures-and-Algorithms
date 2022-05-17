package assignments.graph;

import java.util.Scanner;

public class GraphProject {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number of vertices: (test data) ");
//		int numVertices = in.nextInt();
		int numVertices = 16;
		System.out.print("\nEnter edge sequence: (test data)");
//		String[] edgeSequence = in.next().split(";");
		String[] edgeSequence = "0,1;0,4;0,5;1,0;1,2;1,5;2,1;2,3;2,6;3,2;3,6;3,7;4,0;4,5;4,8;5,0;5,1;5,4;5,8;6,2;6,3;6,9;6,10;6,11;7,3;7,11;8,4;8,5;8,9;8,12;8,13;9,6;9,8;9,10;10,6;10,9;10,13;10,14;11,6;11,7;11,15;12,8;12,13;13,8;13,10;13,12;14,10;15,11;"
				.split(";");
		System.out.println();
		System.out.println();
		in.close();

		Graph graph = new Graph(numVertices);

		for (String edge : edgeSequence) {
			String[] e = edge.split(",");
			graph.addEdge(Integer.parseInt(e[0]), Integer.parseInt(e[1]));
		}

		System.out.print("Graph nodes visited in BFS order: ");
		graph.breadthFirstSearch();
		System.out.println();

		System.out.println();
		graph.print();
	}

}
