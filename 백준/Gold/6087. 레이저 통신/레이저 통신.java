import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
    static int N, M;
    static char[][] map;

    static class Node implements Comparable<Node> {
        int x, y, d, cost;

        Node(int x, int y, int d, int c) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }


    static int[][] dxy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] se = input(br);

        System.out.println(solve(se));

    }

    static int solve(int[] se) {
        int[][][] v = new int[N][M][4];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                Arrays.fill(v[i][j], Integer.MAX_VALUE);

        PriorityQueue<Node> q = new PriorityQueue<>();

        for (int i = 0; i < 4; i++) {
            v[se[0]][se[1]][i] = 0;
            q.add(new Node(se[0], se[1], i, 0));
        }

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int d = -1; d <= 1; d++) {
                int nd = (now.d + 4 + d) % 4;
                int nx = now.x + dxy[nd][0];
                int ny = now.y + dxy[nd][1];
                int nc = now.cost + (d == 0 ? 0 : 1);

                if (!mapChk.apply(nx, ny) || map[nx][ny] == '*' || nc >= v[nx][ny][nd])
                    continue;
                v[nx][ny][nd] = nc;

                q.add(new Node(nx, ny, nd, nc));
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++)
            min = Math.min(min, v[se[2]][se[3]][i]);

        return min;
    }

    static BiFunction<Integer, Integer, Boolean> mapChk = (x, y) -> 0 <= x && x < N && 0 <= y && y < M;

    static int[] input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[] ret = new int[4];
        int c = 0;
        
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'C') {
                    ret[c++] = i;
                    ret[c++] = j;
                }
            }
        }

        return ret;
    }
}
