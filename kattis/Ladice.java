/**
 * @author Wong Chee Hong (A0217558W)
 */
public class Ladice {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        StringBuilder sb = new StringBuilder();
        int N = fio.nextInt();
        int L = fio.nextInt();
        UFDS drawers = new UFDS(L + 1);

        for (int i = 0; i < N; i++) {
            int A = fio.nextInt();
            int B = fio.nextInt();

            drawers.unionSet(A, B);
            int size = drawers.representativeValue(A);
            if (size != 0) {
                drawers.setRepresentativeValue(A, size + 1);
                sb.append("LADICA").append("\n");
            } else {
                sb.append("SMECE").append("\n");
            }

        }

        System.out.println(sb);

        fio.close();
    }
}

class UFDS {
    int[] p;
    int[] rank;

    UFDS(int N) {
        this.p = new int[N];
        this.rank = new int[N];

        for (int i = 0; i < N; i++) {
            p[i] = -1;
            rank[i] = 0;
        }
    }

    /**
     * Returns the value that represents this set.
     * @param x the items that belongs in the set
     * @return A negative value.
     */
    public int representativeValue(int x) {
        return p[findSet(x)];
    }

    public void setRepresentativeValue(int x, int value) {
        p[findSet(x)] = value;
    }

    /**
     * Finds the representative element of the set.
     * @param UFDS The UFDS we are working with
     * @param x The element of which we want to find the set it belongs to. x is assumed to be in the UFDS.
     * @return the index of the representative element
     */
    public int findSet(int x) {

        if (p[x] <= 0) {
            return x;
        } else {
            return findSet(p[x]);
        }
    }

    public void unionSet(int x, int y) {
        int i = findSet(x);
        int j = findSet(y);
        if (i != j) {
            if (rank[i] > rank[j]) {
                p[i] += p[j];
                p[j] = i;
            } else {
                p[j] += p[i];
                p[i] = j;
                if (rank[i] == rank[j]) {
                    rank[j] = rank[j] + 1;
                }
            } 
             

        }
    }

}