import java.util.Scanner;

class TakeTwoStones {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        System.out.println(getWinner(N));
    }

    private static String getWinner(int N) {
        if (N%2 == 0) {
            return "Bob";
        } else {
            return "Alice";
        }
    }

}