import java.util.Scanner;

public class NumberFun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCases = sc.nextInt();

        for (int i = testCases; i > 0; i--) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            System.out.println(
                a + b == c ? "Possible"
                : Math.abs(a - b) == c ? "Possible"
                : a * b == c ? "Possible"
                : c * a == b ? "Possible"
                : c * b == a ? "Possible"
                : "Impossible"
            );
        }

        sc.close();
    }
}
