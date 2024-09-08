import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N, M, A, B;
    static int[][] map, dp;

    static int[][] se;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        map = new int[N + 1][M + 1];

        Arrays.fill(map[0], -1);
        for (int i = 0; i <= N; i++)
            map[i][0] = -1;

        while (A-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
        }
        while (B-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 2;
        }

        se = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[j][i] == 1) {
                    if (se[i][0] == 0)
                        se[i][0] = se[i][1] = j;
                    else
                        se[i][1] = Math.max(se[i][1], j);
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{1, 1, 1});
        for (int i = 1; i <= M; i++)
            if (se[i][0] != 0) {
                q.add(new int[]{i, se[i][0]});
                if (se[i][1] != se[i][0])
                    q.add(new int[]{i, se[i][1]});
            }


        q.add(new int[]{M, N});

        dp[1][1] = 1;
        int[] bef = q.poll();
        while (!q.isEmpty()) {
            int[] now = q.poll();
            go(bef[1], bef[0], now[1], now[0]);
            bef = now;
        }

        System.out.println(dp[N][M]);
    }

    static void go(int a, int b, int c, int d) {
        for (int i = a; i <= c; i++) {
            for (int j = b; j <= d; j++) {
                if (i == 1 && j == 1)
                    continue;
                if (map[i][j] == 2) {
                    dp[i][j] = -1;
                    continue;
                }
                dp[i][j] = (dp[i][j - 1] == -1 ? 0 : dp[i][j - 1]) + (dp[i - 1][j] == -1 ? 0 : dp[i - 1][j]);
            }
        }
    }
}
