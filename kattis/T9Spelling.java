/**
 * @author Wong Chee Hong (A0217558W)
 */
public class T9Spelling {
    
    public static void main(String[] args) {
        FastIO fio = new FastIO();

        //lookup table
        int[] key = {0,2,22,222,3,33,333,4,44,444,5,55,555,6,66,666,7,77,777,7777,8,88,888,9,99,999,9999};

        int N = fio.nextInt();
        for (int i = 1; i <= N; i++) { //for each string
            String input = fio.nextLine();
            int len = input.length();
            int previous = 10; //always modulo 10 if there is a previous number
            StringBuilder output = new StringBuilder();
            
            for (int j = 0; j < len; j++) {
                char curr = input.charAt(j);
                int idx = (curr - 96) < 0 ? 0 : curr - 96;
                int t9Representation = key[idx];
                if (t9Representation%10 == previous) {
                    output.append(" ").append(t9Representation);
                } else {
                    output.append(t9Representation);
                }

                previous = t9Representation%10;
            }

            System.out.printf("Case #%d: %s\n",i,output);

        }

        fio.close();


    }
}
