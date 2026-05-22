import java.util.Random;

public class Experiment {
    private long lastBfsTime;
    private long lastDfsTime;

    public void runTraversals(Graph g) {

        System.out.print("BFS Traversal: ");
        long startBFS = System.nanoTime();
        g.bfs(0);
        long endBFS = System.nanoTime();
        lastBfsTime = endBFS - startBFS;

        System.out.print("DFS Traversal: ");
        long startDFS = System.nanoTime();
        g.dfs(0);
        long endDFS = System.nanoTime();
        lastDfsTime = endDFS - startDFS;
    }

    public void printResults() {
        System.out.println("--- Execution Times ---");
        System.out.println("BFS Time: " + lastBfsTime + " ns");
        System.out.println("DFS Time: " + lastDfsTime + " ns");
        System.out.println();
    }

    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};

        for (int size : sizes) {
            System.out.println("=========================================");
            System.out.println("Testing Graph with " + size + " vertices");
            System.out.println("=========================================");

            Graph g = generateRandomGraph(size);

            if (size == 10) {
                System.out.println("--- Graph Adjacency List (Small Graph) ---");
                g.printGraph();
                System.out.println();
            }

            runTraversals(g);
            printResults();
        }
    }

    private Graph generateRandomGraph(int size) {
        Graph g = new Graph();
        Random rand = new Random();


        for (int i = 0; i < size; i++) {
            g.addVertex(new Vertex(i));
        }


        for (int i = 0; i < size; i++) {
            int edgesToCreate = rand.nextInt(3) + 1; // 1 to 3 edges per node
            for(int j = 0; j < edgesToCreate; j++) {
                int to = rand.nextInt(size);
                if (i != to) {
                    g.addEdge(i, to);
                }
            }
        }
        return g;
    }
    public void runDijkstraBonusTest() {
        System.out.println("=========================================");
        System.out.println("Testing Dijkstra's Algorithm (Bonus Task)");
        System.out.println("=========================================");
        Graph g = new Graph();
        for (int i = 0; i < 5; i++) g.addVertex(new Vertex(i));


        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 1);
        g.addEdge(2, 1, 2);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 5);
        g.addEdge(3, 4, 3);

        g.dijkstra(0);
        System.out.println();
    }
}