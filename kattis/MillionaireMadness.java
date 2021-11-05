import java.util.*;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class MillionaireMadness {
    static int[][] coinMap;
    static int length;
    static int width;
    static int[][] MOVES = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        length = fio.nextInt();
        width = fio.nextInt();
        coinMap = new int[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                coinMap[i][j] = fio.nextInt();
            }
        }

        PriorityQueue<Triple> priorityQueue = new PriorityQueue<>();
        HashMap<Pair, Pair> taken = new HashMap<>();

        priorityQueue.offer(new Triple(new Pair(0,0), new Pair(0,0), 0));

        while (!priorityQueue.isEmpty()) {
            Triple nextEdge = priorityQueue.poll();
            Pair currentVert = nextEdge.q;
            //System.out.printf("%d, %d \n", currentVert.x, currentVert.y);

            if (currentVert.equals(new Pair(length - 1, width - 1))) {
                taken.put(currentVert, nextEdge.p);
                break;
            }

            if (!taken.containsKey(currentVert)) {
                taken.put(currentVert, nextEdge.p);

                for (int[] move : MOVES) {
                    int newX = currentVert.x + move[0];
                    int newY = currentVert.y + move[1];

                    if (newX >= 0 && newX < length && newY >= 0 && newY < width) {
                        Pair nextVert = new Pair(newX, newY);
                        if (!taken.containsKey(nextVert)) { //if taken, then this new edge will cause a cycle
                            int weight = Math.max(0, coinMap[newX][newY] - coinMap[currentVert.x][currentVert.y]);
                            priorityQueue.offer(new Triple(currentVert, nextVert, weight));
                        }
                    }

                }
            }

        }


        System.out.println(findMaxWeightFromOrigin(taken, new Pair(length - 1, width - 1), 0));

        fio.close();
    }

    static int findMaxWeightFromOrigin(HashMap<Pair, Pair> path, Pair target, int currMaxWeight) {

        Pair prev = path.get(target);

        if (prev == target) {
            return currMaxWeight;
        } else {
            //System.out.printf("%d, %d \n", prev.x, prev.y);
            int weight = coinMap[target.x][target.y] - coinMap[prev.x][prev.y];
            
            int newWeight = Math.max(currMaxWeight, weight);
            return findMaxWeightFromOrigin(path, prev, newWeight);
        }
    }

}

class Pair {
    int x,y;

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
        return Arrays.hashCode(new int[]{x, y});
    }
}

class Triple implements Comparable<Triple> {
    Pair p, q;
    int w;

    public Triple(Pair p, Pair q, int w) {
        this.p = p;
        this.q = q;
        this.w = w;
    }

    public int compareTo(Triple other) {

        return this.w - other.w;
    }

}
