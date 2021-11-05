import java.util.*;

/**
 * @author Wong Chee Hong (A0217558W)
 * 
 * Approach: Count the number of SCCs with no indegree.
 * Better Approach: Run DFS on the toposort order of the graph
 */
public class Dominos {
    static HashMap<Integer, ArrayList<Integer>> adjList;
    //static HashMap<Integer, ArrayList<Integer>> transpose;
    public static void main(String[] args) {
        //long startTime = System.nanoTime();
        FastIO fio = new FastIO();
        int testcaseNum = fio.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testcaseNum; i++) { // for each test case
            int numberOfTiles = fio.nextInt();
            int numbeOfLines = fio.nextInt();

            adjList = new HashMap<>();
            //transpose = new HashMap<>();

            for (int j = 0; j < numbeOfLines; j++) { //initialise graphs
                int firstDomino = fio.nextInt();
                int secondDomino = fio.nextInt();
                ArrayList<Integer> set = adjList.get(firstDomino);
                if (set == null) {
                    set = new ArrayList<>();
                    adjList.put(firstDomino, set);
                }
                set.add(secondDomino);
            }

            boolean[] taken = new boolean[numberOfTiles + 1];
            ArrayList<Integer> toposort = new ArrayList<>(numberOfTiles + 1);
            for (Integer v : adjList.keySet()) {
                if (!taken[v]) {
                    DFS(v, taken, toposort);
                }
            }

            Collections.reverse(toposort);
            taken = new boolean[numberOfTiles + 1];

            int SCC = 0;

            for (Integer v : toposort) {
                if (!taken[v]) {
                    SCC++;
                    DFS(v, taken);
                }
            }


            sb.append(numberOfTiles - toposort.size() + SCC).append("\n");
        }

        System.out.print(sb);


        fio.close();
    }
    public static void DFS(Integer u, boolean[] taken, ArrayList<Integer> toposort) {
        taken[u] = true;

        if (adjList.get(u) != null) {
            for (Integer neighbour : adjList.get(u)) {
                if (!taken[neighbour]) {
                    DFS(neighbour, taken, toposort);
                }
            }
        }
        toposort.add(u);

    }

    public static void DFS(Integer u, boolean[] taken) {
        taken[u] = true;

        if (adjList.get(u) != null) {
            for (Integer neighbour : adjList.get(u)) {
                if (!taken[neighbour]) {
                    DFS(neighbour, taken);
                }
            }
        }
    }

}