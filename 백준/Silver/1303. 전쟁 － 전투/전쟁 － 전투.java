import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][];
        for (int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        int ans = 0;
        v = new boolean[N][M];

        int ans1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (v[i][j] || map[i][j] == 'B')
                    continue;
                int sq = bfs(i, j, map[i][j]);
                ans1 += sq * sq;
            }
        }

        int ans2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (v[i][j] || map[i][j] == 'W')
                    continue;
                int sq = bfs(i, j, map[i][j]);
                ans2 += sq * sq;
            }
        }
        System.out.println(ans1 + " " + ans2);
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[][] v;
    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int bfs(int x, int y, char c) {
        int count = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        v[x][y] = true;

        while (!q.isEmpty()) {
            Node n = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = n.x + dxy[d][0];
                int ny = n.y + dxy[d][1];
                if (!mapChk(nx, ny) || map[nx][ny] != c)
                    continue;
                v[nx][ny] = true;
                count++;
                q.add(new Node(nx, ny));
            }
        }
        return count;
    }

    static boolean mapChk(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M || v[x][y])
            return false;
        return true;

    }
}