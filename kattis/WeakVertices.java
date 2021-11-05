import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class WeakVertices {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        StringBuilder sb = new StringBuilder();
        int V = fio.nextInt();
        do {
            ArrayList<HashSet<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                HashSet<Integer> vertexList = new HashSet<>();
                for (int j = 0; j < V; j++) {
                    int edge = fio.nextInt();
                    if (edge == 1) {
                        vertexList.add(j);
                    }
                }
                adjList.add(vertexList);
            }

            for (int i = 0; i < V; i++) {
                HashSet<Integer> neighbourList = adjList.get(i);
                boolean weakFlag = true;
                for (int neighbour : neighbourList) {
                    if (!weakFlag) {
                        break;
                    }
                    HashSet<Integer> neighbourXS = adjList.get(neighbour);
                    for (int x : neighbourXS) {
                        if (neighbourList.contains(x)) {
                            weakFlag = false;
                            break;
                        }
                    }
                }

                if (weakFlag) {
                    sb.append(i).append(" ");
                }
            }

            sb.append("\n");

            V = fio.nextInt();
        } while (V > 0);
        
        System.out.print(sb);


        fio.close();
    }
}
