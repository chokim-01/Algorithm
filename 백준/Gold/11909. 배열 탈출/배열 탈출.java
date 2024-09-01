import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[][] map = new int[N + 1][N + 1];
        int[][] save = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            map[i] = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <= N; i++)
            map[i][0] = map[0][i] = save[i][0] = save[0][i] = 1000000;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 && j == 1)
                    continue;

                int left = map[i][j] - map[i][j - 1];
                int up = map[i][j] - map[i - 1][j];

                if (left < 0)
                    left = save[i][j - 1];
                else
                    left += save[i][j - 1] + 1;

                if (up < 0)
                    up = save[i - 1][j];
                else
                    up += save[i - 1][j] + 1;


                save[i][j] = Math.min(left, up);
            }
        }

        System.out.println(save[N][N]);
    }
}
