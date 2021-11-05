import java.util.HashMap;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class JoinStrings {
    public static void main(String[] args) {
        FastIO fio = new FastIO();

        int N = fio.nextInt();
        String[] strings = new String[N+1];
        for (int i = 1; i <= N; i++) {
            strings[i] = fio.nextLine();
        }

        int[] numbers = new int[N+1];
        HashMap<Integer, Integer> hm = new HashMap<>();
        int pointer = 1;

        for (int i = 1; i <= N - 1; i++) {
            int first = fio.nextInt();
            int second = fio.nextInt();

            int last = numbers[second] == 0 ? second : hm.get(second);

            if (numbers[first] == 0) {
                numbers[first] = second;
            } else {
                numbers[hm.get(first)] = second;
            }

            hm.put(first, last);

            if (i == N-1) {
                pointer = first;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(pointer != 0) {
            sb.append(strings[pointer]);
            pointer = numbers[pointer];
        }

        System.out.println(sb);


        fio.close();
    }
}
