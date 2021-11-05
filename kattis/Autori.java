import java.util.stream.Collectors;
import java.util.*;

public class Autori {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String title = sc.next();
        sc.close();

        System.out.println(
            Arrays.stream(title.split("-")).map(str -> Character.toString(str.charAt(0))).collect(Collectors.joining())
        );
    }

}
