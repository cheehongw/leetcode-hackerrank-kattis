/**
 * @author Wong Chee Hong (A0217558W)
 */
public class Islands {

    static char[][] image_map;
    static boolean[][] taken;
    static int[][] MOVES = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int rows;
    static int cols;

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        rows = fio.nextInt();
        cols = fio.nextInt();
        image_map = new char[rows][cols];
        taken = new boolean[rows][cols];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            image_map[i] = fio.next().toCharArray();
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //System.out.print(image_map[i][j]);
                if (!taken[i][j] && image_map[i][j] == 'L') {
                    count++;
                    DFS(i,j);
                }
            }
            //System.out.println("");
        }

        System.out.println(count);

        fio.close();
    }

    public static void DFS(int i, int j) {
        taken[i][j] = true;

        for (int x = 0; x < 4; x++) {
            int nextRow = i + MOVES[x][0];
            int nextCol = j + MOVES[x][1];

            if (nextCol >= 0 && nextCol < cols && nextRow >= 0 && nextRow < rows && !taken[nextRow][nextCol]) {
                if (image_map[nextRow][nextCol] != 'W') {
                    DFS(nextRow, nextCol);
                }
            }
        }
    }
}
