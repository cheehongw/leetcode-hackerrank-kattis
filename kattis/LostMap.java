import java.util.*;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class LostMap {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numOfVillages = fio.nextInt();
        HashSet<Pair> roads = new HashSet<>();
        int[][] adjMatrix = new int[numOfVillages + 1][numOfVillages + 1];
        boolean[] taken = new boolean[numOfVillages + 1];

        for (int i = 1; i <= numOfVillages; i++) {
            for (int j = 1; j <= numOfVillages; j++) {
                adjMatrix[i][j] = fio.nextInt();
            }
        }

        PriorityQueue<Triple> pq = new PriorityQueue<>();
        
        //initialisation
        for (int i  = 2; i <= numOfVillages; i++) {
            pq.offer(new Triple(1, i, adjMatrix[1][i]));
        }
        taken[1] = true;

        int numOfRoadsAdded = 0;
        while(numOfRoadsAdded < numOfVillages - 1) {
            Triple nextEdge = pq.poll();
            //System.out.println(nextEdge.v);

            if (!taken[nextEdge.v]) {
                taken[nextEdge.v] = true;
                roads.add(new Pair(nextEdge.u, nextEdge.v));
                numOfRoadsAdded++;

                for (int i=1; i <= numOfVillages; i++) {
                    if (!taken[i]) {
                        pq.offer(new Triple(nextEdge.v, i, adjMatrix[nextEdge.v][i]));
                        //System.out.printf("%d %d \n", nextEdge.v, i);
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (Pair road : roads) {
            sb.append(road.toString()).append("\n");
        }

        System.out.print(sb);

        fio.close();
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;

            return this.x == otherPair.x && this.y == otherPair.y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[] { x, y });
    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }
}

class Triple implements Comparable<Triple>{
    int u, v, w;

    Triple (int u, int v, int w) {
        this.v = v;
        this.u = u;
        this.w = w;
    }

    @Override
    public int compareTo(Triple other) {
        return this.w - other.w;
    }
}