/**
 * https://leetcode.com/problems/network-delay-time/
 * 
 * A quick read through of the problem statement shows that they want the
 * biggest shortest-path distance from any given node k.
 *
 * Floyd-Warshall algorithm - a dynamic programming solution - is used.
 */ 
class Solution {
    
    int[][] shortestPaths;
    
    public int networkDelayTime(int[][] times, int n, int k) {
        shortestPaths = new int[n][n];        
        
        //initialize 2D array for memoization 
        //memoization is often important in dynamic programming
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                shortestPaths[i][j] = i == j ? 0 : 9000000;
            }
        }
        
        //store edge weights from given edgelist into the memo-table
        for (int[] edge : times) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            int cost = edge[2];
            
            shortestPaths[u][v] = Math.min(shortestPaths[u][v], cost);
        }
        
        /**
         * floyd-warshall algorithm
         * guiding qns for optimal sub-substructure: 
         *   - for a given starting node x, what are all the shortest paths from node i to j using x?
         *   - now for an additional node x+1, what are all the shortest paths from i to j using x and x+1?
         */
        for (int x = 0; x < n; x++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    shortestPaths[i][j] = Math.min(shortestPaths[i][j], shortestPaths[i][x] + shortestPaths[x][j]);
                    
                }       
            }
        }
        

        int curr_highest = 0;
        for (int i = 0; i < n; i++) {
            if (shortestPaths[k - 1][i] >= 9000000) {
                curr_highest = -1;
                break;
            }
            
            curr_highest = Math.max(curr_highest, shortestPaths[k - 1][i]);
        }
        
        return curr_highest;
    }
}