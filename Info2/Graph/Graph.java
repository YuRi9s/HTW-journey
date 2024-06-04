import java.util.List;

public interface Graph {
    /*
     * Gibt Anzahl der Knoten zur ¨uck.
     */
    public int getNodeCount();

    /*
     * Gibt Anzahl der Kanten zur ¨uck.
     */
    public int getEdgeCount();

    /*
     * Gibt die Adjazenzliste des Knotens u zur ¨uck.
     */
    public List<Integer> getList(int u);

    /*
     * Stellt fest , ob die Kante (u,v) existiert .
     */
    public boolean containsEdge(int u, int v);

    /*
     * F¨ugt die Kante (u,v) zum Graphen hinzu .
     */
    public void addEdge(int u, int v);

    /*
     * Entfernt die Kante (u,v) aus dem Graphen .
     */
    public void removeEdge(int u, int v);

}