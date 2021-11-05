import java.util.Arrays;

/**
 * @author Wong Chee Hong (A0217558W)
 * Updated to perform sorting on created array instead of copying new arrays, hopefully faster.
 */
public class BestRelayTeam {

    public static void main(String[] args) {
        FastIO fio = new FastIO();

        int n = fio.nextInt();
        Athelete[] arr = new Athelete[n];

        //list of atheletes
        for (int i = 0; i < n; i++) {
            Athelete athelete = new Athelete(fio.next(), fio.nextDouble(), fio.nextDouble());
            arr[i] = athelete;
        }

        Arrays.sort(arr, (x, y) -> x.subsequentLegs > y.subsequentLegs ? 1 : -1);

        //find slowest of the 4 among the best 4 timings
        Athelete slowestOfThe4 = arr[3];
        Arrays.sort(arr, 0, 4, (x, y) -> x.flyingStart > y.flyingStart ? 1 : -1);

        if (n != 4) { // i.e. we need to check if the fastest flying start gives the best time savings
            Athelete bestFlyingStart = arr[0];
            double timeSavings = slowestOfThe4.flyingStart - bestFlyingStart.flyingStart;

            Arrays.sort(arr, 4, n, (x, y) -> x.firstLeg > y.firstLeg ? 1 : -1);
            Athelete fastestRemaining = arr[4];

            if (slowestOfThe4.firstLeg - timeSavings > fastestRemaining.firstLeg) {
                Arrays.sort(arr, 0, 4, (x, y) -> x.subsequentLegs > y.subsequentLegs ? 1 : -1);
                arr[3] = fastestRemaining;
                Arrays.sort(arr, 0, 4, (x, y) -> x.flyingStart > y.flyingStart ? 1 : -1);
            }
        }

        double totalTime = arr[0].firstLeg + arr[1].subsequentLegs + arr[2].subsequentLegs + arr[3].subsequentLegs;

        System.out.println(totalTime);
        for (int i = 0; i < 4; i++) {
            System.out.println(arr[i].name);
        }

        fio.close();
    }
}

class Athelete {

    public String name;
    public double firstLeg, subsequentLegs;
    public double flyingStart;

    public Athelete(String name, double firstLeg, double subsequentLegs) {
        this.name = name;
        this.firstLeg = firstLeg;
        this.subsequentLegs = subsequentLegs;
        this.flyingStart = firstLeg - subsequentLegs;
    }

}
