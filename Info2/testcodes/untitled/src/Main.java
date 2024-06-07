public class Main {
    public static void main(String[] args) {
        // Create an instance of GraphAL with 4 nodes
        Graph graph = new GraphAL(4);

        // Test methods
        System.out.println("Node count: " + graph.getNodeCount());
        System.out.println("Edge count: " + graph.getEdgeCount());

        // Add some edges
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Node count after adding edges: " + graph.getNodeCount());
        System.out.println("Edge count after adding edges: " + graph.getEdgeCount());

        // Check if edge exists
        System.out.println("(1, 2) edge exists: " + graph.containsEdge(1, 2));
        System.out.println("(2, 1) edge exists: " + graph.containsEdge(2, 1));

        // Get adjacency list
        System.out.println("Adjacency list for node 1: " + graph.getList(1));

        // Remove an edge
        graph.removeEdge(1, 2);
        System.out.println("Edge exists: " + graph.containsEdge(1, 2));
    }
}
