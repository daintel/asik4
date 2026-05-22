import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        this.vertices = new HashMap<>();
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.putIfAbsent(v.getId(), v);
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        if (vertices.containsKey(from) && vertices.containsKey(to)) {
            adjacencyList.get(from).add(new Edge(from, to));
            adjacencyList.get(to).add(new Edge(to, from));
        }
    }

    public void printGraph() {
        for (int vId : adjacencyList.keySet()) {
            System.out.print("Vertex " + vId + " is connected to: ");
            for (Edge e : adjacencyList.get(vId)) {
                System.out.print(e.getDestination() + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int start) {
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (Edge edge : adjacencyList.get(current)) {
                int neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (Edge edge : adjacencyList.get(current)) {
            int neighbor = edge.getDestination();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void addEdge(int from, int to, int weight) {
        if (vertices.containsKey(from) && vertices.containsKey(to)) {
            adjacencyList.get(from).add(new Edge(from, to, weight));
            adjacencyList.get(to).add(new Edge(to, from, weight)); // неориентированный граф
        }
    }


    public void dijkstra(int start) {
        if (!vertices.containsKey(start)) return;

        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();


        for (Integer v : vertices.keySet()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(start, 0);


        for (int i = 0; i < vertices.size(); i++) {
            int u = -1;
            int minDistance = Integer.MAX_VALUE;


            for (Integer v : vertices.keySet()) {
                if (!visited.contains(v) && distances.get(v) < minDistance) {
                    minDistance = distances.get(v);
                    u = v;
                }
            }

            if (u == -1) break;
            visited.add(u);


            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.getDestination();
                if (!visited.contains(v)) {
                    int newDist = distances.get(u) + edge.getWeight();
                    if (newDist < distances.get(v)) {
                        distances.put(v, newDist);
                    }
                }
            }
        }


        System.out.println("Dijkstra's Shortest Paths from Vertex " + start + ":");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("To Vertex " + entry.getKey() + " -> Distance: " +
                    (entry.getValue() == Integer.MAX_VALUE ? "Unreachable" : entry.getValue()));
        }
    }
}