import java.util.LinkedList;
import java.util.List;

public class GraphAL implements Graph {

    // Anzahl Knoten
    private int nodeCount;

    // Anzahl Kanten
    private int edgeCount;

    // Adjazenzlisten
    private LinkedList<Integer>[] lists;

    public GraphAL(int n) {
        nodeCount = n;
        lists = new LinkedList[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            lists[i] = new LinkedList<Integer>();
        }
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public List<Integer> getList(int u) {
        return lists[u];
    }

    public boolean containsEdge(int u, int v) {
        for (int i = 0; i < getNodeCount(); i++) {
            if (lists[i].contains(u)) {
                for (int node : lists[u]) {
                    if (node == v) {
                        return true;
                    }
                }
            }
        }
        return false; // Edge (u,v) not found in any adjacency list
    }



    public void addEdge(int u, int v) {
        if (!lists[u].contains(v)) {
            lists[u].add(v);
            edgeCount++;
        }
    }

    public void removeEdge(int u, int v) {
        if (lists[u].contains(v)) {
            lists[u].remove(v);
            edgeCount--;
        }
    }
}