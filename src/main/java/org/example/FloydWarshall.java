package org.example;

public class FloydWarshall {
    final static int INF = 99999; // Represents infinity

    public void floydWarshall(int graph[][], int V) {
        int dist[][] = new int[V][V];
        int i, j, k;

        // Initialize the solution matrix same as input graph matrix
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Add all vertices one by one to the set of intermediate vertices.
        for (k = 0; k < V; k++) {
            System.out.println("Iteration " + (k + 1) + ": Using vertex " + k + " as intermediate.");
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from i to j, update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
            printMatrix(dist, V);
        }
    }

    void printMatrix(int dist[][], int V) {
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        /* Let us create the following weighted graph
                   10
                (0)------->(3)
                 |         /|\
               5 |          |
                 |          | 1
                \|/         |
                (1)------->(2)
                   3           */
        int graph[][] = { {0,   5,  INF, 10},
                {INF, 0,   3,  INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0} };
        int V = 4;
        FloydWarshall fw = new FloydWarshall();

        System.out.println("Original Adjacency Matrix:");
        fw.printMatrix(graph, V);

        fw.floydWarshall(graph, V);
    }
}