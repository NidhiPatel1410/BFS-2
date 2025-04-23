// DFS
// In this problem, using graph coloring technique to identify whether it is bipartite or not. Using DFS to color the graph such that
// every adjacent node should get a different color. If in between found that adjacent node is having same color, then returning
// false. Else, true.

// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    // Colors arrays will hold the color of each node
    int[] colors;
    int n;
    boolean isBipartite;

    public boolean isBipartite(int[][] graph) {
        // Base case
        if (graph == null || graph.length == 0) {
            return false;
        }
        n = graph.length;
        colors = new int[n];
        // Initially fill the colors array with -1
        Arrays.fill(colors, -1);
        isBipartite = true;
        // Loop over the graph nodes
        for (int i = 0; i < n; i++) {
            // Check if it is not colored
            if (colors[i] == -1) {
                // Assign a color to it
                colors[i] = 0;
                // And jump to it's neighbors i.e. call dfs
                dfs(graph, i);
                // If anywhere in between, isBipartite is already false, return
                if (!isBipartite) {
                    return false;
                }
            }
        }
        // Else return whatever is in isBipartite
        return isBipartite;

    }

    private void dfs(int[][] graph, int u) {
        // Base case
        if (isBipartite == false) {
            return;
        }
        // Go over the neighbours
        for (int j = 0; j < graph[u].length; j++) {
            // Take one neighbour at a time
            int v = graph[u][j];
            // Check if it is not colored
            if (colors[v] == -1) {
                // Give to it the color that is opposite of the parent
                colors[v] = colors[u] == 0 ? 1 : 0;
                // Then call dfs on it, that is jump to it's neighbours
                dfs(graph, v);
            } else {
                // Else if it is already colored than check if it having same color as the
                // parent
                if (colors[u] == colors[v]) {
                    // Make the isBipartite to false
                    isBipartite = false;
                    // Break
                    break;
                }
            }
        }
    }
}

// BFS
// In this problem, using graph coloring technique to identify whether it is
// bipartite or not. Using BFS to color the graph such that
// every adjacent node should get a different color. If in between found that
// adjacent node is having same color, then returning
// false. Else, true.

// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public boolean isBipartite(int[][] graph) {
        // Base case
        if (graph == null || graph.length == 0) {
            return false;
        }
        int n = graph.length;
        // Take the queue for the BFS
        Queue<Integer> q = new LinkedList<>();
        // Take colors array
        int[] colors = new int[n];
        // Fill initially with -1
        Arrays.fill(colors, -1);
        // Go over nodes in graph
        for (int i = 0; i < n; i++) {
            // // Check if it is not colored
            if (colors[i] == -1) {
                // Assign a color to it
                colors[i] = 0;
                // And add to queue
                q.add(i);
                // Start bfs
                while (!q.isEmpty()) {
                    // Poll current
                    int curr = q.poll();
                    // Go over it's neighbours and perform same check as we did in dfs
                    for (int j = 0; j < graph[curr].length; j++) {
                        int v = graph[curr][j];
                        if (colors[v] == -1) {
                            colors[v] = colors[curr] == 0 ? 1 : 0;
                            q.add(v);
                        } else {
                            if (colors[v] == colors[curr]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}