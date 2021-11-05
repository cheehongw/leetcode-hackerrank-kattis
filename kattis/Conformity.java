import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class Conformity {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int N = fio.nextInt();
        HashMap<Combination, Integer> h = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int[] combis = new int[5];
            for (int j = 0; j < 5; j++) {
                combis[j] = fio.nextInt();
            }

            Combination combos = new Combination(combis);

            Integer freq = h.get(combos);
            //System.out.println(combos);
            if (freq == null) {
                h.put(combos, 1);
            } else {
                h.put(combos, freq + 1);

            }
        }

        int maxFreq = 0;
        int count = 0;
        for (Combination c : h.keySet()) {
            if (h.get(c) > maxFreq) {
                maxFreq = h.get(c);
                count = 1;
            } else if (h.get(c) == maxFreq) {
                count++;
            }
        }
        
        System.out.println(maxFreq*count);

        fio.close();
    }
}

class Combination {
    int[] combinations;

    Combination(int... values) {
        this.combinations = values;
        Arrays.sort(this.combinations);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(combinations);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Combination) {
            boolean res = Arrays.equals(this.combinations, ((Combination) other).combinations);
            return res;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : this.combinations) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }

}