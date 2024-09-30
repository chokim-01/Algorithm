import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int[][][] dxy = {{{0, -1, 1}, {0, 0, 2}, {1, 0, 1}}, {{-1, 0, 1}, {0, 0, 2}, {0, -1, 1}}, {{-1, 0, 1}, {0, 0, 2}, {0, 1, 1}}, {{0, 0, 2}, {0, 1, 1}, {1, 0, 1}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);
        int ans = solve(0, 0, 0);

        System.out.println(ans);
    }

    static int solve(int x, int y, int c) {
        if (x == N && y == 0)
            return c;

        int ret = c;
        for (int d = 0; d < 4; d++) {
            boolean flag = true;

            for (int i = 0; i < 3; i++) {
                int nx = x + dxy[d][i][0];
                int ny = y + dxy[d][i][1];
                if (!mapChk(nx, ny) || v[nx][ny]) {
                    flag = false;
                    break;
                }
            }

            if (!flag)
                continue;
            int nc = 0;
            for (int i = 0; i < 3; i++) {
                int nx = x + dxy[d][i][0];
                int ny = y + dxy[d][i][1];
                nc += map[nx][ny] * dxy[d][i][2];
                v[nx][ny] = true;
            }

            ret = Math.max(ret, solve(y + 1 == M ? x + 1 : x, y + 1 == M ? 0 : y + 1, c + nc));

            for (int i = 0; i < 3; i++) {
                int nx = x + dxy[d][i][0];
                int ny = y + dxy[d][i][1];
                v[nx][ny] = false;
            }
        }

        ret = Math.max(ret, solve(y + 1 == M ? x + 1 : x, y + 1 == M ? 0 : y + 1, c));

        return ret;
    }

    static boolean mapChk(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M)
            return false;
        return true;
    }

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        v = new boolean[N][M];
        map = new int[N][];
        for (int i = 0; i < N; i++)
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}