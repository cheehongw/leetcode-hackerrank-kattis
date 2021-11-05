import java.util.PriorityQueue;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class AssigningWorkstations {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int N = fio.nextInt();
        int m = fio.nextInt();

        PriorityQueue<int[]> pq = new PriorityQueue<>(N, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < N; i++) {
            int[] pair = new int[2];
            pair[0] = fio.nextInt();
            pair[1] = fio.nextInt();
            pq.add(pair);
        }
        
        PriorityQueue<Integer> inUseUntil = new PriorityQueue<>();
        int timeElapsed = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            int[] nextResearcher = pq.poll();
            
            //update timeElapsed
            timeElapsed = nextResearcher[0];

            //remove all expired CPUs
            while (!inUseUntil.isEmpty() && inUseUntil.peek() + m < timeElapsed) {
                inUseUntil.poll();
            }

            //check if there is free CPUs
            if (!inUseUntil.isEmpty() && inUseUntil.peek() <= timeElapsed) {
                inUseUntil.poll();
                count = count + 1;
                inUseUntil.add(timeElapsed + nextResearcher[1]);
            } else {
                inUseUntil.add(timeElapsed + nextResearcher[1]);
            }

        }

        System.out.println(count);
        fio.close();
    }
}