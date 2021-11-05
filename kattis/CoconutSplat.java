import java.util.*;
/**
 * @author Wong Chee Hong (A0217558W)
 */
public class CoconutSplat {
    public static void main(String[] args) {
        FastIO fio = new FastIO();

        int s = fio.nextInt();
        int n = fio.nextInt();
    
        LinkedList<int[]> game = new LinkedList<int[]>();

        //initialize game state
        for (int p = 1; p <= n; p++) {
            game.offer(new int[]{p, 0});
        }

        while (game.size() != 1) {
            int remaining = (((s % game.size()) - 1) + game.size()) % game.size();

            while (remaining != 0) {
                int[] polled = game.poll();
                game.offer(polled);
                remaining--;
            }

            int[] lastTouched = game.peek();                    //gameStates: 
            if (lastTouched[1] == 0) {                          //  0 -> folded
                lastTouched[1] = 1;                             //  1 -> fisted
                game.addFirst(new int[]{lastTouched[0], 1});    //  2 -> palm down
            } else if (lastTouched[1] == 1) {                   //  3 or more -> out of game
                lastTouched[1] = 2;
                game.poll();
                game.offer(lastTouched);
            } else if (lastTouched[1] == 2) {
                game.poll();
            }
        }

        System.out.println(game.peek()[0]);


        fio.close();



    }
}
