# Assignment 4: Graph Traversal and Representation System

## A. Project Overview
This project implements a Graph data structure using an adjacency list. A graph consists of **Vertices** (nodes representing entities or states) and **Edges** (connections linking the vertices). This system supports undirected connections and implements two fundamental algorithms to explore the graph: **Breadth-First Search (BFS)** and **Depth-First Search (DFS)**.

## B. Class Descriptions
* **Vertex:** Represents a single node in the graph, containing a unique integer `id`.
* **Edge:** Represents a directed or undirected connection between a `source` vertex and a `destination` vertex.
* **Graph:** Manages the graph structure using an adjacency list (`Map<Integer, List<Edge>>`). This approach maps a vertex ID to a list of its outgoing edges, which is highly space-efficient for sparse graphs.
* **Experiment:** A utility class that automatically generates graphs of varying sizes (10, 30, 100 vertices), executes the traversals, and tracks execution time using `System.nanoTime()`.
* **Main:** The entry point of the program.

## C. Algorithm Descriptions

### Breadth-First Search (BFS)
* **Step-by-step:** BFS starts at the root node, explores all of its immediate neighbors, and pushes them into a `Queue`. It then dequeues the next node, explores its unvisited neighbors, and repeats this process level-by-level until all connected nodes are visited.
* **Use cases:** Finding the shortest path in unweighted graphs, peer-to-peer networks, GPS navigation.
* **Time complexity:** O(V + E), where V is the number of vertices and E is the number of edges.

### Depth-First Search (DFS)
* **Step-by-step:** DFS starts at the root and dives as deep as possible along a single branch before backtracking. It uses recursion (which relies on the system Call Stack) to track the path and mark nodes as visited.
* **Use cases:** Solving mazes, topological sorting, detecting cycles in a graph.
* **Time complexity:** O(V + E).

## D. Experimental Results

| Graph Size | BFS Execution Time (ns) | DFS Execution Time (ns) |
|------------|-------------------------|-------------------------|
| 10         | 1,401,800               | 495,000                 |
| 30         | 1,389,400               | 992,400                 |
| 100        | 3,705,700               | 1,683,200               |

**Analysis:**
* **How does graph size affect performance?** As expected, larger graphs take more time. The jump from 30 to 100 vertices caused a massive spike in execution time for both algorithms.
* **Which traversal is faster?** In my specific tests, DFS was consistently faster than BFS across all graph sizes. This is likely because recursive method calls (DFS) have slightly less overhead than creating and managing a `LinkedList` Queue (BFS) in Java.
* **Do results match O(V + E)?** Yes. The overall execution time scales up in a linear fashion as we add more vertices and connections to the network.
* **How does structure affect order?** BFS explores all immediate neighbors first, which is why the traversal output shows nodes clustered around the start node. DFS dives straight down one path until it hits a dead end, which scatters the visited nodes differently.
* **When is BFS preferred?** When you need to find the shortest path in an unweighted graph, or if you know the target is close to the starting point.
* **What are the limitations of DFS?** It doesn't guarantee the shortest path. Also, on massive graphs, deep recursion could cause a `StackOverflowError` due to system memory limits.

## E. Screenshots

docs/screenshots

## F. Reflection
This assignment helped me understand how graphs are actually structured in code, rather than just in theory. Building the adjacency list using HashMaps was a great practical way to see how we can optimize data lookups.

The biggest difference I noticed between the two algorithms was their implementation logic: BFS relies on a data structure (Queue), while DFS relies on the system's call stack via recursion. One challenge I faced was measuring the time correctly, because simple operations execute so fast in Java that I had to strictly rely on `System.nanoTime()` instead of standard milliseconds to capture the real performance differences.

## G. Bonus Task: Dijkstra's Algorithm
**Implementation Details:**
* **Edge Weights:** The `Edge` class was modified to include a `weight` field. An overloaded constructor was added to ensure backward compatibility with unweighted BFS/DFS tests (defaulting weight to 1).
* **Graph Structure:** The `Graph` class was updated to support passing custom weights into the `addEdge` method.
* **Algorithm Logic:** Implemented `void dijkstra(int start)` using HashMaps to track distances and `visited` nodes. As permitted by the guidelines, it uses an iterative loop (without a priority queue) to find the minimum distance unvisited node, relaxing adjacent edges step-by-step. Time complexity for this simple loop approach is O(V^2).
* **Output:** The system strictly computes and cleanly prints the shortest distance from the source vertex to every other reachable vertex in the graph.