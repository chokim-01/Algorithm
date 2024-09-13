import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
    static int N, M;
    static int[][] map, save;
    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] v;
    static int ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input(br);

        System.out.println(dfs(0, 0, 0) ? -1 : ans + 1);
    }

    static boolean dfs(int x, int y, int count) {
        ans = ans < count ? count : ans;

        for (int d = 0; d < 4; d++) {
            int nx = x + dxy[d][0] * map[x][y];
            int ny = y + dxy[d][1] * map[x][y];

            if (!mapChk.apply(nx, ny) || map[nx][ny] == -1 || count+1 <= save[nx][ny])
                continue;
            if (v[nx][ny])
                return true;

            v[nx][ny] = true;
            save[nx][ny] = count + 1;
            if (dfs(nx, ny, count + 1))
                return true;
            v[nx][ny] = false;

        }
        return false;
    }

    static BiFunction<Integer, Integer, Boolean> mapChk = (x, y) -> 0 <= x && x < N && 0 <= y && y < M;

    static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        save = new int[N][M];
        for (int i = 0; i < N; i++)
            map[i] = new String(br.readLine().toCharArray()).chars().map(c -> c == 'H' ? -1 : c-'0').toArray();

        v = new boolean[N][M];

        ans = 0;
    }
}
