import java.util.Scanner;

public class Tarifa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int total = X; //starts with X

        for (int N = sc.nextInt(); N > 0; N--) {
            int p = sc.nextInt();
            total = total < p ? 0 : total - p;
            total += X; //add for the next month
        }

        System.out.println(total);
        sc.close();

    }

}
