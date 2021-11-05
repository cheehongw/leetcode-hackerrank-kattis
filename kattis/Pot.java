import java.util.Scanner;

public class Pot {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int result = 0;

        for (int i = n; i > 0; i--) {
            int x = sc.nextInt();
            int power = x%10;
            int base = x/10;

            result += Math.pow(base, power);
        }
        sc.close();
        System.out.print(result);
    }
}
