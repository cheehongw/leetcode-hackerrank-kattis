import java.util.*;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class HumanCannonballRun {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        Coord startPos = new Coord(fio.nextDouble(), fio.nextDouble());
        Coord targetPos = new Coord(fio.nextDouble(), fio.nextDouble());

        int n = fio.nextInt();

        //sssp dijkstra structures
        double[] distanceEstimate = new double[n+2];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Vertex[] verts = new Vertex[n+2];
        Vertex sourceVertex = new Vertex(startPos, 0);
        Vertex targetVertex = new Vertex(targetPos, n+1);

        verts[0] = sourceVertex;
        verts[n+1] = targetVertex;

        distanceEstimate[0] = 0;
        distanceEstimate[n+1] = startPos.distanceTo(targetPos)/5;
        pq.add(new Pair(n+1, distanceEstimate[n+1]));

        for (int i = 1; i <= n; i++) {
            Coord cannonCoord = new Coord(fio.nextDouble(), fio.nextDouble());
            Vertex vert = new Vertex(cannonCoord, i);
            distanceEstimate[i] = startPos.distanceTo(cannonCoord)/5;
            verts[i] = vert;
            pq.add(new Pair(i, distanceEstimate[i]));
        }

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (p.d == distanceEstimate[p.v]) {
                if (p.v == n+1) {
                    break;
                }
                //System.out.printf("%s %s \n", p.d, p.v);
                for (int i = 0; i <= n+1; i++) {
                    if (distanceEstimate[i] > distanceEstimate[p.v] + verts[p.v].edgeWeight(verts[i])) {
                        distanceEstimate[i] = distanceEstimate[p.v] + verts[p.v].edgeWeight(verts[i]);
                        pq.add(new Pair(i, distanceEstimate[i]));
                    }
                }
            }

        }

        System.out.println(distanceEstimate[n+1]);

        fio.close();
    }
}

class Pair implements Comparable<Pair> {
    int v;
    Double d;

    Pair(int v, double d) {
        this.v = v;
        this.d = d;
    }

    @Override
    public int compareTo(Pair other) {
        return this.d.compareTo(other.d);
    }


}

class Vertex {

    Coord coord;
    int index;

    public Vertex(Coord coord, int index) {
        this.coord = coord;
        this.index = index;
    }

    public double edgeWeight(Vertex other) {
        return this.coord.timeTaken(other.coord);
    }

}

class Coord {
    double x, y;

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Coord other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public double timeTaken(Coord other) {
        double distanceBetween = this.distanceTo(other);
        double timeIfWalkOnly = distanceBetween/5;
        double timeIfCannonball = Math.abs(distanceBetween - 50)/5 + 2;

        return Math.min(timeIfCannonball, timeIfWalkOnly);
    }
}
