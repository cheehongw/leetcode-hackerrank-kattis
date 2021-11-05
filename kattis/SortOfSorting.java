import java.util.Arrays;

public class SortOfSorting {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int n = fio.nextInt();

        while (n != 0) {
            String[] names = new String[n];
            for (int i = 0; i < n; i++) {
                names[i] = fio.next();
            }

            Arrays.sort(names, (x, y) -> {
                return x.charAt(0) - y.charAt(0) == 0 
                    ? x.charAt(1) - y.charAt(1) 
                    : x.charAt(0) - y.charAt(0);
            });

            for(int i = 0; i < n; i++) {
                System.out.println(names[i]);
            }
            System.out.println();
            n = fio.nextInt();
        }

        fio.close();
    }

}
