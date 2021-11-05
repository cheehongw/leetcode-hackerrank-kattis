import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class KattisQuest {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int N = fio.nextInt();
        TreeSet<Quest> tree = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            String command = fio.next();
            if (command.length() == 3) {
                int e = fio.nextInt();
                int g = fio.nextInt();
                Quest q = new Quest(e, g, rand.nextInt(200001));
                while (!tree.add(q)) {
                    q = new Quest(e, g, rand.nextInt(200001));
                }
            } else { // command equals "query"
                int X = fio.nextInt();
                long output = 0;

                while (X > 0) {
                    Quest key = tree.floor(new Quest(X, 100001, 200001));
                    if (key == null) {
                        break;
                    } else {
                        output = output + key.gold;
                        X = X - key.energy;
                        tree.remove(key);
                    }

                }

                sb.append(output).append("\n");

            }
        }

        System.out.print(sb);
        fio.close();
    }
}

class Quest implements Comparable<Quest> {

    int energy;
    int gold;
    int idx;

    public Quest(int energy, int gold, int idx) {
        this.energy = energy;
        this.gold = gold;
        this.idx = idx;
    }

    @Override
    public int compareTo(Quest other) {
        if (this.energy == other.energy) {
            int x = this.gold - other.gold;
            return x == 0 ? this.idx - other.idx : x;
        } else
            return this.energy - other.energy;
    }

    @Override
    public String toString() {
        return String.format("energy: %d, gold:%d", energy, gold);
    }

}
