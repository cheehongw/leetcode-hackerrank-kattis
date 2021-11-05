import java.util.Scanner;

public class LastFactorialDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        for (int i = testCases; i > 0; i--) {
            int factorial = sc.nextInt();

            System.out.println(
                factorial == 1 ? 1 
                : factorial == 2 ? 2
                : factorial == 3 ? 6
                : factorial == 4 ? 4
                : 0);
        }

        sc.close();
    }
}
