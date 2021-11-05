import java.util.*;

/**
 * @author Wong Chee Hong (A0217558W)
 */
public class CardTrading {

    public static void main(String[] args) {
        FasterIO fio = new FasterIO();
        try {
            int N = fio.nextInt();
            int T = fio.nextInt();
            int K = fio.nextInt();

            int[] deckSize = new int[T + 1];
            for (int i = 1; i <= N; i++) { // store Anthony's deck
                int idx = fio.nextInt();
                deckSize[idx] += 1;
            }

            long[] buyTable = new long[T];
            long totalSellPrice = 0;
            long totalBuyPrice = 0;

            for (int i = 1; i <= T; i++) { // for each type
                int buyPrice = fio.nextInt();
                int sellPrice = fio.nextInt();
                int numInHand = deckSize[i];

                long actualBuyPrice = (2 - numInHand) * buyPrice + (numInHand * sellPrice);
                buyTable[i - 1] = actualBuyPrice;
                totalSellPrice += numInHand * sellPrice;
            }

            Arrays.sort(buyTable);

            for (int i = 0; i < K; i++) {
                totalBuyPrice += buyTable[i];
            }
            System.out.println(totalSellPrice - totalBuyPrice);

            fio.close();
        } catch (Exception e) {

        }
    }

}
