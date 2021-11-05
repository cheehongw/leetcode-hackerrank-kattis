import java.util.Scanner;

public class DetailedDifferences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        for (int i = testCases; i > 0; i--) {
            String first = sc.next();
            String second = sc.next();

            System.out.println(first);
            System.out.println(second);

            for (int j = 0; j < first.length(); j++) {
                if (first.charAt(j) == second.charAt(j)) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println("\n");
            sc.close();
        }
    }
}
