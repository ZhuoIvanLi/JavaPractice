package Blind75.Graph;

/**
 * Dijkstra’s shortest path algorithm:
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        DijkstraAlgorithm t = new DijkstraAlgorithm();
        t.dijkstra(graph, 0);
    }

    public static void dijkstra(int[][] g, int src) {
        int n = g.length;
        int[] dis = new int[n];
        boolean[] isVisited = new boolean[n];

        // Initialize distance and visited boolean
        for (int i = 0; i < n; i++) {
            dis[i] = Integer.MAX_VALUE;
            isVisited[i] = false;
        }

        dis[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            // Find the min distance point.
            int row = findMinIndex(dis, isVisited);
            isVisited[row] = true;

            // Setup minimum distance point's adjacent points
            for (int column = 0; column < n; column++) {
                if (!isVisited[column] && g[row][column] != 0 && dis[column] > dis[row] + g[row][column]) {
                    dis[column] = dis[row] + g[row][column];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(dis[i]);
        }
    }

    public static int findMinIndex(int[] dis, boolean[] boo) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < dis.length; i++){
            if (min > dis[i] && !boo[i]) {
                min = dis[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

}
