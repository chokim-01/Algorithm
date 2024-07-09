import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
    static int N, M, T;
    static int[][] map;
    static boolean[][] v;
    static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);

        int ans = bfs();
        System.out.println(ans > T ? "Fail" : ans);
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int bfs() {
        v = new boolean[N][M];

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        v[0][0] = true;

        int min = 1234567891;
        int time = 0;
        while (!q.isEmpty() && time < T) {
            int size = q.size();
            time++;
            while (size-- > 0) {
                Node now = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dxy[d][0];
                    int ny = now.y + dxy[d][1];
                    if (!mapChk(nx, ny) || map[nx][ny] == 1 || v[nx][ny])
                        continue;
                    if (map[nx][ny] == 2)
                        min = time + (N - 1 - nx) + (M - 1 - ny);
                    if (nx == N - 1 && ny == M - 1)
                        return Math.min(time,min);
                    v[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
        return min;
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
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++)
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
