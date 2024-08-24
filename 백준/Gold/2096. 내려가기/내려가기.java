import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[][] map;

    static int[][] before = {{0, 1}, {0, 1, 2}, {1, 2}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        // min max / save / k
        int[][][] dp = new int[2][2][3];

        for (int i = 1; i <= N; i++) {
            // init
            for (int k = 0; k < 3; k++) {
                dp[0][1][k] = 0;
                dp[1][1][k] = 1000000;
            }

            // calc
            for (int k = 0; k < 3; k++) {
                for (int bef : before[k])
                    dp[0][1][k] = Math.max(dp[0][1][k], dp[0][0][bef] + map[i][k]);

                for (int bef : before[k])
                    dp[1][1][k] = Math.min(dp[1][1][k], dp[1][0][bef] + map[i][k]);
            }

            // init
            for (int k = 0; k < 3; k++) {
                dp[0][0][k] = dp[0][1][k];
                dp[1][0][k] = dp[1][1][k];
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++)
            max = Math.max(max, dp[0][1][k]);
        for (int k = 0; k < 3; k++)
            min = Math.min(min, dp[1][1][k]);

        System.out.println(max + " " + min);
    }

    static void input(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][3];

        for (int i = 1; i <= N; i++)
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
